package dao;

import modelo.Asistente;
import modelo.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoDAO {
    private String url = "jdbc:mysql://localhost:3307/gestion_eventos";
    private String user = "root";
    private String password = "1234";

    public void insertarEvento(Evento e) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO eventos (nombre, ubicacion, fecha, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUbicacion());
            ps.setString(3, e.getFecha());
            ps.setDouble(4, e.getPrecio());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    public void actualizarEvento(int id, Evento e) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE eventos SET nombre = ?, ubicacion = ?, fecha = ?, precio = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUbicacion());
            ps.setString(3, e.getFecha());
            ps.setDouble(4, e.getPrecio());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

 
    public void borrarEvento(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM eventos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    
    public Map<Evento, Integer> obtenerEventosConTotalAsistentes() {
        Map<Evento, Integer> resultados = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT e.*, COUNT(i.asistente_id) AS total FROM eventos e " +
                    "LEFT JOIN inscripciones i ON e.id = i.evento_id GROUP BY e.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Evento e = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                );
                resultados.put(e, rs.getInt("total"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultados;
    }

    
    public List<Asistente> obtenerAsistentesDeEvento(int eventoId) {
        List<Asistente> asistentes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT a.* FROM asistentes a " +
                    "JOIN inscripciones i ON a.id = i.asistente_id WHERE i.evento_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventoId);
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

  
    public List<Evento> obtenerEventosMas2Asistentes() {
        List<Evento> eventos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT e.* FROM eventos e " +
                    "JOIN inscripciones i ON e.id = i.evento_id " +
                    "GROUP BY e.id HAVING COUNT(i.asistente_id) > 2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventos.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return eventos;
    }

    
    public List<Evento> obtener3EventosMasIngresos() {
        List<Evento> eventos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT e.* FROM eventos e " +
                    "LEFT JOIN inscripciones i ON e.id = i.evento_id " +
                    "GROUP BY e.id ORDER BY (e.precio * COUNT(i.asistente_id)) DESC LIMIT 3";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventos.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return eventos;
    }

   
    public Evento obtenerEventoMasCaroDeUbicacion(String ubicacion) {
        Evento e = null;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM eventos WHERE ubicacion = ? ORDER BY precio DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ubicacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return e;
    }
}
