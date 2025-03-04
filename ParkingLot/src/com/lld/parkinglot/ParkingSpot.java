public class ParkingSpot {
    public static Enum ParkingSpotType;
    private final int floor;
    private final int id;
    private final VehicleType vehicleType;
    private boolean occupied;

    public ParkingSpot(int floor, int id, VehicleType vehicleType) {
        this.floor = floor;
        this.id = id;
        this.vehicleType = vehicleType;
    }

    public int getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
