import java.util.*;

public class DriverManager {
    static PriorityQueue<Driver> availableDrivers = new PriorityQueue<>(
            Comparator.comparingInt((Driver d) -> -d.experienceYears));

    static List<Driver> assignedDrivers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n== Driver Assignment Menu ==");
            System.out.println("1. Add Available Driver");
            System.out.println("2. Assign Driver");
            System.out.println("3. Show Available Drivers");
            System.out.println("4. Show Assigned Drivers");
            System.out.println("5. Record Driver Activity");
            System.out.println("6. Show Assigned Drivers and Activity Logs");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDriver();
                    break;
                case 2:
                    assignDriver();
                    break;
                case 3:
                    showAvailableDrivers();
                    break;
                case 4:
                    showAssignedDrivers();
                    break;
                case 5:
                    recordActivity();
                    break;
                case 6:
                    showDriverActivities();
                    break;
                case 7:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addDriver() {
        System.out.print("Enter driver ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter years of experience: ");
        int exp = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter proximity/location: ");
        String loc = scanner.nextLine();

        Driver d = new Driver(id, name, exp, loc);
        availableDrivers.offer(d);
        System.out.println("Driver added to available queue.");
    }

    private static void assignDriver() {
        if (availableDrivers.isEmpty()) {
            System.out.println("No available drivers.");
            return;
        }
        Driver d = availableDrivers.poll();
        assignedDrivers.add(d);
        System.out.println("Assigned Driver: " + d);
    }

    private static void showAvailableDrivers() {
        if (availableDrivers.isEmpty()) {
            System.out.println("No available drivers.");
            return;
        }
        System.out.println("\n-- Available Drivers --");
        for (Driver d : availableDrivers) {
            System.out.println(d);
        }
    }

    private static void showAssignedDrivers() {
        if (assignedDrivers.isEmpty()) {
            System.out.println("No assigned drivers.");
            return;
        }
        System.out.println("\n-- Assigned Drivers --");
        for (Driver d : assignedDrivers) {
            System.out.println(d);
        }
    }

    private static void showDriverActivities() {
        if (assignedDrivers.isEmpty()) {
            System.out.println("No assigned drivers.");
            return;
        }

        System.out.println("\n-- Assigned Drivers and Activity Logs --");
        for (Driver d : assignedDrivers) {
            System.out.println(d); 

            if (!d.assignedRoutes.isEmpty()) {
                System.out.println("   Routes:");
                for (String r : d.assignedRoutes) {
                    System.out.println("      • " + r);
                }
            } else {
                System.out.println("   Routes: None");
            }

            if (!d.delays.isEmpty()) {
                System.out.println("   Delays:");
                for (String delay : d.delays) {
                    System.out.println("      • " + delay);
                }
            } else {
                System.out.println("   Delays: None");
            }

            if (!d.infractions.isEmpty()) {
                System.out.println("   Infractions:");
                for (String infraction : d.infractions) {
                    System.out.println("      • " + infraction);
                }
            } else {
                System.out.println("   Infractions: None");
            }

            System.out.println();
        }
    }

    public static void saveToFile() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("drivers.txt")) {
            for (Driver d : availableDrivers) {
                writer.println(d.driverID + "," + d.name + "," + d.experienceYears + "," + d.proximity + ",available");
            }
            for (Driver d : assignedDrivers) {
                writer.println(d.driverID + "," + d.name + "," + d.experienceYears + "," + d.proximity + ",assigned");
            }
        } catch (Exception e) {
            System.out.println("Error saving drivers to file: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new java.io.File("drivers.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    int exp = Integer.parseInt(parts[2]);
                    String loc = parts[3];
                    String status = parts[4];

                    Driver d = new Driver(id, name, exp, loc);

                    if (status.equalsIgnoreCase("available")) {
                        availableDrivers.offer(d);
                    } else if (status.equalsIgnoreCase("assigned")) {
                        assignedDrivers.add(d);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("(Info) No existing drivers.txt found or error reading it.");
        }
    }

    private static void recordActivity() {
        System.out.print("Enter driver ID: ");
        String id = scanner.nextLine();

        for (Driver d : assignedDrivers) {
            if (d.driverID.equalsIgnoreCase(id)) {
                System.out.println("1. Add Route");
                System.out.println("2. Add Delay");
                System.out.println("3. Add Infraction");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter route: ");
                        d.addRoute(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter delay: ");
                        d.addDelay(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter infraction: ");
                        d.addInfraction(scanner.nextLine());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                System.out.println("Activity recorded.");
                return;
            }
        }
        System.out.println("Driver not found among assigned drivers.");
    }

}
