
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {

    private final SystemBooking booking;
    private final View view;
    private final Scanner scanner;

    public Controller(View view) {
        this.booking = SystemBooking.getInstance();
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        createTenHotel();
        boolean exit = false;
        while (!exit) {
            view.displayMenuLogIn();
            int choice = getIntInput("Enter number choice:");
            switch (choice) {
                case 1:
                    Registration();//new user/manager
                    break;
                case 2:
                    Login();//go to action after login
                    break;
                case 3:
                    ForgotUsername();//forgot username
                    break;
                case 4:
                    ForgotPassword();//forgot password
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
        view.displayMessage("Exiting...");
        this.scanner.close();
        System.exit(0);
    }

    private void ForgotPassword() {
        view.displayMessage("Enter name:");
        String name = this.scanner.nextLine();
        view.displayMessage("Enter email:");
        String email = this.scanner.nextLine();
        long phone = getLongInput("Enter phone number:");
        boolean password = booking.forgotPassword(name, email, phone);
        if (password) {
            view.displayMessage("password sent to your email.");
        } else {
            view.displayMessage("person not found.");
        }
    }

    private void ForgotUsername() {
        view.displayMessage("Enter email:");
        String email = this.scanner.nextLine();
        long phone = getLongInput("Enter phone number:");
        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        boolean username = booking.forgotUsername(email, phone, password);
        if (username) {
            view.displayMessage("Username sent to your email.");
        } else {
            view.displayMessage("person not found.");
        }
    }

    private void SearchHotels() {
        view.displayMessage("Enter how you want to search: all or by sort or by filter or both");
        List<Hotel> hotels = booking.getAllHotels();
        boolean exit = false;
        booking.sortHotels("id");//so the last sort wont affect new sort
        while (!exit) {
            switch (this.scanner.nextLine().toLowerCase()) {
                case "all":
                    exit = true;
                    break;
                case "sort":
                    hotels = sortHotels();
                    exit = true;
                    break;
                case "filter":
                    view.displayMenuFilter();
                    hotels = filterHotels(hotels);
                    exit = true;
                    break;
                case "both":
                    view.displayMessage("first we going by sort then filter");
                    hotels = sortHotels();
                    view.displayMessage("now we going by filter");
                    view.displayMenuFilter();
                    hotels = filterHotels(hotels);
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }
        view.displaySearchHotels(hotels);
    }

    public List<Hotel> sortHotels() {
        view.displayMenuSort();
        while (true) {
            String sort = this.scanner.nextLine();
            switch (sort) {
                case "1":
                    return booking.sortHotels("price");
                case "2":
                    return booking.sortHotels("rating");
                case "3":
                    return booking.sortHotels("rating and price");
                case "4":
                    return booking.sortHotels("size of rooms");
                case "5":
                    return booking.sortHotels("top reviews");
                case "0":
                    return null;
                default:
                    view.displayMessage("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public List<Hotel> filterHotels(List<Hotel> hotels) {
        boolean exit = false;
        int rating = 0;
        double priceMin = 0, priceMax = 0;
        Date startDate = null, endDate = null;
        List<String> amenitiesHotel = new ArrayList<>(), filters = new ArrayList<>(), amenitiesRoom = new ArrayList<>();
        String location = null;
        while (!exit) {
            view.displayMessage("Choose filters until satisfide then choose Exit:");
            String f = this.scanner.nextLine();
            switch (f.toLowerCase()) {
                case "1":
                    view.displayMessage("Enter filters (comma separated, WIFI, PARKING, SPA, GYM, POOL, RESTAURANT):");
                    String filtersStr = this.scanner.nextLine();
                    amenitiesHotel = Arrays.asList(filtersStr.split(","));
                    f = "amenities to hotel";
                    filters.add(f);
                    break;
                case "2":
                    view.displayMessage("Enter filters (comma separated, balcony, kitchen, tv, wifi, air conditioning, minibar):");
                    String filtersStr2 = this.scanner.nextLine();
                    amenitiesRoom = Arrays.asList(filtersStr2.split(","));
                    f = "amenities to room";
                    filters.add(f);
                    break;
                case "3":
                    startDate = getDateInput("Enter start date (yyyy-MM-dd):");
                    endDate = getDateInput("Enter end date (yyyy-MM-dd):");
                    f = "date";
                    filters.add(f);
                    break;
                case "4":
                    view.displayMessage("Enter Location:");
                    location = this.scanner.nextLine();
                    f = "location";
                    filters.add(f);
                    break;
                case "5":
                    priceMin = getDoubleInput("Enter minimum price:");
                    priceMax = getDoubleInput("Enter maximum price:");
                    f = "price";
                    filters.add(f);
                    break;
                case "6":
                    rating = getIntInput("Enter star rating:");
                    f = "rating";
                    filters.add(f);
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        return booking.filterHotels(hotels, filters, amenitiesHotel, amenitiesRoom, startDate, endDate, priceMin, priceMax, rating, location);
    }

    private Date getDateInput(String message) {
        view.displayMessage(message+" format (dd-MM-yy)");
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        while (date == null) {

            String dateStr = this.scanner.nextLine();
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                view.displayMessage("Invalid date format. Please enter dates in the format dd-MM-yy.");
                view.displayMessage(message);
            }
        }
        return date;
    }

    private int getIntInput(String message) {
        view.displayMessage(message);
        int number = -1;
        while (number < 0) {
            try {
                number = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
                view.displayMessage(message);
            }
        }
        return number;
    }

    private double getDoubleInput(String message) {
        view.displayMessage(message);
        double number = -1;
        while (number < 0) {
            try {
                number = Double.parseDouble(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
                view.displayMessage(message);
            }
        }
        return number;
    }

    private long getLongInput(String message) {
        view.displayMessage(message);
        long number = -1;
        while (number < 0) {
            try {
                number = Long.parseLong(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
                view.displayMessage(message);
            }
        }
        return number;
    }

    private void Login() {
        long personId = -1, phone = -1;
        String name = null, email = null, choise = null;
        boolean exit = false;
        while (!exit) {
            view.displayLogin();
            choise = this.scanner.nextLine();
            switch (choise.toLowerCase()) {
                case "1":
                    personId = getLongInput("Enter user ID:");
                    choise = "id";
                    exit = true;
                    break;
                case "2":
                    view.displayMessage("Enter user name:");
                    name = this.scanner.nextLine();
                    choise = "name";
                    exit = true;
                    break;
                case "3":
                    view.displayMessage("Enter user email:");
                    email = this.scanner.nextLine();
                    choise = "email";
                    exit = true;
                    break;
                case "4":
                    phone = getLongInput("Enter user phone number:");
                    exit = true;
                    choise = "phone";
                    break;
                case "0":
                    exit = true;
                    choise = "exit";
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again");
            }
        }
        if (choise.equals("exit")) {
            return;
        }
        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        personId = booking.login(personId, choise, name, email, phone, password);
        if (personId == -1) {
            view.displayMessage("Invalid login credentials.");
            return;
        }
        Person person = booking.findPersonById(personId);
        switch (person) {
            case null -> {
                view.displayMessage("Person not found.");
                return;
            }
            case Manager manager -> {
                view.displayMessage("\nManager " + person.getName() + " Log In.");
                ManagerActions(manager.getId());
            }
            case User user -> {
                view.displayMessage("\nUser " + person.getName() + " Log In.");
                UserActions(user.getId());
            }
            default -> {
                view.displayMessage("Invalid login credentials.");
            }
        }
    }

    private void Registration() {
        view.displayMessage("Enter User Or Manager");
        String type = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user") || type.equalsIgnoreCase("manager")) {
            view.displayMessage("Enter name:");
            String name = this.scanner.nextLine();
            long phone = getLongInput("Enter phone number:");
            view.displayMessage("Enter email:");
            String email = this.scanner.nextLine();
            view.displayMessage("Enter password:");
            String password = this.scanner.nextLine();
            booking.addPerson(type, name, phone, email, password);
            view.displayMessage("Registration successful!");
        } else {
            view.displayMessage("Invalid choice. Please try again.");
        }
    }

    private void displayProfile(long userId) {
        Person person = booking.findPersonById(userId);
        view.displayMessage("Profile:\n" + person.toString());
    }

    private void UserActions(long userId) {
        boolean exit = false;
        while (!exit) {
            view.displayMenuUser(); // menu for user actions
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    displayProfile(userId); // View user profile
                    break;
                case 2:
                    SearchHotels(); // Search hotels
                    break;
                case 3:
                    SearchRoomsInHotel(); // Search rooms in a specific hotel
                    break;
                case 4:
                    ViewWishlist(userId); // View wishlist
                    break;
                case 5:
                    RemoveFromWishlist(userId); // Remove from wishlist
                    break;
                case 6:
                    ViewPastOrders(userId); // View past orders
                    return;
                case 7:
                    MakeReservation(userId); // Make a reservation
                    break;
                case 8:
                    payReservation(userId); // Pay for a reservation
                    break;
                case 9:
                    cancelReservation(userId); // Cancel a reservation
                    break;
                case 10:
                    ViewNotificationsType(userId); // View Notifications Type
                    break;
                case 11:
                    AddNotificationType(userId); // Add Notification Type
                    break;
                case 12:
                    RemoveNotificationType(userId); // Remove Notification Type
                    break;
                case 0:
                    booking.logOut(userId);
                    view.displayMessage("log out");// log out
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void cancelReservation(long userId) {
        int reservationId = getIntInput("Enter reservation id");
        String s = booking.cancelReservation(userId, reservationId);
        view.displayMessage(s);
    }

    private void payReservation(long userId) {
        int reservationId = getIntInput("Enter reservation id");
        double amountreservation = booking.findResarvationById(userId, reservationId);
        if (amountreservation == -1) {
            view.displayMessage("Reservation not found.");
            return;
        } else {
            view.displayMessage("The amount of the reservation is: " + amountreservation);
        }
        view.displayMessage("Enter no to cancel payment or any key to continue.");
        String s = this.scanner.nextLine();
        if (s.equalsIgnoreCase("no")) {
            return;
        }
        long cardNumber = 0;
        int cvv = 0;
        Date expirationDate = null;
        String cardHolderName = null, paymentMethod = null;
        boolean exit = false;
        while (!exit) {
            view.displayMessage("Enter payment method:  credit card, debit card, apple pay, google pay, paypal, bit");
            paymentMethod = this.scanner.nextLine();
            switch (paymentMethod.toLowerCase()) {
                case "credit card", "debit card", "apple pay", "google pay" -> {
                    cardNumber = getLongInput("Enter card number");
                    cvv = getIntInput("Enter cvv");
                    expirationDate = getDateInput("Enter expiration date");
                    view.displayMessage("Enter card holder name");
                    cardHolderName = this.scanner.nextLine();
                    exit = true;
                    break;
                }
                case "paypal", "Bit" -> {
                    cardNumber = getLongInput("Enter card number");
                    exit = true;
                    break;
                }
                default -> {
                    view.displayMessage("Invalid choice. Please try again.");
                    break;
                }
            }
        }
        booking.payReservation(userId, reservationId, paymentMethod, amountreservation, cardNumber, cvv, expirationDate, cardHolderName);
        view.displayMessage("Payment successful.");
        view.displayMessage("Enter review if you want to add one or press enter to skip");
        String review = this.scanner.nextLine();
        if (review.equalsIgnoreCase("review")) {
            addReview(userId, reservationId);
        }
    }

    private void addReview(long userId, int reservationId) {
        int rating = getIntInput("Enter rating");
        view.displayMessage("Enter review");
        String review = this.scanner.nextLine();
        Date date = new Date();
        booking.addReview(userId, reservationId, rating, review, date);
    }

    private void SearchRoomsInHotel() {
        long roomId = getLongInput("Enter id number of room");
        view.displayMessage(booking.findRoomById(roomId).toString());
    }

    private void AddNotificationType(long personId) {
        view.displayMessage("Enter notification type to add: SMS,email,whatsapp");
        while (true) {
            String s = this.scanner.nextLine();
            if (s.equalsIgnoreCase("SMS") || s.equalsIgnoreCase("email") || s.equalsIgnoreCase("whatsapp")) {
                booking.addNotificationType(personId, s);
                break;
            } else {
                view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void RemoveNotificationType(long personId) {
        view.displayMessage("Enter notification type to remove: SMS,email,whatsapp");
        while (true) {
            String s = this.scanner.nextLine();
            if (s.equalsIgnoreCase("SMS") || s.equalsIgnoreCase("email") || s.equalsIgnoreCase("whatsapp")) {
                booking.removeNotificationType(personId, s);
                break;
            } else {
                view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void ViewNotificationsType(long userId) {
        view.displayMessage("You can be notify by:");
        List<String> s = booking.notificationType(userId);
        if (s.isEmpty()) {
            view.displayMessage("None");
        } else {
            for (int i = 0; i < s.size(); i++) {
                view.displayMessage(s.get(i).toString());
            }
        }
    }

    private void MakeReservation(long userId) {
        long roomId = getLongInput("Enter room id");
        Date checkInDate = getDateInput("Enter check in date");
        Date checkOutDate = getDateInput("Enter check out date");
        int numAdults = getIntInput("Enter number of adults");
        int numChildren = getIntInput("Enter number of children");
        long reservation = booking.makeReservation(userId, roomId, checkInDate, checkOutDate, numAdults, numChildren);
        if (reservation != -1) {
            view.displayMessage("Reservation successful. Your reservation ID is " + reservation);
        } else {
            view.displayMessage("Reservation not successful.");
        }
    }

    private void RemoveFromWishlist(long userId) {
        long reservationId = getLongInput("Enter reservation id");
        if(booking.removeFromWishlist(userId, reservationId)){
            view.displayMessage("Reservation removed from wishlist.");
        } else {
            view.displayMessage("Reservation not found in wishlist.");
        }
    }

    private void ViewWishlist(long userId) {
        List<Reservation> wishlist = booking.getWishList(userId);
        if (wishlist.isEmpty()) {
            view.displayMessage("Your wishlist is currently empty.");
        } else {
            view.displayMessage("Your Wishlist:");
            for (Reservation reservation : wishlist) {
                view.displayMessage(reservation.toString());
            }
        }
    }

    private void ViewPastOrders(long userId) {
        List<Reservation> pastOrders = booking.getPastOrder(userId);
        if (pastOrders.isEmpty()) {
            view.displayMessage("You don't have any past orders.");
        } else {
            view.displayMessage("Your Past Orders:");
            for (Reservation reservation : pastOrders) {
                view.displayMessage(reservation.toString());
            }
        }
    }

    private void ManagerActions(long managerId) {
        boolean exit = false;
        while (!exit) {
            view.displayMenuManager(); // menu for manager actions
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    displayProfile(managerId); // View manager profile
                    break;
                case 2:
                    CreateHotel(managerId); // Create hotel
                    break;
                case 3:
                    CreateRoom(managerId); // Create room
                    break;
                case 4:
                    SendNotificationToAllInHotel(managerId); // Send notification to all users in specific hotel
                    break;
                case 5:
                    ViewHotels(managerId); // View hotel
                    break;
                case 6:
                    ViewAllRoomInHotel(managerId); // View room
                    break;
                case 7:
                    ViewReservationInHotel(managerId); // View reservation
                    break;
                case 8:
                    ViewNotificationsType(managerId); // View Notifications Type
                    break;
                case 9:
                    AddNotificationType(managerId); // Add notification
                    break;
                case 10:
                    RemoveNotificationType(managerId); // Remove notification
                    break;
                case 0:
                    booking.logOut(managerId);
                    view.displayMessage("log out");// log out
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void CreateHotel(long managerId) {
        try {
            view.displayMessage("Enter hotel name:");
            String hotelName = this.scanner.nextLine();
            view.displayMessage("Enter hotel location:");
            String location = this.scanner.nextLine();
            view.displayMessage("Enter hotel description:");
            String description = this.scanner.nextLine();
            long hotelId = booking.createHotel(managerId, hotelName, location, description);
            if (hotelId != -1) {
                view.displayMessage("Hotel created successfully. Hotel ID is " + hotelId);
            } else {
                view.displayMessage("Hotel not created.");
            }
        } catch (Exception e) {
            view.displayMessage("Invalid Input. Try Again");
        }
    }

    private void CreateRoom(long managerId) {
        try {
            view.displayMessage("Enter hotel id:");
            long hotelId = Long.parseLong(this.scanner.nextLine());
            if (managerId != booking.getHotel(managerId, hotelId).getManager().getId()) {
                view.displayMessage("You are not the manager of this hotel.");
                return;
            }
            RoomFactory.RoomType roomType = getRoomType();
            RoomFactory.View roomView = getRoomView();
            int numAdults = getIntInput("Enter number of adults:");
            int numChildren = getIntInput("Enter number of children:");
            long roomId = booking.createRoom(managerId, hotelId, roomType, roomView, numAdults, numChildren);
            if (roomId != -1) {
                view.displayMessage("Room created successfully. Room ID is " + roomId);
            } else {
                view.displayMessage("Room not created.");
            }
        } catch (NumberFormatException e) {
            view.displayMessage("Invalid Input. Try Again");
        }
    }

    private RoomFactory.RoomType getRoomType() {
        view.displayRoomFactory();
        int choice = getIntInput("Enter room type:");
        switch (choice) {
            case 1:
                return RoomFactory.RoomType.SINGLE;
            case 2:
                return RoomFactory.RoomType.DOUBLE;
            case 3:
                return RoomFactory.RoomType.SUITE;
            case 4:
                return RoomFactory.RoomType.KING;
            case 5:
                return RoomFactory.RoomType.TWIN;
            case 6:
                return RoomFactory.RoomType.STUDIO;
            case 7:
                return RoomFactory.RoomType.ACCESSIBLE;
            default:
                return null;
        }
    }

    private RoomFactory.View getRoomView() {
        view.displayRoomFactoryView();
        int choice = getIntInput("Enter number for room view:");
        switch (choice) {
            case 1:
                return RoomFactory.View.CITY;
            case 2:
                return RoomFactory.View.SEA;
            default:
                return null;
        }
    }

    private void SendNotificationToAllInHotel(long managerId) {
        long hotelId = getLongInput("Enter hotel id:");
        view.displayMessage("Enter notification message:");
        String message = this.scanner.nextLine();
        booking.sendNotificationToHotelUsers(managerId, hotelId, message);
    }

    private void ViewAllRoomInHotel(long managerId) {
        long hotelId = getLongInput("Enter hotel id:");
        List<Room> rooms = booking.getHotel(managerId, hotelId).getRooms();
        view.displayRoomsManager(rooms);
    }

    private void ViewReservationInHotel(long managerId) {
        long hotelId = getLongInput("Enter hotel id:");

        List<Reservation> reservations = booking.getHotel(managerId, hotelId).getReservations();
        view.displayReservationsManager(reservations);
    }

    private void ViewHotels(long managerId) {
        List<Hotel> hotels = booking.getHotels(managerId);
        view.displayHotelsManager(hotels);
    }

    private void createTenHotel() { //create for the deta base 2 manager ,each have 5 hotel ,each hotel have 7 room
        booking.addPerson("manager", "manager1", 123456789, "manager1@example.com", "password1");
        booking.login(0, "id", null, null, (long) 0, "password1");
        booking.createHotels(0, "name", "", "");
        booking.addNotificationType(0, "email");
        booking.logOut(0);

        booking.addPerson("manager", "manager2", 987654321, "manager2@example.com", "password2");
        booking.login(1, "id", null, null, (long) 0, "password2");
        booking.createHotels(1, "name", "", "");
        booking.logOut(1);

        booking.addPerson("user", "daniel", 0, "email", "123");
    }
}
