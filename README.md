# 🚗 Automobile Service Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

> A full-stack web application for managing automobile service operations — built with Java, Spring Boot, MySQL, and vanilla JavaScript.

---

## 📌 About the Project

The **Automobile Service Management System** is a complete full-stack web application designed to streamline vehicle service operations. It handles user management, vehicle tracking, and service workflows through a clean RESTful API backend and a responsive frontend interface.

This project was built to demonstrate real-world full-stack development skills — from database design to API development to responsive UI.

---

## ✨ Features

- 🔐 **User Management** — Register, login, and manage user accounts
- 🚘 **Vehicle Management** — Add, update, and track vehicles by owner
- 🔧 **Service Operations** — Create and manage service requests and history
- 📋 **RESTful API** — Clean, well-structured API endpoints
- 📱 **Responsive UI** — Works seamlessly across desktop and mobile
- 🏗️ **MVC Architecture** — Clean separation of concerns

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java (Core Java) |
| Backend Framework | Spring Boot |
| Frontend | HTML, CSS, JavaScript |
| Database | MySQL |
| Architecture | MVC + OOP Principles |
| Build Tool | Maven |
| Version Control | Git & GitHub |
| IDE | IntelliJ IDEA |

---

## 📁 Project Structure

```
AutomobileApplication/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/AutomobileApplication/
│   │   │       ├── controller/    # REST Controllers
│   │   │       ├── model/         # Entity Classes
│   │   │       ├── repository/    # Database Layer
│   │   │       └── service/       # Business Logic
│   │   └── resources/
│   │       ├── static/            # Frontend (HTML, CSS, JS)
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL
- IntelliJ IDEA (recommended)

### Installation

**1. Clone the repository:**
```bash
git clone https://github.com/gokulakrishnans016/automobile-service-management.git
cd automobile-service-management
```

**2. Set up the database:**
```sql
CREATE DATABASE automobile_db;
```

**3. Configure `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/automobile_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**4. Run the application:**
```bash
mvn spring-boot:run
```

**5. Open in browser:**
```
http://localhost:8080
```

---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| POST | `/api/users` | Create a new user |
| GET | `/api/vehicles` | Get all vehicles |
| POST | `/api/vehicles` | Add a new vehicle |
| GET | `/api/services` | Get all service records |
| POST | `/api/services` | Create a service request |
| PUT | `/api/services/{id}` | Update service status |
| DELETE | `/api/services/{id}` | Delete a service record |

---

## 💡 Key Learnings

- Designed and implemented **RESTful APIs** from scratch using Spring Boot
- Applied **MVC architecture** for clean, maintainable code
- Built a **responsive frontend** integrated with backend APIs
- Managed relational data using **MySQL with JPA/Hibernate**
- Used **Maven** for dependency management and project build

---

## 👨‍💻 Author

**Gokula Krishnan S**
- 📧 Email: [gokulakrishnan634@gmail.com](mailto:gokulakrishnan634@gmail.com)
- 💼 LinkedIn: [linkedin.com/in/gokula-krishnan-s-238b15292](https://www.linkedin.com/in/gokula-krishnan-s-238b15292)
- 🐙 GitHub: [github.com/gokulakrishnans016](https://github.com/gokulakrishnans016)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">
  ⭐ If you found this project useful, give it a star!
</div>
