import java.util.Comparator;
import java.util.List;

public class SortById implements Sorting {

    @Override
    public void sort(List<Hotel> Hotels) {
        for (Hotel hotel : Hotels) {
            hotel.getRooms().sort(Comparator.comparingLong(Room::getRoomId));
        }
    }
}