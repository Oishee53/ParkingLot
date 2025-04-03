import java.io.*;
import java.util.*;

public class CSVManager {
    private static final String FILE_PATH = "parking_lot.csv";


    public static List<Slot> loadParkingLot() throws IOException {
        List<Slot> slots = new ArrayList<>();
        File file = new File(FILE_PATH);


        if (!file.exists()) {
            return slots;
        }

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            int slotNumber = Integer.parseInt(data[0]);
            String registrationNumber = data[1].equals(" ") ? "" : data[1];
            String color = data[2].equals(" ") ? "" : data[2];
            boolean isOccupied = Boolean.parseBoolean(data[3]);
            slots.add(new Slot(slotNumber, registrationNumber, color, isOccupied));
        }
        reader.close();
        return slots;
    }


    public static void saveParkingLot(List<Slot> slots) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write("slot_number,registration_number,color,occupied\n");
        for (Slot slot : slots) {
            writer.write(slot.getSlotNumber() + "," +
                    (slot.getCar() != null ? slot.getCar().getRegistrationNumber() : "") + "," +
                    (slot.getCar() != null ? slot.getCar().getColor() : "") + "," +
                    slot.isOccupied() + "\n");
        }
        writer.close();
    }


    public static void initializeParkingLot(int n) throws IOException {
        List<Slot> slots = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            slots.add(new Slot(i, "", "", false)); // Empty slots initially
        }
        saveParkingLot(slots);
    }
}
