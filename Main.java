import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create some rooms
        Room room1 = new Room("Single", Arrays.asList("Breakfast", "WiFi"), 100.0);
        Room room2 = new Room("Double", Arrays.asList("Breakfast", "WiFi", "Hot Tub"), 150.0);
        Room room3 = new Room("Suite", Arrays.asList("Breakfast", "WiFi", "Hot Tub", "Balcony"), 300.0);

        // Create a list of rooms
        List<Room> rooms = Arrays.asList(room1, room2, room3);

        // Create a hotel with the list of rooms
        Hotel hotel = new Hotel("Grand Hotel", rooms.size(), rooms, 5);

        // Display hotel details before applying discount
        System.out.println("Before discount:");
        System.out.println(hotel);

        // Apply a discount to all rooms
        Room.applyDiscountToAllRooms(10); // Apply a 10% discount

        // Display hotel details after applying discount
        System.out.println("After applying 10% discount:");
        System.out.println(hotel);

        // Reset the discount
        Room.resetDiscount();

        // Display hotel details after resetting the discount
        System.out.println("After resetting discount:");
        System.out.println(hotel);
    }
}
