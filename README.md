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

```
  ╔══════════════════════════════════════════════════╗
  ║       CONSOLE-BASED TASK MANAGEMENT SYSTEM       ║
  ║          Internship Project — Java OOP            ║
  ╚══════════════════════════════════════════════════╝
  Sample data loaded. You're ready to go!

  ┌─────────────────────────────────┐
  │           MAIN MENU             │
  ├─────────────────────────────────┤
  │  1.  Add New Task               │
  │  2.  View All Tasks             │
  │  3.  Update Task                │
  │  4.  Delete Task                │
  │  5.  Exit                       │
  └─────────────────────────────────┘
Enter your choice: 2

  ══════════════════════════════════════════
   ALL TASKS
  ══════════════════════════════════════════
  ID     TITLE                   DESCRIPTION                     STATUS
  ──────────────────────────────────────────────────────────────────────────────
  1      Design Database Schema  Create ERD and define all tab..  [✓] Completed
  2      Build REST API          Implement CRUD endpoints using..  [~] In Progress
  3      Write Unit Tests        Cover all service-layer method..  [ ] Pending
  4      Deploy to AWS           Set up EC2 instance and deploy..  [ ] Pending
  ──────────────────────────────────────────────────────────────────────────────
  Total Tasks: 4
```

---

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

**[Your Full Name]**
B.Tech CSE — [Year]
[Your College Name]
GitHub: [@yourusername](https://github.com/yourusername)

---

## 📄 License

This project is open-source and free to use for learning and internship submission purposes.
