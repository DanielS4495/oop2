import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Controller {
    private SearchService searchService;
    private View view;

    public Controller(SearchService searchService, View view) {
        this.searchService = searchService;
        this.view = view;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.displayMenu();
            int choice = getIntInput(scanner, "Enter your choice:");

            switch (choice) {
                case 1:
                    handleSearchHotels(scanner);
                    break;
                case 2:
                    handleSearchRoomsInHotel(scanner);
                    break;
                case 3:
                    handleBookHotel(scanner);
                    break;
                case 4:
                    view.displayMessage("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleSearchHotels(Scanner scanner) {
        Date startDate = getDateInput(scanner, "Enter start date (yyyy-MM-dd):");
        Date endDate = getDateInput(scanner, "Enter end date (yyyy-MM-dd):");
        view.displayMessage("Enter filters (comma separated, e.g., Breakfast,Balcony):");
        String filtersStr = scanner.nextLine();
        List<String> filters = Arrays.asList(filtersStr.split(","));
        int minStars = getIntInput(scanner, "Enter minimum star rating:");
        double minPrice = getDoubleInput(scanner, "Enter minimum price:");
        double maxPrice = getDoubleInput(scanner, "Enter maximum price:");
        view.displayMessage("Enter Location:");
        String Location = scanner.nextLine();

        List<Hotel> results = searchService.search(startDate, endDate, filters, minStars,Location, minPrice, maxPrice);
        view.displayHotels(results);
    }

    private void handleSearchRoomsInHotel(Scanner scanner) {
        view.displayMessage("Enter hotel name:");
        String hotelName = scanner.nextLine();
        Date startDate = getDateInput(scanner, "Enter start date (yyyy-MM-dd):");
        Date endDate = getDateInput(scanner, "Enter end date (yyyy-MM-dd):");
        int adults = getIntInput(scanner, "Enter number of adults:");
        int children = getIntInput(scanner, "Enter number of children:");
        double minPrice = getDoubleInput(scanner, "Enter minimum price:");
        double maxPrice = getDoubleInput(scanner, "Enter maximum price:");
        Hotel hotelToSearch = null;
        for (Hotel hotel : searchService.getHotels()) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                hotelToSearch = hotel;
                break;
            }
        }

        if (hotelToSearch == null) {
            view.displayMessage("Hotel not found.");
            return;
        }
        List<Room> results = searchService.searchRoomsInHotel(hotelToSearch, startDate, endDate, adults, children, minPrice, maxPrice);
        view.displayRooms(results);
    }

    private void handleBookHotel(Scanner scanner) {
        view.displayMessage("Enter hotel name to book:");
        String hotelName = scanner.nextLine();
        Hotel hotelToBook = null;
        for (Hotel hotel : searchService.getHotels()) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                hotelToBook = hotel;
                break;
            }
        }

        if (hotelToBook == null) {
            view.displayMessage("Hotel not found.");
            return;
        }

        view.displayMessage("Enter room type:");
        String roomType = scanner.nextLine();
        Room roomToBook = null;
        for (Room room : hotelToBook.getRooms()) {
            if (room.getType().equalsIgnoreCase(roomType)) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook == null) {
            view.displayMessage("Room type not found.");
            return;
        }

        Date bookingStartDate = getDateInput(scanner, "Enter start date (yyyy-MM-dd):");
        Date bookingEndDate = getDateInput(scanner, "Enter end date (yyyy-MM-dd):");

        if (!roomToBook.isAvailable(bookingStartDate, bookingEndDate)) {
            view.displayMessage("Room is not available for the selected dates.");
            return;
        }

        roomToBook.book(bookingStartDate, bookingEndDate);
        view.displayMessage("Hotel booked successfully!");
    }

    private Date getDateInput(Scanner scanner, String prompt) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (date == null) {
            view.displayMessage(prompt);
            String dateStr = scanner.nextLine();
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                view.displayMessage("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
            }
        }
        return date;
    }

    private int getIntInput(Scanner scanner, String prompt) {
        int number = -1;
        while (number < 0) {
            view.displayMessage(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private double getDoubleInput(Scanner scanner, String prompt) {
        double number = -1;
        while (number < 0) {
            view.displayMessage(prompt);
            try {
                number = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }
}





// import java.util.Scanner;

// public class HotelBookingSystem {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Hi guest, want to log in or stay guest (login/guest)?");
//         String loginChoice = scanner.nextLine().toLowerCase();

//         if (loginChoice.equals("login")) {
//             // Handle login logic (replace with your authentication system)
//             System.out.println("Login successful!");
//             mainMenu(scanner);
//         } else if (loginChoice.equals("guest")) {
//             mainMenu(scanner);
//         } else {
//             System.out.println("Invalid choice. Please enter 'login' or 'guest'.");
//             main(args); // Recursively call main to get a valid choice
//         }
//     }

//     private static void mainMenu(Scanner scanner) {
//         System.out.println("\nDo you want to (search/show old orders/exit)?");
//         String choice = scanner.nextLine().toLowerCase();

//         switch (choice) {
//             case "search":
//                 searchMenu(scanner);
//                 break;
//             case "show old orders":
//                 // Implement functionality to show user's past orders
//                 System.out.println("This feature is not yet implemented.");
//                 mainMenu(scanner);
//                 break;
//             case "exit":
//                 System.out.println("Thank you for using the Hotel Booking System!");
//                 System.exit(0);
//             default:
//                 System.out.println("Invalid choice. Please enter 'search', 'show old orders', or 'exit'.");
//                 mainMenu(scanner);
//         }
//     }

//     private static void searchMenu(Scanner scanner) {
//         System.out.println("\nWhat do you want to look for? Enter details (location, date, adult, children):");
//         String searchDetails = scanner.nextLine();

//         System.out.println("\nDo you want to search by? (hotel/room/ranking/amenities):");
//         String searchCriteria = scanner.nextLine().toLowerCase();

//         // Implement functionality to search for hotels/rooms based on user criteria (searchDetails and searchCriteria)

//         System.out.println("Search results displayed. Do you want to (order/return to search/exit)?");
//         String orderChoice = scanner.nextLine().toLowerCase();

//         switch (orderChoice) {
//             case "order":
//                 // Implement functionality to place an order (including selecting hotel, room, etc.)

//                 System.out.println("Do you want to return to search or exit (search/exit)?");
//                 String afterOrderChoice = scanner.nextLine().toLowerCase();
//                 if (afterOrderChoice.equals("search")) {
//                     searchMenu(scanner);
//                 } else if (afterOrderChoice.equals("exit")) {
//                     System.exit(0);
//                 } else {
//                     System.out.println("Invalid choice. Please enter 'search' or 'exit'.");
//                     afterOrderChoice(scanner); // Recursively get a valid choice after order
//                 }
//                 break;
//             case "return to search":
//                 searchMenu(scanner);
//                 break;
//             case "exit":
//                 System.exit(0);
//             default:
//                 System.out.println("Invalid choice. Please enter 'order', 'return to search', or 'exit'.");
//                 searchMenu(scanner); // Recursively get a valid choice after search
//         }
//     }
// }
