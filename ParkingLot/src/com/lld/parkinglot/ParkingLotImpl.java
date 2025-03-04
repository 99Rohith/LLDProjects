import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotImpl extends ParkingLot {
    private static ParkingLotImpl INSTANCE;
    private final FreeParkingSpotsContainer freeParkingSpotsContainer = 
                                                                    new FreeParkingSpotsContainer();
    private final Map<String, ParkingTicket> parkingSpotsTakenByNo = new ConcurrentHashMap<>();
    private final Map<Integer, ParkingTicket> parkingSpotsTakenById = new ConcurrentHashMap<>();
    private final Set<Location> entires = ConcurrentHashMap.newKeySet();
    private final Set<Location> exits = ConcurrentHashMap.newKeySet();

    private ParkingLotImpl() {
        super();
    }

    public synchronized static ParkingLotImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLotImpl();
        }

        return INSTANCE;
    }

    @Override
    public void initialize(ArrayList<ParkingSpot> parkingSpots, ArrayList<Location> entries,
                           ArrayList<Location> exits) {
        for (ParkingSpot parkingSpot: parkingSpots) {
            this.freeParkingSpotsContainer.addSpot(parkingSpot);
        }

        for (Location location: entries) {
            this.entires.add(location);
        }

        for (Location location: exits) {
            this.exits.add(location);
        }
    }

    @Override
    public ParkingTicket park(String vehicleNumber, VehicleType vehicleType,
                              Location entry) {
        if (!this.entires.contains(entry)) return null;
        ParkingSpot parkingSpot = this.freeParkingSpotsContainer.getNearestSpot(vehicleType, entry);
        if (parkingSpot == null) return null;
        ParkingTicket parkingTicket = new ParkingTicketImpl(vehicleNumber, parkingSpot);
        parkingSpotsTakenById.put(parkingTicket.getTicketId(), parkingTicket);
        parkingSpotsTakenByNo.put(vehicleNumber, parkingTicket);
        return parkingTicket;
    }

    @Override
    public double exit(String vehicleNumber) {
        ParkingTicket parkingTicket = this.parkingSpotsTakenByNo.get(vehicleNumber);
        return getCost(parkingTicket);
    }

    @Override
    public double exit(int parkingTicketId) {
        ParkingTicket parkingTicket = this.parkingSpotsTakenById.get(parkingTicketId);
        return getCost(parkingTicket);
    }

    private double getCost(ParkingTicket parkingTicket) {
        if (parkingTicket == null) return 0;
        this.parkingSpotsTakenByNo.remove(parkingTicket.getVehicleNumber());
        this.parkingSpotsTakenById.remove(parkingTicket.getTicketId());
        this.freeParkingSpotsContainer.releaseParkingSpot(parkingTicket.getParkingSpot());
        return parkingTicket.getCost();
    }

    @Override
    public void addEntry(Location location) {
        if (this.entires.contains(location)) return;
        if (this.exits.contains(location)) {
            this.exits.remove(location);
        }

        this.entires.add(location);
    }

    @Override
    public void addExit(Location location) {
        if (this.exits.contains(location)) return;
        if (this.entires.contains(location)) {
            this.entires.remove(location);
        }

        this.exits.add(location);
    }

    @Override
    public void removeEntry(Location location) {
        if (this.entires.contains(location)) {
            this.entires.remove(location);
        }
    }

    @Override
    public void removeExit(Location location) {
        if (this.exits.contains(location)) {
            this.exits.remove(location);
        }
    }
}
