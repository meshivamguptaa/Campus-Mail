# Campus-Mail
An Internal Mail System Designed for campuses or organizations.
# 📧 Campus Mail Client

A desktop-based mail client built using **Java Swing** that allows users to communicate within a closed campus network. The application supports user authentication, message composition, and mailbox management (Inbox, Sent, Draft).

---

## 🚀 Features

* 🔐 User Registration & Login
* 👤 Session Management
* 📥 Inbox – View received messages
* 📤 Sent – View sent messages
* 📝 Draft – Save and manage drafts
* ✉️ Compose Mail with attachment support
* 🔄 Dynamic UI navigation (no multiple windows)
* 🗂️ Clean layered architecture (UI, Service, DAO, Model)

---

## 🏗️ Architecture

The project follows a **layered architecture**:

* **UI Layer** – Handles user interaction (Swing panels)
* **Service Layer** – Contains business logic (e.g., sending messages)
* **DAO Layer** – Handles database operations using JDBC
* **Model Layer** – Defines entities like `User` and `Message`
* **Core Layer** – Manages session state (`SessionManager`)

---

## 🛠️ Tech Stack

* **Java (JDK 8+)**
* **Java Swing (UI)**
* **MySQL (Database)**
* **JDBC (Database Connectivity)**

---

## ⚙️ Setup Instructions

1. Clone the repository:

   ```bash
   git clone <your-repo-link>
   cd campus-mail-client
   ```

2. Configure MySQL database:

   * Create a database (e.g., `campus_mail`)
   * Create required tables (`users`, `messages`)
   * Update DB credentials in `DBConnection.java`

3. Add MySQL Connector JAR:

   * Place `mysql-connector-j-*.jar` inside `lib/`
   * Add it to your classpath

4. Run the application:

   ```bash
   javac -cp ".;lib/mysql-connector-j-*.jar" -d . src/*/*.java
   java -cp ".;lib/mysql-connector-j-*.jar" core.MainApp
   ```

---

## 📂 Project Structure

```
src/
 ├── ui/        → UI panels (Login, Dashboard, Compose, etc.)
 ├── service/   → Business logic (AuthService, MessageService)
 ├── dao/       → Database access (UserDAO, MessageDAO)
 ├── model/     → Entity classes (User, Message)
 ├── core/      → Session management, main entry
```

---

## 🧠 Key Concepts Implemented

* MVC-inspired layered design
* Session handling in desktop apps
* Database normalization using IDs
* Dynamic UI switching using JPanel
* Separation of concerns (UI vs Logic vs Data)

---

## 🔒 Future Improvements

* Password hashing & security enhancements
* Search and filtering functionality
* File storage for attachments
* Real-time updates (refresh mechanism)
* UI upgrade using modern frameworks (JavaFX)

---

## 👨‍💻 Author

Shivam Gupta

---

## 📌 Note

This project is built for academic purposes to demonstrate full-stack desktop application development using Java.

