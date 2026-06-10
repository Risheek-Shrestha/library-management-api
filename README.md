# 📚 Library Management REST API

A RESTful API for managing a library's book inventory, built with Spring Boot 4, Spring Security (JWT), Spring Data JPA, and PostgreSQL. Includes Docker support for easy deployment.

## 🛠️ Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Security + JWT (jjwt 0.11.5)
- Spring Data JPA + Hibernate
- PostgreSQL
- Lombok
- Maven
- Docker & Docker Compose

## 🚀 Features

- Full CRUD operations for books
- Pagination support for large datasets
- JWT-based authentication and stateless session management
- Role-based access control (`ROLE_USER` / `ROLE_ADMIN`)
- User registration and login endpoints
- Global exception handling with clean JSON error responses
- Bean Validation on incoming requests
- Proper HTTP status codes (200, 201, 204, 400, 404, 500)
- 3-layer architecture: Controller → Service → Repository
- Docker + Docker Compose support

## 📁 Project Structure

```
src/main/java/com.risheek.libraryapi/
├── LibraryapiApplication.java        # Main entry point — starts the app
├── Book.java                         # Book entity
├── BookNotFoundException.java        # Custom exception
├── ErrorResponse.java                # Error response model
├── GlobalExceptionHandler.java       # Handles all exceptions globally
├── LibraryController.java            # Book REST endpoints
├── LibraryManagementRepository.java  # Book database layer
├── LibraryService.java               # Book business logic
├── AuthController.java               # Register & login endpoints
├── User.java                         # User entity
├── UserRepository.java               # User database layer
├── CustomUserDetailService.java      # Spring Security UserDetailsService
├── SecurityConfig.java               # Security filter chain & role config
├── JwtUtil.java                      # JWT token generation & validation
└── JwtAuthFilter.java                # JWT request filter
```

## ⚙️ Setup & Run Locally

### Prerequisites

- Java 21+
- Maven
- PostgreSQL running locally

### Steps

1. Clone the repository
```bash
git clone https://github.com/Risheek-Shrestha/library-management-api.git
cd library-management-api
```

2. Create PostgreSQL database
```sql
CREATE DATABASE library_db;
```

3. Copy the example properties and fill in your values
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

4. Update `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your-256-bit-secret-key-here
jwt.expiration=86400000
```

5. Run the application
```bash
./mvnw spring-boot:run
```

API will start at `http://localhost:8080`

---

## 🐳 Run with Docker Compose

1. Copy the example env file and fill in your values
```bash
cp .env.example .env
```

2. Edit `.env`
```env
DB_NAME=library_db
DB_USER=your_db_user
DB_PASSWORD=your_db_password
JWT_SECRET=your-256-bit-secret-key-here
JWT_EXPIRATION=86400000
```

3. Start services
```bash
docker compose up --build
```

API will be available at `http://localhost:8080`. PostgreSQL is exposed on port `5433`.

---

## 🔐 Authentication

All write operations (POST, PUT, DELETE) require authentication with a JWT token issued to a user with `ROLE_ADMIN`. GET endpoints are publicly accessible without a token.

### Register a User

**POST** `/api/auth/register`
```json
{
  "username": "admin",
  "password": "password123"
}
```
> Note: newly registered users receive `ROLE_USER` by default. Promote to `ROLE_ADMIN` directly in the database to enable write access.

### Login

**POST** `/api/auth/login`
```json
{
  "username": "admin",
  "password": "password123"
}
```

**Response:** a JWT token string. Include it in subsequent requests:
```
Authorization: Bearer <token>
```

---

## 📌 API Endpoints

| Method | Endpoint | Description | Auth Required | Status Code |
|--------|----------|-------------|---------------|-------------|
| POST | `/api/auth/register` | Register new user | No | 201 |
| POST | `/api/auth/login` | Login and get JWT | No | 200 |
| GET | `/api/v1/library-management?page=0&size=5` | Get paginated books | No | 200 |
| GET | `/api/v1/library-management/{id}` | Get book by ID | No | 200 |
| POST | `/api/v1/library-management` | Add new book | Yes (ADMIN) | 201 |
| PUT | `/api/v1/library-management/{id}` | Update book | Yes (ADMIN) | 200 |
| DELETE | `/api/v1/library-management/{id}` | Delete book | Yes (ADMIN) | 204 |

---

## 📝 Sample Requests

### Add a Book (requires ADMIN token)

**POST** `/api/v1/library-management`
```json
{
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "price": 599.99,
    "available": true
}
```

**Success — 201 Created:**
```json
{
    "id": 1,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "price": 599.99,
    "available": true
}
```

### Error Responses

**404 Not Found:**
```json
{
    "status": 404,
    "message": "Book not found with id: 99",
    "timestamp": "2026-06-10T13:00:00"
}
```

**400 Bad Request (validation failure):**
```json
{
    "status": 400,
    "message": "title: must not be blank",
    "timestamp": "2026-06-10T13:00:00"
}
```

---

## 📸 API in Action

### Get All Books — 200
![Get All Books](screenshots/get-all-books.png)

### Create Book — 201 Created
![Create Book](screenshots/create-book.png)

### Book Not Found — 404
![Not Found](screenshots/not-found.png)

### Validation Error — 400
![Validation](screenshots/validation-error.png)

### Delete Book — 204
![Delete](screenshots/delete-book.png)

---

## 👨‍💻 Author

**Risheek Shrestha**
- GitHub: [@Risheek-Shrestha](https://github.com/Risheek-Shrestha)
- Email: shrestharisheek@gmail.com
