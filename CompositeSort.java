import java.util.ArrayList;
import java.util.List;

public class CompositeSort implements Sorting {
    private List<Sorting> sorts = new ArrayList<>();

    public void addSort(Sorting sort) {
        sorts.add(sort);
    }

    @Override
    public void sort(List<Hotel> hotels) {
        for (Sorting sort : sorts) {
            sort.sort(hotels);
        }
    }
}
