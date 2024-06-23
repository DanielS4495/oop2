
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private static long Id = 0;
    private final long roomId;
    private final String type;
    private final List<String> amenities;
    private double price; //per night per guest
    private final int numAdults;
    private final int numChildren;
    private Map<Date, Boolean> available;

    public Room(String type, List<String> amenities, int numAdults, int numChildren) {
        this.roomId = Room.Id++;
        this.type = type;
        this.amenities = amenities;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.price = setPriceCreation();
        this.available = new HashMap<>();
    }

    private double setPriceCreation() {
        if (type.equals("Single")) {
            price = 50.0;
        } else if (type.equals("Double")) {
            price = 90.0;
        } else if (type.equals("King")) {
            price = 100.0;
        } else if (type.equals("Twin")) {
            price = 90.0;
        } else if (type.equals("Suite")) {
            price = 150.0;
        } else if (type.equals("Studio")) {
            price = 80.0;
        } else if (type.equals("Accessible")) {
            price = 90.0;
        }
        return price;
    }

    public long getRoomId() {
        return roomId;
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

    public boolean isAvailable(Date checkIn, Date checkOut) {
        try {
            if (checkOut.before(checkIn)) {
                throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkIn);
            // Handle same-day check-in/check-out
            if (checkIn.equals(checkOut)) {
                return available.containsKey(checkIn) && available.get(checkIn);
            }
            // Iterate through dates from checkIn (inclusive) to checkOut (exclusive)
            while (cal.getTime().before(checkOut)) {
                Date dateToCheck = cal.getTime();
                if (!available.containsKey(dateToCheck) || !available.get(dateToCheck)) {
                    return false;
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean bookDate(Date checkIn, Date checkOut, String userId) throws Exception {
        // Check availability first using isAvailable
        try {
            if (!isAvailable(checkIn, checkOut)) {
                throw new Exception("Room is not available for the requested dates.");
            }

            // Mark booked dates as unavailable
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkIn);
            while (cal.getTime().before(checkOut)) {
                Date dateToMark = cal.getTime();
                available.put(dateToMark, false);
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //what do i need to check? 
    //do i need to do also cancel on reservation? no i can do it with systembooking
    public boolean cancelation(Date checkIn, Date checkOut) {
        try {
            if (checkOut.before(checkIn)) {
                throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
            }
            //i need to check if it true what i did
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkIn);
            while (cal.getTime().before(checkOut)) {
                Date dateToMark = cal.getTime();
                available.put(dateToMark, true);
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Room: type='" + type + "', amenities=" + amenities + ", price=" + price + ", numAdults=" + numAdults
                + ", numChildren=" + numChildren;
    }
}