# 📋 Console-Based Task Management System

> A menu-driven CRUD application built in Java using Object-Oriented Programming concepts.
> Developed as part of a Software Development Internship.

---

## 🧾 Project Overview

This is a **console-based Task Management System** that allows users to manage tasks through a clean, interactive menu. It demonstrates core Java programming concepts including OOP, ArrayList, Scanner, exception handling, and switch-case navigation.

---

## ✨ Features

| Feature | Description |
|---|---|
| ➕ Add Task | Create a new task with title, description, and auto-assigned ID |
| 📋 View Tasks | Display all tasks in a formatted, tabular layout |
| ✏️ Update Task | Modify title, description, or status of any existing task |
| 🗑️ Delete Task | Remove a task by ID with confirmation prompt |
| 🔒 Safe Input | Handles invalid input gracefully — no crashes |
| 🆔 Auto ID | Task IDs are generated automatically |
| 🎯 Status Tracking | Statuses: `Pending`, `In Progress`, `Completed` |

---

## 🗂️ Project Structure

```
TaskManagementSystem/
│
├── src/
│   ├── Task.java       # Task model class (OOP entity)
│   └── Main.java       # Main application — menus, CRUD logic
│
└── README.md           # Project documentation
```

---

## ⚙️ Technical Requirements

- **Language:** Java (JDK 8 or higher)
- **IDE:** IntelliJ IDEA / Eclipse / VS Code (with Java Extension)
- **No external libraries** — pure Java standard library

---

## 🚀 How to Compile and Run

### Option A — Using the Terminal (Recommended)

```bash
# Step 1: Navigate to the src folder
cd TaskManagementSystem/src

# Step 2: Compile both Java files
javac Task.java Main.java

# Step 3: Run the application
java Main
```

### Option B — Using an IDE

1. Open the `TaskManagementSystem/src/` folder in your IDE.
2. Right-click `Main.java` → **Run**.

---

## 🖥️ Sample Output

<img width="455" height="344" alt="{B8A0A26E-3085-4014-9708-56A2CDB3AF9F}" src="https://github.com/user-attachments/assets/caf41a8b-80aa-42be-9be2-e634f3327af3" />


## 🧠 Concepts Demonstrated

- **Object-Oriented Programming** — Encapsulation with Task class
- **ArrayList** — Dynamic in-memory data storage
- **Scanner** — Console input handling
- **Switch-Case** — Menu navigation
- **Loops** — Main application loop with `while`
- **Exception Handling** — `try-catch` for invalid integer input
- **String Manipulation** — Truncation, formatting, comparison
- **Methods** — Clean separation of concerns across helper methods

---

## 👤 Author

**Akshat Sharma**
B.Tech CSE — 3rd Year
Sharda University
GitHub: [@akshatsharma-aks](https://github.com/akshatsharma-aks)

---

## 📄 License

This project is open-source and free to use for learning and internship submission purposes.
