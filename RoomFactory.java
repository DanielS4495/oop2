import java.util.Arrays;
import java.util.List;

public class RoomFactory {

    public static Room createRoom(RoomType type, View view, int numAdults, int numChildren) {
        try {
            if (numAdults < 1 || numChildren < 0) {
                throw new IllegalArgumentException("Invalid number of guests");
            }

            List<String> features = getFeatures(type, view);
            return new Room(type.toString(), features, numAdults, numChildren);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static List<String> getFeatures(RoomType type, View view) {
        switch (type) {
            case SINGLE:
                return Arrays.asList("Bed", "Bathroom with shower", "Towels", "Toiletries", "Hairdryer", view.toString());
            case DOUBLE:
                return Arrays.asList("2 Beds", "Bathroom with shower/tub", "Towels", "Toiletries", "Hairdryer", "Mini fridge", view.toString());
            case KING:
                return Arrays.asList("King Bed", "Bathroom with shower/tub", "Luxury bathrobes", "Slippers", "Coffee maker", "Bottled water", view.toString());
            case TWIN:
                return Arrays.asList("2 Twin Beds", "Bathroom with shower", "Towels", "Toiletries", "Hairdryer", view.toString());
            case SUITE:
                return Arrays.asList("Separate bedroom(s)", "Living Area", "Bathroom with shower/tub", "Towels", "Toiletries", "Hairdryer", "Sofa bed (if applicable)", "Coffee maker", "Microwave", view.toString());
            case STUDIO:
                return Arrays.asList("Sleeping area with convertible bed", "Living Area", "Kitchenette with stovetop and mini fridge", "Bathroom with shower", "Dishes", "Coffee maker", view.toString());
            case ACCESSIBLE:
                return Arrays.asList("Accessible Bed", "Bathroom with grab bars and roll-in shower", "Shower seat", "Handheld showerhead", "Lowered sink", "Emergency call button", view.toString());
            default:
                throw new IllegalArgumentException("Unknown room type");
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
