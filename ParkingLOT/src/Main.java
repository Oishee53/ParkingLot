import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        createCSVFile("parking_lot.csv");

        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to the Automated Parking Lot System!");


        System.out.println("Enter the number of slots in the parking lot: ");
        int numSlots = scanner.nextInt();
        scanner.nextLine();


        ParkingLot parkingLot;
        List<Slot> slots = CSVManager.loadParkingLot();


        if (slots.isEmpty()) {
            CSVManager.initializeParkingLot(numSlots);
            parkingLot = new ParkingLot(numSlots);
            System.out.println("Created a parking lot with " + numSlots + " slots");
        } else {
            parkingLot = new ParkingLot(numSlots);
        }

        while (true) {
            System.out.println("\nEnter command: ");
            String command = scanner.nextLine();

            if (command.startsWith("create_parking_lot")) {
                String[] parts = command.split(" ");
                int n = Integer.parseInt(parts[1]);
                CSVManager.initializeParkingLot(n);
                parkingLot = new ParkingLot(n);
                System.out.println("Created a parking lot with " + n + " slots");
            } else if (command.startsWith("park")) {
                String[] parts = command.split(" ");
                String regNo = parts[1];
                String color = parts[2];
                System.out.println(parkingLot.parkCar(regNo, color));
            } else if (command.startsWith("leave")) {
                String[] parts = command.split(" ");
                int slotNumber = Integer.parseInt(parts[1]);
                System.out.println(parkingLot.leaveSlot(slotNumber));
            } else if (command.equals("status")) {
                parkingLot.status();
            } else if (command.startsWith("registration_numbers_for_cars_with_colour")) {
                String[] parts = command.split(" ");
                String color = parts[1];
                System.out.println(parkingLot.registrationNumbersForCarsWithColor(color));
            } else if (command.startsWith("slot_number_for_registration_number")) {
                String[] parts = command.split(" ");
                String regNo = parts[1];
                System.out.println(parkingLot.slotNumberForRegistrationNumber(regNo));
            } else if (command.startsWith("slot_numbers_for_cars_with_colour")) {
                String[] parts = command.split(" ");
                String color = parts[1];
                System.out.println(parkingLot.slotNumbersForCarsWithColor(color));
            } else if (command.equals("exit")) {
                System.out.println("Exiting");
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
        scanner.close();
    }
    private static void createCSVFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("CSV file created: " + file.getName());
            } else {
                // System.out.println("CSV file already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating: " + fileName);
            e.printStackTrace();
        }
    }
}
