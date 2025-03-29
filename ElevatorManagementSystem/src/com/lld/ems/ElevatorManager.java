public abstract class ElevatorManager {
    public abstract void addElevator(int elevatorId);
    public abstract void removeElevator(int id); // same as out of service

    /** method called from lobby */
    public abstract int getElevator(int floor, boolean up);

   /**
    * method called from inside the elevator should be called only after
    * elevator reached the floor
    */
    public abstract void moveElevator(int elevatorId, int srcFloor, int destFloor);
}
