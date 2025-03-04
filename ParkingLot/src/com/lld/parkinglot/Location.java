public class Location {
    private final int floor;
    private final int id;
    public Location(int floor, int id) {
        this.floor = floor;
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }

    public Location getPrevFloor() {
        return new Location(this.floor - 1, this.getId());
    }

    public Location getNextFloor() {
        return new Location(this.floor + 1, this.getId());
    }
}
