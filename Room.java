import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Room {
    private String type;
    private List<String> amenities;
    private double price;
    private int numAdults;
    private int numChildren;
    private Map<Date, Date> bookings;

    public Room(String type, List<String> amenities, double price, int numAdults, int numChildren) {
        this.type = type;
        this.amenities = amenities;
        this.price = price;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.bookings = new HashMap<>();
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
        return (numAdults <= getNumAdults() && numChildren <= getNumChildren());
    }

    public boolean isAvailable(Date startDate, Date endDate) {
        for (Map.Entry<Date, Date> booking : bookings.entrySet()) {
            Date bookedStartDate = booking.getKey();
            Date bookedEndDate = booking.getValue();
            if (startDate.before(bookedEndDate) && endDate.after(bookedStartDate)) {
                return false;
            }
        }
        return true;
    }

    public void book(Date startDate, Date endDate) {
        bookings.put(startDate, endDate);
    }

    @Override
    public String toString() {
        return "Room{type='" + type + "', amenities=" + amenities + ", price=" + price + ", numAdults=" + numAdults
                + ", numChildren=" + numChildren + "}";
    }
}
