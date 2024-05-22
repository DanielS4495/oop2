import java.util.*;

public class Hotel {
    private String name;
    private int numRooms;
    private List<Room> rooms;
    private Map<Date, Boolean> availability; // Calendar mapping to room availability
    private int starRanking;

    public Hotel(String name, int numRooms, List<Room> rooms, int starRanking) {
        this.name = name;
        this.numRooms = numRooms;
        this.rooms = rooms;
        this.starRanking = starRanking;
        this.availability = new HashMap<>();
        initializeAvailability();
    }

    private void initializeAvailability() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        for (int i = 0; i < 365; i++) {
            availability.put(calendar.getTime(), true);
            calendar.add(Calendar.DATE, 1);
        }
    }

    public String getName() {
        return name;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Map<Date, Boolean> getAvailability() {
        return availability;
    }

    public int getStarRanking() {
        return starRanking;
    }

    public void setAvailability(Date date, boolean available) {
        availability.put(date, available);
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "', starRanking=" + starRanking + '}';
    }
}
