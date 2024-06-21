public class Main {
    public static void main(String[] args) {
            HotelServer server = new HotelServer();
            SearchService searchService = new SearchService(server.getHotels());
            View view = new View();
            Controller controller = new Controller(searchService, view);
    
            controller.start();
        }
    }
    
