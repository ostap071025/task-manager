# Task Manager API

REST API for task management built with Java and Spring Boot.

## Features

- Create, update and delete tasks
- Create and manage users
- Task filtering and pagination
- Task priorities and statuses
- Validation and global exception handling
- DTO-based architecture
- PostgreSQL integration
- Dockerized database

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Gradle
- Lombok

---

## Architecture

The project follows a layered architecture:

```text
Controller -> Service -> Repository -> Database
```

Additional layers:
- DTOs
- Mappers
- Global Exception Handling

---

## Entities

### User
- id
- name
- email
- password

### Task
- id
- title
- description
- status
- priority
- deadline
- createdAt

Relationship:

```text
One User -> Many Tasks
```

---

## API Examples

### Create Task

```http
POST /api/tasks
```

### Get All Tasks

```http
GET /api/tasks
```

### Update Task

```http
PATCH /api/tasks/{id}
```

### Delete Task

```http
DELETE /api/tasks/{id}
```

---

## Running the Project

### 1. Clone repository

```bash
git clone <repository-url>
```

### 2. Start PostgreSQL with Docker

```bash
docker compose up
```

### 3. Run application

```bash
./gradlew bootRun
```

---

## Database

PostgreSQL is used as the main database.

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task-manager
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## Current Learning Goals

This project is actively used to practice:
- Spring Boot
- REST API design
- JPA/Hibernate
- Relationships between entities
- Pagination and filtering
- Clean backend architecture
- Docker

---

## Future Improvements

- Spring Security + JWT
- Unit and integration tests
- Swagger/OpenAPI documentation
- Role-based authorization
- CI/CD pipeline
