
public abstract class Elevator implements Runnable {
    public static enum STATE {
        UP,
        DOWN,
        IDLE
    }

    protected final int mElevatorId;
    protected STATE mState = STATE.IDLE;
    protected boolean mRunning = false;
    protected int mCurrentFloor;
    protected int mDestinationFloor;

    protected Elevator(int elevatorId, int startFloor) {
        this.mElevatorId = elevatorId;
        this.mCurrentFloor = startFloor;
    }

    public abstract boolean isRunning();
    public abstract int getElevatorId();
    public abstract void setState(STATE state);
    public abstract STATE getState();
    public abstract int getCurrentFloor();
    public abstract int getDestinationFloor();
    public abstract void move(int destinationFloor);
}
