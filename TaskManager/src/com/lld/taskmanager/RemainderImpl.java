import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Remainders are triggered when a task is due.
 * If customer doesn't make the task as completed,
 * Remainder is requeued for Config.MAX_REMAINDERS
 * Tasks are requeued based on frequency.
 */
public class RemainderImpl extends Remainder implements Runnable {
    private static RemainderImpl INSTANCE = null;

    /* private final class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getDueTime().compareTo(task2.getDueTime());
        }
    }*/

    private final PriorityBlockingQueue<Task> pq = new PriorityBlockingQueue<>(11);
    private final TasksInventory tasksInventory = TasksInventory.getInstance();

    private boolean keepRunning = false;

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
                if (LocalDateTime.now().until(pq.peek().getDueTime(), ChronoUnit.SECONDS) <= 0) {
                    Task task = pq.peek();
                    pq.poll();
                    triggerRemainder(task);
                }
            }

            waitForMs(1000);
        }
    }

    public synchronized void stopRunning() {
        this.keepRunning = false;
    }

    @Override
    public void onTaskAdded(Task task) {
        pq.add(task);
    }

    @Override
    public synchronized void onTaskUpdated(Task task) {
        pq.remove(task);
        pq.add(task);
    }

    @Override
    public synchronized void onTaskCancelled(Task task) {
        System.out.println("Task cancelled: " + task.toString());
        pq.remove(task);
        tasksInventory.removeTask(task.getTaskId());
    }

    @Override
    public void listTasks() {
        PriorityQueue<Task> pqCopy = new PriorityQueue<>(pq);
        System.out.println("Tasks List: ");
        while (!pqCopy.isEmpty()) {
            System.out.println(pqCopy.poll().toString());
        }
    }

    private void waitForMs(long timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private synchronized void startRunning() {
        this.keepRunning = true;
    }

    private void triggerRemainder(Task task) {
        if (Config.MAX_REMAINDERS == 0) return;
        if (task == null) {
            System.err.println("No task found with task");
            return;
        }

        requeueTaskIfRequired(task);
    }

    private void requeueTaskIfRequired(Task task) {
        final LocalDateTime dueTime = task.getDueTime();
        final LocalDateTime originalDueTime = task.getOriginalDueTime();
        final int taskFrequency = task.getFrequency();
        final int numQueued = task.getNumQueued();
        if (numQueued > Config.MAX_REMAINDERS) {
            task.setCompleted(true);
        }

        if (task.getCompleted()) {
            System.out.println("Task completed: " + task.getTaskId());
            task.setCompleted(false);
            task.setNumQueued(1);
            if (taskFrequency == 1) {
                tasksInventory.removeTask(task.getTaskId());
            } else {
                if (tasksInventory.get(task.getTaskId()) != null) {
                    task.updateFrequency(taskFrequency - 1);
                    task.updateDueTime(originalDueTime.plusDays(task.getRepeatDays()), true);
                    pq.add(task);
                }
            }

            return;
        }

        System.out.println("Remainder for task: " + task.toString());
        if (tasksInventory.get(task.getTaskId()) != null) {
            task.setNumQueued(numQueued + 1);
            task.updateDueTime(dueTime.plusSeconds(Config.REMAINDER_TIME_SEC), false);
            pq.add(task);
        }
    }
}
