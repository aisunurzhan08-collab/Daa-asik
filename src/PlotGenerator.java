import java.io.*;
import java.util.*;

public class PlotGenerator {

    public static void main(String[] args) {

        System.out.println("Reading results.csv...");

        try {
            File file = new File("results/results.csv");

            Scanner scanner = new Scanner(file);

            // Skip header
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            scanner.close();

            System.out.println("CSV read successfully.");

        } catch (Exception e) {
            System.out.println("Error reading CSV.");
        }
    }
}
