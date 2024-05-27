// import java.util.*;
// import java.text.SimpleDateFormat;
// import java.text.ParseException;

// public class HotelClient {
//     public static void main(String[] args) {
//         HotelServer server = new HotelServer();
//         SearchService searchService = new SearchService(server.getHotels());

//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("1. Search Hotels");
//             System.out.println("2. Book Hotel");
//             System.out.println("3. Exit");
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline

//             if (choice == 1) {
//                 Date startDate = null;
//                 do {
//                     System.out.println("Enter start date (yyyy-mm-dd):");
//                     String startDateStr = scanner.nextLine();
//                     try {
//                         startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
//                     } catch (ParseException e) {
//                         System.out.println("Invalid date format. Please enter dates in the format yyyy-mm-dd.");
//                     }
//                 } while (startDate == null);

//                 Date endDate = null;
//                 do {
//                     System.out.println("Enter end date (yyyy-mm-dd):");
//                     String endDateStr = scanner.nextLine();
//                     try {
//                         endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
//                     } catch (ParseException e) {
//                         System.out.println("Invalid date format. Please enter dates in the format yyyy-mm-dd.");
//                     }
//                 } while (endDate == null);

//                 System.out.println("Enter filters (comma separated, e.g., Breakfast,Balcony):");
//                 String filtersStr = scanner.nextLine();
//                 System.out.println("Enter minimum star rating:");
//                 int minStars = scanner.nextInt();
//                 scanner.nextLine(); // Consume newline

//                 List<String> filters = Arrays.asList(filtersStr.split(","));

//                 List<Hotel> results = searchService.search(startDate, endDate, filters, minStars);
//                 System.out.println("Search Results:");
//                 for (Hotel hotel : results) {
//                     System.out.println(hotel);
//                 }
//             } else if (choice == 2) {
//                 System.out.println("Enter hotel name to book:");
//                 String hotelName = scanner.nextLine();
//                 Hotel hotelToBook = null;
//                 for (Hotel hotel : server.getHotels()) {
//                     if (hotel.getName().equalsIgnoreCase(hotelName)) {
//                         hotelToBook = hotel;
//                         break;
//                     }
//                 }

//                 if (hotelToBook == null) {
//                     System.out.println("Hotel not found.");
//                     break;
//                 }

//                 System.out.println("Enter room type:");
//                 String roomType = scanner.nextLine();
//                 Room roomToBook = null;
//                 for (Room room : hotelToBook.getRooms()) {
//                     if (room.getType().equalsIgnoreCase(roomType)) {
//                         roomToBook = room;
//                         break;
//                     }
//                 }

//                 if (roomToBook == null) {
//                     System.out.println("Room type not found.");
//                     break;
//                 }

//                 Date bookingStartDate = null;
//                 do {
//                     System.out.println("Enter start date (yyyy-MM-dd):");
//                     String bookingStartDateStr = scanner.nextLine();
//                     try {
//                         bookingStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingStartDateStr);
//                     } catch (ParseException e) {
//                         System.out.println("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
//                     }
//                 } while (bookingStartDate == null);

//                 Date bookingEndDate = null;
//                 do {
//                     System.out.println("Enter end date (yyyy-MM-dd):");
//                     String bookingEndDateStr = scanner.nextLine();
//                     try {
//                         bookingEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingEndDateStr);
//                     } catch (ParseException e) {
//                         System.out.println("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
//                     }
//                 } while (bookingEndDate == null);

//                 Reservation reservation = searchService.makeReservation(hotelToBook, roomToBook, bookingStartDate, bookingEndDate);
//                 System.out.println("Booking confirmed: " + reservation);
//             } else if (choice == 3) {
//                 scanner.close();
//                 break;
//             }
//         }
//     }
// }
