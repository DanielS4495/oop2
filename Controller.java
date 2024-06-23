
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {

    // private SearchService searchService;
    private SystemBooking booking;
    private View view;
    private long userId;
    private long managerId;
    private Scanner scanner;

    public Controller(View view) {
        this.booking = SystemBooking.getInstance();
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    ////////////////////////// when i do log in it sent me to action there i have the menu 
    // public void start() {
    //     this.scanner this.scanner = new this.scanner(System.in);
    //     while (true) {
    //         view.displayMenu();
    //         int choice = getIntInput(this.scanner, "Enter number choice:");
    //         switch (choice) {
    //             case 1:
    //                 SearchHotels(this.scanner);
    //                 break;
    //             case 2:
    //                 SearchRoomsInHotel(this.scanner);
    //                 break;
    //             case 3:
    //                 BookHotel(this.scanner);
    //                 break;
    //             // case 4:
    //             // long choice2 = getLongInput(this.scanner, "Enter user id:");
    //             //     UserActions(this.scanner,choice2);
    //             //     break;
    //             // case 5:
    //             // long choice3 = getLongInput(this.scanner, "Enter manager id:");
    //             //     ManagerActions(this.scanner,choice3);
    //             //     break;
    //             case 6:
    //                 view.displayMessage("log out");// log out
    //                 this.scanner.close();
    //                 System.exit(0);
    //                 break;
    //             default:
    //                 view.displayMessage("Invalid choice. Please try again.");
    //         }
    //     }
    // }
    public void start() {
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
                    ForgotUsername();
                    break;
                case 5:
                    ForgotPassword();
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
        view.displayMessage("Enter how you want to search by sort or filter or both");
        List<Hotel> hotels = null;
        switch (this.scanner.nextLine().toLowerCase()) {
            case "sort":
                view.displayMenuSort();
                String sort1 = this.scanner.nextLine();
                hotels = booking.sortHotels(sort1);
                break;
            case "filter":
                view.displayMenuFilter();
                hotels = filterHotels();
                break;
            case "both":
                view.displayMessage("first we going by sort then filter");
                view.displayMenuSort();
                String sort2 = this.scanner.nextLine();
                hotels = booking.sortHotels(sort2);
                view.displayMessage("now we going by filter");
                view.displayMenuFilter();
                hotels = filterHotels();
                break;
            default:
                view.displayMessage("Invalid choice. Please try again.");
        }
        if (hotels != null) {
            view.displayHotels(hotels);
        }
    }

    public List<Hotel> filterHotels() {
        boolean exit = false;
        int rating = 0;
        double priceMin = 0, priceMax = 0;
        Date startDate = null, endDate = null;
        List<String> amenities = null, filters = null;
        String location = null;
        while (!exit) {
            String f = this.scanner.nextLine();
            filters.add(f);
            switch (f.toLowerCase()) {
                case "amenities":
                    view.displayMessage("Enter filters (comma separated, e.g., Breakfast,Balcony):");
                    String filtersStr = this.scanner.nextLine();
                    amenities = Arrays.asList(filtersStr.split(","));
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
        return booking.filterHotels(filters, amenities, startDate, endDate, priceMin, priceMax, rating, location);
    }

    // private void SearchRoomsInHotel() {
    //     view.displayMessage("Enter hotel name:");
    //     String hotelName = this.scanner.nextLine();
    //     Date startDate = getDateInput("Enter start date (yyyy-MM-dd):");
    //     Date endDate = getDateInput("Enter end date (yyyy-MM-dd):");
    //     int adults = getIntInput("Enter number of adults:");
    //     int children = getIntInput("Enter number of children:");
    //     double minPrice = getDoubleInput("Enter minimum price:");
    //     double maxPrice = getDoubleInput("Enter maximum price:");
    //     Hotel hotelToSearch = null;
    //     for (Hotel hotel : searchService.getHotels()) {
    //         if (hotel.getName().equalsIgnoreCase(hotelName)) {
    //             hotelToSearch = hotel;
    //             break;
    //         }
    //     }
    //     if (hotelToSearch == null) {
    //         view.displayMessage("Hotel not found.");
    //         return;
    //     }
    //     List<Room> results = searchService.searchRoomsInHotel(hotelToSearch, startDate, endDate, adults, children, minPrice, maxPrice);
    //     view.displayRooms(results);
    // }
    // private void BookHotel(
    //      this.scanner this.scanner) {
    //     view.displayMessage("Enter hotel name to book:");
    //     String hotelName = this.scanner.nextLine();
    //     Hotel hotelToBook = null;
    //     for (Hotel hotel : searchService.getHotels()) {
    //         if (hotel.getName().equalsIgnoreCase(hotelName)) {
    //             hotelToBook = hotel;
    //             break;
    //         }
    //     }
    //     if (hotelToBook == null) {
    //         view.displayMessage("Hotel not found.");
    //         return;
    //     }
    //     view.displayMessage("Enter room type:");
    //     String roomType = this.scanner.nextLine();
    //     Room roomToBook = null;
    //     for (Room room : hotelToBook.getRooms()) {
    //         if (room.getType().equalsIgnoreCase(roomType)) {
    //             roomToBook = room;
    //             break;
    //         }
    //     }
    //     if (roomToBook == null) {
    //         view.displayMessage("Room type not found.");
    //         return;
    //     }
    //     Date bookingStartDate = getDateInput(this.scanner, "Enter start date (yyyy-MM-dd):");
    //     Date bookingEndDate = getDateInput(this.scanner, "Enter end date (yyyy-MM-dd):");
    //     if (!roomToBook.isAvailable(bookingStartDate, bookingEndDate)) {
    //         view.displayMessage("Room is not available for the selected dates.");
    //         return;
    //     }
    //     roomToBook.book(bookingStartDate, bookingEndDate);
    //     view.displayMessage("Hotel booked successfully!");
    // }
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
            view.displayMessage(message);
            try {
                number = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private double getDoubleInput(String message) {
        double number = -1;
        while (number < 0) {
            view.displayMessage(message);
            try {
                number = Double.parseDouble(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private long getLongInput(String message) {
        long number = -1;
        while (number < 0) {
            view.displayMessage(message);
            try {
                number = Long.parseLong(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private void UserLogin() {
        view.displayMessage("Choose which to log in: Id,name,email,phone");
        String choise = this.scanner.nextLine();
        long userId = -1, userPhone = -1;
        String userName = null, userEmail = null;

        switch (choise.toLowerCase()) {
            case "id":
                view.displayMessage("Enter user ID:");
                userId = Long.parseLong(this.scanner.nextLine());
                break;
            case "name":
                view.displayMessage("Enter user name:");
                userName = this.scanner.nextLine();
                break;
            case "email":
                view.displayMessage("Enter user email:");
                userEmail = this.scanner.nextLine();
                break;
            case "phone":
                view.displayMessage("Enter user phone number:");
                userPhone = Long.parseLong(this.scanner.nextLine());
                break;
            default:
                throw new AssertionError();
        }

        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        if (booking.loginUser(userId, choise, userName, userPhone, password)) {
            // this.user = booking.findUserById(userId);
            UserActions(userId);
        } else {
            view.displayMessage("Invalid login credentials.");
        }
    }

    private void Registration() {
        view.displayMessage("Enter User Or Manager");
        String type = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user") || type.equalsIgnoreCase("manager")) {
            view.displayMessage("Invalid type.");
            return;
        }
        view.displayMessage("Enter name:");
        String name = this.scanner.nextLine();
        view.displayMessage("Enter phone number:");
        String phone = this.scanner.nextLine();
        view.displayMessage("Enter email:");
        String email = this.scanner.nextLine();
        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        if (type.equalsIgnoreCase("user")) {
            booking.addUser(name, Long.parseLong(phone), email, password);
        } else if (type.equalsIgnoreCase("manager")) {
            booking.addManager(name, Long.parseLong(phone), email, password);
        }
        view.displayMessage("Registration successful!");
    }

    private void ManagerLogin() {
        view.displayMessage("Choose which to log in: Id,name,email,phone");
        String choise = this.scanner.nextLine();
        long managerId = -1, managerPhone = -1;
        String managerName = null, managerEmail = null;

        switch (choise.toLowerCase()) {
            case "id":
                view.displayMessage("Enter manager ID:");
                managerId = Long.parseLong(this.scanner.nextLine());
                break;
            case "name":
                view.displayMessage("Enter manager name:");
                managerName = this.scanner.nextLine();
                break;
            case "email":
                view.displayMessage("Enter manager email:");
                managerEmail = this.scanner.nextLine();
                break;
            case "phone":
                view.displayMessage("Enter manager phone number:");
                managerPhone = Long.parseLong(this.scanner.nextLine());
                break;
            default:
                throw new AssertionError();
        }

        view.displayMessage("Enter password:");
        String password = this.scanner.nextLine();
        if (booking.loginManager(managerId, choise, managerName, managerPhone, password)) {
            // this.manager = booking.findManagerById(managerId);
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
        view.displayMessage("User Profile:\n" + manager.toString());
    }

    private void UserActions(long userId) {
        // Implement functionalities specific to logged-in user (e.g., view wishlist, make payment)
        // Use booking methods like getWishList(), payReservation(), etc.
        // ...

        while (true) {
            view.displayMenuUser(); // Separate menu for user actions
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
                    MakeReservation(userId); // Pay for a reservation
                    break;
                case 9:
                    MakeReservation(userId); // Cancel a reservation
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
                    view.displayMessage("log out");// log out
                    this.scanner.close();
                    System.exit(0);
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void SearchRoomsInHotel() {
        view.displayMessage("Enter hotel name:");
        String hotelName = this.scanner.nextLine();
        view.displayMessage("Enter id number of room");
        long roomId = getLongInput("Enter id number of room");
        view.displayMessage(booking.findRoomById(roomId).toString());
    }

    private void AddNotificationType(long userId) {
        view.displayMessage("Enter notification type to add: SMS,email,whatsapp");
        booking.addNotificationType(userId, this.scanner.nextLine());
    }

    private void ViewNotificationsType(long userId2) {
        view.displayMessage("You can be notify by:");
        List<String> s = booking.notificationType(userId);
        if (s.size() == 0) {
            view.displayMessage("None"); 
        }else {
            for (int i = 0; i < s.size(); i++) {
                view.displayMessage(s.get(i));
            }
        }
    }

    private void MakeReservation(long userId) {

        booking.makeReservation(userId, hotelId, roomId, checkInDate, checkOutDate, numAdults, numChildren);
    }

    private void RemoveFromWishlist(long userId) {
        view.displayMessage("Enter reservation id");
        long reservationId= getLongInput("Enter reservation id");
        booking.removeFromWishlist(userId,reservationId);
    }

    private void ManagerActions(long managerId) {
        // Implement functionalities specific to logged-in manager (e.g., create hotel, create room)
        // Use booking methods like createHotel(), createRoom(), etc.
        // ...
    }

    private void ViewWishlist(long userId) {
        List<Reservation> wishlist = booking.getWishList(userId);
        if (wishlist.isEmpty()) {
            view.displayMessage("Your wishlist is currently empty.");
        } else {
            view.displayMessage("Your Wishlist:");
            for (Reservation reservation : wishlist) {
                view.displayMessage(reservation.toString()); // Implement toString() in Reservation for details
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
                view.displayMessage(reservation.toString()); // Implement toString() in Reservation for details
            }
        }
    }

}
