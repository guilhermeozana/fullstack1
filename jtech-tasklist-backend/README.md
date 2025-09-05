![Jtech Logo](http://www.jtech.com.br/wp-content/uploads/2015/06/logo.png)

# jtech-tasklist

## What is

**jtech-tasklist** is a task management API built with **Spring Boot** using the principles of **Hexagonal Architecture** (Ports and Adapters). It provides a clean separation between business logic and external concerns, making the application highly maintainable, testable, and scalable.

---

## Composite by

The project is organized into the following layers:

- **application/core**  
  Contains the domain models (`Tasklist`, `TaskStatus`) and use cases (`TasklistUseCase`) that represent the business logic.

- **ports/input & output**  
  Define interfaces for inbound (controllers, services) and outbound (repositories, gateways) communication.

- **adapters/input**  
  REST controllers and request/response DTOs that expose the application to external clients.

- **adapters/output**  
  Repository implementations and entity mappings that connect the application to the database.

- **config**  
  Application startup configuration and exception handling.

---

## Services

- `TasklistUseCase`  
  Core service responsible for handling task creation, update, deletion, and retrieval.

- `TasklistController`  
  REST controller that exposes endpoints under `/api/v1/tasklists`.

---

## Helper

- **GlobalExceptionHandler**  
  Centralized exception handling using `@RestControllerAdvice`, returning structured `ApiError` responses.

- **EnumValidator**  
  Custom annotation to validate that `status` values match the `TaskStatus` enum.

- **ApiError / ApiValidationError**  
  Standard error response models for consistent API feedback.

---

## How to use

### Create a task

```http
POST /api/v1/tasklists
Content-Type: application/json

{
  "title": "Write unit tests",
  "description": "Cover TasklistRequest with tests",
  "status": "PENDING"
}

### 🔹 Get all tasks
```http
GET /api/v1/tasklists
```

**Response**
```json
[
  {
    "id": "1",
    "title": "Write unit tests",
    "description": "Cover TasklistRequest with tests",
    "status": "PENDING"
  },
  {
    "id": "2",
    "title": "Fix validation",
    "description": "Improve enum validation logic",
    "status": "IN_PROGRESS"
  }
]
```

---

### 🔹 Get a task by ID
```http
GET /api/v1/tasklists/{id}
```

**Example**
```http
GET /api/v1/tasklists/1
```

**Response**
```json
{
  "id": "1",
  "title": "Write unit tests",
  "description": "Cover TasklistRequest with tests",
  "status": "PENDING"
}
```

---

### 🔹 Update a task
```http
PUT /api/v1/tasklists/{id}
Content-Type: application/json
```

**Request Body**
```json
{
  "title": "Write integration tests",
  "description": "Cover controller layer",
  "status": "IN_PROGRESS"
}
```

---

### 🔹 Delete a task
```http
DELETE /api/v1/tasklists/{id}
```

**Example**
```http
DELETE /api/v1/tasklists/1
```

**Response**
```http
204 No Content
```

---

### 🔹 Validation error example
```json
{
  "status": 400,
  "message": "Validation failed",
  "path": "/api/v1/tasklists",
  "timestamp": "2025-09-04T23:31:00",
  "debugMessage": "Validation failed",
  "subErrors": [
    {
      "object": "TasklistRequest",
      "field": "title",
      "rejectedValue": "",
      "message": "Title is required."
    }
  ]
}
```

---

## 🚀 How to run

### Backend
```bash
git clone https://github.com/jtech-solutions/jtech-tasklist.git
cd jtech-tasklist
./mvnw spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080/api/v1/tasklists
```

---

## 📌 Points to improve
- [ ] Add Swagger/OpenAPI documentation  
- [ ] Implement pagination and filtering  
- [ ] Add authentication and authorization (JWT)  
- [ ] Improve test coverage (unit + integration)  
- [ ] Add support for task deadlines and priorities  
- [ ] Internationalize error messages  
- [ ] Add sorting and search capabilities  

---

