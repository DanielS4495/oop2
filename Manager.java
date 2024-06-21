import java.util.List;
import java.util.Map;

public class Manager extends Person implements Observer {//need to add create (hotel/room) + observer of (hotel/room)

    private WishList wishList;
    private List<Reservation> pastOrders;
    private Map<Room, Boolean> subscribedRooms;
    private List<Hotel> Myhotels;
    private List<Room> rooms;
    


    // private boolean login;

    public Manager(long userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    public void createHotel(String name, String address, String description, int starRating, List<Room> rooms) {
        Hotel hotel = new Hotel(this,name, address, description, starRating, rooms);
        // Add hotel to database
    }

    public void createRoom(int roomNumber, RoomType roomType, RoomStatus roomStatus, double price) {
        Room room = new Room(roomNumber, roomType, roomStatus, price);
        // Add room to database
    }

    public void updateRoom(Room room, RoomStatus roomStatus, double price) {
        room.setRoomStatus(roomStatus);
        room.setPrice(price);
        // Update room in database
    }

    public void updateHotel(Hotel hotel, String name, String address, String description, int starRating) {
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setDescription(description);
        hotel.setStarRating(starRating);
        // Update hotel in database
    }

    public void deleteRoom(Room room) {
        // Delete room from database
    }

    public void deleteHotel(Hotel hotel) {
        // Delete hotel from database
    }

    public void addRoom(Room room) {
        // Add room to hotel
    }

    public void removeRoom(Room room) {
        // Remove room from hotel
    }

    public void addSubscriber(Room room, User user) {
        // Add user to room's subscribers
    }

    public void removeSubscriber(Room room, User user) {
        // Remove user from room's subscribers
    }

    public void notifySubscribers(Room room) {
        // Notify all subscribers of room
    }

    public void update(Observable observable) {
        // Update manager based on changes to observable
    }



    
}