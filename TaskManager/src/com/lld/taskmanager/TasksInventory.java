
import java.util.concurrent.ConcurrentHashMap;

public class TasksInventory {
    private static TasksInventory INSTANCE = null;
    private final ConcurrentHashMap<Integer, Task> taskMap = new ConcurrentHashMap<>();
    private TasksInventory(){
        // init nothing
    }

    public static synchronized TasksInventory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksInventory();
        }

        return INSTANCE;
    }

    public Task get(int taskId) {
        if (taskMap.containsKey(taskId)) {
            return taskMap.get(taskId);
        }

        return null;
    }

    public boolean contains(int taskId) {
        return taskMap.containsKey(taskId);
    }

    public void addTask(Task task) {
        taskMap.put(task.getTaskId(), task);
    }

    public void removeTask(int taskId) {
        if (!taskMap.contains(taskId)) return;
        taskMap.remove(taskId);
    }
}
