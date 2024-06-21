import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByDate implements Filtering {
    private Date checkInDate;
    private Date checkOutDate;

    public FilterByDate(Date checkInDate, Date checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            List<Room> filteredRooms = hotel.getRooms().stream()
                    .filter(room -> room.isAvailable(checkInDate, checkOutDate))
                    .collect(Collectors.toList());
            hotel.getRooms().clear();
            hotel.getRooms().addAll(filteredRooms);
        }
        return hotels;
    }
}
