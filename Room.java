import java.util.List;

public class Room {
    private String type;
    private List<String> amenities;
    private double price;
    private int numAdults;
    private int numChildren;

    public Room(String type, List<String> amenities, double price, int numAdults, int numChildren) {
        this.type = type;
        this.amenities = amenities;
        this.price = price;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
    }

    public String getType() {
        return type;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public double getPrice() {
        return price;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public boolean isSuitableForOccupancy(int numAdults, int numChildren) {
        return (numAdults<=getNumAdults() && numChildren<=getNumChildren());
    }

    @Override
    public String toString() {
        return "Room{type='" + type + "', amenities=" + amenities + ", price=" + price + ", numAdults=" + numAdults
                + ", numChildren=" + numChildren + "}";
    }
}
