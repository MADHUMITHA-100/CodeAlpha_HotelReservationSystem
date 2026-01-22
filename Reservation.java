public class Reservation {

    int reservationId;
    String customerName;
    int roomNumber;
    int days;
    double totalAmount;

    // Constructor
    public Reservation(int reservationId, String customerName,
                       int roomNumber, int days, double totalAmount) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.days = days;
        this.totalAmount = totalAmount;
    }
}

