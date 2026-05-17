/**
 * Main.java
 * Entry point and controller for the Console-Based Task Management System.
 *
 * Features:
 *  - Add a new task
 *  - View all tasks in a formatted table
 *  - Update an existing task (title, description, or status)
 *  - Delete a task by ID
 *  - Graceful error handling for invalid input
 *  - Auto-incremented task IDs
 *  - Sample data preloaded for demonstration
 *
 * Author: [Your Name]
 * Project: Console-Based Task Management System
 * Version: 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ─────────────────────────────────────────────
    //  Global State
    // ─────────────────────────────────────────────

    // ArrayList to store all Task objects (acts as our in-memory database)
    static ArrayList<Task> taskList = new ArrayList<>();

    // Auto-incrementing counter for unique task IDs
    static int taskIdCounter = 1;

    // ─────────────────────────────────────────────
    //  Main Method — Application Entry Point
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load sample data so the app isn't empty on first run
        loadSampleData();

        printWelcomeBanner();

        // Main application loop — keeps running until user selects Exit
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    updateTask(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                case 5:
                    running = false;
                    printExitMessage();
                    break;
                default:
                    printError("Invalid option! Please choose between 1 and 5.");
            }
        }

        scanner.close();
    }

    // ─────────────────────────────────────────────
    //  1. ADD TASK
    // ─────────────────────────────────────────────

    /**
     * Prompts the user for task details and adds a new Task to the list.
     * Task ID is auto-generated; status defaults to "Pending".
     *
     * @param scanner Scanner object for reading user input
     */
    static void addTask(Scanner scanner) {
        printSectionHeader("ADD NEW TASK");

        // Collect task details from user
        System.out.print("  Enter Task Title       : ");
        String title = scanner.nextLine().trim();

        System.out.print("  Enter Task Description : ");
        String description = scanner.nextLine().trim();

        // Validate that fields are not empty
        if (title.isEmpty() || description.isEmpty()) {
            printError("Title and Description cannot be empty. Task not added.");
            return;
        }

        // Create new Task with auto-generated ID and default status "Pending"
        Task newTask = new Task(taskIdCounter++, title, description, "Pending");
        taskList.add(newTask);

        printSuccess("Task added successfully! Task ID: " + newTask.getTaskId());
    }

    // ─────────────────────────────────────────────
    //  2. VIEW ALL TASKS
    // ─────────────────────────────────────────────

    /**
     * Displays all tasks in a clean, formatted table.
     * Shows a message if no tasks exist.
     */
    static void viewAllTasks() {
        printSectionHeader("ALL TASKS");

        // Check if there are any tasks to display
        if (taskList.isEmpty()) {
            System.out.println("  No tasks found. Add a task to get started!");
            printDivider();
            return;
        }

        // Print table header
        System.out.printf("  %-5s  %-22s  %-30s  %-15s%n",
                          "ID", "TITLE", "DESCRIPTION", "STATUS");
        System.out.println("  " + "─".repeat(78));

        // Print each task as a table row
        for (Task task : taskList) {
            // Truncate long strings to keep table tidy
            String title       = truncate(task.getTaskTitle(), 22);
            String description = truncate(task.getTaskDescription(), 30);
            String status      = task.getTaskStatus();

            // Color-code status with symbols
            String statusDisplay = formatStatus(status);

            System.out.printf("  %-5d  %-22s  %-30s  %s%n",
                              task.getTaskId(), title, description, statusDisplay);
        }

        System.out.println("  " + "─".repeat(78));
        System.out.println("  Total Tasks: " + taskList.size());
        printDivider();
    }

    // ─────────────────────────────────────────────
    //  3. UPDATE TASK
    // ─────────────────────────────────────────────

    /**
     * Allows updating the title, description, or status of an existing task.
     * Validates that the given task ID exists before proceeding.
     *
     * @param scanner Scanner object for reading user input
     */
    static void updateTask(Scanner scanner) {
        printSectionHeader("UPDATE TASK");

        // First, show existing tasks so user knows which IDs exist
        if (taskList.isEmpty()) {
            System.out.println("  No tasks available to update.");
            printDivider();
            return;
        }

        viewAllTasks();

        int id = getIntInput(scanner, "  Enter Task ID to update: ");

        // Search for the task with the given ID
        Task taskToUpdate = findTaskById(id);

        if (taskToUpdate == null) {
            printError("Task with ID " + id + " not found.");
            return;
        }

        // Show current values before editing
        System.out.println("\n  Current Values:");
        System.out.println("  Title       : " + taskToUpdate.getTaskTitle());
        System.out.println("  Description : " + taskToUpdate.getTaskDescription());
        System.out.println("  Status      : " + taskToUpdate.getTaskStatus());
        System.out.println();

        // Ask what to update — pressing Enter skips a field (keeps old value)
        System.out.print("  New Title (press Enter to keep current): ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("  New Description (press Enter to keep current): ");
        String newDescription = scanner.nextLine().trim();

        // Status must be one of the valid options
        System.out.println("  Status Options: 1. Pending  2. In Progress  3. Completed");
        int statusChoice = getIntInput(scanner, "  Select new status (0 = keep current): ");

        // Apply updates — only if the user entered a new value
        if (!newTitle.isEmpty()) {
            taskToUpdate.setTaskTitle(newTitle);
        }
        if (!newDescription.isEmpty()) {
            taskToUpdate.setTaskDescription(newDescription);
        }

        // Map numeric choice to status string
        switch (statusChoice) {
            case 1: taskToUpdate.setTaskStatus("Pending");     break;
            case 2: taskToUpdate.setTaskStatus("In Progress"); break;
            case 3: taskToUpdate.setTaskStatus("Completed");   break;
            case 0: break; // Keep existing status
            default:
                printError("Invalid status choice. Status not changed.");
        }

        printSuccess("Task ID " + id + " updated successfully!");
    }

    // ─────────────────────────────────────────────
    //  4. DELETE TASK
    // ─────────────────────────────────────────────

    /**
     * Deletes a task from the list by its ID.
     * Prompts for confirmation before deletion.
     *
     * @param scanner Scanner object for reading user input
     */
    static void deleteTask(Scanner scanner) {
        printSectionHeader("DELETE TASK");

        if (taskList.isEmpty()) {
            System.out.println("  No tasks available to delete.");
            printDivider();
            return;
        }

        viewAllTasks();

        int id = getIntInput(scanner, "  Enter Task ID to delete: ");

        Task taskToDelete = findTaskById(id);

        if (taskToDelete == null) {
            printError("Task with ID " + id + " not found.");
            return;
        }

        // Confirm before permanent deletion
        System.out.println("  Task to delete: \"" + taskToDelete.getTaskTitle() + "\"");
        System.out.print("  Are you sure? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            taskList.remove(taskToDelete);
            printSuccess("Task ID " + id + " deleted successfully!");
        } else {
            System.out.println("  ⚠  Deletion cancelled.");
            printDivider();
        }
    }

    // ─────────────────────────────────────────────
    //  HELPER — Find Task By ID
    // ─────────────────────────────────────────────

    /**
     * Searches the taskList for a Task matching the given ID.
     *
     * @param id The task ID to search for
     * @return   The matching Task object, or null if not found
     */
    static Task findTaskById(int id) {
        for (Task task : taskList) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null; // Not found
    }

    // ─────────────────────────────────────────────
    //  HELPER — Safe Integer Input
    // ─────────────────────────────────────────────

    /**
     * Safely reads an integer from the user.
     * Re-prompts if the input is not a valid integer (prevents crashes).
     *
     * @param scanner Scanner to read from
     * @param prompt  Message displayed to the user
     * @return        A valid integer entered by the user
     */
    static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Input was not a number — ask again
                printError("Invalid input. Please enter a number.");
            }
        }
    }

    // ─────────────────────────────────────────────
    //  HELPER — Truncate String for Table Display
    // ─────────────────────────────────────────────

    /**
     * Truncates a string to the specified max length.
     * Appends "..." if the string was truncated.
     *
     * @param text      The string to truncate
     * @param maxLength Maximum allowed length
     * @return          Possibly truncated string
     */
    static String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }

    // ─────────────────────────────────────────────
    //  HELPER — Format Status with Symbols
    // ─────────────────────────────────────────────

    /**
     * Returns a status string with an icon prefix for visual clarity.
     *
     * @param status Raw status string
     * @return       Status with a symbol prefix
     */
    static String formatStatus(String status) {
        switch (status) {
            case "Pending":     return "[ ] Pending";
            case "In Progress": return "[~] In Progress";
            case "Completed":   return "[✓] Completed";
            default:            return status;
        }
    }

    // ─────────────────────────────────────────────
    //  HELPER — Sample Data for Demo
    // ─────────────────────────────────────────────

    /**
     * Pre-loads the system with sample tasks so it's ready to demo immediately.
     * In a real app, this would load from a database or file.
     */
    static void loadSampleData() {
        taskList.add(new Task(taskIdCounter++, "Design Database Schema",
                              "Create ERD and define all tables for the project", "Completed"));
        taskList.add(new Task(taskIdCounter++, "Build REST API",
                              "Implement CRUD endpoints using Spring Boot", "In Progress"));
        taskList.add(new Task(taskIdCounter++, "Write Unit Tests",
                              "Cover all service-layer methods with JUnit tests", "Pending"));
        taskList.add(new Task(taskIdCounter++, "Deploy to AWS",
                              "Set up EC2 instance and deploy the backend application", "Pending"));
    }

    // ─────────────────────────────────────────────
    //  UI HELPERS — Banners, Menus, Messages
    // ─────────────────────────────────────────────

    static void printWelcomeBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║       CONSOLE-BASED TASK MANAGEMENT SYSTEM       ║");
        System.out.println("  ║          Internship Project — Java OOP            ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println("  Sample data loaded. You're ready to go!");
        System.out.println();
    }

    static void printMenu() {
        System.out.println("  ┌─────────────────────────────────┐");
        System.out.println("  │           MAIN MENU             │");
        System.out.println("  ├─────────────────────────────────┤");
        System.out.println("  │  1.  Add New Task               │");
        System.out.println("  │  2.  View All Tasks             │");
        System.out.println("  │  3.  Update Task                │");
        System.out.println("  │  4.  Delete Task                │");
        System.out.println("  │  5.  Exit                       │");
        System.out.println("  └─────────────────────────────────┘");
    }

    static void printSectionHeader(String title) {
        System.out.println();
        System.out.println("  ══════════════════════════════════════════");
        System.out.println("   " + title);
        System.out.println("  ══════════════════════════════════════════");
    }

    static void printSuccess(String message) {
        System.out.println("  ✔  " + message);
        printDivider();
    }

    static void printError(String message) {
        System.out.println("  ✖  ERROR: " + message);
        printDivider();
    }

    static void printDivider() {
        System.out.println();
    }

    static void printExitMessage() {
        System.out.println();
        System.out.println("  ══════════════════════════════════════════");
        System.out.println("   Thank you for using Task Manager. Goodbye!");
        System.out.println("  ══════════════════════════════════════════");
        System.out.println();
    }
}
