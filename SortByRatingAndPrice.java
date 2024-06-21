
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByRatingAndPrice implements Sorting {

    @Override
    public void sort(List<Hotel> Hotels) {
        Collections.sort(Hotels, Comparator.comparing(Hotel::getStarRating)
                .thenComparingInt(hotel -> hotel.getRooms().stream()
                .mapToInt(room -> Double.valueOf(room.getPrice()).intValue())
                .min()
                .orElse(0)));
    }
}
