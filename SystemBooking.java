
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemBooking {

    private Sorting sort;
    private CompositeFilter filter;
    private List<Hotel> hotels;
    private Map<Long, Person> persons;
    private static SystemBooking instance;

    private SystemBooking() {
        // this.filter = new CompositeFilter();
        this.hotels = new ArrayList<>();
        this.persons = new HashMap<>();
    }

    public static synchronized SystemBooking getInstance() {
        if (instance == null) {
            instance = new SystemBooking();
        }
        return instance;
    }

// ****************************************Person Start******************************************************
    public void addPerson(String identifier, String name, long phone, String email, String password) {
        try {
            Person person = null;
            if (identifier.equals("manager")) {
                person = new Manager(name, phone, email, password);
            } else if (identifier.equals("user")) {
                person = new User(name, phone, email, password);
            }
            if (person != null) {
                this.persons.putIfAbsent(person.getId(), person);
            }
        } catch (NullPointerException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Person findPersonById(long personId) {
        if (personId < 0) {
            return null;
        }
        try {
            Person person = this.persons.get(personId);
            switch (person) {
                case Manager manager -> {
                    return manager;
                }
                case User user -> {
                    return user;
                }
                default -> {
                    return null;
                }
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean forgotPassword(String name, String email, Long phone) {
        for (Person person : persons.values()) {
            if (person.getEmail().equals(email) && person.getName().equals(name) && person.getPhone() == phone) {
                sendNotification(person.getId(), person.getPassword());
                return true;
            }
        }
        return false;
    }

    public boolean forgotUsername(String email, Long phone, String password) {
        for (Person person : persons.values()) {
            if (person.getEmail().equals(email) && person.getPassword().equals(password) && person.getPhone() == phone) {
                sendNotification(person.id, person.getName());
                return true;
            }
        }
        return false;
    }

    public long login(long personId, String chooseLogin, String Name, String email, Long phone, String password) {
        try {
            switch (chooseLogin.toLowerCase()) {
                case "id":
                    return persons.get(personId).loginWithId(personId, password);
                case "email":
                    return loginWithEmailPerson(email, password);

                case "name":
                    return loginWithNamePerson(Name, password);

                case "phone":
                    return loginWithPhonePerson(phone, password);

                default:
                    return -1;
            }
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public long loginWithNamePerson(String name, String password) {
        for (Person person : persons.values()) {
            if (person.getName().equals(name) && person.getPassword().equals(password)) {
                person.loginWithName(name, password);
                return person.getId();
            }
        }
        return -1;
    }

    public long loginWithEmailPerson(String email, String password) {
        for (Person person : persons.values()) {
            if (person.getEmail().equals(email) && person.getPassword().equals(password)) {
                person.loginWithEmail(email, password);
                return person.getId();
            }
        }
        return -1;
    }

    public long loginWithPhonePerson(long phone, String password) {
        for (Person person : persons.values()) {
            if (person.getPhone() == phone && person.getPassword().equals(password)) {
                person.loginWithPhone(phone, password);
                return person.getId();
            }
        }
        return -1;
    }

    public void addNotificationType(long personId, String notificationtype) {
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
        persons.get(personId).addNotification(notification);
    }

    public void removeNotificationType(long personId, String notificationtype) {
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
        persons.get(personId).removeNotification(notification);
    }

    public void sendNotification(long personId, String message) {
        persons.get(personId).sendNotification(message);
    }

    public List<String> notificationType(long personId) {
        return persons.get(personId).viewNotification();
    }
// ****************************************Person finish******************************************************

// ****************************************Manager Start******************************************************
    public List<Hotel> getHotels(long managerId) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager manager) {
            return manager.getMyhotels();
        }
        return null;
    }

    public Hotel getHotel(long managerId, long hotelId) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager manager) {
            return manager.getHotel(hotelId);
        }
        return null;
    }

    public void createHotels(long managerId, String name, String location, String description) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager manager) {
            if (manager.getLogin()) {
                for (int i = 0; i < 5; i++) {
                    Hotel hotel = HotelFactory.createHotel(manager, HotelFactory.HotelType.values()[i], "name " + i, "location " + i, "description " + i);
                    this.hotels.add(hotel);
                }
            }
        }
    }

    public List<Reservation> getReservations(long managerId, long hotelId) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager manager) {
            return manager.getReservations(hotelId);
        }
        return null;
    }

    public Manager findManagerById(long managerId) {
        if (managerId < 0) {
            return null;
        }
        Person person = this.persons.get(managerId);
        if (person instanceof Manager manager) {
            return manager;
        } else {
            return null;
        }
    }

    public long createHotel(long managerId, String name, String address, String description) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager) {
            Manager manager = (Manager) person;
            if (manager.getLogin()) {
                Hotel hotel = manager.createHotel(name, address, description);
                this.hotels.add(hotel);
                return hotel.getHotelId();
            }

        }
        return -1;
    }

    public long createRoom(long managerId, long hotelId, RoomFactory.RoomType roomType, RoomFactory.View view, int numAdults, int numChildren) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager) {
            Manager manager = (Manager) person;
            if (manager.getLogin()) {
                return manager.createRoom(hotelId, roomType, view, numAdults, numChildren);
            }
        }
        return -1;
    }

    public void sendNotificationToHotelUsers(long managerId, long hotelId, String message) {
        Person person = this.persons.get(managerId);
        if (person instanceof Manager) {
            Manager manager = (Manager) person;
            if (manager.getLogin()) {
                manager.sendNotification(hotelId, message);
            }
        }
    }
    // ****************************************Manager Finish******************************************************

    // ****************************************User Start******************************************************
    public User findUserById(long userId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            return user;
        } else {
            return null;
        }
    }

    public void logOut(long userId) {
        persons.get(userId).setLoginOut();
    }

    public boolean addReview(long userId, int reservationId, double rating, String review, Date date) {
        try {
            Person person = this.persons.get(userId);
            if (person instanceof User user) {
                Hotel hotel = user.getPastOrders().get(reservationId).getHotel();
                if (hotel == null) {
                    return false;
                }
                Review r = new Review(user, rating, review, date);
                hotel.addReview(r);
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public long makeReservation(long userId, long roomId, Date checkInDate, Date checkOutDate, int numAdults,
            int numChildren) {
        try {
            Person person = this.persons.get(userId);
            if (person instanceof User user) {
                Hotel hotel = findHotelByRoomId(roomId);
                Room room = hotel.getRoom(roomId);
                if (room == null || hotel == null || !room.isSuitableForOccupancy(numAdults, numChildren) || !room.isAvailable(checkInDate, checkOutDate)) {
                    return -1;
                }
                Reservation reservation = new Reservation(user, hotel, room, checkInDate, checkOutDate, numAdults + numChildren);
                user.addToWishlist(reservation);
                return reservation.getReservationId();
            } else {
                return -1;
            }
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    //make pay of reservation
    public boolean payReservation(long userId, int reservationId, String paymentMethod, double amount, long cardNumber, int cvv, Date expirationDate, String cardHolderName) {
        try {
            Person person = this.persons.get(userId);
            if (person instanceof User user) {
                Reservation r = user.getWishList().get(reservationId);
                r.setPayment(paymentMethod, amount, cardNumber, cvv, expirationDate, cardHolderName);
                r.executePayment(amount);
                user.removeFromWishlist(r);
                user.addPastOrder(r);
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public double getTotalPriceOfReservation(long userId, int reservationId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            return user.getWishList().get(reservationId).getTotalPrice();
        } else {
            return -1;
        }
    }

    public String cancelReservation(long userId, int reservationId) {
        try {
            Person person = this.persons.get(userId);
            if (person instanceof User user) {
                Reservation r = user.getPastOrders().get(reservationId);
                if (r == null) {
                    return "Not found Reservation";
                }
                String s = r.cancelOrder();
                if (s.equals("Order cancelled")) {

                    user.removePastOrder(r);
                    return "Reservation canceled";

                } else {
                    return s;
                }
            }
            return "Not found Reservation";
        } catch (IndexOutOfBoundsException e) {
            return "Not found Reservation";
        }
    }

    public boolean removeFromWishlist(long userId, long reservationId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            for (Reservation r : user.getWishList()) {
                if (r.getReservationId() == reservationId) {
                    user.removeFromWishlist(r);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Reservation> getWishList(long userId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            return user.getWishList();
        } else {
            return null;
        }
    }

    public List<Reservation> getPastOrder(long userId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            return user.getPastOrders();
        } else {
            return null;
        }
    }

    public double findResarvationById(long userId, int reservationId) {
        Person person = this.persons.get(userId);
        if (person instanceof User user) {
            for (Reservation reservation : user.getWishList()) {
                if (reservation.getReservationId() == reservationId) {
                    reservation.getTotalPrice();
                }
            }
        }
        return -1;
    }

    public Hotel findHotelByRoomId(long roomId) {
        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) {
                if (room.getRoomId() == roomId) {
                    return hotel;
                }
            }
        }
        return null;
    }

    // ****************************************User Finish******************************************************
    public List<Hotel> sortHotels(String sort) {
        switch (sort.toLowerCase()) {
            case "id":
                this.sort = new SortById();
                break;
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
            for (Room room : hotel.getRooms()) {
                if (room.getRoomId() == roomId) {
                    return room;
                }
            }
        }
        return null;
    }

    public List<Hotel> filterHotels(List<Hotel> hotels, List<String> filter, List<String> amenitiesHotel, List<String> amenitiesRoom, Date start, Date finish, double priceMin,
            double priceMax, int rating, String location) {
        boolean b = false;
        this.filter = new CompositeFilter();
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
        for (Hotel hotel : hotels) {
            hotel.setFilteredRooms(hotel.getRooms());
        }
        return hotels;
    }

}
