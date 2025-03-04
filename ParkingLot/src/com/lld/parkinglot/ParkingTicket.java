import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParkingTicket {
    protected final int ticketId;
    protected final String vehicleNumber;
    protected final LocalDateTime entryTime;
    protected LocalDateTime exitTime = null;
    protected Double parkingCost = null;
    protected final ParkingSpot parkingSpot;
    protected final List<Integer> slabs = new ArrayList<>(Arrays.asList(1, 3, Integer.MAX_VALUE));
    protected final List<Double> rates = new ArrayList<>(Arrays.asList(4.0, 3.5, 2.5));

    public ParkingTicket(String vehicleNumber, ParkingSpot parkingSpot) {
        this.ticketId = TicketIdGenerator.getInstance().getNetId();
        this.vehicleNumber = vehicleNumber;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    /**
     * $4 for the first hour
     * $3.5 for the second and third hours
     * $2.5 for all the remaining hours.
     * Get the history of a vehicle parked for last 3 months
     */
    public abstract double getCost();
    public abstract int getTicketId();
    public abstract String getVehicleNumber();
    public abstract ParkingSpot getParkingSpot();
    public abstract LocalDateTime getExitTime();
}
