import java.util.List;
import java.util.Map;
import java.util.Date;


public class Hotel {
    private String name;
    private String location;
    private int starRating;
    private List<Room> rooms;
    private Map<Date, Boolean> availability;

    public Hotel(String name, String location, List<Room> rooms, int starRating) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.starRating = starRating;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getStarRating() {
        return starRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Map<Date, Boolean> getAvailability() {
        return availability;
    }

    public void setAvailability(Date date, Boolean isAvailable) {
        availability.put(date, isAvailable);
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "', location='" + location + "', starRating=" + starRating + ", rooms=" + rooms.size() + "}";
    }
}
