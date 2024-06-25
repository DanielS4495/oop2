
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
                    UserLogin();//go to action after login
                    break;
                case 3:
                    ManagerLogin();//go to action after login
                    break;
                case 4:
                    ForgotUsername();//forgot username
                    break;
                case 5:
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
        view.displayMessage("Enter User or Manager:");
        String type = this.scanner.nextLine();
        view.displayMessage("Enter name:");
        String name = this.scanner.nextLine();
        view.displayMessage("Enter email:");
        String email = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user")) {
            String password = booking.forgotPasswordUser(name, email);
            if (password != null) {
                view.displayMessage("password sent to your email.");
            } else {
                view.displayMessage("User not found.");
            }
        } else if (type.equalsIgnoreCase("manager")) {
            String password = booking.forgotPasswordManager(name, email);
            if (password != null) {
                view.displayMessage("password sent to your email.");
            } else {
                view.displayMessage("Manager not found.");
            }
        }

    }

    private void ForgotUsername() {
        view.displayMessage("Enter User or Manager:");
        String type = this.scanner.nextLine();
        view.displayMessage("Enter email:");
        String email = this.scanner.nextLine();
        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user")) {
            String userName = booking.forgotUsernameUser(email, password);
            if (userName != null) {
                view.displayMessage("Username sent to your email.");
            } else {
                view.displayMessage("User not found.");
            }
        } else if (type.equalsIgnoreCase("manager")) {
            String userName = booking.forgotUsernameManager(email, password);
            if (userName != null) {
                view.displayMessage("Username sent to your email.");
            } else {
                view.displayMessage("Manager not found.");
            }
        }

    }

    private void SearchHotels() {
        view.displayMessage("Enter how you want to search: all or by sort or by filter or both");
        List<Hotel> hotels = new ArrayList<>();
        boolean exit = false;
        while (!exit) {
            switch (this.scanner.nextLine().toLowerCase()) {
                case "all":
                    hotels = booking.getAllHotels();
                    exit = true;
                    break;
                case "sort":
                    view.displayMenuSort();
                    String sort1 = this.scanner.nextLine();
                    hotels = booking.sortHotels(sort1);
                    exit = true;
                    break;
                case "filter":
                    view.displayMenuFilter();
                    hotels = filterHotels();
                    exit = true;
                    break;
                case "both":
                    view.displayMessage("first we going by sort then filter");
                    view.displayMenuSort();
                    String sort2 = this.scanner.nextLine();
                    hotels = booking.sortHotels(sort2);
                    view.displayMessage("now we going by filter");
                    view.displayMenuFilter();
                    hotels = filterHotels();
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }
        view.displaySearchHotels(hotels);
    }

    public List<Hotel> filterHotels() {
        boolean exit = false;
        int rating = 0;
        double priceMin = 0, priceMax = 0;
        Date startDate = null, endDate = null;
        List<String> amenitiesHotel = new ArrayList<>(), filters = new ArrayList<>(), amenitiesRoom = new ArrayList<>();
        String location = null;
        while (!exit) {
            view.displayMessage("Choose the filter not the number until satisfide then choose Exit:");
            String f = this.scanner.nextLine();
            filters.add(f);
            switch (f.toLowerCase()) {
                case "amenities to hotel":
                    view.displayMessage("Enter filters (comma separated, WIFI, PARKING, SPA, GYM, POOL, RESTAURANT):");
                    String filtersStr = this.scanner.nextLine();
                    amenitiesHotel = Arrays.asList(filtersStr.split(","));
                    break;
                case "amenities to room":
                    view.displayMessage("Enter filters (comma separated, balcony, kitchen, tv, wifi, air conditioning, minibar):");
                    String filtersStr2 = this.scanner.nextLine();
                    amenitiesRoom = Arrays.asList(filtersStr2.split(","));
                    break;
                case "date":
                    startDate = getDateInput("Enter start date (yyyy-MM-dd):");
                    endDate = getDateInput("Enter end date (yyyy-MM-dd):");
                    break;
                case "location":
                    view.displayMessage("Enter Location:");
                    location = this.scanner.nextLine();
                    break;
                case "price":
                    view.displayMessage("Enter minimum price:");
                    priceMin = getDoubleInput("Enter minimum price:");
                    view.displayMessage("Enter maximum price:");
                    priceMax = getDoubleInput("Enter maximum price:");
                    break;
                case "rating"://maybe exatly 
                    view.displayMessage("Enter minimum star rating:");
                    rating = getIntInput("Enter minimum star rating:");
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        return booking.filterHotels(filters, amenitiesHotel, amenitiesRoom, startDate, endDate, priceMin, priceMax, rating, location);
    }

    private Date getDateInput(String message) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (date == null) {
            view.displayMessage(message);
            String dateStr = this.scanner.nextLine();
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                view.displayMessage("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
            }
        }
        return date;
    }

    private int getIntInput(String message) {
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

    private void UserLogin() {
        long userId = -1, userPhone = -1;
        String userName = null, userEmail = null, choise = null;
        boolean exit = false;
        while (!exit) {
            view.displayMessage("Choose which to log in: Id,name,email,phone");
            choise = this.scanner.nextLine();
            switch (choise.toLowerCase()) {
                case "id":
                    view.displayMessage("Enter user ID:");
                    userId = getLongInput("Enter user ID:");
                    exit = true;
                    break;
                case "name":
                    view.displayMessage("Enter user name:");
                    userName = this.scanner.nextLine();
                    exit = true;
                    break;
                case "email":
                    view.displayMessage("Enter user email:");
                    userEmail = this.scanner.nextLine();
                    exit = true;
                    break;
                case "phone":
                    view.displayMessage("Enter user phone number:");
                    userPhone = Long.parseLong(this.scanner.nextLine());
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again");
            }
        }

        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        userId = booking.loginUser(userId, choise, userName, userEmail, userPhone, password);
        if (userId != -1) {
            view.displayMessage("User " + userName + " Log In.");
            UserActions(userId);
        } else {
            view.displayMessage("Invalid login credentials.");
        }
    }

    private void Registration() {
        view.displayMessage("Enter User Or Manager");
        String type = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user") || type.equalsIgnoreCase("manager")) {
            view.displayMessage("Enter name:");
            String name = this.scanner.nextLine();
            view.displayMessage("Enter phone number:");
            long phone = getLongInput("Enter phone number:");
            view.displayMessage("Enter email:");
            String email = this.scanner.nextLine();
            view.displayMessage("Enter password:");
            String password = this.scanner.nextLine();
            if (type.equalsIgnoreCase("user")) {
                booking.addUser(name, phone, email, password);
            } else if (type.equalsIgnoreCase("manager")) {
                booking.addManager(name, phone, email, password);
            }
            view.displayMessage("Registration successful!");
        } else {
            view.displayMessage("Invalid choice. Please try again.");
        }
    }

    private void ManagerLogin() {
        long managerId = -1, managerPhone = -1;
        String managerName = null, managerEmail = null, choise = null;
        boolean exit = false;
        while (!exit) {
            view.displayMessage("Choose which to log in: Id,name,email,phone");
            choise = this.scanner.nextLine();
            switch (choise.toLowerCase()) {
                case "id":
                    view.displayMessage("Enter manager ID:");
                    managerId = getLongInput("Enter manager ID:");
                    exit = true;
                    break;
                case "name":
                    view.displayMessage("Enter manager name:");
                    managerName = this.scanner.nextLine();
                    exit = true;
                    break;
                case "email":
                    view.displayMessage("Enter manager email:");
                    managerEmail = this.scanner.nextLine();
                    exit = true;
                    break;
                case "phone":
                    view.displayMessage("Enter manager phone number:");
                    managerPhone = getLongInput("Enter manager phone number:");
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again");
            }
        }

        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        managerId = booking.loginManager(managerId, choise, managerName, managerEmail, managerPhone, password);// this.manager = booking.findManagerById(managerId);
        if (managerId != -1) {
            view.displayMessage("Manager " + managerName + " Log In.");
            ManagerActions(managerId);
        } else {
            view.displayMessage("Invalid login credentials.");
        }
    }

    private void displayUserProfile(long userId) {
        User user = booking.findUserById(userId);
        view.displayMessage("User Profile:\n" + user.toString());
    }

    private void displayManagerProfile(long managerId) {
        Manager manager = booking.findManagerById(managerId);
        view.displayMessage("Manager Profile:\n" + manager.toString());
    }

    private void UserActions(long userId) {
        boolean exit = false;
        while (!exit) {
            view.displayMenuUser(); // menu for user actions
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    displayUserProfile(userId); // View user profile
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
                    AddNotificationType(userId); // Remove Notification Type
                    break;
                case 0:
                    booking.logOutUser(userId);
                    view.displayMessage("log out");// log out
                    exit = true;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void cancelReservation(long userId) {
        view.displayMessage("Enter reservation id");
        int reservationId = getIntInput("Enter reservation id");
        String s = booking.cancelReservation(userId, reservationId);
        view.displayMessage(s);
    }

    private void payReservation(long userId) {
        view.displayMessage("Enter reservation id");
        int reservationId = getIntInput("Enter reservation id");
        view.displayMessage("Enter amount");
        double amount = getDoubleInput("Enter amount");
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
                    view.displayMessage("Enter card number");
                    cardNumber = getLongInput("Enter card number");
                    view.displayMessage("Enter cvv");
                    cvv = getIntInput("Enter cvv");
                    view.displayMessage("Enter expiration date");
                    expirationDate = getDateInput("Enter expiration date");
                    view.displayMessage("Enter card holder name");
                    cardHolderName = this.scanner.nextLine();
                    exit = true;
                    break;
                }
                case "paypal", "Bit" -> {
                    view.displayMessage("Enter card number");
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
        booking.payReservation(userId, reservationId, paymentMethod, amount, cardNumber, cvv, expirationDate, cardHolderName);
        view.displayMessage("Payment successful.");
        view.displayMessage("Enter review if you want to add one or press enter to skip");
        String review = this.scanner.nextLine();
        if(review.equals("review"))
        addReview(userId,reservationId);
    }

    private void addReview(long userId, int reservationId) {
        view.displayMessage("Enter rating");
        int rating = getIntInput("Enter rating");
        view.displayMessage("Enter review");
        String review = this.scanner.nextLine();
        Date date = new Date();
        booking.addReview(userId, reservationId, rating, review,date);
    }

    private void SearchRoomsInHotel() {
        view.displayMessage("Enter id number of hotel");
        long hotelId = getLongInput("Enter id number of hotel");
        view.displayMessage("Enter id number of room");
        long roomId = getLongInput("Enter id number of room");
        view.displayMessage(booking.findRoomById(roomId, hotelId).toString());
    }

    private void AddNotificationType(long userId) {
        view.displayMessage("Enter notification type to add: SMS,email,whatsapp");
        booking.addNotificationType(userId, this.scanner.nextLine());
    }

    private void ViewNotificationsType(long userId) {
        view.displayMessage("You can be notify by:");
        List<String> s = booking.notificationType(userId);
        if (s.isEmpty()) {
            view.displayMessage("None");
        } else {
            for (int i = 0; i < s.size(); i++) {
                view.displayMessage(s.get(i));
            }
        }
    }

    private void MakeReservation(long userId) {
        view.displayMessage("Enter hotel id");
        long hotelId = getLongInput("Enter hotel id");
        view.displayMessage("Enter room id");
        long roomId = getLongInput("Enter room id");
        view.displayMessage("Enter check in date");
        Date checkInDate = getDateInput("Enter check in date");
        view.displayMessage("Enter check out date");
        Date checkOutDate = getDateInput("Enter check out date");
        view.displayMessage("Enter number of adults");
        int numAdults = getIntInput("Enter number of adults");
        view.displayMessage("Enter number of children");
        int numChildren = getIntInput("Enter number of children");
        long reservation = booking.makeReservation(userId, hotelId, roomId, checkInDate, checkOutDate, numAdults, numChildren);
        view.displayMessage("Reservation successful. Your reservation ID is " + reservation);
    }

    private void RemoveFromWishlist(long userId) {
        view.displayMessage("Enter reservation id");
        long reservationId = getLongInput("Enter reservation id");
        booking.removeFromWishlist(userId, reservationId);
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
                    displayManagerProfile(managerId); // View manager profile
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
                    AddNotification(managerId); // Add notification
                    break;
                case 9:
                    RemoveNotification(managerId); // Remove notification
                    break;
                case 0:
                    booking.logOutManager(managerId);
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
            if (managerId != booking.getHotel(managerId, hotelId).getManager().getManagerId()) {
                view.displayMessage("You are not the manager of this hotel.");
                return;
            }
            RoomFactory.RoomType roomType = getRoomType();
            RoomFactory.View roomView = getRoomView();
            view.displayMessage("Enter number of adults:");
            int numAdults = getIntInput("Enter number of adults:");
            view.displayMessage("Enter number of children:");
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

    private void AddNotification(long managerId) {
        view.displayMessage("Enter notification type to add: SMS,email,whatsapp");
        booking.addNotificationTypeManager(managerId, this.scanner.nextLine());
    }

    private void RemoveNotification(long managerId) {
        view.displayMessage("Enter notification type to remove: SMS,email,whatsapp");
        booking.removeNotificationTypeManager(managerId, this.scanner.nextLine());
    }

    private void SendNotificationToAllInHotel(long managerId) {
        view.displayMessage("Enter hotel id:");
        long hotelId = getLongInput("Enter hotel id:");
        view.displayMessage("Enter notification message:");
        String message = this.scanner.nextLine();
        booking.sendNotification(managerId, hotelId, message);
    }

    private void ViewAllRoomInHotel(long managerId) {
        view.displayMessage("Enter hotel id:");
        long hotelId = getLongInput("Enter hotel id:");
        List<Room> rooms = booking.getHotel(managerId, hotelId).getRooms();
        view.displayRoomsManager(rooms);
    }

    private void ViewReservationInHotel(long managerId) {
        view.displayMessage("Enter hotel id:");
        long hotelId = getLongInput("Enter hotel id:");

        List<Reservation> reservations = booking.getHotel(managerId, hotelId).getReservations();
        view.displayReservationsManager(reservations);
    }

    private void ViewHotels(long managerId) {
        List<Hotel> hotels = booking.getHotels(managerId);
        view.displayHotelsManager(hotels);
    }

    private void createTenHotel() { //create for the deta base 2 manager ,each have 5 hotel ,each hotel have 7 room
        booking.addManager("manager1", 123456789, "manager1@example.com", "password1");
        booking.loginWithNameManager("manager1", "password1");
        booking.createHotels(0, "name", "", "");
        booking.logOutManager(0);

        booking.addManager("manager2", 987654321, "manager2@example.com", "password2");
        booking.loginWithNameManager("manager2", "password2");
        booking.createHotels(1, "name", "", "");
        booking.logOutManager(1);
    }
}
