
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ElevatorManagerImpl extends ElevatorManager {
    private final Map<Integer, Elevator> mElevators = new HashMap<>();
    private Random random = new Random();

    @Override
    public void addElevator(int elevatorId) {
        if (mElevators.containsKey(elevatorId)) return;
        mElevators.put(elevatorId, new ElevatorImpl(elevatorId, (random.nextInt() + 1) % Config.NUM_FLOORS));
        System.out.println("Added elevator " + elevatorId + " with floor " + mElevators.get(elevatorId).getCurrentFloor());
    }

    @Override
    public void removeElevator(int elevatorId) throws IllegalStateException {
        if (!mElevators.containsKey(elevatorId)) return;
        if (mElevators.get(elevatorId).isRunning()) {
            throw new IllegalStateException("This elevator is running, wait for it to stop");
        }

        mElevators.remove(elevatorId);
    }

    @Override
    public int getElevator(int srcFloor, boolean up) {
        /**
         * case - 1: up, lift going up, lift crossed srcFloor - dist = destFloor - curFloor + destFloor - srcFloor // abs(destFloor - curFloor) + abs(destFloor - srcFloor)
         * case - 3: up, lift going down, lift destination is below your srcFloor - dist = curFloor - destFloor + srcFloor - destFloor // abs(destFloor - curFloor) + abs(destFloor - srcFloor)
         * case - 5: !up, lift going down, but lift crossed srcFloor - dist = curFloor - destFloor + srcFloor - destFloor // abs(destFloor - curFloor) + abs(destFloor - srcFloor)
         * case - 7: !up, lift going up, lift destination is above your srcFloor - dist = destFloor - curFloor + destFloor - srcFloor // abs(destFloor - curFloor) + abs(destFloor - srcFloor)
         * 
         * case - 2: up, lift going up, but lift didn't crossed srcFloor - dist = srcFloor - curFloor // abs(srcFloor - curFloor)
         * case - 4: up, lift going down, lift destination is >= your srcFloor - dist = curFloor - srcFloor // abs(srcFloor - curFloor)
         * case - 6: !up, lift going down, curFloor > srcFloor - dist = curFloor - srcFloor // abs(srcFloor - curFloor)
         * case - 8: !up, lift going up, lift destination is <= your floor - dist = abs(srcFloor - curFloor)
         * case - 9: up || !up, lift idle, dist = abs(srcFloor - curFloor)
         */

        int bestElevator = -1, bestDistance = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Elevator> elevatorEntry : mElevators.entrySet()) {
            Elevator elevator = elevatorEntry.getValue();
            Elevator.STATE state = elevator.getState();
            int curFloor = elevator.getCurrentFloor();
            int destFloor = elevator.getDestinationFloor();
            int distance;
            if (up && state.equals(Elevator.STATE.UP) && curFloor > srcFloor) {
                distance = Math.abs(destFloor - curFloor) + Math.abs(destFloor - srcFloor);
            } else if (up && state.equals(Elevator.STATE.DOWN) && destFloor < srcFloor) {
                distance = Math.abs(destFloor - curFloor) + Math.abs(destFloor - srcFloor);
            } else if (!up && state.equals(Elevator.STATE.DOWN) && curFloor < srcFloor) {
                distance = Math.abs(destFloor - curFloor) + Math.abs(destFloor - srcFloor);
            } else if (!up && state.equals(Elevator.STATE.UP) && destFloor > srcFloor) {
                distance = Math.abs(destFloor - curFloor) + Math.abs(destFloor - srcFloor);
            } else if (up && state.equals(Elevator.STATE.UP) && curFloor < srcFloor) {
                distance = Math.abs(srcFloor - curFloor);
            } else if (up && state.equals(Elevator.STATE.DOWN) && destFloor >= srcFloor) {
                distance = Math.abs(srcFloor - curFloor);
            } else if (!up && state.equals(Elevator.STATE.DOWN) && curFloor > srcFloor) {
                distance = Math.abs(srcFloor - curFloor);
            } else if (!up && state.equals(Elevator.STATE.UP) && destFloor <= srcFloor) {
                distance = Math.abs(srcFloor - curFloor);
            } else {
                distance = Math.abs(srcFloor - curFloor);
            }

            if (distance < bestDistance) {
                bestDistance = distance;
                bestElevator = elevatorEntry.getKey();
            }
        }

        mElevators.get(bestElevator).move(srcFloor);
        return bestElevator;
    }

    @Override
    public void moveElevator(int elevatorId, int srcFloor, int destFloor) throws
        IllegalArgumentException {
        if (mElevators.get(elevatorId).getCurrentFloor() != srcFloor) {
            throw new IllegalArgumentException("The elevator hasn't reached srcFloor");
        }

        System.out.println("Moving Elevator " + elevatorId + " from " + srcFloor + " to " + destFloor);
        mElevators.get(elevatorId).move(destFloor);
    }
}
