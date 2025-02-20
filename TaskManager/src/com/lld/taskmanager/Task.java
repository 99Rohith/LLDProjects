import java.time.LocalDateTime;

public abstract class Task {
    protected int taskId;
    protected String taskName;
    protected String description;
    protected LocalDateTime dueTime;
    protected int frequency;
    protected boolean completed = false;

    public abstract int getTaskId();
    public abstract LocalDateTime getDueTime();
    public abstract int getFrequency();
    public abstract void updateDueTime(LocalDateTime dueTime);
    public abstract void updateFrequency(int frequency);
    public abstract boolean getCompleted();
}
