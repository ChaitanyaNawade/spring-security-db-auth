# 🔐 Spring Security — Database Authentication

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-brightgreen?style=for-the-badge&logo=springsecurity)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=for-the-badge&logo=apachemaven)

---

## 📌 About This Project

This project demonstrates a complete implementation of **Spring Security** with **Database Authentication** using **MySQL** and **Role-Based Access Control (RBAC)**.

It covers the full authentication and authorization flow — from user registration to role-based redirection after login.

---

## ✨ Features

- ✅ **Database Authentication** — Users stored and verified from MySQL DB
- ✅ **BCrypt Password Encoding** — No plain text passwords stored
- ✅ **Role-Based Access Control** — ADMIN and USER roles with separate access
- ✅ **Custom Login Page** — Thymeleaf based login form
- ✅ **Custom Success Handler** — Redirects based on role after login
- ✅ **Exception Handling** — 403 Access Denied page
- ✅ **User Registration API** — REST endpoint to register new users
- ✅ **Logout** — Secure logout with redirect

---

## 🏗️ Project Structure

```
SecurityImpl03/
├── src/main/java/com/chaitanya/SecurityImpl03/
│   ├── config/
│   │   └── SecurityConfig.java          # Security rules & filter chain
│   ├── controller/
│   │   ├── HomeController.java          # Page controllers
│   │   └── RegistrationController.java  # User registration REST API
│   ├── entity/
│   │   └── MyUser.java                  # User entity (DB table)
│   ├── Handler/
│   │   └── SuccessHandler.java          # Role-based redirect after login
│   ├── repositories/
│   │   └── UserRepository.java          # JPA Repository
│   └── service/
│       └── MyUserDetailsService.java    # Loads user from DB
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html                   # Custom login page
│   │   ├── home.html                    # Public home page
│   │   ├── admin-home.html              # Admin dashboard
│   │   ├── user-home.html              # User dashboard
│   │   ├── 403.html                    # Access denied page
│   │   └── error/404.html              # Not found page
│   └── application.properties.example  # DB config template
└── pom.xml
```

---

## 🔄 Authentication Flow

```
User submits login form
        │
        ▼
UsernamePasswordAuthenticationFilter
        │
        ▼
DaoAuthenticationProvider
        │
        ▼
MyUserDetailsService.loadUserByUsername()
        │
        ▼
MySQL DB — User found?
        │
   ┌────┴────┐
  YES        NO
   │          │
   ▼          ▼
BCrypt     UsernameNotFoundException
matches?       │
   │           ▼
   ▼        401 Error
Authenticated ✅
   │
   ▼
SecurityContextHolder stores user
   │
   ▼
SuccessHandler checks ROLE
   │
┌──┴──┐
ADMIN  USER
  │      │
  ▼      ▼
/admin /user
/home  /home
```

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Programming Language |
| Spring Boot | 3.x | Application Framework |
| Spring Security | 6.x | Authentication & Authorization |
| Spring Data JPA | 3.x | Database ORM |
| MySQL | 8.0 | Database |
| Thymeleaf | 3.x | HTML Templating |
| Lombok | Latest | Reduce Boilerplate |
| BCrypt | Built-in | Password Encoding |
| Maven | 3.x | Build Tool |

---

## 📡 API Endpoints

| Method | URL | Access | Description |
|--------|-----|--------|-------------|
| GET | `/home` | Public | Home page |
| GET | `/login` | Public | Login page |
| POST | `/login` | Public | Login form submit |
| POST | `/register/user` | Public | Register new user |
| GET | `/admin/home` | ADMIN only | Admin dashboard |
| GET | `/user/home` | USER only | User dashboard |
| GET | `/403` | Public | Access denied page |
| POST | `/logout` | Authenticated | Logout |

---

## ⚙️ How to Run

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.x+

### Step 1 — Clone the Repository
```bash
git clone https://github.com/ChaitanyaNawade/spring-security-db-auth.git
cd spring-security-db-auth
```

### Step 2 — Create MySQL Database
```sql
CREATE DATABASE security;
```

### Step 3 — Configure application.properties
Copy the example file and update credentials:
```bash
cp SecurityImpl03/src/main/resources/application.properties.example \
   SecurityImpl03/src/main/resources/application.properties
```

Update with your DB credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/security
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Step 4 — Run the Application
```bash
cd SecurityImpl03
mvn spring-boot:run
```

### Step 5 — Register a User
```bash
POST http://localhost:8080/register/user

# Request Body (JSON):
{
  "username": "chaitanya",
  "password": "1234",
  "role": "ADMIN"
}
```

### Step 6 — Login
```
Go to → http://localhost:8080/login
Username: chaitanya
Password: 1234
```

---

## 🔑 Key Components Explained

### SecurityConfig.java
Defines the security rules — which URLs are public, which require ADMIN/USER role, and configures form login with custom success handler.

### MyUserDetailsService.java
Implements `UserDetailsService` — Spring Security calls `loadUserByUsername()` automatically during login to fetch user from MySQL DB.

### SuccessHandler.java
Custom authentication success handler — checks user's role after login and redirects ADMIN to `/admin/home` and USER to `/user/home`.

### BCryptPasswordEncoder
Password is never stored as plain text. BCrypt encodes password during registration and verifies during login using `matches()`.

---

## 💡 Learnings from This Project

- Difference between **Authentication** (who are you?) and **Authorization** (what can you do?)
- How **Filter Chain** intercepts every HTTP request before reaching the Controller
- How **DaoAuthenticationProvider** connects Spring Security to a custom database
- Why **BCrypt** uses salt — same password gives different hash every time
- How **SecurityContextHolder** stores authenticated user per thread

---

## 🚀 What's Next

- [ ] JWT Token Authentication (Stateless)
- [ ] OAuth2 Login (Google/GitHub)
- [ ] Refresh Token Implementation
- [ ] Method Level Security (`@PreAuthorize`)

---

## 👨‍💻 Author

**Chaitanya Nawade**  
Java Backend Developer | Spring Boot Enthusiast  
[GitHub](https://github.com/ChaitanyaNawade) • [LinkedIn](https://www.linkedin.com/in/your-linkedin)

---

⭐ **If this project helped you, give it a star!**

