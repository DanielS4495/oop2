import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


public class SearchService {
    private List<Hotel> hotels;

    public SearchService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Hotel> search(Date startDate, Date endDate, List<String> filters, int minStars, String location) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if ((location == null || hotel.getLocation().equalsIgnoreCase(location))
                    && matchesFilters(hotel, filters, minStars)
                    && isAvailable(hotel, startDate, endDate)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public List<Room> searchRoomsInHotel(Hotel hotel, Date startDate, Date endDate, int numAdults, int numChildren) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.getNumAdults() >= numAdults && room.getNumChildren() >= numChildren && isRoomAvailable(hotel, room, startDate, endDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private boolean matchesFilters(Hotel hotel, List<String> filters, int minStars) {
        if (hotel.getStarRanking() < minStars) {
            return false;
        }

        for (Room room : hotel.getRooms()) {
            if (room.getAmenities().containsAll(filters)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAvailable(Hotel hotel, Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            if (!hotel.getAvailability().getOrDefault(calendar.getTime(), false)) {
                return false;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return true;
    }

    private boolean isRoomAvailable(Hotel hotel, Room room, Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            if (!hotel.getAvailability().getOrDefault(calendar.getTime(), false)) {
                return false;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return true;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public Reservation makeReservation(Hotel hotel, Room room, Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            hotel.setAvailability(calendar.getTime(), false);
            calendar.add(Calendar.DATE, 1);
        }
        return new Reservation(hotel, room, startDate, endDate);
    }
    public List<Room> filterRoomsByPrice(double minPrice, double maxPrice) {
        List<Room> filteredRooms = new ArrayList<>();
        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) {
                double roomPrice = room.getPrice();
                if (roomPrice >= minPrice && roomPrice <= maxPrice) {
                    filteredRooms.add(room);
                }
            }
        }
        return filteredRooms;
    }
}
