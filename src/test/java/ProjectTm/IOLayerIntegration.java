package ProjectTm;

import java.io.IOException;
import java.util.ArrayList;

public class IOLayerIntegration {
    public static void main(String[] args) {
        String movieFilePath = "src/main/resources/movies.txt";
        String userFilePath = "src/main/resources/users.txt";
        String resultFilePath = "integration_result.txt";

        ReadMovie movieReader = new ReadMovie(movieFilePath);
        ReadUser userReader = new ReadUser(userFilePath);
        DataBase db = new DataBase();

        try {
            System.out.println("Reading movie and user data...");
            ArrayList<Movie> movies = movieReader.getMovies();
            ArrayList<User> users = userReader.getUsers();
            
            db.setMoviesDataBase(movies);
            db.setUsersDataBase(users);
            System.out.println("Processing search and recommendations...");
            db.movieSearch();
            db.movieRecommend();
            
            System.out.println("Writing results to: " + resultFilePath);
            WriteFile writer = new WriteFile(resultFilePath, db.getUsersDataBase(), db.getMoviesDataBase());
            writer.buildFile();

            System.out.println("Integration successful!");

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Integration failed during I/O Layer testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
