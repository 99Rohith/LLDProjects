import java.time.LocalDateTime;

public class TaskManagerImpl extends TaskManager {
    private final TasksInventory tasksInventory = TasksInventory.getInstance();
    private final Remainder remainder = RemainderImpl.getInstance();

    @Override
    public void addTask(String taskName, String description, LocalDateTime dueTime, int frequency) {
        int taskId = TaskIdGenerator.generateTaskId();
        Task newTask = new TaskImpl(taskId, taskName, description, dueTime, frequency);
        tasksInventory.addTask(newTask);
        remainder.onTaskAdded(newTask);
    }

    @Override
    public void cancelTask(int taskId) {

    }

    @Override
    public void markTaskAsComplete(int taskId) {

    }

    @Override
    public void updateTaskDueTime(int taskId, String dueTime) {

    }

    @Override
    public void updateTaskFrequency(int taskId, int frequency) {

    }
}
