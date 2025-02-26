import java.time.LocalDateTime;

public abstract class Task {
    protected int taskId;
    protected String taskName;
    protected String description;
    protected LocalDateTime dueTime;
    protected int frequency;
    protected long repeatDays;
    protected int numQueued;
    protected LocalDateTime originalDueTime;
    protected boolean cancelled = false;
    protected boolean completed = false;

    public abstract int getTaskId();
    public abstract LocalDateTime getDueTime();
    public abstract int getFrequency();
    public abstract long getRepeatDays();
    public abstract boolean getCompleted();
    public abstract int getNumQueued();
    public abstract LocalDateTime getOriginalDueTime();

    public abstract void updateDueTime(LocalDateTime dueTime, boolean updateOriginal);
    public abstract void updateFrequency(int frequency);
    public abstract void setNumQueued(int numQueued);
    public abstract void setCompleted(boolean completed);
}
