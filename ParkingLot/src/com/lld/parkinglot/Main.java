import java.util.ArrayList;

class ParkAndUnpark implements Runnable {
    private final long mSleepDuration;
    private final ParkingLot mParkingLot;
    private final String mVehicleNumber;
    private final VehicleType mVehicleType;
    private final Location mEntry;
    private ParkingTicket mParkingTicket;

    public ParkAndUnpark(ParkingLot parkingLot, long sleepDuration,
                         String vehicleNumber, VehicleType vehicleType,
                         Location entry) {
        this.mSleepDuration = sleepDuration;
        this.mParkingLot = parkingLot;
        this.mVehicleNumber = vehicleNumber;
        this.mVehicleType = vehicleType;
        this.mEntry = entry;
    }

    @Override
    public void run() {
        for (int i=0;i<5;++i) {
            park();
            try {
                Thread.sleep(this.mSleepDuration);
            } catch (InterruptedException e) {
                System.err.println("Interrupted");
            }

            unpark();
        }
    }

    private void park() {
        this.mParkingTicket = this.mParkingLot.park(this.mVehicleNumber, this.mVehicleType,
                                                        this.mEntry);
        if (this.mParkingTicket == null) {
            System.err.println("Couldn't park " + this.mVehicleNumber + ", No spots left");
            return;
        }

        System.out.println("Entry: " + mParkingTicket.toString());
    }

    private void unpark() {
        if (this.mParkingTicket == null) return;
        this.mParkingLot.exit(this.mVehicleNumber);
        System.out.println("Exit: " + mParkingTicket.toString());
        this.mParkingTicket = null;
    }
}

/**
 * Design a parking lot with multiple floors.
 * The parking lot has diffrent parking spaces for 2 wheelers, 4 wheelers, inactive and Electric.
 * Assign an empty parking spot to a vehicle when vehicle enters and remove the vehicle
 * when it is unparked. 
 * Each vehicle will have a vehicleNumber
 * The system should provide a spot that is nearest to entry point floor.
 * The parking lot should have multiple entry and exit points.
 * Customers can collect a parking ticket from the entry points and can pay the 
 * parking fee at the exit points on their way out. System should display the fare when exit.
 * The system should support a per-hour parking fee model. $4 for the first hour $3.5
 * for the second and third hours, and $2.5 for all the remaining hours.
 * Get the history of a vehicle parked for last 3 months
 * 
 * 
 * ParkingLot % javac -d ./build/ ./src/com/lld/parkinglot/*.java
 * ParkingLot % cd build
 * build % java Main
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
        ArrayList<Location> entries = new ArrayList<>();
        ArrayList<Location> exits = new ArrayList<>();
        for (int floor=0;floor<4;++floor) {
            int id = 1;
            for (VehicleType vehicleType: VehicleType.values()) {
                parkingSpots.add(new ParkingSpot(floor, id, vehicleType));
                id++;
            }

            entries.add(new Location(floor, 1));
            exits.add(new Location(floor, 2));
        }

        ParkingLot parkingLot = ParkingLotImpl.getInstance();
        parkingLot.initialize(parkingSpots, entries, exits);
        Runnable r1 = new ParkAndUnpark(parkingLot, 1000, "0E", VehicleType.TWO_WHEELER, entries.get(0));
        Runnable r2 = new ParkAndUnpark(parkingLot, 2000, "1E", VehicleType.TWO_WHEELER, entries.get(0));
        Runnable r3 = new ParkAndUnpark(parkingLot, 3000, "2E", VehicleType.TWO_WHEELER, entries.get(0));
        Runnable r4 = new ParkAndUnpark(parkingLot, 1000, "3E", VehicleType.TWO_WHEELER, entries.get(0));
        Runnable r5 = new ParkAndUnpark(parkingLot, 2000, "4E", VehicleType.TWO_WHEELER, entries.get(0));
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();
        new Thread(r5).start();
    }
}
