
import java.util.Arrays;

public class RoomFactory {

    public static Room createRoom(RoomType type, View view, int numAdults, int numChildren) {
        try {
            if (numAdults < 1 || numChildren < 0) {
                throw new IllegalArgumentException("Invalid number of guests");
            } else {
                switch (type.toString().toLowerCase()) {
                    case "single":
                        return new Room(type.toString(), Arrays.asList("Bed", "Bathroom with shower", "Towels", "Toiletries", "Hairdryer", view.toString()), numAdults, numChildren);
                    case "double":
                        return new Room(type.toString(), Arrays.asList("2 Beds", "Bathroom with shower/tub", "Towels", "Toiletries", "Hairdryer", "Mini fridge", view.toString()), numAdults, numChildren);
                    case "king":
                        return new Room(type.toString(), Arrays.asList("King Bed", "Bathroom with shower/tub", "Luxury bathrobes", "Slippers", "Coffee maker", "Bottled water", view.toString()), numAdults, numChildren);
                    case "twin":
                        return new Room(type.toString(), Arrays.asList("2 Twin Beds", "Bathroom with shower", "Towels", "Toiletries", "Hairdryer", view.toString()), numAdults, numChildren);
                    case "suite":
                        return new Room(type.toString(), Arrays.asList("Separate bedroom(s)", "Living Area", "Bathroom with shower/tub", "Towels", "Toiletries", "Hairdryer", "Sofa bed (if applicable)", "Coffee maker", "Microwave", view.toString()), numAdults, numChildren);
                    case "studio":
                        return new Room(type.toString(), Arrays.asList("Sleeping area with convertible bed", "Living Area", "Kitchenette with stovetop and mini fridge", "Bathroom with shower", "Dishes", "Coffee maker", view.toString()), numAdults, numChildren);
                    case "accessible":
                        return new Room(type.toString(), Arrays.asList("Accessible Bed", "Bathroom with grab bars and roll-in shower", "Shower seat", "Handheld showerhead", "Lowered sink", "Emergency call button", view.toString()), numAdults, numChildren);
                    default:
                        throw new IllegalArgumentException("Unknown room type");
                }
            }
          
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public enum View {
        CITY,
        SEA
    }
    public enum RoomType {
        SINGLE,
        DOUBLE,
        KING,
        TWIN,
        SUITE,
        STUDIO,
        ACCESSIBLE
    }
}
