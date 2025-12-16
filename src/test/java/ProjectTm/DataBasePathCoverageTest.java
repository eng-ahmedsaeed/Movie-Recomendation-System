package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DataBasePathCoverageTest {

    private DataBase db;

    @BeforeEach
    public void setUp() {
        db = new DataBase();
    }

    /**
     // Path P1: No Users
     */
    @Test
    public void testMovieRecommend_P1_NoUsers() {
        db.setUsersDataBase(new ArrayList<>());
        db.setMoviesDataBase(new ArrayList<>());

        db.movieRecommend();

        assertEquals(0, db.getUsersDataBase().size(), "Should be 0 users");
    }

    /**
     * Path P2: Users exist, but No Searched Movies
     */
    @Test
    public void testMovieRecommend_P2_NoSearchedMovies() {
        User user = new User("U12345678", "UserWithoutSearch"); // ID adjusted to fit regex if needed
        user.setSearchedMovieInst(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertNotNull(recommended);
        assertEquals(0, recommended.size());
    }

    /**
     * Path P3: Users & Searched Movies exist, but No Movies in DB
     */
    @Test
    public void testMovieRecommend_P3_NoMoviesInDB() {
        User user = new User("U22345678", "UserWithSearch");
        // تصحيح: مسحنا السنة (2020)
        Movie searchedMovie = new Movie("M001", "SearchedFilm", new ArrayList<>(Arrays.asList("Action")));
        user.setSearchedMovie(searchedMovie);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        db.setMoviesDataBase(new ArrayList<>());

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(0, recommended.size());
    }

    /**
     * Path P4: Successful Recommendation (True Path)
     */
    @Test
    public void testMovieRecommend_P4_SuccessfulRecommendation() {
        User user = new User("U33345678", "ActionFan");
        // تصحيح: مسحنا السنة
        Movie likedMovie = new Movie("M001", "Action Movie One", new ArrayList<>(Arrays.asList("Action")));
        user.setSearchedMovie(likedMovie);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        // تصحيح: مسحنا السنة
        Movie recomMovie = new Movie("M002", "Action Movie Two", new ArrayList<>(Arrays.asList("Action")));
        movies.add(recomMovie);
        movies.add(likedMovie);

        db.setMoviesDataBase(movies);

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertTrue(recommended.contains(recomMovie), "Should recommend M002");
        assertEquals(1, recommended.size());
    }

    /**
     * Path P5: Failed Recommendation / Self-Exclusion (False Path)
     */
    @Test
    public void testMovieRecommend_P5_FailOrSameID() {
        User user = new User("U44445678", "ComedyFan");
        // تصحيح: مسحنا السنة
        Movie likedMovie = new Movie("M003", "Funny Movie", new ArrayList<>(Arrays.asList("Comedy")));
        user.setSearchedMovie(likedMovie);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(likedMovie); // Same ID
        // تصحيح: مسحنا السنة
        movies.add(new Movie("M004", "Scary Movie", new ArrayList<>(Arrays.asList("Horror")))); // Diff Genre

        db.setMoviesDataBase(movies);

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(0, recommended.size());
    }
}