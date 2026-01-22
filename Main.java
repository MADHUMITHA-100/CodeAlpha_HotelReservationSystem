import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> hotel.showAvailableRooms();
                case 2 -> hotel.bookRoom();
                case 3 -> hotel.cancelReservation();
                case 4 -> hotel.viewReservations();
                case 5 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
