import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class test { ///////////////////////// need to do junit
    public static void main(String[] args) {
        // Test searching hotels
        String searchHotelsInput = "1\n2024-07-07\n2024-07-09\nBreakfast,Balcony\n1\n50\n200\n4\n3\n";
        testInput(searchHotelsInput);

        // Test booking a hotel
        String bookHotelInput = "2\nHotel 1\nSingle\n2024-07-07\n2024-07-09\n3\n";
        testInput(bookHotelInput);
    }

    private static void testInput(String input) {
        // Set up the input stream
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set up the output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Run the main application
        Main.main(new String[]{});

        // Display the output
        System.out.println(out.toString());
    }
}
