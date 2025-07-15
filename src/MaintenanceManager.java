import java.util.*;
import java.io.*;

public class MaintenanceManager {
    static PriorityQueue<Vehicle> maintenanceQueue = new PriorityQueue<>(
            Comparator.comparingInt(v -> -(v.mileage - v.lastServiceMileage)) // highest overdue first
    );

    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n== Maintenance Scheduler ==");
            System.out.println("1. Flag Vehicles Needing Maintenance");
            System.out.println("2. Record Maintenance");
            System.out.println("3. Show Maintenance Records");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    flagVehicles();
                    break;
                case 2:
                    recordMaintenance();
                    break;
                case 3:
                    showMaintenanceRecords();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void flagVehicles() {

        maintenanceQueue.clear();

        for (Vehicle v : VehicleManager.vehicles.values()) {
            int mileageSinceService = v.mileage - v.lastServiceMileage;

            if (mileageSinceService >= 10000) {
                maintenanceQueue.offer(v);
            }
        }

        if (maintenanceQueue.isEmpty()) {
            System.out.println("No vehicles currently need maintenance.");
        } else {
            System.out.println("Vehicles needing maintenance flagged:");
            for (Vehicle v : maintenanceQueue) {
                System.out.println("  " + v.regNumber + " (Mileage: " + v.mileage + ")");
            }
        }
    }

    private static void recordMaintenance() {
        System.out.print("Enter Vehicle Registration Number: ");
        String reg = scanner.nextLine();

        Vehicle v = VehicleManager.vehicles.get(reg);
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter Part Replaced: ");
        String part = scanner.nextLine();

        System.out.print("Enter Cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        v.addMaintenance(part, cost);
        System.out.println("Maintenance recorded for " + v.regNumber);
    }

    private static void showMaintenanceRecords() {
        for (Vehicle v : VehicleManager.vehicles.values()) {
            System.out.println("\nVehicle: " + v.regNumber);
            if (v.maintenanceHistory.isEmpty()) {
                System.out.println("  No maintenance records.");
            } else {
                for (Map.Entry<String, Double> entry : v.maintenanceHistory.entrySet()) {
                    System.out.printf("  Part: %s | Cost: %.2f%n", entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter("maintenance.txt")) {
            for (Vehicle v : VehicleManager.vehicles.values()) {
                for (Map.Entry<String, Double> entry : v.maintenanceHistory.entrySet()) {
                    writer.printf("%s,%s,%.2f,%d%n",
                            v.regNumber, entry.getKey(), entry.getValue(), v.lastServiceMileage);
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving maintenance records: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File("maintenance.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String reg = parts[0];
                    String part = parts[1];
                    double cost = Double.parseDouble(parts[2]);
                    int lastServiceMileage = Integer.parseInt(parts[3]);

                    Vehicle v = VehicleManager.vehicles.get(reg);
                    if (v != null) {
                        v.addMaintenance(part, cost);
                        v.lastServiceMileage = lastServiceMileage;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("(Info) No maintenance.txt found.");
        }
    }
}
