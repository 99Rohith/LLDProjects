
import java.util.ArrayList;

/**
 *  Track state of lifts i.e. floor lift is at, move direction, number of passengers,
 *  existing and future requests. assign lift optimally to a user who is on a given
 *  floor and wants to go to a destination floor.
 *  % javac -d ./build/ ./src/com/lld/ems/*.java
 *  % cd build
 *  build % java Main
 */
public class Main {
    public static void main(String[] args) {
        ElevatorManager elevatorManager = new ElevatorManagerImpl();
        for (int i=1;i<=Config.NUM_ELEVATROS;++i) {
            elevatorManager.addElevator(i);
        }
        
        ArrayList<Integer> elevators = new ArrayList<>();
        for (int i=1;i<=Config.NUM_FLOORS;++i) {
            int elevatorId = elevatorManager.getElevator(i, true);
            System.out.println("Got elevator " + elevatorId + " for floor " + i);
            elevators.add(elevatorId);
        }

        for (int i=1;i<=Config.NUM_FLOORS;++i) {
            final int floorId = i;
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        elevatorManager.moveElevator(elevators.get(floorId-1), floorId, 10 - floorId);
                        break;
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
    }
}
