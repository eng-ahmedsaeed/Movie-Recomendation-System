package ProjectTm.WhiteBox;

import ProjectTm.DataBase;
import ProjectTm.Movie;
import ProjectTm.User;
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

    @Test
    public void testMovieSearch_S1_NoUsers() {
        db.setUsersDataBase(new ArrayList<>());
        db.movieSearch();
        assertEquals(0, db.getUsersDataBase().size());
    }

    @Test
    public void testMovieSearch_S2_UserWithNoSearchedMovies() {
        User user = new User("U100", "EmptySearcher");
        user.setSearchedMovieInst(new ArrayList<>());

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        db.movieSearch();

        assertEquals(0, db.getUsersDataBase().get(0).getSearchedMovie().size());
    }

    @Test
    public void testMovieSearch_S3_MovieNotFound() {
        User user = new User("U101", "LostSearcher");
        Movie m = new Movie("M999", "Missing Movie", new ArrayList<>());
        user.setSearchedMovieInst(new ArrayList<>(Arrays.asList(m)));

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("M001", "Existing Movie"));
        db.setMoviesDataBase(movies);

        db.movieSearch();

        Movie result = db.getUsersDataBase().get(0).getSearchedMovie().get(0);
        assertEquals("Not found", result.getName());
    }

    @Test
    public void testMovieSearch_S4_MovieFound() {
        User user = new User("U102", "HappySearcher");
        Movie searchKey = new Movie("M001");
        user.setSearchedMovieInst(new ArrayList<>(Arrays.asList(searchKey)));

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        db.setUsersDataBase(users);

        ArrayList<Movie> movies = new ArrayList<>();
        Movie fullMovie = new Movie("M001", "Full Data Movie",
                new ArrayList<>(Arrays.asList("Drama")));
        movies.add(fullMovie);
        db.setMoviesDataBase(movies);

        db.movieSearch();

        Movie result = db.getUsersDataBase().get(0).getSearchedMovie().get(0);
        assertEquals("Full Data Movie", result.getName());
        assertEquals("Drama", result.getGenre().get(0));
    }
}