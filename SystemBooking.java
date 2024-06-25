
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemBooking {

    private Sorting sort;
    private CompositeFilter filter;
    private List<Hotel> hotels;
    private Map<Long, Manager> managers;
    private Map<Long, User> users;
    private static SystemBooking instance;

    private SystemBooking() {
        this.filter = new CompositeFilter();
        this.hotels = new ArrayList<>();
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
        Manager manager = new Manager(name, phone, email, password);
        this.managers.putIfAbsent(manager.getManagerId(), manager);
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
                sendNotification(manager.getManagerId(), manager.getPassword());
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

    public List<Hotel> getHotels(long messageId) {
        return managers.get(messageId).getMyhotels();
    }

    public Hotel getHotel(long messageId, long hotelId) {
        return managers.get(messageId).getHotel(hotelId);
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

    public void createHotels(long managerId, String name, String location, String description) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                Hotel hotel;
                for (int i = 0; i < 5; i++) {//HotelFactory.HotelType.values()[i]
                    hotel = HotelFactory.createHotel(managers.get(managerId), HotelFactory.HotelType.values()[i], name, "location " + i, "description " + i);
                    this.hotels.add(hotel);
                }
            }
        }
    }

    public List<Reservation> getReservations(long managerId, long hotelId) {
        return managers.get(managerId).getReservations(hotelId);
    }

    public long loginManager(long managerId, String chooseLogin, String Name, String email, Long phone, String password) {
        switch (chooseLogin.toLowerCase()) {
            case "id":
                return managers.get(managerId).loginWithId(managerId, password);
            case "email":
                return loginWithEmailManager(email, password);

            case "name":
                return loginWithNameManager(Name, password);

            case "phone":
                return loginWithPhoneManager(phone, password);

            default:
                return -1;
        }
    }

    public long loginWithNameManager(String name, String password) {
        for (Manager manager : managers.values()) {
            if (manager.getName().equals(name)) {
                manager.loginWithName(name, password);
                return manager.getManagerId();
            }
        }
        return -1;
    }

    public long loginWithEmailManager(String email, String password) {
        for (Manager manager : managers.values()) {
            if (manager.getEmail().equals(email)) {
                manager.loginWithEmail(email, password);
                return manager.getManagerId();
            }
        }
        return -1;
    }

    public long loginWithPhoneManager(long phone, String password) {
        for (Manager manager : managers.values()) {
            if (manager.getPhone() == phone) {
                manager.loginWithPhone(phone, password);
                return manager.getManagerId();
            }
        }
        return -1;
    }

    public long createHotel(long managerId, String name, String address, String description) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                Hotel hotel = this.managers.get(managerId).createHotel(name, address, description);
                this.hotels.add(hotel);
                return hotel.getHotelId();
            }

        }
        return -1;
    }

    public long createRoom(long managerId, long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        if (this.managers.get(managerId) != null) {
            if (this.managers.get(managerId).getLogin()) {
                long roomId = this.managers.get(managerId).createRoom(hotelId, roomType, view, numAdults, numChildren);
                return roomId;
            }
        }
        return -1;
    }

    public void addNotificationTypeManager(long managerId, String notificationtype) {
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
        findManagerById(managerId).addNotification(notification);
    }

    public void removeNotificationTypeManager(long managerId, String notificationtype) {
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
        findManagerById(managerId).removeNotification(notification);
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
        User user = new User(name, phone, email, password);
        this.users.putIfAbsent(user.getUserId(), user);
    }

    public User findUserById(long userId) {
        return this.users.get(userId);
    }

    public long loginUser(long userId, String chooseLogin, String name, String email, Long phone, String password) {
        switch (chooseLogin.toLowerCase()) {
            case "id":
                return users.get(userId).loginWithId(userId, password);
            case "email":
                return loginWithEmailUser(email, password);

            case "name":
                return loginWithNameUser(name, password);

            case "phone":
                return loginWithPhoneUser(phone, password);

            default:
                return -1;
        }
    }

    public long loginWithNameUser(String name, String password) {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                user.loginWithName(name, password);
                return user.getUserId();
            }
        }
        return -1;
    }

    public long loginWithEmailUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                user.loginWithEmail(email, password);
                return user.getUserId();
            }
        }
        return -1;
    }

    public long loginWithPhoneUser(long phone, String password) {
        for (User user : users.values()) {
            if (user.getPhone() == phone) {
                user.loginWithPhone(phone, password);
                return user.getUserId();
            }
        }
        return -1;
    }

    public void logOutUser(long userId) {
        users.get(userId).setLoginOut();
    }

    public String forgotPasswordUser(String name, String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getName().equals(name)) {
                sendNotification(user.getUserId(), user.getPassword());
                return user.getPassword();
            }
        }
        return null;
    }

    public String forgotUsernameUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                sendNotification(user.getUserId(), user.getName());
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

    public void addReview(long userId, int reservationId, double rating, String review, Date date) {
        Hotel hotel = users.get(userId).getWishList().get(reservationId).getHotel();
        if (hotel == null) {
            return;
        }
        Review r = new Review(users.get(userId), rating, review, date);
        hotel.addReview(r);
    }

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
    public void payReservation(long userId, int reservationId, String paymentMethod, double amount, long cardNumber, int cvv, Date expirationDate, String cardHolderName) {
        Reservation r = users.get(userId).getWishList().get(reservationId);
        r.setPayment(paymentMethod, amount, cardNumber, cvv, expirationDate, cardHolderName);
        r.executePayment(amount);
        users.get(userId).removeFromWishlist(r);
        users.get(userId).addPastOrder(r);
    }

    public double getTotalPriceOfReservation(User user, int reservationId) {
        return user.getWishList().get(reservationId).getTotalPrice();
    }

    public String cancelReservation(long userId, int reservationId) {
        try {

            Reservation r = users.get(userId).getPastOrders().get(reservationId);
            if (r == null) {
                return "Not found Reservation";
            }
            String s = r.cancelOrder();
            if (s.equals("Order cancelled")) {

                users.get(userId).removePastOrder(r);
                return "Reservation canceled";

            } else {
                return s;
            }
        } catch (Exception e) {
            return "Not found Reservation";
        }
    }

    public void removeFromWishlist(long userId, long reservationId) {
        for (Reservation r : users.get(userId).getWishList()) {
            if (r.getReservationId() == reservationId) {
                users.get(userId).removeFromWishlist(r);
                break;
            }
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

    public Room findRoomById(long roomId, long hotelId) {
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

    public List<Hotel> filterHotels(List<String> filter, List<String> amenitiesHotel, List<String> amenitiesRoom, Date start, Date finish, double priceMin,
            double priceMax, int rating, String location) {
        boolean b = false;
        for (String f : filter) {
            switch (f.toLowerCase()) {
                case "amenities to hotel":
                    if (amenitiesHotel != null) {
                        Filtering filtering = new FilterByAmenitiesHotel(amenitiesHotel);
                        this.filter.addFilter(filtering);
                        b = true;
                        break;
                    }
                case "amenities to room":
                    if (amenitiesRoom != null) {
                        Filtering filtering = new FilterByAmenitiesRoom(amenitiesRoom);
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

    public List<Hotel> getAllHotels() {
        return hotels;
    }

}
