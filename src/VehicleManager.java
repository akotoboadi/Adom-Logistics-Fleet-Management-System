import java.util.Scanner;

public class VehicleManager {
    static VehicleNode root = null;
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n== Vehicle Database Menu ==");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Search Vehicle by Mileage");
            System.out.println("3. Display All Vehicles (Sorted by Mileage)");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    searchVehicle();
                    break;
                case 3:
                    displayVehicles();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addVehicle() {
        System.out.print("Enter registration number: ");
        String reg = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter mileage: ");
        int mileage = scanner.nextInt();
        System.out.print("Enter fuel usage: ");
        double fuel = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter driver ID: ");
        String driverID = scanner.nextLine();

        Vehicle vehicle = new Vehicle(reg, type, mileage, fuel, driverID);
        root = insert(root, vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private static VehicleNode insert(VehicleNode node, Vehicle vehicle) {
        if (node == null) {
            return new VehicleNode(vehicle);
        }
        if (vehicle.mileage < node.vehicle.mileage) {
            node.left = insert(node.left, vehicle);
        } else {
            node.right = insert(node.right, vehicle);
        }
        return node;
    }

    private static void searchVehicle() {
        System.out.print("Enter mileage to search: ");
        int mileage = scanner.nextInt();
        VehicleNode result = search(root, mileage);
        if (result != null) {
            System.out.println("Vehicle found: " + result.vehicle);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private static VehicleNode search(VehicleNode node, int mileage) {
        if (node == null || node.vehicle.mileage == mileage) {
            return node;
        }
        if (mileage < node.vehicle.mileage) {
            return search(node.left, mileage);
        } else {
            return search(node.right, mileage);
        }
    }

    private static void displayVehicles() {
        System.out.println("\n-- All Vehicles (Sorted by Mileage) --");
        inOrderTraversal(root);
    }

    private static void inOrderTraversal(VehicleNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.vehicle);
            inOrderTraversal(node.right);
        }
    }
}
