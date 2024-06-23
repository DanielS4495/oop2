
// import RoomFactory.RoomType;
// import RoomFactory.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manager extends Person {//need to add create (hotel/room) + observer of (hotel/room)

    private Map<Room, Boolean> subscribedRooms;
    private Map<Long, Hotel> Myhotels;//////////
    // private List<Room> rooms;
    // private Map<Long, Room> rooms;
    private List<Notification> notifications;

    // private boolean login;
    public Manager(long managerId, String name, long phone, String email, String password) {
        super(managerId, name, phone, email, password);
        this.notifications = new ArrayList<>();
        // this.rooms = new HashMap<>();
    }

    @Override
    public long getPersonId() {
        return super.getPersonId();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public long getPhone() {
        return super.getPhone();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public boolean getLogin() {
        return super.getLogin();
    }

    public Hotel createHotel(String name, String address, String description) {
        if (!getLogin()) {
            return null;
        }
        List<Room> rooms = new ArrayList<>();
        int count = 2;
        for (int i = 0; i < RoomFactory.RoomType.values().length; i++) {
            if (i % 2 == 1) {
                count++;
            }
            Room room1 = new RoomFactory().createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.CITY, count, count - 2);
            Room room2 = new RoomFactory().createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.SEA, count, count - 2);
            rooms.add(room1);
            rooms.add(room2);
        }
        Hotel hotel = new Hotel(this, name, address, rooms, description);
        Myhotels.put(hotel.getHotelId(), hotel);
        return hotel;
    }

    public void createRoom(long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (!getLogin()) {
            return;
        }
        Room room = new RoomFactory().createRoom(roomType, view, numAdults, numChildren);
        Myhotels.get(hotelId).addRoom(room, this);
    }

    public void deleteRoom(Room room, Hotel hotel) {//what if we dont have the room?
        if (!getLogin()) {
            return;
        }
        Myhotels.get(hotel).removeRoom(room, this);
        // rooms.remove(room);
        // Delete room from database
    }

    public void deleteHotel(Hotel hotel) {
        if (!getLogin()) {
            return;
        }
        Myhotels.remove(hotel);
        // Delete hotel from database
    }

    // public void addSubscriber(Room room, User user) {
    //     if (!getLogin()) {
    //         return;
    //     }
    //     // Add user to room's subscribers
    // }
    // public void removeSubscriber(Room room, User user) {
    //     if (!getLogin()) {
    //         return;
    //     }
    //     // Remove user from room's subscribers
    // }
    // // notify user of hotel changes
    // public void notifySubscribers(Room room) {
    //     if (!getLogin()) {
    //         return;
    //     }
    //     // Notify all subscribers of room
    // }
    //notify all subscribers of hotel changes
    public void sendNotification(long hotelId, String message) {
        if (!getLogin()) {
            return;
        }
        Myhotels.get(hotelId).sendNotification(message);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String oldPassword, String newPassword) {
        super.setPassword(oldPassword, newPassword);
    }

    @Override
    public boolean loginWithId(long id, String password) {
        return super.loginWithId(id, password);
    } // For login functionality

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
    // public boolean login(String loginType, long personId, String email,String name, long phone, String password) {
    //     return super.login(loginType, personId, email, name, phone, password);
    // }
    public void sendNotification(String message) {
        for (Notification notificationType : notifications) {
            notificationType.sendNotification(message, null,this);
        }
    }

    public void addNotification(Notification notificationType) {
        if (notificationType != null) {
            notifications.add(notificationType);
        }
    }

    public void removeNotification(Notification notificationType) {
        if (notificationType != null) {
            notifications.remove(notificationType);
        }
    }
    @Override
    public String toString() {
        return "Manager [email=" + getEmail() + ", name=" + getName() + ", password=" + getPassword() + ", phone="
                + getPhone() + "]";
    }
}
