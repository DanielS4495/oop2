
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private final long hotelId;
    private static long Id = 0;
    private final long roomId;
    private final String type;
    private final List<String> amenities;
    private double price; //per night per guest
    private final int numAdults;
    private final int numChildren;
    private Map<LocalDate, Boolean> available;

    public Room(long hotelId, String type, List<String> amenities, int numAdults, int numChildren) {
        this.hotelId = hotelId;
        this.roomId = Id++;
        this.type = type;
        this.amenities = amenities;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.price = setPriceCreation();
        this.available = new HashMap<>();
        initializeAvailability();
    }

    private double setPriceCreation() {
        if (type.equalsIgnoreCase("single")) {
            price = 50.0;
        } else if (type.equalsIgnoreCase("double")) {
            price = 90.0;
        } else if (type.equalsIgnoreCase("king")) {
            price = 100.0;
        } else if (type.equalsIgnoreCase("twin")) {
            price = 90.0;
        } else if (type.equalsIgnoreCase("suite")) {
            price = 150.0;
        } else if (type.equalsIgnoreCase("studio")) {
            price = 80.0;
        } else if (type.equalsIgnoreCase("accessible")) {
            price = 90.0;
        }
        return price;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public String getType() {
        return type;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public double getPrice() {
        return price;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public boolean isSuitableForOccupancy(int numAdults, int numChildren) {
        return (numAdults <= getNumAdults() && numChildren <= getNumChildren());
    }

    private void initializeAvailability() {
        LocalDate today = LocalDate.now();
        for (LocalDate date = today; date.isBefore(today.plusYears(1)); date = date.plusDays(1)) {
            available.put(date, true);
        }
    }

    // Method to check availability
    public boolean isAvailableConvert(LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
        }

        // Handle same-day check-in/check-out
        if (checkIn.equals(checkOut)) {
            return available.containsKey(checkIn) && available.get(checkIn);
        }

        // Iterate through dates from checkIn (inclusive) to checkOut (exclusive)
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            if (!available.containsKey(date) || !available.get(date)) {
                System.out.println("Date not available: " + date); // Debugging log
                return false;
            }
        }
        return true;
    }

    // Method to book a room
    public boolean bookDateConvert(LocalDate checkIn, LocalDate checkOut, String userId) {
        if (!isAvailableConvert(checkIn, checkOut)) {
            System.out.println("Room is not available for the requested dates.");
            return false;
        }

        // Iterate through dates from checkIn (inclusive) to checkOut (exclusive)
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            if (available.containsKey(date)) {
                available.put(date, false);
            }
        }
        return true;
    }

    // Method to cancel a reservation
    public boolean bookDate(Date checkIn, Date checkOut, String userId) {
        LocalDate normalizedCheckIn = convertToLocalDateViaInstant(checkIn);
        LocalDate normalizedCheckOut = convertToLocalDateViaInstant(checkOut);
        return bookDateConvert(normalizedCheckIn, normalizedCheckOut, userId);
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public boolean isAvailable(Date checkIn, Date checkOut) {
        LocalDate normalizedCheckIn = convertToLocalDateViaInstant(checkIn);
        LocalDate normalizedCheckOut = convertToLocalDateViaInstant(checkOut);
        return isAvailableConvert(normalizedCheckIn, normalizedCheckOut);
    }

    public boolean cancelation(Date checkIn, Date checkOut) {
        LocalDate normalizedCheckIn = convertToLocalDateViaInstant(checkIn);
        LocalDate normalizedCheckOut = convertToLocalDateViaInstant(checkOut);
        return cancelationConvert(normalizedCheckIn, normalizedCheckOut);
    }

    public boolean cancelationConvert(LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
        }

        // Iterate through dates from checkIn (inclusive) to checkOut (exclusive)
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            if (available.containsKey(date)) {
                available.put(date, true);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Room: type = " + type + ", Room Id =" + roomId + ", Hotel Id = " + hotelId + ", price = " + price + ", numAdults = " + numAdults
                + ", numChildren = " + numChildren + ", amenities =" + amenities;
    }
}
