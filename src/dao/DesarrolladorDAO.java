package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.List;

public class DesarrolladorDAO {

    private EntityManagerFactory emf;

    public DesarrolladorDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 7a. Inserción de un desarrollador
    public void insertarDesarrollador(Desarrollador desarrollador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(desarrollador);
        em.getTransaction().commit();
        em.close();
    }

    // 7b. Actualización de cualquier campo (salvo ID), según su ID
    public void actualizarDesarrollador(int id, String nombre, int anyosExperiencia, double salario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador desarrollador = em.find(Desarrollador.class, id);
        if (desarrollador != null) {
            desarrollador.setNombre(nombre);
            desarrollador.setAnyosExperiencia(anyosExperiencia);
            desarrollador.setSalario(salario);
        }
        em.getTransaction().commit();
        em.close();
    }

    // 7c. Borrado de un desarrollador, según su ID
    public void borrarDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador desarrollador = em.find(Desarrollador.class, id);
        if (desarrollador != null) {
            em.remove(desarrollador);
        }
        em.getTransaction().commit();
        em.close();
    }

    // 7d. Asignar un Desarrollador a un Proyecto, según sus IDs
    public void asignarDesarrollador(int desarrolladorId, int proyectoId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador desarrollador = em.find(Desarrollador.class, desarrolladorId);
        Proyecto proyecto = em.find(Proyecto.class, proyectoId);
        if (desarrollador != null && proyecto != null) {
            desarrollador.getProyectos().add(proyecto);
            proyecto.getDesarrolladores().add(desarrollador);
        }
        em.getTransaction().commit();
        em.close();
    }

    // 7e. Eliminar la asignación de un Desarrollador en un Proyecto, según sus IDs
    public void eliminarAsignacion(int desarrolladorId, int proyectoId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador desarrollador = em.find(Desarrollador.class, desarrolladorId);
        Proyecto proyecto = em.find(Proyecto.class, proyectoId);
        if (desarrollador != null && proyecto != null) {
            desarrollador.getProyectos().remove(proyecto);
            proyecto.getDesarrolladores().remove(desarrollador);
        }
        em.getTransaction().commit();
        em.close();
    }

    // 7f. Obtener todos los Proyecto de un desarrollador, según su ID
    public List<Proyecto> obtenerProyectosDeDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        Desarrollador desarrollador = em.find(Desarrollador.class, id);
        List<Proyecto> proyectos = desarrollador.getProyectos();
        proyectos.toString();
        em.close();
        return proyectos;
    }

    // 7g. Obtener la media de anyosExperiencia de todos los desarrolladores
    public double obtenerMediaExperiencia() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> query = em.createQuery(
                "select avg(d.anyosExperiencia) from Desarrollador d",
                Double.class);
        double media = query.getSingleResult();
        em.close();
        return media;
    }

    // 7h. Obtener los Desarrollador que no tienen ningún proyecto asignado
    public List<Desarrollador> obtenerDesarrolladoresSinProyecto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Desarrollador> query = em.createQuery(
                "select d from Desarrollador d where d.proyectos is empty",
                Desarrollador.class);
        List<Desarrollador> desarrolladores = query.getResultList();
        em.close();
        return desarrolladores;
    }
}
