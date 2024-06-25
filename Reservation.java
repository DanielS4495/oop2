
import java.util.Date;

public class Reservation {

    private static long count = 0;
    private final long reservationId;
    private final User user;
    private final Hotel hotel;
    private final Room room;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final int numGuests;
    private final double totalPrice; // Total cost of the booking
    private ReservationStatus status; // Order status (PENDING, CONFIRMED, CANCELLED)
    private Payment payment;

    public Reservation(User user, Hotel hotel, Room room, Date checkInDate, Date checkOutDate, int numGuests) {
        this.reservationId = count++;
        this.user = user;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numGuests = numGuests;
        this.status = ReservationStatus.PENDING; //pending until payment is made
        this.totalPrice = calculateTotalPrice();
    }

    public User getUser() {
        return user;
    }

    public long getReservationId() {
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

    public String cancelOrder() {
        if (status == ReservationStatus.CONFIRMED || status == ReservationStatus.PENDING) {
            Date cancellationDeadline = new Date(getCheckInDate().getTime() - (7 * 24 * 60 * 60 * 1000));
            if (cancellationDeadline.after(new Date())) {
                this.status = ReservationStatus.CANCELLED;
                getHotel().cancelation(this);
                return ("Order cancelled");
            } else {
                return ("Cancellation not allowed within one week of the check-in date.");

            }
        } else {
            return ("Order already cancelled.");

        }
    }

    public void executePayment(double amount) {
        if (payment != null && getTotalPrice() == amount && status == ReservationStatus.PENDING) {
            payment.pay(amount);
            setStatus(ReservationStatus.CONFIRMED);
            getHotel().addReservation(this);
        }
    }

    public Payment setPayment(String paymentMethod, double amount, long cardNumber, int cvv, Date expirationDate, String cardHolderName) {
        if (this.user.getLogin()) {
            System.out.println("Invalid user login credentials. Payment failed.");
            return null;
        }
        if (this.totalPrice != amount) {
            System.out.println("Invalid amount. Payment failed.");
            return null;
        }
        switch (paymentMethod.toLowerCase()) {
            case "credit card":
                return new CreditCardPayment(cardNumber, cvv, expirationDate, cardHolderName, amount);
            case "debit card":
                return new DebitCardPayment(cardNumber, cvv, expirationDate, cardHolderName, amount);
            case "PAYPAL":
                return new PayPalPayment(cardNumber, amount);
            case "bit":
                return new BitPayment(cardNumber, amount);
            case "apple pay":
                return new ApplePayPayment(cardNumber, cvv, expirationDate, cardHolderName, amount);
            case "google pay":
                return new GooglePayPayment(cardNumber, cvv, expirationDate, cardHolderName, amount);
            default:
                return null;
        }
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Reservation: Id:" + reservationId + ", User: " + user + ", Hotel: " + hotel + ", Room: " + room + ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate + ", Guests: " + numGuests + ", Total Price: " + totalPrice + ", Status: " + status;
    }
}

enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}
