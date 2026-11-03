//Karin Mustafa 
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

    private static ArrayList<InputStream> registeredStreams = new ArrayList<>(); 
    private Scanner scanner;

    public InputReader() {
        this(System.in);
    }

    public InputReader(InputStream inputStream) {
        if (registeredStreams.contains(inputStream)) {
            throw new IllegalStateException("This InputStream is already in use. ");
        }
        registeredStreams.add(inputStream);
        this.scanner = new Scanner(inputStream);
    }

    public String handleText(String inputData) {
        System.out.print(inputData + "?>");
        String input = scanner.nextLine().trim();
        return input;
    }

    public int handleInt(String inputData) {
        System.out.print(inputData+ "?>");
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong: Enter valid integer!");
            scanner.next(); 
        }
        int value = scanner.nextInt();
        scanner.nextLine(); 
        return value;
    }

    public double handleDouble(String inputData) {
        System.out.print(inputData + "?>");
        while (!scanner.hasNextDouble()) {
            System.out.println("Wrong: Enter valid decimal number!");
            scanner.next(); 
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); 
        return value;
    }
    
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
        registeredStreams.clear();
    }
}
 