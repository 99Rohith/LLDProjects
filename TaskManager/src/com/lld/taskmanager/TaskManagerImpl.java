import java.time.LocalDateTime;

public class TaskManagerImpl extends TaskManager {
    private final TasksInventory tasksInventory = TasksInventory.getInstance();
    private final Remainder remainder = RemainderImpl.getInstance();

    @Override
    public void addTask(String taskName, String description, LocalDateTime dueTime) {
        int taskId = TaskIdGenerator.generateTaskId();
        Task newTask = new TaskImpl(taskId, taskName, description, dueTime, 1, 0);
        tasksInventory.addTask(newTask);
        remainder.onTaskAdded(newTask);
    }

    @Override
    public void addTask(String taskName, String description, LocalDateTime dueTime, int frequency,
                        long repeatDays) {
        int taskId = TaskIdGenerator.generateTaskId();
        Task newTask = new TaskImpl(taskId, taskName, description, dueTime, frequency, repeatDays);
        tasksInventory.addTask(newTask);
        remainder.onTaskAdded(newTask);
    }

    @Override
    public void cancelTask(int taskId) {
        if (!tasksInventory.contains(taskId)) {
            System.err.println("No task with taskId=" + taskId);
            return;
        }

        Task task = tasksInventory.get(taskId);
        remainder.onTaskCancelled(task);
    }

    @Override
    public void markTaskAsComplete(int taskId) {
        if (!tasksInventory.contains(taskId)) return;
        Task task = tasksInventory.get(taskId);
        task.setCompleted(true);
    }

    @Override
    public void updateTaskDueTime(int taskId, LocalDateTime dueTime) {
        if (!tasksInventory.contains(taskId)) return;
        Task task = tasksInventory.get(taskId);
        task.updateDueTime(dueTime, true);
        remainder.onTaskUpdated(task);
    }

    @Override
    public void updateTaskFrequency(int taskId, int frequency) {
        if (!tasksInventory.contains(taskId)) return;
        Task task = tasksInventory.get(taskId);
        task.updateFrequency(frequency);
    }

    @Override
    public void listTasks() {
        remainder.listTasks();
    }
}
