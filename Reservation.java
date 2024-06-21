
import java.util.Date;

public class Reservation {

    private final int reservationId;
    private final User user;
    private final Hotel hotel;
    private final Room room;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final int numGuests;
    private final double totalPrice; // Total cost of the booking
    private ReservationStatus status; // Order status (e.g., PENDING, CONFIRMED, CANCELLED)
    private Payment payment;

    public Reservation(int reservationId, User user, Hotel hotel, Room room, Date checkInDate, Date checkOutDate, int numGuests, ReservationStatus status) {
        this.reservationId = reservationId;
        this.user = user;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numGuests = numGuests;
        this.status = ReservationStatus.PENDING;//pending until payment is made
        this.totalPrice = calculateTotalPrice();
    }

    public User getUser() {
        return user;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    private double calculateTotalPrice() {
        long nights = getCheckOutDate().getTime() - getCheckInDate().getTime();
        int numDays = (int) Math.ceil(nights / (1000.0 * 60 * 60 * 24)); // Convert milliseconds to days (round up)

        return (numDays * getRoom().getPrice() * numGuests); // Nights multiplied by room price multiplied by number of guests
    }

    public boolean cancelOrder() {
        if (status == ReservationStatus.CONFIRMED || status == ReservationStatus.PENDING) {
            // Check for cancellation deadline (one week before check-in)
            Date cancellationDeadline = new Date(getCheckInDate().getTime() - (7 * 24 * 60 * 60 * 1000));
            if (cancellationDeadline.after(new Date())) {
                // Cancellation allowed before the deadline
                this.status = ReservationStatus.CANCELLED;
                getHotel().markRoomAvailable(getCheckInDate(), getCheckOutDate()); // Update room availability
                return true;
            } else {
                System.out.println("Cancellation not allowed within one week of the check-in date.");
                return false;
            }
        } else {
            System.out.println("Order already cancelled.");
            return false;
        }
        //user.notify("Order cancelled: " + this.toString());
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void executePayment(double amount) {
        if (payment != null && getTotalPrice() == amount && status == ReservationStatus.PENDING) {
            payment.pay(amount);
            setStatus(ReservationStatus.CONFIRMED);
        }
    }

    public Payment setPayment(String paymentMethod, double amount, long cardNumber, int cvv, Date expirationDate, Date cardHolderName) {
        if (this.user.getLogin()) {
            System.out.println("Invalid user login credentials. Payment failed.");
            return null;
        }
        if (this.totalPrice != amount) {
            System.out.println("Invalid amount. Payment failed.");
            return null;
        }
        switch (paymentMethod) {
            case "CREDIT_CARD":
                return new CreditCardPayment(cardNumber, cvv, expirationDate, cardHolderName, amount);
            case "PAYPAL":
                return new PayPalPayment(cardNumber, amount);
            default:
                return null;
        }
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Reservation [reservationId=" + reservationId + ", user=" + user + ", hotel=" + hotel + ", room=" + room + ", location=" + hotel.getLocation()
                + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", numGuests=" + numGuests
                + ", totalPrice=" + totalPrice + ", status=" + status + "]";
    }

}

enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}
