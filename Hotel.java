
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {

    private static long count = 0;
    private long hotelId = 0;
    private final Manager manager;
    private final String name;
    private final String location;
    private final String description;
    private List<String> amenities;
    private List<Room> rooms;
    // private Map<Date, List<Room>> availability;
    private List<User> subscribers;
    private List<Review> reviews;

    public Hotel(Manager manager, String name, String location, List<Room> rooms, String description) {
        this.hotelId = count++;
        this.manager = manager;
        this.name = name;
        this.location = location;
        this.description = description;
        this.amenities = new ArrayList<>();
        this.rooms = rooms;
        // this.availability = new HashMap<>();
        this.reviews = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public long getHotelId() {
        return hotelId;
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
    public Room getRoom(long roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }
    public Manager getManager() {
        return manager;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public int getRating() {
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        int all = sum / reviews.size();
        if (all < 1) {
            return 1;
        }
        return all;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addSubscribe(User user) {
        subscribers.add(user);
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

    // public void addAvailability(Date date, Boolean isAvailable, Manager manager) {
    //     if (this.manager == manager) {
    //         availability.put(date, isAvailable);
    //     }
    // }
    public void removeRoom(Room room, Manager manager) {
        if (this.manager == manager) {
            rooms.remove(room);
        }
    }

    public void removeSubscribe(User user) {
        subscribers.remove(user);
    }

    // public void removeAvailability(Date date, Manager manager) {
    //     if (this.manager == manager) {
    //         availability.remove(date);
    //     }
    // }
    public void removeAmenities(String amenity, Manager manager) {
        if (this.manager == manager) {
            amenities.remove(amenity);
        }
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // public void removeAvailability(Date date, Boolean isAvailable, Manager manager) {
    //     if (this.manager == manager) {
    //         availability.remove(date, isAvailable);
    //     }
    // }
    //do i need this?
    // public void markRoomAvailable(Date checkInDate, Date checkOutDate, Manager manager) {
    //     if (this.manager == manager) {
    //         for (Room room : rooms) {
    //             if (room.isAvailable(checkInDate, checkOutDate)) {
    //                 room.setAvailability(true);
    //             }
    //         }
    //     }
    // }
    //do i need this?
    // public void markRoomUnavailable(Date checkInDate, Date checkOutDate, Manager manager) {
    //     if (this.manager == manager) {
    //     }
    // }
    //what do i need to check?
    public void cancelation(Reservation res) {
        res.getRoom().cancelation(res.getCheckInDate(), res.getCheckOutDate());
        // res.setStatus(ReservationStatus.CANCELED);
    }
    public void sendNotification(String message) {
        for (User user : subscribers) {
            user.sendNotification(message);
        }
    }   
}
