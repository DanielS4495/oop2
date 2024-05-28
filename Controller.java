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
