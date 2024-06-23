import java.util.List;
import java.util.stream.Collectors;

public class FilterByRating implements Filtering {
    private double rating;
    

    public FilterByRating(int rating){
        this.rating = rating;
        
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        return hotels.stream()
        .filter(hotel -> hotel.getRating() == rating)
        .collect(Collectors.toList());
        
    }
}
