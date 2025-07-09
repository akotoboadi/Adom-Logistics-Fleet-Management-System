import java.util.ArrayList;
import java.util.List;

public class Driver {
    String driverID;
    String name;
    int experienceYears;
    String proximity; // e.g., "Tema", "Accra", etc.

    List<String> assignedRoutes = new ArrayList<>();
    List<String> delays = new ArrayList<>();
    List<String> infractions = new ArrayList<>();

    public Driver(String driverID, String name, int experienceYears, String proximity) {
        this.driverID = driverID;
        this.name = name;
        this.experienceYears = experienceYears;
        this.proximity = proximity;
    }

    public void addRoute(String route) {
        assignedRoutes.add(route);
    }

    public void addDelay(String delay) {
        delays.add(delay);
    }

    public void addInfraction(String infraction) {
        infractions.add(infraction);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Exp: %d yrs | Location: %s",
                driverID, name, experienceYears, proximity);
    }
}
