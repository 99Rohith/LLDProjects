import java.util.ArrayList;

public abstract class ParkingLot {
    public abstract void initialize(ArrayList<ParkingSpot> parkingSpots, ArrayList<Location> entries,
                                    ArrayList<Location> exits);
    public abstract void addEntry(Location location);
    public abstract void addExit(Location location);
    public abstract void removeEntry(Location location);
    public abstract void removeExit(Location location);
    public abstract ParkingTicket park(String vehicleNumber, VehicleType vehicleType,
                                        Location entry);
    public abstract double exit(String vehicleNumber);
    public abstract double exit(int parkingTicketId);
}
