import java.util.List;

public class View {
    public void displayMenu() {
        System.out.println("1. Search Hotels");
        System.out.println("2. Search Room in specific hotel"); // need this?
        System.out.println("3. Book Hotel");
        System.out.println("4. Exit");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayHotels(List<Hotel> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            System.out.println("Search Results:");
            for (Hotel hotel : hotels) {
                System.out.println(hotel);
            }
        }
    }

    public void displayRooms(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }

    public void displayReservation(Reservation reservation) {
        System.out.println("Reservation successful:");
        System.out.println(reservation);
    }
}
