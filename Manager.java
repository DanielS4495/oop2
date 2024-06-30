
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager extends Person {

    private Map<Long, Hotel> Myhotels;

    public Manager(String name, long phone, String email, String password) {
        super(name, phone, email, password);
        this.Myhotels = new HashMap<>();
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

    public void addHotel(Hotel hotel) {
        Myhotels.putIfAbsent(hotel.getHotelId(), hotel);
    }

    public Hotel createHotel(String name, String address, String description) {
        if (!getLogin()) {
            return null;
        }
        Hotel hotel = HotelFactory.createHotel(this, HotelFactory.HotelType.Basic, name, address, description);
        Myhotels.putIfAbsent(hotel.getHotelId(), hotel);
        return hotel;
    }

    public long createRoom(long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (!getLogin()) {
            return -1;
        }
        Room room = RoomFactory.createRoom(hotelId, roomType, view, numAdults, numChildren);
        Myhotels.get(hotelId).addRoom(room, this);
        return room.getRoomId();
    }

    public void sendNotification(long hotelId, String message) { //send notification to all user in hotel
        if (!getLogin()) {
            return;
        }
        Myhotels.get(hotelId).sendNotification(message);
    }
}
