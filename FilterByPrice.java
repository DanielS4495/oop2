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
        for (Hotel hotel : hotels) {
            List<Room> filteredRooms = hotel.getRooms().stream()
                    .filter(room -> room.getPrice() >= minPrice && room.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            hotel.getRooms().clear();
            hotel.getRooms().addAll(filteredRooms);
        }
        return hotels;
    }
}
