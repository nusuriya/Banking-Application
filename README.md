🏦 Banking Application – Spring Boot & JWT Authentication

📌 Overview
A secure Spring Boot–based banking backend implementing JWT authentication, idempotent fund transfers, and ledger‑style debit/credit transactions. The system enforces account state and balance validations, ensures transactional consistency with JPA/MySQL, and publishes asynchronous transaction events using Apache Kafka for decoupled auditing and future extensibility.


## 🚀 Features

- User registration and login  
- JWT-based authentication (stateless security)  
- Secure REST APIs using Spring Security  
- Role-based authorization (ROLE_USER, expandable to ROLE_ADMIN)  
- Account management functionality  
- DTO-based request/response handling  
- Input validation using Jakarta Validation (`@NotNull`, `@NotBlank`)  
- Global exception handling with meaningful HTTP responses  
- MySQL integration with Spring Data JPA (Hibernate)  
- Clean architecture (Controller → Service → Repository)


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

- **Controller Layer** – Exposes REST APIs and handles HTTP requests
- **Service Layer** – Contains business logic and validations
- **Repository Layer** – Handles database interactions using Spring Data JPA
- **Security Layer** – Manages JWT authentication and authorization

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

