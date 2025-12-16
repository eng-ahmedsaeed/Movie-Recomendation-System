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

    @Test
    public void testMovieRecommend_P1_NoUsers() {
        db.setUsersDataBase(new ArrayList<>());
        db.setMoviesDataBase(new ArrayList<>());
        db.movieRecommend();
        assertEquals(0, db.getUsersDataBase().size());
    }

    @Test
    public void testMovieRecommend_P2_NoSearchedMovies() {
        User user = new User("U12345678", "UserOne");
        user.setSearchedMovieInst(new ArrayList<>());
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);
        db.movieRecommend();
        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertNotNull(recommended);
        assertEquals(0, recommended.size());
    }

    @Test
    public void testMovieRecommend_P3_NoMoviesInDB() {
        User user = new User("U22345678", "UserTwo");
        Movie searchedMovie = new Movie("M001", "FilmOne", new ArrayList<>(Arrays.asList("Action")));
        user.setSearchedMovie(searchedMovie);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);
        db.setMoviesDataBase(new ArrayList<>());
        db.movieRecommend();
        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(0, recommended.size());
    }

    @Test
    public void testMovieRecommend_P4_SuccessfulRecommendation() {
        User user = new User("U33345678", "UserThree");
        Movie likedMovie = new Movie("M001", "FilmOne", new ArrayList<>(Arrays.asList("Action")));
        user.setSearchedMovie(likedMovie);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        Movie recomMovie = new Movie("M002", "FilmTwo", new ArrayList<>(Arrays.asList("Action")));
        movies.add(recomMovie);
        movies.add(likedMovie);
        db.setMoviesDataBase(movies);

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertTrue(recommended.contains(recomMovie));
        assertEquals(1, recommended.size());
    }

    @Test
    public void testMovieRecommend_P5_FailOrSameID() {
        User user = new User("U44445678", "UserFour");
        Movie likedMovie = new Movie("M003", "FilmThree", new ArrayList<>(Arrays.asList("Comedy")));
        user.setSearchedMovie(likedMovie);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(likedMovie);
        movies.add(new Movie("M004", "FilmFour", new ArrayList<>(Arrays.asList("Horror"))));
        db.setMoviesDataBase(movies);

        db.movieRecommend();

        ArrayList<Movie> recommended = db.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(0, recommended.size());
    }
}