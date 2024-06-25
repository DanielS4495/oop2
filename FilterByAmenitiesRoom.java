
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByAmenitiesRoom implements Filtering {

    private List<String> requiredAmenities;

    public FilterByAmenitiesRoom(List<String> requiredAmenities) {
        this.requiredAmenities = requiredAmenities;
    }

    @Override
    public List<Hotel> filter(List<Hotel> hotels) {
        List<Hotel> filteredHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<Room> filteredRooms = hotel.getRooms().stream() 
                    .filter(room -> room.getAmenities().containsAll(requiredAmenities))
                    .collect(Collectors.toList());

            if (!filteredRooms.isEmpty()) { // Check if any rooms in this hotel have amenities
                hotel.setFilteredRooms(filteredRooms);
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

}
