import java.util.Date;

public class Reservation {
    private Hotel hotel;
    private Room room;
    private Date startDate;
    private Date endDate;

    public Reservation(Hotel hotel, Room room, Date startDate, Date endDate) {
        this.hotel = hotel;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Reservation{hotel=" + hotel + ", room=" + room + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
