import java.util.List;

public class Room {
    private String type; // e.g., single, double, suite
    private List<String> amenities; // e.g., breakfast, hot tub, etc.
    private double price;
    private static double discount = 0;

    public Room(String type, List<String> amenities, double price) {
        this.type = type;
        this.amenities = amenities;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public double getPrice() {
        if(discount!=0)
        return  this.price * (1 - discount / 100);
        else return this.price;
    }

    public static void applyDiscountToAllRooms(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        discount = percentage;
    }

    public static void resetDiscount() {
        discount = 0;
    }

    @Override
    public String toString() {
        return "Room{type='" + type + "', amenities=" + amenities + ", price=" + price + '}';
    }
}
