import dao.AsistenteDAO;
import dao.DesarrolladorDAO;
import dao.EventoDAO;
import dao.ProyectoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Asistente;
import modelo.Desarrollador;
import modelo.Evento;
import modelo.Proyecto;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto3.odb");

        //EventoDAO eventoDAO = new EventoDAO();
        //AsistenteDAO asistenteDAO = new AsistenteDAO();

        //Evento e = new Evento("Feria Tech", "Sevilla", "2026-09-10", 45.0);
        //eventoDAO.insertarEvento(e);
        //e.setPrecio(55.0);
        //eventoDAO.actualizarEvento(11, e);
        //eventoDAO.borrarEvento(11);

        //Asistente a = new Asistente("Pedro Molina", "pedro@example.com", 29);
        //asistenteDAO.insertarAsistente(a);
        //a.setEmail("pedro.nuevo@example.com");
        //asistenteDAO.actualizarAsistente(11, a);
        //asistenteDAO.borrarAsistente(11);
        //asistenteDAO.inscribirAsistente(1, 2, "2026-05-01");
        //asistenteDAO.eliminarInscripcion(1, 2);

        //System.out.println("Eventos con total de asistentes:" + eventoDAO.obtenerEventosConTotalAsistentes());

        //System.out.println("\nAsistentes del evento con ID 1:" + eventoDAO.obtenerAsistentesDeEvento(1));

        //System.out.println("\nEventos con más de 2 asistentes:" + eventoDAO.obtenerEventosMas2Asistentes());

        //System.out.println("\nTop 3 eventos con más ingresos:" + eventoDAO.obtener3EventosMasIngresos());

        //System.out.println("\nEvento más caro en Madrid:" + eventoDAO.obtenerEventoMasCaroDeUbicacion("Madrid"));

        //System.out.println("\nAsistentes con gasto total:" + asistenteDAO.obtenerAsistentesConGastoTotal());

        //System.out.println("\nEdad media de los asistentes:" + asistenteDAO.obtenerEdadMedia());

        //System.out.println("\nAsistentes sin ninguna inscripción:" + asistenteDAO.obtenerAsistentesSinInscripcion());

        ProyectoDAO proyectoDAO = new ProyectoDAO(emf);
        DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(emf);

        proyectoDAO.insertarProyecto(new Proyecto("Sistema ERP", 50000.0, "Java"));
        proyectoDAO.insertarProyecto(new Proyecto("App Delivery", 12000.0, "Kotlin"));
        proyectoDAO.insertarProyecto(new Proyecto("Portal E-commerce", 25000.0, "JavaScript"));
        proyectoDAO.insertarProyecto(new Proyecto("Motor IA Clasificador", 80000.0, "Python"));
        proyectoDAO.insertarProyecto(new Proyecto("Dashboard Cripto", 15000.0, "TypeScript"));
        proyectoDAO.insertarProyecto(new Proyecto("Gestor Hospitalario", 45000.0, "Java"));
        proyectoDAO.insertarProyecto(new Proyecto("Red Social Gaming", 30000.0, "C#"));
        proyectoDAO.insertarProyecto(new Proyecto("App Finanzas Personales", 8000.0, "Swift"));
        proyectoDAO.insertarProyecto(new Proyecto("Sistema Logística", 60000.0, "Go"));
        proyectoDAO.insertarProyecto(new Proyecto("Analítica Big Data", 95000.0, "Python"));

        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Ana García", 5, 35.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Luis Pérez", 2, 20.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Marta Ruiz", 8, 50.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Carlos Soler", 1, 15.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Elena Beltrán", 12, 65.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Jorge Sanz", 4, 30.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Sofía Vega", 6, 40.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Pablo Lara", 3, 25.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Lucía Ortiz", 10, 55.0));
        desarrolladorDAO.insertarDesarrollador(new Desarrollador("Iván Cano", 7, 42.0));

        desarrolladorDAO.asignarDesarrollador(11, 1);
        desarrolladorDAO.asignarDesarrollador(11, 6);
        desarrolladorDAO.asignarDesarrollador(12, 2);
        desarrolladorDAO.asignarDesarrollador(12, 3);
        desarrolladorDAO.asignarDesarrollador(13, 1);
        desarrolladorDAO.asignarDesarrollador(13, 4);
        desarrolladorDAO.asignarDesarrollador(13, 10);
        desarrolladorDAO.asignarDesarrollador(14, 2);
        desarrolladorDAO.asignarDesarrollador(15, 4);
        desarrolladorDAO.asignarDesarrollador(15, 9);
        desarrolladorDAO.asignarDesarrollador(15, 10);
        desarrolladorDAO.asignarDesarrollador(16, 6);
        desarrolladorDAO.asignarDesarrollador(16, 7);
        desarrolladorDAO.asignarDesarrollador(17, 3);
        desarrolladorDAO.asignarDesarrollador(17, 5);
        desarrolladorDAO.asignarDesarrollador(17, 10);
        desarrolladorDAO.asignarDesarrollador(18, 5);
        desarrolladorDAO.asignarDesarrollador(18, 8);
        desarrolladorDAO.asignarDesarrollador(19, 1);
        desarrolladorDAO.asignarDesarrollador(19, 9);
        desarrolladorDAO.asignarDesarrollador(19, 10);
        desarrolladorDAO.asignarDesarrollador(20, 4);
        desarrolladorDAO.asignarDesarrollador(20, 7);
        desarrolladorDAO.asignarDesarrollador(20, 10);
        desarrolladorDAO.asignarDesarrollador(12, 10);
        desarrolladorDAO.asignarDesarrollador(14, 10);
        desarrolladorDAO.asignarDesarrollador(16, 10);
        desarrolladorDAO.asignarDesarrollador(18, 10);
        desarrolladorDAO.asignarDesarrollador(13, 9);
        desarrolladorDAO.asignarDesarrollador(20, 9);

        Proyecto p = new Proyecto("Proyecto final gestiones", 1000.0, "Java");
        proyectoDAO.insertarProyecto(p);
        proyectoDAO.actualizarProyecto(p.getId(), "Proyecto final programación", 2000.0, "Java");
        proyectoDAO.borrarProyecto(p.getId());
        Desarrollador d = new Desarrollador("Alba ", 1, 10.0);
        desarrolladorDAO.insertarDesarrollador(d);
        desarrolladorDAO.actualizarDesarrollador(d.getId(), "Alba Ruiz de la Vega ", 3, 20.0);
        desarrolladorDAO.borrarDesarrollador(d.getId());
        desarrolladorDAO.asignarDesarrollador(11, 2);
        desarrolladorDAO.eliminarAsignacion(11, 2);

        System.out.println("\nNº desarrolladores por proyecto: " + proyectoDAO.obtenerNumDesarrolladoresPorProyecto());
        System.out.println("\nDesarrolladores del proyecto 1: " + proyectoDAO.obtenerDesarrolladoresDeProyecto(1));
        System.out.println("\nProyectos con más de 5 desarrolladores: " + proyectoDAO.obtenerProyectosConMasDe5Desarrolladores());
        System.out.println("\nTop 3 proyectos por presupuesto: " + proyectoDAO.obtenerTop3Presupuesto());
        System.out.println("\nProyecto Python con menor presupuesto: " + proyectoDAO.obtenerProyectoMasBaratoPorLenguaje("Python"));

    
        System.out.println("\nProyectos del desarrollador 11: " + desarrolladorDAO.obtenerProyectosDeDesarrollador(11));
        System.out.println("\nMedia de años de experiencia: " + desarrolladorDAO.obtenerMediaExperiencia());
        System.out.println("\nDesarrolladores sin proyecto: " + desarrolladorDAO.obtenerDesarrolladoresSinProyecto());

        emf.close();
    }
}
