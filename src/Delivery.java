public class Delivery {
    String packageID;
    String origin;
    String destination;
    String assignedVehicleID;
    String assignedDriverID;
    String eta; // Estimated Time of Arrival
    String status; // e.g., Pending, In Transit, Delivered

    public Delivery(String packageID, String origin, String destination,
            String assignedVehicleID, String assignedDriverID, String eta) {
        this.packageID = packageID;
        this.origin = origin;
        this.destination = destination;
        this.assignedVehicleID = assignedVehicleID;
        this.assignedDriverID = assignedDriverID;
        this.eta = eta;
        this.status = "Pending"; // default

    }

    @Override
    public String toString() {
        return "PackageID: " + packageID +
                ", From: " + origin +
                ", To: " + destination +
                ", Vehicle: " + assignedVehicleID +
                ", Driver: " + assignedDriverID +
                ", ETA: " + eta +
                ", Status: " + status;
    }
}
