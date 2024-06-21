import java.util.ArrayList;
import java.util.List;

public class CompositeFilter implements Filtering {
    private List<Filtering> filters = new ArrayList<>();

    public void addFilter(Filtering filter) {
        filters.add(filter);
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        List<Hotel> filteredHotels = hotels;
        for (Filtering filter : filters) {
            filteredHotels = filter.filter(filteredHotels);
        }
        return filteredHotels;
    }
}
