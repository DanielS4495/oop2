
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private final Manager manager;
    private final String name;
    private final String location;
    private final String description;
    private List<String> amenities;
    private List<Room> rooms;
    private Map<Date, List<Room>> availability;
    private List<Review> reviews;

    public Hotel(Manager manager, String name, String location, String description) {
        this.manager = manager;
        this.name = name;
        this.location = location;
        this.description = description;
        this.amenities = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.availability = new HashMap<>();
        this.reviews = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Map<Date, List<Room>> getAvailability() {
        return availability;
    }
    public int getRating() {
       int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return (sum / reviews.size());
    } 
    public void addAmenity(String amenity, Manager manager) {
        if (this.manager == manager) {
            amenities.add(amenity);
        }
    }

    public void addRoom(Room room, Manager manager) {
        if (this.manager == manager) {
            rooms.add(room);
        }
    }

    public void addAvailability(Date date, Boolean isAvailable, Manager manager) {
        if (this.manager == manager) {
            availability.put(date, isAvailable);
        }
    }

    public void removeRoom(Room room, Manager manager) {
        if (this.manager == manager) {
            rooms.remove(room);
        }
    }

    public void removeAvailability(Date date, Manager manager) {
        if (this.manager == manager) {
            availability.remove(date);
        }
    }

    public void removeAmenities(String amenity, Manager manager) {
        if (this.manager == manager) {
            amenities.remove(amenity);
        }
    }

    public void removeAvailability(Date date, Boolean isAvailable, Manager manager) {
        if (this.manager == manager) {
            availability.remove(date, isAvailable);
        }
    }

    public void markRoomAvailable(Date checkInDate, Date checkOutDate, Manager manager) {
        if (this.manager == manager) {
            for (Room room : rooms) {
                if (room.isAvailable(checkInDate, checkOutDate)) {
                    room.setAvailability(true);
                }
            }
        }
    }

    public void markRoomUnavailable(Date checkInDate, Date checkOutDate, Manager manager) {
        if (this.manager == manager) {
        }
    
}
}