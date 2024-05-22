import java.util.*;

public class HotelServer {
    private List<Hotel> hotels;

    public HotelServer() {
        this.hotels = initializeHotels();
    }

    private List<Hotel> initializeHotels() {
        List<Hotel> hotels = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            List<Room> rooms = new ArrayList<>();
            rooms.add(new Room("Single", Arrays.asList("Breakfast", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 50));
            rooms.add(new Room("Double", Arrays.asList("Breakfast", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 100));
            rooms.add(new Room("Suite", Arrays.asList("Breakfast", "Hot Tub", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 200));
            rooms.add(new Room("Family", Arrays.asList("Breakfast", "Balcony", "WiFi", "Parking", "Gym", "Restaurant", "Room Service", "Air Conditioning", "Television", "Mini Bar", "Shuttle Service", "Pool"), 150));
            rooms.add(new Room("Executive Suite", Arrays.asList("Breakfast", "Hot Tub", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 300));
            rooms.add(new Room("Penthouse", Arrays.asList("Breakfast", "Hot Tub", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 500));
            rooms.add(new Room("Standard", Arrays.asList("Breakfast", "WiFi", "Parking", "Air Conditioning", "Television", "Shuttle Service"), 75));
            rooms.add(new Room("Deluxe", Arrays.asList("Breakfast", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 150));
            rooms.add(new Room("Bungalow", Arrays.asList("Breakfast", "Hot Tub", "Balcony", "WiFi", "Parking", "Gym", "Spa", "Restaurant", "Room Service", "Air Conditioning", "Laundry", "Television", "Mini Bar", "Shuttle Service", "Pool"), 400));
            rooms.add(new Room("Economy", Arrays.asList("Breakfast", "WiFi", "Air Conditioning", "Television"), 40));
            hotels.add(new Hotel("Hotel " + i, rooms.size(), rooms, i % 5 + 1));
        }
        return hotels;
    }
    

    public List<Hotel> getHotels() {
        return hotels;
    }
}
