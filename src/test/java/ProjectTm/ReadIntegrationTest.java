package ProjectTm;

import java.io.IOException;
import java.util.ArrayList;

public class ReadIntegrationTest {
    public static void main(String[] args) {
        String moviePath = "src/main/resources/movies.txt";
        String userPath = "src/main/resources/users.txt";

        ReadMovie movieReader = new ReadMovie(moviePath);
        ReadUser userReader = new ReadUser(userPath);

        try {
            System.out.println("--- Testing ReadMovie Integration ---");
            ArrayList<Movie> movies = movieReader.getMovies(); 
            System.out.println("Successfully parsed " + movies.size() + " movies.");
            movieReader.printMovies(); //

            System.out.println("\n--- Testing ReadUser Integration ---");
            ArrayList<User> users = userReader.getUsers(); // Invokes ValidateUser internally
            System.out.println("Successfully parsed " + users.size() + " users.");
            userReader.printUsers(); 

        } catch (IOException e) {
            System.err.println("File not found or unreadable: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Validation failed during parsing: " + e.getMessage());
        }
    }
}