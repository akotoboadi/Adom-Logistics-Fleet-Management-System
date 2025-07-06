public class Driver {
    String driverID;
    String name;
    int experienceYears;
    String proximity; // e.g., "Tema", "Accra", etc.

    public Driver(String driverID, String name, int experienceYears, String proximity) {
        this.driverID = driverID;
        this.name = name;
        this.experienceYears = experienceYears;
        this.proximity = proximity;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Exp: %d yrs | Location: %s",
                driverID, name, experienceYears, proximity);
    }
}
