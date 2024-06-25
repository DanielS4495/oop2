
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelFactory {

    public static Hotel createHotel(Manager manager, HotelType type, String name, String location, String description) {
        Hotel hotel;
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            rooms.add(RoomFactory.createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.CITY, i + 1, i));
            rooms.add(RoomFactory.createRoom(RoomFactory.RoomType.values()[i], RoomFactory.View.SEA, i + 1, i));
        }
        hotel = new Hotel(manager, name, location, rooms, description);
        switch (type.toString().toLowerCase()) {
            case "basic":
                hotel.setAmenities(Arrays.asList(Amenity.WIFI.toString()), manager);
                break;
            case "standard":
                hotel.setAmenities(Arrays.asList(Amenity.WIFI.toString(), Amenity.PARKING.toString()), manager);
                break;
            case "economy":
                hotel.setAmenities(Arrays.asList(Amenity.WIFI.toString(), Amenity.GYM.toString(), Amenity.RESTAURANT.toString()), manager);
                break;
            case "premium":
                hotel.setAmenities(Arrays.asList(Amenity.WIFI.toString(), Amenity.PARKING.toString(), Amenity.POOL.toString(), Amenity.RESTAURANT.toString()), manager);
                break;
            case "luxury":
                hotel.setAmenities(Arrays.asList(Amenity.WIFI.toString(), Amenity.PARKING.toString(), Amenity.POOL.toString(), Amenity.RESTAURANT.toString(), Amenity.SPA.toString(), Amenity.GYM.toString()), manager);
            default:
                break;
        }
        manager.addHotel(hotel);
        return hotel;
    }

    public enum HotelType {
        Basic,
        Standard,
        economy,
        premium,
        Luxury
    }

    public enum Amenity {
        WIFI, PARKING, SPA, GYM, POOL, RESTAURANT;
    }
}
