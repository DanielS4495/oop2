import java.util.ArrayList;
import java.util.List;

public class WishList {
    private List<Reservation> wishlist = new ArrayList<>();

    public void addReservation(Reservation Reservation) {
        wishlist.add(Reservation);
    }

    public void removeReservation(Reservation Reservation) {
        wishlist.remove(Reservation);
    }

    public List<Reservation> getWishlist() {
        return wishlist;
    }
}
