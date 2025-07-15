import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    String regNumber;
    String type;
    int mileage;
    double fuelUsage;
    String driverID;
    int lastServiceMileage;

    // maintenance history (part → cost)
    public Map<String, Double> maintenanceHistory = new HashMap<>();

    public Vehicle(String regNumber, String type, int mileage, double fuelUsage, String driverID) {
        this.regNumber = regNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverID = driverID;
        this.lastServiceMileage = 0; // initially assume no service yet
    }

    public void addMaintenance(String part, double cost) {
        maintenanceHistory.put(part, cost);
        this.lastServiceMileage = this.mileage; // reset service mileage
    }

    @Override
    public String toString() {
        return String.format(
                "Reg: %s | Type: %s | Mileage: %d | Last Service: %d | Fuel: %.2f | Driver: %s | Maintenance Records: %d",
                regNumber, type, mileage, lastServiceMileage, fuelUsage, driverID, maintenanceHistory.size());
    }
}
