public class Main {
    public static void main(String[] args) {
            Manager manager = new Manager(1, "Daniel", 1234567890, "", "12345");
            HotelServer server = new HotelServer(manager);
            SearchService searchService = new SearchService(server.getHotels());
            View view = new View();
            Controller controller = new Controller(searchService, view);
    
            controller.start();
        }
    }
    
