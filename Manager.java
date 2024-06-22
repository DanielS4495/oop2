
// import RoomFactory.RoomType;
// import RoomFactory.View;
import java.util.List;
import java.util.Map;

public class Manager extends Person implements Observer {//need to add create (hotel/room) + observer of (hotel/room)

    private Map<Room, Boolean> subscribedRooms;
    private List<Hotel> Myhotels;
    private List<Room> rooms;

    // private boolean login;
    public Manager(long userId, String name, long phone, String email, String password) {
        super(userId, name, phone, email, password);
    }

    public void createHotel(String name, String address, String description, int starRating, List<Room> rooms) {
        if (!isLoggedIn()) {
            return;
        }
        for (int i = 0; i < RoomFactory.RoomType.values().length; i++) {
            Room room1 = new RoomFactory().createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.CITY, i + 1, 0);
            Room room2 = new RoomFactory().createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.CITY, i + 1, 0);
            rooms.add(room1);
            rooms.add(room2);
        }

        Hotel hotel = new Hotel(this, name, address, rooms, description);
        Myhotels.add(hotel);
        // Add hotel to database
    }

    public void createRoom(RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (!isLoggedIn()) {
            return;
        }
        Room room = new RoomFactory().createRoom(roomType, view, numAdults, numChildren);
        // Add room to database
    }

    // public void updateRoom(Room room) {
    //     if (!isLoggedIn()) {
    //         return;
    //     }
    //     room.setRoomStatus(roomStatus);
    //     room.setPrice(price);
    //     // Update room in database
    // }

    // public void updateHotel(Hotel hotel, String name, String address, String description, int starRating) {
    //     if (!isLoggedIn()) {
    //         return;
    //     }
    //     hotel.setName(name);
    //     hotel.setAddress(address);
    //     hotel.setDescription(description);
    //     hotel.setStarRating(starRating);
    //     // Update hotel in database
    // }

    public void deleteRoom(Room room,Hotel hotel) {
        if (!isLoggedIn()) {
            return;
        }
        Myhotels.get(Myhotels.indexOf(hotel)).getRooms().remove(room);
        rooms.remove(room);
        // Delete room from database
    }

    public void deleteHotel(Hotel hotel) {
        if (!isLoggedIn()) {
            return;
        }
        Myhotels.remove(hotel);
        // Delete hotel from database
    }

 
    public void addSubscriber(Room room, User user) {
        if (!isLoggedIn()) {
            return;
        }
        // Add user to room's subscribers
    }

    public void removeSubscriber(Room room, User user) {
        if (!isLoggedIn()) {
            return;
        }
        // Remove user from room's subscribers
    }

    // notify user of hotel changes
    public void notifySubscribers(Room room) {
        if (!isLoggedIn()) {
            return;
        }
        // Notify all subscribers of room
    }

    private boolean isLoggedIn() {
        // Implement your login check logic here
        // Return true if manager is logged in, false otherwise
        return super.getLogin();
    }

    @Override
    public boolean loginWithEmail(String email, String password) {
        return super.loginWithEmail(email, password);
    } // For login functionality

    @Override
    public boolean loginWithName(String name, String password) {
        return super.loginWithName(name, password);
    } // For login functionality

    @Override
    public boolean loginWithPhone(long phone, String password) {
        return super.loginWithPhone(phone, password);
    } // For login functionality

    // @Override
    // public void update(String message) {
    //     System.out.println("User " + super.getName() + " received update: " + message);
    // }
}
