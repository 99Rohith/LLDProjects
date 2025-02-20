
import java.time.LocalDateTime;

public class TaskImpl extends Task {
    public TaskImpl(int taskId,
                    String taskName,
                    String description,
                    LocalDateTime dueTime,
                    int frequency) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.dueTime = dueTime;
        this.frequency = frequency;
    }

    @Override
    public boolean getCompleted() {
        return this.completed;
    }

    @Override
    public int getTaskId() {
        return this.taskId;
    }

    @Override
    public synchronized void updateDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    @Override
    public LocalDateTime getDueTime() {
        return this.dueTime;
    }

    @Override
    public synchronized void updateFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int getFrequency() {
        return this.frequency;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", dueTime=" + dueTime +
                ", frequency=" + frequency +
                '}';
    }
}
