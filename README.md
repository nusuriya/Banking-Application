🏦 Banking Application – Spring Boot & JWT Authentication

📌 Overview
This project is a secure backend banking application built using Spring Boot 3 and Java 17, demonstrating modern backend development practices.
The application exposes RESTful APIs for user authentication and banking operations, secured with JWT-based authentication using Spring Security 6.
The project focuses on real-world concepts such as stateless authentication, layered architecture, data persistence with JPA, and robust exception handling.


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
