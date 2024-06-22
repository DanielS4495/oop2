
import java.awt.Composite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class SystemBooking { //neeed singelton need user list

    private Sorting sort;
    private CompositeFilter filter;
    private List<Hotel> hotels;
    private List<Manager> managers;
    private List<User> users;
    private Reservation reservation;
    // private Search bookingSearch;
    // private WishList wishList;
    // private BookingSubject bookingSubject;
    // private HotelSorterContext hotelSorterContext;
    // private HotelFilterContext hotelFilterContext;
    //  private HashMap<Long, User> users;

    public SystemBooking() {
        // this.bookingSearch = new BookingSearch();
        // this.reservation = new Reservation();
        // // this.wishList = new WishList();
        // this.users = new HashMap<>();
        // this.bookingSubject = new BookingSubject();
        // this.hotelSorterContext = new HotelSorterContext();
        // this.hotelFilterContext = new HotelFilterContext();
        this.managers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.sort = new CompositeSort();
        this.filter = new CompositeFilter();
        this.hotels = new ArrayList<>();

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

    // public void sortHotels(List<Hotel> hotels, Sorting sort) {
    //     hotelSorterContext.setSorting(sort);
    //     hotelSorterContext.sortHotels(hotels);
    // }
 private void sortHotels(String sort) {
        Collections.sort(hotels, comparator);
    } 
    public void sortHotels( String sort ) {
        switch(sort.toLowerCase())
        {
            case "price":
            this.sort = new SortByPrice();
            this.sort.sort(hotels);
                break;
            case "rating":
            this.sort = new SortByPrice();
            this.sort.sort(hotels);
                break;
            case "rating and price":
            this.sort = new SortByPrice();
            this.sort.sort(hotels);
                break;
            case "room size":
            this.sort = new SortByPrice();
            this.sort.sort(hotels);
                break;
        }
      
        // else if(sort=="distance")
        // {
        //     this.sort.addSort(new SortByDistance());
        // }
        // else if(sort=="popularity")
        // {
        //     this.sort.addSort(new SortByPopularity());
        // }



        // this.sort.addSort(new SortByRating());
        // this.sort.addSort(new SortByPrice());
        // this.sort.addSort(new SortByDistance());
        // this.sort.addSort(new SortByPopularity());
        this.sort.sort(hotels);

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
