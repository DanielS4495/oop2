import java.util.List;
import java.util.stream.Collectors;

public class FilterByAmenitiesHotel implements Filtering {
    private List<String> requiredAmenities;

    public FilterByAmenitiesHotel(List<String> requiredAmenities) {
        this.requiredAmenities = requiredAmenities;
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        return hotels.stream()
                .filter(hotel -> hotel.getAmenities().containsAll(requiredAmenities))
                .collect(Collectors.toList());
    }
}
