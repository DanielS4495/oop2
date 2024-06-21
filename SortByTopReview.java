
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByTopReview implements Sorting {

    @Override
    public void sort(List<Hotel> Hotels) {
        for (Hotel hotel : Hotels) {
            hotel.setReviews(hotel.getReviews().stream()
                            .sorted(Comparator.comparingDouble(Review::getRating).thenComparing(Review::getDate))
                            .collect(Collectors.toList()));
        }
    }
}
