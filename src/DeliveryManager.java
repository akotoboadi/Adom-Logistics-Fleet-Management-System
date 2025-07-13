import java.util.*;
import java.io.*;

public class DeliveryManager {
    static Queue<Delivery> deliveries = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n== Delivery Tracking Menu ==");
            System.out.println("1. Add Delivery");
            System.out.println("2. Show All Deliveries");
            System.out.println("3. Update/Reroute Delivery");
            System.out.println("4. Update Delivery Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDelivery();
                    break;
                case 2:
                    showDeliveries();
                    break;
                case 3:
                    updateDelivery();
                    break;
                case 4:
                    updateDeliveryStatus();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addDelivery() {
        System.out.print("Enter Package ID: ");
        String pkgID = scanner.nextLine();

        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Assigned Vehicle ID: ");
        String vehicleID = scanner.nextLine();

        System.out.print("Enter Assigned Driver ID: ");
        String driverID = scanner.nextLine();

        System.out.print("Enter ETA: ");
        String eta = scanner.nextLine();

        Delivery d = new Delivery(pkgID, origin, destination, vehicleID, driverID, eta);
        deliveries.offer(d);

        System.out.println("Delivery added.");
    }

    private static void showDeliveries() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries.");
            return;
        }

        System.out.println("\n-- Deliveries --");
        for (Delivery d : deliveries) {
            System.out.println(d);
        }
    }

    private static void updateDelivery() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries to update.");
            return;
        }

        System.out.print("Enter Package ID to update: ");
        String pkgID = scanner.nextLine();

        boolean found = false;
        for (Delivery d : deliveries) {
            if (d.packageID.equalsIgnoreCase(pkgID)) {
                System.out.println("Current Status: " + (d.status == null ? "Pending" : d.status));

                if ("Delivered".equalsIgnoreCase(d.status)) {
                    System.out.println("This package has already been delivered. Cannot update.");
                    return;
                }

                System.out.print("Enter new Destination: ");
                d.destination = scanner.nextLine();

                System.out.print("Enter new ETA: ");
                d.eta = scanner.nextLine();

                System.out.print("Update status (Pending/In Transit/Delivered): ");
                String newStatus = scanner.nextLine();
                if (!newStatus.trim().isEmpty()) {
                    d.status = newStatus;
                }

                System.out.println("Delivery updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Delivery not found.");
        }
    }

    private static void updateDeliveryStatus() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries.");
            return;
        }

        System.out.print("Enter Package ID to update status: ");
        String pkgID = scanner.nextLine();

        boolean found = false;
        for (Delivery d : deliveries) {
            if (d.packageID.equalsIgnoreCase(pkgID)) {
                System.out.println("Current Status: " + d.status);
                System.out.print("Enter new Status (Pending/In Transit/Delivered/Returned): ");
                String newStatus = scanner.nextLine();
                d.status = newStatus;
                System.out.println("Status updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Delivery not found.");
        }
    }

    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter("deliveries.txt")) {
            for (Delivery d : deliveries) {
                writer.println(d.packageID + "," + d.origin + "," + d.destination + "," +
                        d.assignedVehicleID + "," + d.assignedDriverID + "," + d.eta);
            }
        } catch (Exception e) {
            System.out.println("Error saving deliveries: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File("deliveries.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Delivery d = new Delivery(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    deliveries.offer(d);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("(Info) No existing deliveries.txt found.");
        }
    }
}
