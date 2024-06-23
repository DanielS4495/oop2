
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemBooking { //neeed singelton need user list

    private static long userId = 0;
    private static long managerId = 0;
    private Sorting sort;
    private CompositeFilter filter;
    // private Map<Long,Hotel> hotels;
    private List<Hotel> hotels;
    private Map<Long, Manager> managers;
    private Map<Long, User> users;
    // private Reservation reservation;
    // private Search bookingSearch;
    // private WishList wishList;
    // private BookingSubject bookingSubject;
    // private HotelSorterContext hotelSorterContext;
    // private HotelFilterContext hotelFilterContext;
    //  private HashMap<Long, User> users;
    private static SystemBooking instance;

    private SystemBooking() {
        // this.bookingSearch = new BookingSearch();
        // this.reservation = new Reservation();
        // // this.wishList = new WishList();
        // this.users = new HashMap<>();
        // this.bookingSubject = new BookingSubject();
        // this.hotelSorterContext = new HotelSorterContext();
        // this.hotelFilterContext = new HotelFilterContext();
        // this.managers = new ArrayList<>();
        // this.users = new ArrayList<>();
        this.filter = new CompositeFilter();
        this.hotels = new ArrayList<>();
        // this.hotels = new HashMap<>();
        this.managers = new HashMap<>();
        this.users = new HashMap<>();

    }

    public static synchronized SystemBooking getInstance() {
        if (instance == null) {
            instance = new SystemBooking();
        }
        return instance;
    }

// ****************************************Manager Start******************************************************
    public void addManager(String name, long phone, String email, String password) {
        Manager manager = new Manager(managerId++, name, phone, email, password);
        this.managers.put(manager.getPersonId(), manager);
    }

    public Manager findManagerById(long managerId) {
        return this.managers.get(managerId);
    }

    public void logOutManager(long managerId) {
        managers.get(managerId).setLoginOut();
    }

    public String forgotPasswordManager(String name, String email) {
        for (Manager manager : managers.values()) {
            if (manager.getEmail().equals(email) && manager.getName().equals(name)) {
                sendNotification(manager.getPersonId(), manager.getPassword());
                return manager.getPassword();
            }
        }
        return null;
    }

    public String forgotUsernameManager(String email, String password) {
        for (Manager manager : managers.values()) {
            if (manager.getEmail().equals(email) && manager.getPassword().equals(password)) {
                return manager.getName();
            }
        }
        return null;
    }

    public String getNameManager(long managerId) {
        return managers.get(managerId).getName();
    }

    public String getEmailManager(long managerId) {
        return managers.get(managerId).getEmail();
    }

    public long getPhoneManager(long managerId) {
        return managers.get(managerId).getPhone();
    }

    public boolean getLoginManager(long managerId) {
        return managers.get(managerId).getLogin();
    }

    public boolean loginManager(long managerId, String chooseLogin, String emailOrName, Long phone, String password) {
        switch (chooseLogin.toLowerCase()) {
            case "id":
                return managers.get(managerId).loginWithId(managerId, password);
            case "email":
                return managers.get(managerId).loginWithEmail(emailOrName, password);

            case "name":
                return managers.get(managerId).loginWithName(emailOrName, password);

            case "phone":
                return managers.get(managerId).loginWithPhone(phone, password);

            default:
                return false;
        }
    }

    public void createHotel(long managerId, String name, String address, String description) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                Hotel hotel = this.managers.get(managerId).createHotel(name, address, description);
                this.hotels.add(hotel);
            }

        }

    }

    public void createRoom(long managerId, long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                this.managers.get(managerId).createRoom(hotelId, roomType, view, numAdults, numChildren);
            }
        }
    }

    public void sendNotification(long managerId, long hotelId, String message) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                this.managers.get(managerId).sendNotification(hotelId, message);
            }
        }
    }
    // ****************************************Manager Finish******************************************************

    // ****************************************User Start******************************************************
    public void addUser(String name, long phone, String email, String password) {
        User user = new User(userId++, name, phone, email, password);
        this.users.put(user.getPersonId(), user);
    }

    public User findUserById(long userId) {
        return this.users.get(userId);
    }

    public boolean loginUser(long userId, String chooseLogin, String emailOrName, Long phone, String password) {
        switch (chooseLogin.toLowerCase()) {
            case "id":
                return users.get(userId).loginWithId(userId, password);
            case "email":
                return users.get(userId).loginWithEmail(emailOrName, password);

            case "name":
                return users.get(userId).loginWithName(emailOrName, password);

            case "phone":
                return users.get(userId).loginWithPhone(phone, password);

            default:
                return false;
        }
    }

    public void logOutUser(long userId) {
        users.get(userId).setLoginOut();
    }

    public String forgotPasswordUser(String name, String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getName().equals(name)) {
                sendNotification(user.getPersonId(), user.getPassword());
                return user.getPassword();
            }
        }
        return null;
    }

    public String forgotUsernameUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                sendNotification(user.getPersonId(), user.getName());
                return user.getName();
            }
        }
        return null;
    }

    public String getNameUser(long userId) {
        return users.get(userId).getName();
    }

    public String getEmailUser(long userId) {
        return users.get(userId).getEmail();
    }

    public long getPhoneUser(long userId) {
        return users.get(userId).getPhone();
    }

    public boolean getLoginUser(long userId) {
        return users.get(userId).getLogin();
    }

    public Hotel findHotelById(long hotelId) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId() == hotelId) {
                return hotel;
            }
        }
        return null;
    }

    //return id of reservation
    public long makeReservation(long userId, long hotelId, long roomId, Date checkInDate, Date checkOutDate, int numAdults,
            int numChildren) {
        Hotel hotel = findHotelById(hotelId);
        Room room = hotel.getRoom(roomId);
        if (room == null || !room.isSuitableForOccupancy(numAdults, numChildren) || !room.isAvailable(checkInDate, checkOutDate) || hotel == null) {
            return -1;
        }
        Reservation reservation = new Reservation(users.get(userId), hotel, room, checkInDate, checkOutDate, numAdults + numChildren);
        users.get(userId).addToWishlist(reservation);
        return reservation.getReservationId();
    }

    //make pay of reservation
    public void payReservation(User user, int reservationId, String paymentMethod, double amount, long cardNumber, int cvv, Date expirationDate, Date cardHolderName) {
        // WishList w = user.getWishlist();
        Reservation r = user.getWishList().get(reservationId);
        r.setPayment(paymentMethod, amount, cardNumber, cvv, expirationDate, cardHolderName);
        r.executePayment(amount);
        user.removeFromWishlist(r);
        user.addPastOrder(r);
    }

    public double getTotalPriceOfReservation(User user, int reservationId) {
        return user.getWishList().get(reservationId).getTotalPrice();
    }

    public void cancelReservation(long userId, int reservationId) {
        // user.pastOrders.get(reservationId).cancel();
        Reservation r = users.get(userId).getPastOrders().get(reservationId);
        users.get(userId).removePastOrder(r);
        // reservation.cancel();
        // user.removeFromWishlist(reservation);
    }

    public void removeFromWishlist(long userId, long reservationId) {
        Reservation res = null;
        for (Reservation r : users.get(userId).getWishList()) {
            if (r.getReservationId() == reservationId) {
                res = r;
                break;
            }
        }
        if (res.getReservationId() == reservationId) {
            users.get(userId).removeFromWishlist(res);
        }
    }

    public List<Reservation> getWishList(long userId) {
        return users.get(userId).getWishList();
    }

    public List<Reservation> getPastOrder(long userId) {
        return users.get(userId).getPastOrders();
    }

    public List<String> notificationType(long userId) {
        return findUserById(userId).viewNotification();
    }

    public void addNotificationType(long userId, String notificationtype) {
        Notification notification = null;
        switch (notificationtype.toLowerCase()) {
            case "sms":
                notification = new SMS();
                break;
            case "email":
                notification = new Email();
                break;
            case "whatsapp":
                notification = new WhatsApp();
                break;
            default:
                break;
        }
        findUserById(userId).addNotification(notification);
    }

    public void removeNotificationType(long userId, String notificationtype) {
        Notification notification = null;
        switch (notificationtype.toLowerCase()) {
            case "sms":
                notification = new SMS();
                break;
            case "email":
                notification = new Email();
                break;
            case "whatsapp":
                notification = new WhatsApp();
                break;
            default:
                break;
        }
        findUserById(userId).removeNotification(notification);
    }

    public void sendNotification(long userId, String message) {
        findUserById(userId).sendNotification(message);
    }

    public void sendToManagerNotification(long managerId, String message) {
        findManagerById(managerId).sendNotification(message);
    }

    // ****************************************User Finish******************************************************
    public List<Hotel> sortHotels(String sort) {
        switch (sort.toLowerCase()) {
            case "price":
                this.sort = new SortByPrice();
                break;
            case "rating":
                this.sort = new SortByRating();
                break;
            case "rating and price":
                this.sort = new SortByRatingAndPrice();
                break;
            case "room size":
                this.sort = new SortByRoomsInHotel();
                break;
            case "top review":
                this.sort = new SortByTopReview();
                break;
            default:
                break;
        }
        this.sort.sort(hotels);
        return hotels;
    }

    public Room findRoomById(long roomId) {
        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) // return hotel.getRooms().get((int) roomId);
            {
                if (room.getRoomId() == roomId) {
                    return room;
                }
            }
        }
        return null;
    }

    public List<Hotel> filterHotels(List<String> filter, List<String> amenities, Date start, Date finish, double priceMin,
            double priceMax, int rating, String location) {
        boolean b = false;
        for (String f : filter) {
            switch (f.toLowerCase()) {
                case "amenities":
                    if (amenities != null) {
                        Filtering filtering = new FilterByAmenities(amenities);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                case "date":
                    if (start != null && finish != null) {
                        Filtering filtering = new FilterByDate(start, finish);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                case "price":
                    if (priceMin > 0 && priceMax >= priceMin) {
                        Filtering filtering = new FilterByPrice(priceMin, priceMax);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                case "rating":
                    if (rating > 0) {
                        Filtering filtering = new FilterByRating(rating);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                case "location":
                    if (location != null) {
                        Filtering filtering = new FilterByLocation(location);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                default:
                    break;
            }
        }
        if (b) {
            return this.filter.filter(hotels);
        }
        return null;
    }

}
