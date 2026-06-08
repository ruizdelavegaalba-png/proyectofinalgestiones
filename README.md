# Proyectos de Programación — DAW

Repositorio con los proyectos de la asignatura **Programación** del Grado Superior en Desarrollo de Aplicaciones Web (DAW), correspondientes a los temas 6 y 7.

---

## Estructura del repositorio

```
src/
├── dao/
│   ├── AsistenteDAO.java
│   ├── DesarrolladorDAO.java
│   ├── EventoDAO.java
│   └── ProyectoDAO.java
├── modelo/
│   ├── Asistente.java
│   ├── Desarrollador.java
│   ├── Evento.java
│   └── Proyecto.java
└── Main.java
```

---

## Tema 6 — Acceso a bases de datos relacionales con JDBC

### Descripción

Proyecto de acceso a base de datos **MySQL** usando el conector **JDBC** (`mysql-connector-j-9.6.0.jar`).

Modela un sistema de **gestión de eventos**, con dos entidades principales y su relación a través de una tabla de inscripciones.

### Entidades

| Clase | Descripción |
|-------|-------------|
| `Evento` | Representa un evento con nombre, fecha, precio y aforo |
| `Asistente` | Representa una persona inscrita con nombre, email y edad |

### Patrón utilizado

- **DAO (Data Access Object)**: cada entidad tiene su propia clase DAO con métodos CRUD y consultas específicas.
- Conexión mediante `DriverManager` con `PreparedStatement` y `ResultSet`.
- Uso de `try-with-resources` para gestión segura de recursos.

### Base de datos

```
URL: jdbc:mysql://localhost:3307/gestion_eventos
Usuario: root
Contraseña: root
```

Tablas: `eventos`, `asistentes`, `inscripciones`

### Librería necesaria

```
mysql-connector-j-9.6.0.jar
```

---

## Tema 7 — Persistencia de objetos con JPA y ObjectDB

### Descripción

Proyecto de **base de datos orientada a objetos** usando **JPA** con la implementación **ObjectDB** (`objectdb-jk-2.9.5.jar` + `jakarta.persistence-api-3.2.0.jar`).

Modela un sistema de **gestión de proyectos y desarrolladores**, con una relación muchos a muchos entre ambas entidades.

### Entidades

| Clase | Descripción |
|-------|-------------|
| `Proyecto` | Entidad JPA que representa un proyecto software |
| `Desarrollador` | Entidad JPA que representa un desarrollador |

> La relación entre `Proyecto` y `Desarrollador` es `@ManyToMany` bidireccional.

### Patrón utilizado

- **DAO (Data Access Object)**: cada DAO recibe un `EntityManagerFactory` por constructor.
- Cada método crea su propio `EntityManager` local, gestiona la transacción (`begin/commit`) y lo cierra al finalizar.
- Consultas mediante JPQL con `createQuery()`.

### Anotaciones JPA principales

```java
@Entity
@Id
@GeneratedValue
@ManyToMany
```

### Librerías necesarias

```
jakarta.persistence-api-3.2.0.jar
objectdb-jk-2.9.5.jar
```

---

## Configuración del entorno

- **IDE**: IntelliJ IDEA
- **JDK**: Java 17+
- Las librerías `.jar` deben añadirse manualmente como dependencias del proyecto en IntelliJ (`File > Project Structure > Libraries`).

---

## Notas

- El `Main.java` contiene ejemplos de uso de todos los métodos DAO de ambos temas.
