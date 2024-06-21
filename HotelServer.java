import java.util.*;

public class HotelServer {
    private List<Hotel> hotels;

    public HotelServer() {
        this.hotels = initializeHotels();
    }

    private List<Hotel> initializeHotels() {
        List<Hotel> hotels = new ArrayList<>();
        // Your existing code for initializing hotels

        // Example of initializing hotels with places
        for (int i = 1; i <= 10; i++) {
            String place = "City " + i;
            List<Room> rooms = new ArrayList<>();
            // Initialize rooms for the hotel
            Hotel hotel = new Hotel("Hotel " + i, place, rooms, i % 5 + 1);
            hotels.add(hotel);
        }
        return hotels;
    }

    // public List<Hotel> searchHotelsByPlace(String place) {
    //     List<Hotel> result = new ArrayList<>();
    //     for (Hotel hotel : hotels) {
    //         if (hotel.getLocation().equalsIgnoreCase(place)) {
    //             result.add(hotel);
    //         }
    //     }
    //     return result;
    // }

    // public List<Room> searchRoomsByHotelAndOccupancy(String hotelName, int numAdults, int numChildren) {
    //     List<Room> result = new ArrayList<>();
    //     for (Hotel hotel : hotels) {
    //         if (hotel.getName().equalsIgnoreCase(hotelName)) {
    //             for (Room room : hotel.getRooms()) {
    //                 if (room.isSuitableForOccupancy(numAdults, numChildren)) {
    //                     result.add(room);
    //                 }
    //             }
    //             break; 
    //         }
    //     }
    //     return result;
    // }

    public List<Hotel> getHotels() {
        return hotels;
    }
}
