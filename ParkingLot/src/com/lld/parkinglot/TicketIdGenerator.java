public class TicketIdGenerator {
    private static TicketIdGenerator INSTANCE;
    private int id = -1;

    private TicketIdGenerator() {}

    public synchronized static TicketIdGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicketIdGenerator();
        }

        return INSTANCE;
    }

    public synchronized int getNetId() {
        id++;
        return id;
    }
}
