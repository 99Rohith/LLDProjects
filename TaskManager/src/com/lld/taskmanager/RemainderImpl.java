import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class RemainderImpl extends Remainder implements Runnable {
    private static RemainderImpl INSTANCE = null;

    private final class Pair {
        private LocalDateTime dueTime;
        private int taskId;
        private int numQueued;

        public Pair(LocalDateTime timestamp, int taskId) {
            this.dueTime = timestamp;
            this.taskId = taskId;
            this.numQueued = 1;
        }

        public LocalDateTime getDueTime() {
            return this.dueTime;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public int getNumQueued() {
            return this.numQueued;
        }
    }

    private final PriorityQueue<Pair> pq = new PriorityQueue<>((Pair task1, Pair task2) -> {
        return task1.getDueTime().compareTo(task2.getDueTime());
    });

    private boolean keepRunning = false;
    private final TasksInventory tasksInventory = TasksInventory.getInstance();

    private RemainderImpl() {
        new Thread(this).start();
    }

    public static synchronized RemainderImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemainderImpl();
        }

        return INSTANCE;
    }

    @Override
    public void run() {
        startRunning();
        while (this.keepRunning) {
            if (!pq.isEmpty()) {
                if (LocalDateTime.now().compareTo(pq.peek().getDueTime()) <= 0) {
                    
                }
            }

            waitForMs(1000);
        }
    }

    public void stopRunning() {
        synchronized (this) {
            this.keepRunning = false;
        }
    }

    @Override
    public void onTaskAdded(Task task) {

    }

    @Override
    public void onTaskUpdated(Task task) {

    }

    @Override
    public void onTaskCancelled(Task task) {

    }

    private void waitForMs(long timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private void startRunning() {
        synchronized (this) {
            this.keepRunning = true;
        }
    }

    private void triggerRemainder(Pair pair) {
        Task task = tasksInventory.get(pair.getTaskId());
        if (task == null) {
            System.err.println("No task found with task id " + pair.getTaskId());
            return;
        }

        if (task.getCompleted()) return;
        System.out.println("Remainder for task: " + task.toString());
        requeueTaskIfRequired(pair);
    }

    private void requeueTaskIfRequired(Pair pair) {
        Task task = tasksInventory.get(pair.getTaskId());
        synchronized (task) {
            LocalDateTime dueTime = task.getDueTime();
            int taskFrequency = task.getFrequency();
            if (taskFrequency == 1) {
                tasksInventory.removeTask(pair.getTaskId());
                return;
            }

            task.updateFrequency(taskFrequency - 1);
            if (task.getCompleted()) {

            }

            task.updateDueTime(dueTime.plusMinutes(pair.getTaskId()));
            pq.add(new Pair(task.getDueTime(), taskId));
        }
    }
}
