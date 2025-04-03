import java.io.IOException;
import java.util.*;

public class ParkingLot {
    private List<Slot> slots;

    public ParkingLot(int n) throws IOException {
        this.slots = CSVManager.loadParkingLot();
        if (slots.isEmpty()) {
            for (int i = 1; i <= n; i++) {
                slots.add(new Slot(i, "", "", false));
            }
            CSVManager.saveParkingLot(slots);
        }
    }


    public String parkCar(String registrationNumber, String color) throws IOException {
        for (Slot slot : slots) {
            if (!slot.isOccupied()) {
                slot.parkCar(new Car(registrationNumber, color));
                CSVManager.saveParkingLot(slots);
                return "Allocated slot number: " + slot.getSlotNumber();
            }
        }
        return "Sorry, parking lot is full";
    }

    // Method to remove a car and free the slot
    public String leaveSlot(int slotNumber) throws IOException {
        Slot slot = slots.get(slotNumber - 1);
        if (slot.isOccupied()) {
            slot.leave();
            CSVManager.saveParkingLot(slots);
            return "Slot number " + slotNumber + " is free";
        }
        return "Slot number " + slotNumber + " is already free";
    }

    // Method to print the current status
    public void status() {
        System.out.println("Slot No. | Registration No. | Colour");
        for (Slot slot : slots) {
            if (slot.isOccupied()) {
                System.out.println(slot.getSlotNumber() + " | " + slot.getCar().getRegistrationNumber() + " | " + slot.getCar().getColor());
            }
        }
    }


    public String registrationNumbersForCarsWithColor(String color) {
        StringBuilder result = new StringBuilder();
        for (Slot slot : slots) {
            if (slot.isOccupied() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                if (result.length() > 0) result.append(", ");
                result.append(slot.getCar().getRegistrationNumber());
            }
        }
        return result.length() > 0 ? result.toString() : "Not found";
    }


    public String slotNumberForRegistrationNumber(String regNo) {
        for (Slot slot : slots) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equals(regNo)) {
                return String.valueOf(slot.getSlotNumber());
            }
        }
        return "Not found";
    }


    public String slotNumbersForCarsWithColor(String color) {
        StringBuilder result = new StringBuilder();
        for (Slot slot : slots) {
            if (slot.isOccupied() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                if (result.length() > 0) result.append(", ");
                result.append(slot.getSlotNumber());
            }
        }
        return result.length() > 0 ? result.toString() : "Not found";
    }
}
