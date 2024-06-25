
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByPrice implements Filtering {

    private double minPrice;
    private double maxPrice;

    public FilterByPrice(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        if (hotels == null || minPrice < 0 || maxPrice < minPrice) {
            return hotels; 
        }
        List<Hotel> filteredHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<Room> filteredRooms = hotel.getRooms().stream()
                    .filter(room -> room.getPrice() >= minPrice && room.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            if (!filteredRooms.isEmpty()) { // Check if any rooms are in the price range

                hotel.setFilteredRooms(filteredRooms); 
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

}
