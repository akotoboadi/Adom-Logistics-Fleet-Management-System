import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🚚 Welcome to Adom Logistics Fleet Management System 🚚");

        VehicleManager.loadFromFile();
        DriverManager.loadFromFile();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Vehicle Database");
            System.out.println("2. Driver Assignment");
            System.out.println("3. Delivery Tracking");
            System.out.println("4. Maintenance Scheduler");
            System.out.println("5. Fuel Efficiency Reports");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    VehicleManager.menu();
                    break;
                case 2:
                    DriverManager.menu();
                    break;
                case 3:
                    DeliveryManager.menu();
                    break;
                case 4:
                    MaintenanceManager.menu();
                    break;
                case 5:
                    FuelReportGenerator.generateReport();
                    break;
                case 6:
                    VehicleManager.saveToFile();
                    DriverManager.saveToFile();
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }
}
