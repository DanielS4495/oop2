import java.util.Arrays;

public class RoomFactory {
    public static Room createRoom(String type) {
        switch (type.toLowerCase()) {
            case "single":
                return new Room("Single", Arrays.asList("Bed", "Bathroom"), 50.0, 1, 0);
            case "double":
                return new Room("Double", Arrays.asList("2 Beds", "Bathroom"), 90.0, 2, 0);
            // Add more room types
            default:
                throw new IllegalArgumentException("Unknown room type");
        }
    }
}
