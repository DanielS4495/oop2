import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {
    private SearchService searchService;
    private View view;
    private Scanner scanner;

    public Controller(SearchService searchService, View view) {
        this.searchService = searchService;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            view.displayMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    handleSearch();
                    break;
                case 2:
                    handleSearchRoomsInHotel();
                    break;
                case 3:
                    handleBooking();
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    view.displayMessage("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private void handleSearch() {
        Date startDate = getDateInput("Enter start date (yyyy-MM-dd):");
        Date endDate = getDateInput("Enter end date (yyyy-MM-dd):");

        view.displayMessage("Enter filters (comma separated, e.g., Breakfast,Balcony):");
        String filtersStr = scanner.nextLine().trim();
        List<String> filters = filtersStr.isEmpty() ? Collections.emptyList() : Arrays.asList(filtersStr.split(","));

        view.displayMessage("Enter minimum star rating:");
        int minStars = getIntInput();

        view.displayMessage("Enter location:");
        String location = scanner.nextLine().trim();

        List<Hotel> results = searchService.search(startDate, endDate, filters, minStars, location);
        view.displayHotels(results);
    }

    private void handleSearchRoomsInHotel() {
        view.displayMessage("Enter hotel name to search rooms:");
        String hotelName = scanner.nextLine();
        Hotel hotel = null;
        for (Hotel h : searchService.getHotels()) {
            if (h.getName().equalsIgnoreCase(hotelName)) {
                hotel = h;
                break;
            }
        }

        if (hotel == null) {
            view.displayMessage("Hotel not found.");
            return;
        }

        Date startDate = getDateInput("Enter start date (yyyy-MM-dd):");
        Date endDate = getDateInput("Enter end date (yyyy-MM-dd):");

        view.displayMessage("Enter number of adults:");
        int numAdults = getIntInput();

        view.displayMessage("Enter number of children:");
        int numChildren = getIntInput();

        List<Room> availableRooms = searchService.searchRoomsInHotel(hotel, startDate, endDate, numAdults, numChildren);
        view.displayRooms(availableRooms);
    }

    private void handleBooking() {
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

        Date bookingStartDate = getDateInput("Enter start date (yyyy-MM-dd):");
        Date bookingEndDate = getDateInput("Enter end date (yyyy-MM-dd):");

        Reservation reservation = searchService.makeReservation(hotelToBook, roomToBook, bookingStartDate, bookingEndDate);
        view.displayReservation(reservation);
    }

    private Date getDateInput(String prompt) {
        Date date = null;
        do {
            view.displayMessage(prompt);
            String dateStr = scanner.nextLine();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            } catch (ParseException e) {
                view.displayMessage("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
            }
        } while (date == null);
        return date;
    }

    private int getIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                return input;
            } catch (InputMismatchException e) {
                view.displayMessage("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}
