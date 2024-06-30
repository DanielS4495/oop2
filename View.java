
import java.util.List;

public class View {

    public void displayLogin() {
        System.out.println("1. Id");
        System.out.println("2. Name");
        System.out.println("3. Email");
        System.out.println("4. Phone");
        System.out.println("0. Exit");
    }

    public void displayMenuNotification() {
        System.out.println("1. SMS");
        System.out.println("2. Email");
        System.out.println("3. Whatsapp");
        System.out.println("0. Exit");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySearchHotels(List<Hotel> hotels) {
        if (hotels == null || hotels.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            System.out.println("Search Results:");
            for (Hotel hotel : hotels) {
                if (!hotel.getFilteredRooms().isEmpty()) {
                    System.out.println(hotel.toString());
                    displaySearchRooms(hotel.getFilteredRooms());
                }
            }
        }
    }

    public void displayHotelsManager(List<Hotel> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("Dont have any hotels.");
        } else {
            System.out.println("\nMy Hotel:");
            for (Hotel hotel : hotels) {
                System.out.println(hotel.toString());
            }
        }
    }

    public void displaySearchRooms(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.println("\nAvailable Rooms:");
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }

    public void displayRoomsManager(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found in this hotel.");
        } else {
            System.out.println("All the rooms in this hotel:");
            for (Room room : rooms) {
                System.out.println(room.toString());
            }
        }
    }

    public void displayReservationsManager(List<Reservation> reservation) {
        if (reservation.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Reservations:");
            for (Reservation res : reservation) {
                System.out.println(res.toString());
            }
        }
    }

    public void displayWishlist(List<Reservation> wishlist) {
        if (wishlist.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            System.out.println("Wishlist:");
            for (Reservation reservation : wishlist) {
                System.out.println(reservation.toString());
            }
        }
    }

    public void displayMenuLogIn() {
        System.out.println("\n1. new user/manager");
        System.out.println("2. log in");
        System.out.println("3. forgot username");
        System.out.println("4. forgot password");
        System.out.println("0. Exit");
    }

    public void displayMenuUser() {
        System.out.println("\n1. View Profile");
        System.out.println("2. Search Hotels");
        System.out.println("3. Search specific Room by id");
        System.out.println("4. View Wishlist");
        System.out.println("5. Remove from Wishlist");
        System.out.println("6. View Past Orders");
        System.out.println("7. Make a Reservation");
        System.out.println("8. Pay for a Reservation");
        System.out.println("9. Cancel a Reservation");
        System.out.println("10. View My Notifications Type");
        System.out.println("11. Add Notification Type");
        System.out.println("12. Remove Notification Type");
        System.out.println("0. Logout");
    }

    public void displayMenuManager() {
        System.out.println("\n1. View Profile");
        System.out.println("2. create Hotel");
        System.out.println("3. create Room");
        System.out.println("4. send Notification to all user in specific hotel");
        System.out.println("5. View All Hotels");
        System.out.println("6. View All Rooms in Hotel");
        System.out.println("7. View All Reservations in Hotel");
        System.out.println("8. View My Notifications Type");
        System.out.println("9. Add Notification");
        System.out.println("10. Remove Notification");
        System.out.println("0. Logout");
    }

    public void displayMenuSort() {
        System.out.println("1. Sort by Price");
        System.out.println("2. Sort by Rating");
        System.out.println("3. Sort by Rating and Price");
        System.out.println("4. Sort by Size of Room");
        System.out.println("5. Sort by Top reviews");
        System.out.println("0. Exit");
    }

    public void displayMenuFilter() {
        System.out.println("1. Filter by Amenities to hotel");
        System.out.println("2. Filter by Amenities to room");
        System.out.println("3. Filter by Date");
        System.out.println("4. Filter by Location");
        System.out.println("5. Filter by Price");
        System.out.println("6. Filter by Rating");
        System.out.println("0. Exit");
    }

    public void displayRoomFactory() {
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Suite Room");
        System.out.println("4. Studio Room");
        System.out.println("5. King Room");
        System.out.println("6. Twin Room");
        System.out.println("7. Accessible Room");
    }

    public void displayRoomFactoryView() {
        System.out.println("1. City View");
        System.out.println("2. Sea View");
    }
}
