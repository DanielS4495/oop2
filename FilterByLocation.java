
import java.util.List;
import java.util.stream.Collectors;

public class FilterByLocation implements Filtering {

    private String location;

    public FilterByLocation(String location) {
        this.location = location;
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        return hotels.stream()
                .filter(hotel -> hotel.getLocation().equals(location))
                .collect(Collectors.toList());
    }
}
