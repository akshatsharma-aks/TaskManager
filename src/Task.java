/**
 * Task.java
 * Represents a single Task entity in the Task Management System.
 * Follows OOP principles with encapsulation using private fields and public getters/setters.
 *
 * Author: [Your Name]
 * Project: Console-Based Task Management System
 * Version: 1.0
 */
public class Task {

    // ─────────────────────────────────────────────
    //  Fields (Private for Encapsulation)
    // ─────────────────────────────────────────────

    private int taskId;           // Unique identifier, auto-generated
    private String taskTitle;     // Short title of the task
    private String taskDescription; // Detailed description of the task
    private String taskStatus;    // Current status: "Pending", "In Progress", "Completed"

    // ─────────────────────────────────────────────
    //  Constructor
    // ─────────────────────────────────────────────

    /**
     * Parameterized constructor to create a new Task object.
     *
     * @param taskId          Unique ID assigned to this task
     * @param taskTitle       Short, descriptive title
     * @param taskDescription Detailed explanation of the task
     * @param taskStatus      Current status of the task
     */
    public Task(int taskId, String taskTitle, String taskDescription, String taskStatus) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    // ─────────────────────────────────────────────
    //  Getters (Read access to private fields)
    // ─────────────────────────────────────────────

    public int getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    // ─────────────────────────────────────────────
    //  Setters (Write access to private fields)
    // ─────────────────────────────────────────────

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    // ─────────────────────────────────────────────
    //  toString Override — for debug/logging
    // ─────────────────────────────────────────────

    /**
     * Returns a formatted string representation of the Task.
     * Useful for debugging and logging purposes.
     */
    @Override
    public String toString() {
        return "Task{" +
               "ID=" + taskId +
               ", Title='" + taskTitle + '\'' +
               ", Description='" + taskDescription + '\'' +
               ", Status='" + taskStatus + '\'' +
               '}';
    }
}
