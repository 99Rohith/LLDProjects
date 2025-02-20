public class TaskIdGenerator {
    private static int taskIdCounter = 0;

    public static int generateTaskId() {
        taskIdCounter++;
        return taskIdCounter;
    }
}
