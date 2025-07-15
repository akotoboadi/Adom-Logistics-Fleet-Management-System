import java.util.*;

public class FuelReportGenerator {
    static Scanner scanner = new Scanner(System.in);

    public static void generateReport() {
        boolean back = false;

        while (!back) {
            System.out.println("\n== Fuel Efficiency Reports ==");
            System.out.println("1. Show All Vehicles by Fuel Usage");
            System.out.println("2. Flag Fuel Inefficient Vehicles");
            System.out.println("3. Filter Vehicles by Max Fuel Usage");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllSorted();
                    break;
                case 2:
                    flagInefficient();
                    break;
                case 3:
                    filterByMax();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showAllSorted() {
        List<Vehicle> vehicleList = new ArrayList<>(VehicleManager.vehicles.values());
        vehicleList.sort(Comparator.comparingDouble(v -> v.fuelUsage));

        System.out.println("\n-- Vehicles Sorted by Fuel Usage --");
        for (Vehicle v : vehicleList) {
            System.out.printf("Reg: %s | Type: %s | Fuel Usage: %.2f%n", v.regNumber, v.type, v.fuelUsage);
        }
    }

    private static void flagInefficient() {
        System.out.println("\n-- Fuel Inefficient Vehicles (Fuel Usage > 20.0) --");
        boolean found = false;
        for (Vehicle v : VehicleManager.vehicles.values()) {
            if (v.fuelUsage > 20.0) {
                System.out.printf("Reg: %s | Type: %s | Fuel Usage: %.2f%n", v.regNumber, v.type, v.fuelUsage);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles found with inefficient fuel usage.");
        }
    }

    private static void filterByMax() {
        System.out.print("Enter maximum fuel usage to filter: ");
        double maxFuel = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("\n-- Vehicles with Fuel Usage <= %.2f --%n", maxFuel);
        boolean found = false;
        for (Vehicle v : VehicleManager.vehicles.values()) {
            if (v.fuelUsage <= maxFuel) {
                System.out.printf("Reg: %s | Type: %s | Fuel Usage: %.2f%n", v.regNumber, v.type, v.fuelUsage);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles found within specified fuel usage.");
        }
    }
}
