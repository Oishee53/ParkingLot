public class Slot {
    private int slotNumber;
    private Car car;
    private boolean isOccupied;

    public Slot(int slotNumber, String registrationNumber, String color, boolean isOccupied) {
        this.slotNumber = slotNumber;
        this.isOccupied = isOccupied;
        if (isOccupied) {
            this.car = new Car(registrationNumber, color);
        }
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void parkCar(Car car) {
        this.car = car;
        this.isOccupied = true;
    }

    public void leave() {
        this.car = null;
        this.isOccupied = false;
    }

    public Car getCar() {
        return car;
    }
}
