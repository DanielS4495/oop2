import java.util.List;
import java.util.Map;
import java.util.Date;


public class Hotel {
    private String name;
    private String location;
    private int starRanking;
    private List<Room> rooms;
    private Map<Date, Boolean> availability;

    public Hotel(String name, String location, List<Room> rooms, int starRanking) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.starRanking = starRanking;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getStarRanking() {
        return starRanking;
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
        return "Hotel{name='" + name + "', location='" + location + "', starRanking=" + starRanking + ", rooms=" + rooms.size() + "}";
    }
}
