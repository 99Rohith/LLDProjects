
import java.time.LocalDateTime;

public class ParkingTicketImpl extends ParkingTicket {
    public ParkingTicketImpl(String vehicleNumber, ParkingSpot parkingSpot) {
        super(vehicleNumber, parkingSpot);
    }

    @Override
    public double getCost() {
        long numHours = TimeCalculator.difference(this.getExitTime(), this.entryTime);
        double cost = this.rates.get(0);
        for (int i=1;i<this.slabs.size();++i) {
            if (this.slabs.get(i) <= numHours) {
                cost += this.rates.get(i) * (this.slabs.get(i) - this.slabs.get(i-1));
            }
        }

        return this.parkingCost = cost;
    }

    @Override
    public String toString() {
        return "VehicleNumber: " + this.vehicleNumber +
                ", EntryTime: " + String.valueOf(this.entryTime) + 
                ", ExitTime: " + String.valueOf(this.exitTime) +
                ", Cost: " + String.valueOf(this.parkingCost);
    }

    @Override
    public int getTicketId() {
        return this.ticketId;
    }

    @Override
    public synchronized LocalDateTime getExitTime() {
        if (this.exitTime == null) {
            this.exitTime = LocalDateTime.now();
        }

        return this.exitTime;
    }

    @Override
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    @Override
    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }
}
