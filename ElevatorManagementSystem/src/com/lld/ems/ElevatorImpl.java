
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorImpl extends Elevator {
    private final Queue<Integer> mDstFloors = new LinkedBlockingQueue<>();

    public ElevatorImpl(int elevatorId, int startFloor) {
        super(elevatorId, startFloor);
    }

    @Override
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override
    public int getElevatorId() {
        return this.mElevatorId;
    }

    @Override
    public void setState(STATE state) {
        this.mState = state;
    }

    @Override
    public STATE getState() {
        return this.mState;
    }

    @Override
    public int getCurrentFloor() {
        return this.mCurrentFloor;
    }

    @Override
    public int getDestinationFloor() {
        return this.mDestinationFloor;
    }

    @Override
    public void move(int destinationFloor) {
        this.mDstFloors.add(destinationFloor);
        synchronized (this) {
            if (!this.mRunning) {
                this.mRunning = true;
                new Thread(this).start();
            }
        }
    }

    @Override
    public void run() {
        while (!this.mDstFloors.isEmpty()) {
            this.mDestinationFloor = this.mDstFloors.peek();
            this.mDstFloors.remove();
            if (this.mCurrentFloor == this.mDestinationFloor) continue;
            if (this.mCurrentFloor < this.mDestinationFloor) {
                this.setState(Elevator.STATE.UP);
                for (int i=this.mCurrentFloor; i<=this.mDestinationFloor; ++i) {
                    try {
                        Thread.sleep(Config.ELEVATOR_SPEED);
                    } catch (InterruptedException ex) {
                    }

                    System.out.println("Elevator " + this.mElevatorId + " reached " + i + " floor");
                }
            } else {
                this.setState(Elevator.STATE.DOWN);
                for (int i=this.mCurrentFloor; i>=this.mDestinationFloor; --i) {
                    try {
                        Thread.sleep(Config.ELEVATOR_SPEED);
                    } catch (InterruptedException ex) {
                    }

                    System.out.println("Elevator " + this.mElevatorId + " reached " + i + " floor");
                }
            }

            try {
                Thread.sleep(Config.ELEVATOR_STOP_DURATION);
            } catch (InterruptedException ex) {
            }
        }

        this.mRunning = false;
        this.setState(Elevator.STATE.IDLE);
    }
}
