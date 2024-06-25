
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByRoomsInHotel implements Sorting { 

    @Override
    public void sort(List<Hotel> Hotels) {
        Collections.sort(Hotels, Comparator.comparingInt(hotel -> ((Hotel) hotel).getRooms().size()));
    }
}
