import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByRating implements Sorting {

    @Override
    public void sort(List<Hotel> Hotels) {
        Collections.sort(Hotels, Comparator.comparingDouble(Hotel::getRating));
    }
}