import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FreeParkingSpotsContainer {
    private final Map<Integer, ParkingSpotsPerFloor> freeParkingSpots = new ConcurrentHashMap<>();
    private int maxFloor;
    private int minFloor;

    private class ParkingSpotsPerFloor {
        private final Map<Enum, Set<Integer>> freeSpots = new ConcurrentHashMap<>();
        private final Map<Integer, ParkingSpot> idToSpotMapping = new HashMap<>();

        /**
         * Checks if spot is already present.
         * If present raises exception, else adds the spot
         */
        public synchronized void addSpot(ParkingSpot parkingSpot) {
            if (!idToSpotMapping.containsKey(parkingSpot.getId())) {
                idToSpotMapping.put(parkingSpot.getId(), parkingSpot);
            } else {
                idToSpotMapping.replace(parkingSpot.getId(), parkingSpot);
            }

            freeSpots.putIfAbsent(parkingSpot.getVehicleType(), new HashSet<>());
            freeSpots.get(parkingSpot.getVehicleType()).add(parkingSpot.getId());
        }

        /**
         * Checks if spot is already present.
         * If present in a different category, Changes the category
         */
        public synchronized void updateSpot(ParkingSpot parkingSpot) throws IllegalAccessError,
                                                                            IllegalStateException {
            if (!idToSpotMapping.containsKey(parkingSpot.getId())) {
                throw new IllegalAccessError("There is no parking spot with given id");
            }

            if (idToSpotMapping.get(parkingSpot.getId()).isOccupied()) {
                throw new IllegalStateException("Parking spot is currently occupied, can't modify");
            }

            ParkingSpot parkingSpotExist = idToSpotMapping.get(parkingSpot.getId());
            freeSpots.get(parkingSpotExist.getVehicleType()).remove(parkingSpotExist.getId());
            addSpot(parkingSpot);
        }
        
        public synchronized ParkingSpot getFreeParkingSpot(VehicleType vehicleType) {
            if (!this.freeSpots.containsKey(vehicleType)) return null;
            if (this.freeSpots.get(vehicleType).isEmpty()) return null;
            Integer parkingSpotId = -1;
            for (Integer i: this.freeSpots.get(vehicleType)) {
                parkingSpotId = i;
                break;
            }

            this.freeSpots.get(vehicleType).remove(parkingSpotId);
            return idToSpotMapping.get(parkingSpotId);
        }
    }

    public synchronized void addSpot(ParkingSpot parkingSpot) {
        if (!freeParkingSpots.containsKey(parkingSpot.getFloor())) {
            ParkingSpotsPerFloor parkingSpotsPerFloor = new ParkingSpotsPerFloor();
            freeParkingSpots.put(parkingSpot.getFloor(), parkingSpotsPerFloor);
        }

        this.maxFloor = Math.max(this.maxFloor, parkingSpot.getFloor());
        this.minFloor = Math.min(this.minFloor, parkingSpot.getFloor());
        freeParkingSpots.get(parkingSpot.getFloor()).addSpot(parkingSpot);
    }

    public void updateSpot(ParkingSpot parkingSpot) throws IllegalAccessError,
                                                           IllegalStateException {
        if (!freeParkingSpots.containsKey(parkingSpot.getFloor())) {
            throw new IllegalAccessError("There is no parking spot with given id in given floor");
        }

        freeParkingSpots.get(parkingSpot.getFloor()).addSpot(parkingSpot);
    }

    public void releaseParkingSpot(ParkingSpot parkingSpot) {
        this.addSpot(parkingSpot);
    }

    public ParkingSpot getNearestSpot(VehicleType vehicleType, Location location) {
        if (!this.freeParkingSpots.containsKey(location.getFloor())) return null;
        ParkingSpotsPerFloor parkingSpotsPerFloor = this.freeParkingSpots.get(location.getFloor());
        ParkingSpot parkingSpot = parkingSpotsPerFloor.getFreeParkingSpot(vehicleType);
        if (parkingSpot != null) {
            parkingSpot.setOccupied(true);
            return parkingSpot;
        }

        Location down = location.getPrevFloor();
        Location up = location.getNextFloor();

        while ((down.getFloor() >= this.minFloor) && (up.getFloor() <= this.maxFloor)) {
            if (this.freeParkingSpots.containsKey(down.getFloor())) {
                parkingSpotsPerFloor = this.freeParkingSpots.get(down.getFloor());
                parkingSpot = parkingSpotsPerFloor.getFreeParkingSpot(vehicleType);
                if (parkingSpot != null) {
                    parkingSpot.setOccupied(true);
                    return parkingSpot;
                }
            }

            if (this.freeParkingSpots.containsKey(up.getFloor())) {
                parkingSpotsPerFloor = this.freeParkingSpots.get(up.getFloor());
                parkingSpot = parkingSpotsPerFloor.getFreeParkingSpot(vehicleType);
                if (parkingSpot != null) {
                    parkingSpot.setOccupied(true);
                    return parkingSpot;
                }
            }

            down = down.getPrevFloor();
            up = up.getNextFloor();
        }

        while (down.getFloor() >= this.minFloor) {
            if (this.freeParkingSpots.containsKey(down.getFloor())) {
                parkingSpotsPerFloor = this.freeParkingSpots.get(down.getFloor());
                parkingSpot = parkingSpotsPerFloor.getFreeParkingSpot(vehicleType);
                if (parkingSpot != null) {
                    parkingSpot.setOccupied(true);
                    return parkingSpot;
                }
            }

            down = down.getPrevFloor();
        }

        while (up.getFloor() <= this.maxFloor) {
            if (this.freeParkingSpots.containsKey(up.getFloor())) {
                parkingSpotsPerFloor = this.freeParkingSpots.get(up.getFloor());
                parkingSpot = parkingSpotsPerFloor.getFreeParkingSpot(vehicleType);
                if (parkingSpot != null) {
                    parkingSpot.setOccupied(true);
                    return parkingSpot;
                }
            }

            up = up.getNextFloor();
        }

        return null;
    }
}
