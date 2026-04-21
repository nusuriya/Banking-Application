🏦 Banking Application – Spring Boot & JWT Authentication

📌 Overview
A secure Spring Boot–based banking backend implementing JWT‑based authentication, idempotent fund transfers, and ledger‑style debit/credit transactions. The system enforces account state and balance validations, ensures transactional consistency using Spring Data JPA with MySQL, and publishes asynchronous transaction events using Apache Kafka to support decoupled auditing, notifications, and future extensibility.


## 🚀 Features

- User registration and login
- JWT‑based authentication (stateless security)
- Secure REST APIs using Spring Security
- Role‑based authorization (ROLE_USER, expandable to ROLE_ADMIN)
- Account management (create, update, close, balance tracking)
- Idempotent fund transfer processing (exactly‑once execution)
- Ledger‑style debit and credit transaction recording
- Apache Kafka integration for event‑driven transaction publishing
- DTO‑based request/response handling
- Input validation using Jakarta Validation (@NotNull, @NotBlank)
- Centralized global exception handling with meaningful HTTP responses
- MySQL integration using Spring Data JPA (Hibernate)
- Clean layered architecture (Controller → Service → Repository)


## 📡 API Endpoints

### Authentication
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Authenticate user and generate JWT |

### Accounts
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/accounts` | Create a new bank account |
| GET | `/api/accounts/{id}` | Fetch account by ID |
| GET | `/api/accounts` | Fetch all accounts |
| PUT | `/api/accounts/{id}` | Update account details |

### Transactions
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/transactions/transfer` | Transfer funds between accounts |


## 🏗️ Architecture Overview

The application follows a layered architecture:

The application follows a layered and event‑driven architecture:

- Controller Layer
   Exposes REST APIs, handles request validation, and delegates processing.


- Service Layer
   Contains core business logic, including account validation, balance checks, idempotent transaction handling, and ledger persistence.


- Repository Layer
   Handles data persistence using Spring Data JPA with MySQL.


- Security Layer
   Manages user authentication and authorization using JWT and Spring Security.


- Event Layer (Kafka)
   Publishes transaction completion events asynchronously for non‑blocking side effects such as auditing, notifications, and analytics.

This separation improves maintainability, testability, and scalability.


## 🗄️ Sample Database Records

### users
| id | email | role |
|----|------|------|
| 1 | user@test.com | ROLE_USER |

### account
| accountid | email | balance | closed |
|----------|------|---------|--------|
| 101 | user@test.com | 5000.00 | N |

