public class Vehicle {
    String regNumber;
    String type;
    int mileage;
    double fuelUsage;
    String driverID;

    public Vehicle(String regNumber, String type, int mileage, double fuelUsage, String driverID) {
        this.regNumber = regNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverID = driverID;
    }

    @Override
    public String toString() {
        return String.format("Reg: %s | Type: %s | Mileage: %d | Fuel: %.2f | Driver: %s",
                regNumber, type, mileage, fuelUsage, driverID);
    }
}
