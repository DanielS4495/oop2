
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private static long count = 0;
    private long hotelId = 0;
    private final Manager manager;
    private final String name;
    private final String location;
    private final String description;
    private List<String> amenities;
    private List<Room> rooms;//all the rooms
    private List<Room> filterRooms;//for filter
    private List<User> subscribers;
    private List<Review> reviews;
    private List<Reservation> reservations;

    public Hotel(Manager manager, String name, String location, String description) {
        this.hotelId = count++;
        this.manager = manager;
        this.name = name;
        this.location = location;
        this.description = description;
        this.amenities = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.filterRooms = rooms;
        this.reviews = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.reservations = new ArrayList<>();
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

    public List<User> getSubscribers() {
        return subscribers;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
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

    public void setAmenities(List<String> amenities, Manager manager) {
        if (this.manager == manager) {
            for (String a : amenities) {
                addAmenity(a, manager);
            }
        }
    }

    public void addRoom(Room room, Manager manager) {
        if (this.manager == manager) {
            rooms.add(room);
        }
    }

    public void addRooms(List<Room> addRooms, Manager manager) {
        if (this.manager == manager) {
            for (Room room : addRooms) {
                this.rooms.add(room);
            }
        }
    }

    public void removeRoom(Room room, Manager manager) {
        if (this.manager == manager) {
            rooms.remove(room);
        }
    }

    public void removeSubscribe(User user) {
        subscribers.remove(user);
    }

    public void removeAmenities(String amenity, Manager manager) {
        if (this.manager == manager) {
            amenities.remove(amenity);
        }
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void cancelation(Reservation res) {
        res.getRoom().cancelation(res.getCheckInDate(), res.getCheckOutDate());
        reservations.remove(res);
    }

    public void sendNotification(String message) {
        for (User user : subscribers) {
            user.sendNotification(message);
        }
    }

    public List<Room> getFilteredRooms() {
        return this.filterRooms;
    }

    public void setFilteredRooms(List<Room> filteredRooms) {
        this.filterRooms = filteredRooms;
    }

    @Override
    public String toString() {
        return "\nHotel: hotelId: " + hotelId + ", name: " + name + ", location: " + location + ", description: " + description + ", amenities: " + amenities.toString() + ", reviews: " + reviews.toString();
    }

}
