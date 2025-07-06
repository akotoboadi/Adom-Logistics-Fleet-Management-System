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
            System.out.println("3. Search Vehicle by Registration Number");
            System.out.println("4. Display All Vehicles (Sorted by Mileage)");
            System.out.println("5. Remove Vehicle by Mileage");
            System.out.println("6. Back to Main Menu");
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
                    searchByReg();
                    break;
                case 4:
                    displayVehicles();
                    break;
                case 5:
                    removeVehicle();
                    break;
                case 6:
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

    private static void removeVehicle() {
        System.out.print("Enter mileage of vehicle to remove: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();

        root = remove(root, mileage);
        System.out.println("Vehicle removed (if it existed).");
    }

    private static VehicleNode remove(VehicleNode node, int mileage) {
        if (node == null)
            return null;

        if (mileage < node.vehicle.mileage) {
            node.left = remove(node.left, mileage);
        } else if (mileage > node.vehicle.mileage) {
            node.right = remove(node.right, mileage);
        } else {
            // Node found
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            // Node with 2 children: find inorder successor
            VehicleNode successor = minValueNode(node.right);
            node.vehicle = successor.vehicle;
            node.right = remove(node.right, successor.vehicle.mileage);
        }
        return node;
    }

    private static VehicleNode minValueNode(VehicleNode node) {
        VehicleNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private static void searchByReg() {
        System.out.print("Enter registration number to search: ");
        String reg = scanner.nextLine();

        Vehicle found = searchByReg(root, reg);
        if (found != null) {
            System.out.println("Vehicle found: " + found);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private static Vehicle searchByReg(VehicleNode node, String reg) {
        if (node == null)
            return null;

        if (node.vehicle.regNumber.equalsIgnoreCase(reg)) {
            return node.vehicle;
        }

        Vehicle leftResult = searchByReg(node.left, reg);
        if (leftResult != null)
            return leftResult;

        return searchByReg(node.right, reg);
    }

    public static void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new java.io.File("vehicles.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String reg = parts[0];
                    String type = parts[1];
                    int mileage = Integer.parseInt(parts[2]);
                    double fuel = Double.parseDouble(parts[3]);
                    String driverID = parts[4];

                    Vehicle vehicle = new Vehicle(reg, type, mileage, fuel, driverID);
                    root = insert(root, vehicle);
                }
            }
        } catch (Exception e) {
            System.out.println("(Info) No existing vehicles.txt found or error reading it.");
        }
    }

    public static void saveToFile() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("vehicles.txt")) {
            saveInOrder(root, writer);
        } catch (Exception e) {
            System.out.println("Error saving vehicles to file: " + e.getMessage());
        }
    }

    private static void saveInOrder(VehicleNode node, java.io.PrintWriter writer) {
        if (node != null) {
            saveInOrder(node.left, writer);
            Vehicle v = node.vehicle;
            writer.println(v.regNumber + "," + v.type + "," + v.mileage + "," + v.fuelUsage + "," + v.driverID);
            saveInOrder(node.right, writer);
        }
    }

}
