public class Room {

    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    // Constructor
    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true; // room is free initially
    }
}
