
import java.time.LocalDateTime;

public abstract class TaskManager {
    public abstract void addTask(String taskName, String description,
                                 LocalDateTime dueTime, int frequency);
    public abstract void cancelTask(int taskId);
    public abstract void markTaskAsComplete(int taskId);
    public abstract void updateTaskDueTime(int taskId, String dueTime);
    public abstract void updateTaskFrequency(int taskId, int frequency);
}
