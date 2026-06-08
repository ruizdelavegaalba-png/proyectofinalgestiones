package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectoDAO {

    private EntityManagerFactory emf;

    public ProyectoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void insertarProyecto(Proyecto proyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(proyecto);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarProyecto(int id, String nombre, double presupuesto, String lenguajePrincipal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto proyecto = em.find(Proyecto.class, id);
        if (proyecto != null) {
            proyecto.setNombre(nombre);
            proyecto.setPresupuesto(presupuesto);
            proyecto.setLenguajePrincipal(lenguajePrincipal);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarProyecto(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto proyecto = em.find(Proyecto.class, id);
        if (proyecto != null) {
            em.remove(proyecto);
        }
        em.getTransaction().commit();
        em.close();
    }


    public Map<String, Long> obtenerNumDesarrolladoresPorProyecto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery(
                "select p.nombre, count(d) from Proyecto p join p.desarrolladores d group by p.nombre",
                Object[].class);
        List<Object[]> resultados = query.getResultList();
        Map<String, Long> res = new HashMap<>();
        for (Object[] fila : resultados) {
            String nombre = (String) fila[0];
            Long count = (Long) fila[1];
            res.put(nombre, count);
        }
        em.close();
        return res;
    }


    public List<Desarrollador> obtenerDesarrolladoresDeProyecto(int id) {
        EntityManager em = emf.createEntityManager();
        Proyecto proyecto = em.find(Proyecto.class, id);
        List<Desarrollador> desarrolladores = proyecto.getDesarrolladores();
        desarrolladores.toString(); // fuerza carga lazy
        em.close();
        return desarrolladores;
    }

    public List<Proyecto> obtenerProyectosConMasDe5Desarrolladores() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Proyecto p join p.desarrolladores d group by p having count(d) > 5",
                Proyecto.class);
        List<Proyecto> proyectos = query.getResultList();
        em.close();
        return proyectos;
    }

 
    public List<Proyecto> obtenerTop3Presupuesto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Proyecto p order by p.presupuesto desc",
                Proyecto.class);
        query.setMaxResults(3);
        List<Proyecto> proyectos = query.getResultList();
        em.close();
        return proyectos;
    }

   
    public Proyecto obtenerProyectoMasBaratoPorLenguaje(String lenguaje) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Proyecto p where p.lenguajePrincipal = :lenguaje order by p.presupuesto asc",
                Proyecto.class);
        query.setParameter("lenguaje", lenguaje);
        query.setMaxResults(1);
        Proyecto proyecto = query.getSingleResult();
        em.close();
        return proyecto;
    }
}
