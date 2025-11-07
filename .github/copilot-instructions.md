# Copilot Instructions for AI Agents

## Project Overview
This is a Spring Boot CRUD REST API for managing `Student` entities, using PostgreSQL as the database. The architecture follows standard Spring Boot conventions with clear separation into Controller, Service, Repository, and Configuration layers.

## Key Components & Data Flow
- **Student Entity** (`src/main/java/com/example/demo/student/Student.java`): Annotated with `@Entity`, maps to the `student` table. The `age` field is calculated dynamically and marked `@Transient`.
- **Repository Layer** (`StudentRepository.java`): Extends `JpaRepository<Student, Long>`, provides DB access. Custom queries use `@Query`.
- **Service Layer** (`StudentService.java`): Contains business logic for CRUD operations. Uses dependency injection (`@Autowired`) for the repository.
- **Controller Layer** (`StudentController.java`): Handles HTTP requests at `/api/v1/student`. Maps CRUD operations to REST endpoints using `@GetMapping`, `@PostMapping`, `@DeleteMapping`, and `@PutMapping`.
- **Configuration** (`StudentConfig.java`): Seeds initial data using a `CommandLineRunner` bean.
- **Database Config** (`application.properties`): Connection details for PostgreSQL. Uses Hibernate for schema management (`spring.jpa.hibernate.ddl-auto`).

## Developer Workflows
- **Build & Run**: Use Maven (`mvnw spring-boot:run`) to start the server. The default port is 8080.
- **Database Setup**: Create a PostgreSQL database named `student`. Update credentials in `application.properties`.
- **Testing Endpoints**: Use HTTP clients (e.g., curl, Postman) to interact with endpoints:
  - `GET /api/v1/student` — List all students
  - `POST /api/v1/student` — Add a student (JSON body)
  - `DELETE /api/v1/student/{id}` — Remove a student
  - `PUT /api/v1/student/{id}?name=...&email=...` — Update student
- **Entity Management**: The `Student` entity is auto-mapped to the DB. The `age` field is not persisted, but calculated from `dob`.
- **Transactional Updates**: Updates use `@Transactional` to ensure atomicity.

## Project-Specific Patterns
- **Dependency Injection**: All services and repositories are injected via constructor with `@Autowired`.
- **Custom Queries**: Use `@Query` in repositories for custom DB lookups (e.g., find by email).
- **Initial Data**: Seeded via `StudentConfig` on app startup.
- **Error Handling**: Service methods throw `IllegalStateException` for invalid operations (e.g., duplicate email, missing ID).

## Integration Points
- **PostgreSQL**: Ensure the DB is running and accessible. Connection details in `application.properties`.
- **Spring Data JPA**: Used for ORM and repository abstraction.

## Example File References
- `src/main/java/com/example/demo/student/Student.java` — Entity definition
- `src/main/java/com/example/demo/student/StudentRepository.java` — Repository interface
- `src/main/java/com/example/demo/student/StudentService.java` — Business logic
- `src/main/java/com/example/demo/student/StudentController.java` — REST API endpoints
- `src/main/java/com/example/demo/student/StudentConfig.java` — Initial data seeding
- `src/main/resources/application.properties` — DB config

## Conventions
- All business logic is in the Service layer.
- Controllers are thin and delegate to services.
- Use constructor injection for all dependencies.
- Only persist fields mapped in the entity (except those marked `@Transient`).
- Use RESTful endpoint naming and HTTP verbs.

---

If any section is unclear or missing, please provide feedback for further refinement.