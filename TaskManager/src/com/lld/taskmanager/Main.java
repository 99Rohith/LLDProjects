import java.time.LocalDateTime;

/**
 * TaskManager % javac -d ./build/ ./src/com/lld/taskmanager/*.java
 * TaskManager % cd build
 * build % java Main
 * 
 * 1. Create a task with due time and frequency
 * 2. Update the task with new due time
 * 3. Cancel the task
 * 4. List all the tasks
 * 5. Update the task frequency
 * 6. Update the task due time
 * 7. Trigger remainders.
 */
public class Main {
    public static void waitForMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask("Task1", "no repeat", LocalDateTime.now().plusSeconds(5));
        taskManager.addTask("Task2", "no repeat", LocalDateTime.now().plusSeconds(10));
        taskManager.addTask("Task3", "no repeat", LocalDateTime.now().plusSeconds(15));
        taskManager.addTask("Task4", "no repeat", LocalDateTime.now().plusSeconds(20));
        taskManager.addTask("Task5", "no repeat", LocalDateTime.now().plusSeconds(25));
        taskManager.addTask("Task6", "repeat", LocalDateTime.now().plusSeconds(3), 3, 1);
        taskManager.addTask("Task7", "repeat", LocalDateTime.now().plusSeconds(3), 3, 1);
        taskManager.addTask("Task8", "repeat", LocalDateTime.now().plusSeconds(6), 3, 1);
        taskManager.addTask("Task9", "repeat", LocalDateTime.now().plusSeconds(9), 3, 1);
        taskManager.addTask("Task10", "repeat", LocalDateTime.now().plusSeconds(10), 3, 1);
        taskManager.listTasks();

        waitForMs(10000);
        taskManager.cancelTask(4);
        taskManager.updateTaskDueTime(9, LocalDateTime.now().plusSeconds(9));
        waitForMs(90000);
        taskManager.listTasks();
    }
}
