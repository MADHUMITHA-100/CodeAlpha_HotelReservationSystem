import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Hotel {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    int reservationCounter = 1;

    public Hotel() {
        addRooms();
         loadFromFile();
    }

    void addRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
    }

    void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (r.isAvailable) {
                System.out.println(r.roomNumber + " | " + r.category + " | Rs." + r.price);
            }
        }
    }

    void bookRoom() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        System.out.print("Enter days: ");
        int days = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && r.isAvailable) {
                double amount = r.price * days;
                r.isAvailable = false;

                Reservation res = new Reservation(
                        reservationCounter++, name, roomNo, days, amount);
                reservations.add(res);
saveToFile();
                System.out.println("Total Amount: ₹" + amount);
                System.out.println("Payment Successful!");
                System.out.println("Booking ID: " + res.reservationId);
                return;
            }
        }
        System.out.println("Room not available!");
    }

    void cancelReservation() {
    System.out.print("Enter reservation ID: ");
    int id = sc.nextInt();

    for (int i = 0; i < reservations.size(); i++) {
        Reservation r = reservations.get(i);

        if (r.reservationId == id) {

            // Make the room available again
            for (Room room : rooms) {
                if (room.roomNumber == r.roomNumber) {
                    room.isAvailable = true;
                    break;
                }
            }

            // Remove reservation safely
            reservations.remove(i);
saveToFile();
            System.out.println("Reservation cancelled.");
            return;
        }
    }

    System.out.println("Reservation not found!");
}
void viewReservations() {
    if (reservations.isEmpty()) {
        System.out.println("No reservations found.");
        return;
    }

    System.out.println("\nBooking Details:");
    for (Reservation r : reservations) {
        System.out.println(
            "ID: " + r.reservationId +
            " | Name: " + r.customerName +
            " | Room: " + r.roomNumber +
            " | Days: " + r.days +
            " | Amount: Rs." + r.totalAmount
        );
    }
}
void saveToFile() {
    try (PrintWriter pw = new PrintWriter(new FileWriter("reservations.txt"))) {
        for (Reservation r : reservations) {
            pw.println(
                r.reservationId + "," +
                r.customerName + "," +
                r.roomNumber + "," +
                r.days + "," +
                r.totalAmount
            );
        }
    } catch (IOException e) {
        System.out.println("Error saving reservations.");
    }
}
void loadFromFile() {
    File file = new File("reservations.txt");
    if (!file.exists()) return;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            Reservation r = new Reservation(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3]),
                Double.parseDouble(data[4])
            );
            reservations.add(r);
            reservationCounter++;
        }
    } catch (IOException e) {
        System.out.println("Error loading reservations.");
    }
}


}

