package dao;

import modelo.Asistente;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsistenteDAO {
    private String url = "jdbc:mysql://localhost:3307/gestion_eventos";
    private String user = "root";
    private String password = "1234";

   
    public void insertarAsistente(Asistente a) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO asistentes (nombre, email, edad) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getEdad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    
    public void actualizarAsistente(int id, Asistente a) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE asistentes SET nombre = ?, email = ?, edad = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getEdad());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

   
    public void borrarAsistente(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM asistentes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    
    public void inscribirAsistente(int asistenteId, int eventoId, String fechaInscripcion) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO inscripciones (asistente_id, evento_id, fecha_inscripcion) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, asistenteId);
            ps.setInt(2, eventoId);
            ps.setString(3, fechaInscripcion);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    
    public void eliminarInscripcion(int asistenteId, int eventoId) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM inscripciones WHERE asistente_id = ? AND evento_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, asistenteId);
            ps.setInt(2, eventoId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

   
    public Map<Asistente, Double> obtenerAsistentesConGastoTotal() {
        Map<Asistente, Double> resultados = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT a.*, SUM(e.precio) AS gasto_total FROM asistentes a " +
                    "LEFT JOIN inscripciones i ON a.id = i.asistente_id " +
                    "LEFT JOIN eventos e ON i.evento_id = e.id GROUP BY a.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Asistente a = new Asistente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad")
                );
                resultados.put(a, rs.getDouble("gasto_total"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultados;
    }

   
    public double obtenerEdadMedia() {
        double media = 0;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT AVG(edad) AS media FROM asistentes";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                media = rs.getDouble("media");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return media;
    }

   
    public List<Asistente> obtenerAsistentesSinInscripcion() {
        List<Asistente> asistentes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT a.* FROM asistentes a " +
                    "LEFT JOIN inscripciones i ON a.id = i.asistente_id WHERE i.asistente_id IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                asistentes.add(new Asistente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return asistentes;
    }
}
