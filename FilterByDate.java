
import java.util.ArrayList;
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
        if (hotels == null || checkInDate == null || checkOutDate == null) {
            return hotels; 
        }
        List<Hotel> filteredHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<Room> filteredRooms = hotel.getRooms().stream()
                    .filter(room -> room.isAvailable(checkInDate, checkOutDate))
                    .collect(Collectors.toList());
            if (!filteredRooms.isEmpty()) { // Check if any rooms are available
                hotel.setFilteredRooms(filteredRooms); 
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

}
