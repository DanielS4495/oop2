import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemBooking { //neeed singelton need user list
    private Search bookingSearch;
    private Reservation reservation;
    // private WishList wishList;
    private BookingSubject bookingSubject;
    private HotelSorterContext hotelSorterContext;
    private HotelFilterContext hotelFilterContext;
     private HashMap<Long, User> users;
    public SystemBooking() {
        this.bookingSearch = new BookingSearch();
        this.reservation = new Reservation();
        // this.wishList = new WishList();
        this.users = new HashMap<>();
        this.bookingSubject = new BookingSubject();
        this.hotelSorterContext = new HotelSorterContext();
        this.hotelFilterContext = new HotelFilterContext();
    }
    public void addUser(User user) {
        this.users.put(user.getUserId(), user);
    }

    // method to find a user by userId
    public User findUserById(long userId) {
        return this.users.get(userId);
    }
    public List<Hotel> searchHotels(String criteria) {
        return bookingSearch.search(criteria);
    }

    public void makeReservation(Reservation reservation) {
        reservation.reserve(reservation);
        bookingSubject.setMessage("New reservation made: " + reservation.toString());
    }

    public void addToWishlist(Reservation reservation) {
        wishList.addReservation(reservation);
    }

    public void removeFromWishlist(Reservation reservation) {
        wishList.removeReservation(reservation);
    }

    public List<Reservation> getWishlist() {
        return wishList.getWishlist();
    }

    public void sortHotels(List<Hotel> hotels, Sorting sort) {
        hotelSorterContext.setSorting(sort);
        hotelSorterContext.sortHotels(hotels);
    }

    public List<Hotel> filterHotels(List<Hotel> hotels, Filtering filter) {
        hotelFilterContext.setFiltering(filter);
        return hotelFilterContext.filterHotels(hotels);
    }

    public void registerObserver(Observer observer) {
        bookingSubject.addObserver(observer);
    }

    public void unregisterObserver(Observer observer) {
        bookingSubject.removeObserver(observer);
    }
}
