import java.util.*;

public class SearchService {
    private List<Hotel> hotels;

    public SearchService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Hotel> search(Date startDate, Date endDate, List<String> filters, int minStars) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (matchesFilters(hotel, filters, minStars) && isAvailable(hotel, startDate, endDate)) {
                result.add(hotel);
            }
        }
        return result;
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

    public Reservation makeReservation(Hotel hotel, Room room, Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            hotel.setAvailability(calendar.getTime(), false);
            calendar.add(Calendar.DATE, 1);
        }
        return new Reservation(hotel, room, startDate, endDate);
    }
}
