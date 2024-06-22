import java.util.*;

public class HotelServer {
    private List<Manager> managers;
    private List<Hotel> hotels;

    public HotelServer(Manager manager) {
        this.managers = new ArrayList<>();
        this.managers.add(manager);
        this.hotels = initializeHotels(manager);
    }

    private List<Hotel> initializeHotels(Manager manager) {
        List<Hotel> hotels = new ArrayList<>();
        // Your existing code for initializing hotels

        // Example of initializing hotels with places
        for (int i = 1; i <= 10; i++) {
            String place = "City " + i;
            List<Room> rooms = new ArrayList<>();
            List<String> types = Arrays.asList("single", "double", "king", "twin", "suite", "studio", "accessible");
            for(int j = 1; j <= 10; j++) {
                rooms.add(new RoomFactory().createRoom(types.get(j), j, 0));
            }
            // Initialize rooms for the hotel
            Hotel hotel = new Hotel(manager,"Hotel " + i, place, rooms, i % 5 + 1);
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
