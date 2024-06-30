
import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    private List<Reservation> pastOrders;
    private List<Reservation> wishList;

    public User(String name, long phone, String email, String password) {
        super(name, phone, email, password);
        this.wishList = new ArrayList<>();
        this.pastOrders = new ArrayList<>();
    }

    public List<Reservation> getPastOrders() {
        return this.pastOrders;
    }

    public List<Reservation> getWishList() {
        return wishList;
    }

    public void addToWishlist(Reservation reservation) {
        wishList.add(reservation);
    }

    public void addPastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CONFIRMED")) {
            String message = String.format("Reservation for room %d from %s to %s has been CONFIRMED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

            sendNotification(message);
            pastOrders.add(reservation);
        } else {
            System.out.println("Order not completed yet");
        }

    }

    public void removePastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CANCELLED")) {
            pastOrders.remove(reservation);
            String message = String.format("Reservation for room %d from %s to %s has been CANCELLED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());
            sendNotification(message);
        } else {
            System.out.println("Order not CANCELLED yet");
        }

    }

    public void removeFromWishlist(Reservation reservation) {
        wishList.remove(reservation);
    }
}
