
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private Map<Long, Hotel> Myhotels;
    private List<Notification> notifications;
    private final long managerId;
    public static long count = 0;
    private String name;
    private String email;
    private String password;
    private boolean login;
    private long phone;

    public Manager(String name, long phone, String email, String password) {
        this.managerId = count++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = false;
        this.phone = phone;
        this.notifications = new ArrayList<>();
        this.Myhotels = new HashMap<>();
    }

    public long getManagerId() {
        return this.managerId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public long getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getLogin() {
        return this.login;
    }

    public List<Hotel> getMyhotels() {
        return new ArrayList<>(Myhotels.values());
    }

    public Hotel getHotel(Long hotelId) {
        return Myhotels.get(hotelId);
    }

    public List<Reservation> getReservations(long hotelId) {
        return Myhotels.get(hotelId).getReservations();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginOut() {
        this.login = false;
    }

    public void addHotel(Hotel hotel) {
        Myhotels.putIfAbsent(hotel.getHotelId(), hotel);
    }

    public Hotel createHotel(String name, String address, String description) {
        if (!getLogin()) {
            return null;
        }
        List<Room> rooms = new ArrayList<>();
        int c = 2;
        for (int i = 0; i < RoomFactory.RoomType.values().length; i++) {
            if (i % 2 == 1) {
                count++;
            }
            Room room1 = RoomFactory.createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.CITY, c, c - 2);
            Room room2 = RoomFactory.createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.SEA, c, c - 2);
            rooms.add(room1);
            rooms.add(room2);
        }
        Hotel hotel = new Hotel(this, name, address, rooms, description);
        Myhotels.putIfAbsent(hotel.getHotelId(), hotel);
        return hotel;
    }

    public long createRoom(long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (!getLogin()) {
            return -1;
        }
        Room room = RoomFactory.createRoom(roomType, view, numAdults, numChildren);
        Myhotels.get(hotelId).addRoom(room, this);
        return room.getRoomId();
    }

    public void sendNotification(long hotelId, String message) {
        if (!getLogin()) {
            return;
        }
        Myhotels.get(hotelId).sendNotification(message);
    }

    public void setEmail(String oldEmail, String newEmail) {
        if (oldEmail.equals(this.email)) {
            this.email = newEmail;
        }
    }

    public void setPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public long loginWithId(long id, String password) {
        if (this.managerId == id && this.password.equals(password)) {
            this.login = true;
            return managerId;
        }
        return -1;
    }

    public long loginWithEmail(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.login = true;
            return this.managerId;
        }
        return -1;
    }

    public long loginWithName(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            this.login = true;
            return this.managerId;
        }
        return -1;
    }

    public long loginWithPhone(long phone, String password) {
        if (this.phone == phone && this.password.equals(password)) {
            this.login = true;
            return this.managerId;
        }
        return -1;
    }

    public void sendNotification(String message) {
        for (Notification notificationType : notifications) {
            notificationType.sendNotification(message, null, this);
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
        return " name:" + name + ", email:" + email + ", phone:" + phone;
    }
}
