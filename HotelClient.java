import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HotelClient {
    public static void main(String[] args) {
        HotelServer server = new HotelServer();
        SearchService searchService = new SearchService(server.getHotels());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Hotels");
            System.out.println("2. Book Hotel");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                Date startDate = null;
                do {
                    System.out.println("Enter start date (yyyy-mm-dd):");
                    String startDateStr = scanner.nextLine();
                    try {
                        startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter dates in the format yyyy-mm-dd.");
                    }
                } while (startDate == null);

                Date endDate = null;
                do {
                    System.out.println("Enter end date (yyyy-mm-dd):");
                    String endDateStr = scanner.nextLine();
                    try {
                        endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter dates in the format yyyy-mm-dd.");
                    }
                } while (endDate == null);

                System.out.println("Enter filters (comma separated, e.g., Breakfast,Balcony):");
                String filtersStr = scanner.nextLine();
                System.out.println("Enter minimum star rating:");
                int minStars = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                List<String> filters = Arrays.asList(filtersStr.split(","));

                List<Hotel> results = searchService.search(startDate, endDate, filters, minStars);
                System.out.println("Search Results:");
                for (Hotel hotel : results) {
                    System.out.println(hotel);
                }
            } else if (choice == 2) {
                // Implement booking logic here
            } else if (choice == 3) {
                scanner.close();
                break;
            }
        }
    }
}
