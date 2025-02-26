
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TaskImpl extends Task implements Comparable<Task> {
    public TaskImpl(int taskId,
                    String taskName,
                    String description,
                    LocalDateTime dueTime,
                    int frequency,
                    long repeatDays) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.dueTime = dueTime;
        this.frequency = frequency;
        this.repeatDays = repeatDays;
        this.numQueued = 1;
        this.originalDueTime = this.dueTime;
    }

    @Override
    public int compareTo(Task t) {
        return this.dueTime.compareTo(t.getDueTime());
    }

    @Override
    public LocalDateTime getOriginalDueTime() {
        return this.originalDueTime;
    }

    @Override
    public long getRepeatDays() {
        return this.repeatDays;
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
    public synchronized void setNumQueued(int numQueued) {
        this.numQueued = numQueued;
    }

    @Override
    public int getNumQueued() {
        return this.numQueued;
    }

    @Override
    public synchronized void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public synchronized void updateDueTime(LocalDateTime dueTime, boolean updateOriginal) {
        this.dueTime = dueTime;
        if (updateOriginal) {
            this.originalDueTime = dueTime;
        }
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
                ", frequency=" + frequency +
                ", repeatDays=" + repeatDays +
                ", numQueued=" + numQueued +
                ", completed=" + completed +
                ", dueIn=" + LocalDateTime.now().until(dueTime, ChronoUnit.SECONDS) +
                '}';
    }
}
