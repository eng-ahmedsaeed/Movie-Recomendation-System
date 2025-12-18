package ProjectTm;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerLevelTest {
    public static void main(String[] args) {
        // creating stubs
        ArrayList<Movie> stubMovies = new ArrayList<>();
        ArrayList<User> stubUsers = new ArrayList<>();

        // stub movie
        Movie m1 = new Movie("IM001", "Inception"); //
        m1.addGenre("Sci-Fi");
        m1.addGenre("Action");
        stubMovies.add(m1);

        Movie m2 = new Movie("T002", "The Dark Knight"); //
        m2.addGenre("Action");
        m2.addGenre("Drama");
        stubMovies.add(m2);

        // stub user
        User u1 = new User("123456789", "John Doe"); //
        u1.setSearchedMovie(new Movie("IM001")); //
        stubUsers.add(u1);

        // Verifying the flow of Main
        DataBase db = new DataBase();
        
        // Feed stubs into the database
        db.setMoviesDataBase(stubMovies); //
        db.setUsersDataBase(stubUsers);   //

        System.out.println("Executing Search Logic...");
        db.movieSearch(); 
        
        System.out.println("Executing Recommendation Logic...");
        db.movieRecommend(); 

        // Verify WriteFile handles the stub data
        String resultFilePath = "stub_recommendation.txt";
        WriteFile result = new WriteFile(resultFilePath, stubUsers, stubMovies); //
        
        try {
            result.buildFile(); //
            System.out.println("Controller flow test complete. Check " + resultFilePath);
        } catch (IOException e) {
            System.err.println("Controller flow failed at WriteFile stage.");
            e.printStackTrace();
        }
    }
}