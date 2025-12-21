package ProjectTm.WhiteBox;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ProjectTm.DataBase;
import ProjectTm.Movie;
import ProjectTm.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Branch Coverage Tests for DataBase class
 * 
 * Branches to cover:
 * 1. movieSearch() - if (index < 0) : true/false branches
 * 2. movieSearch() - outer loop: enters/doesn't enter
 * 3. movieSearch() - inner loop: enters/doesn't enter
 * 4. movieRecommend() - outer loop: enters/doesn't enter
 * 5. movieRecommend() - middle loop: enters/doesn't enter
 * 6. movieRecommend() - inner loop: enters/doesn't enter
 * 7. movieRecommend() - if condition with genreCompare && !flag: true/false
 * 8. genreCompare() - outer loop: enters/doesn't enter
 * 9. genreCompare() - inner loop: enters/doesn't enter
 * 10. genreCompare() - if match found: returns true / returns false
 */
public class DataBaseBranchCoverageTest {

    private DataBase database;

    @BeforeEach
    void setUp() {
        database = new DataBase();
    }

    // Removed tests for constructor, setters, and insert methods as they are not required for branch coverage.

    // ==================== movieSearch Tests ====================

    // Branch: outer loop doesn't enter (empty users list)
    @Test
    void movieSearch_emptyUsersDataBase_noAction() {
        database.setUsersDataBase(new ArrayList<>());
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieSearch(); // Should not throw, loop not entered
        
        assertTrue(database.getUsersDataBase().isEmpty());
    }

    // Branch: inner loop doesn't enter (user has no searched movies)
    @Test
    void movieSearch_userWithNoSearchedMovies_noAction() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        // User has empty searched movies list
        users.add(user);
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieSearch();
        
        assertTrue(database.getUsersDataBase().get(0).getSearchedMovie().isEmpty());
    }

    // Branch: if (index < 0) is TRUE - movie not found
    @Test
    void movieSearch_movieNotFound_returnsNotFoundMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Existing Movie", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = new Movie("NONEXISTENT");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieSearch();

        // The movie should be replaced with "Not found" movie
        ArrayList<Movie> result = database.getUsersDataBase().get(0).getSearchedMovie();
        assertEquals(1, result.size());
        assertEquals("Not found", result.get(0).getName());
    }

    // Branch: if (index < 0) is FALSE - movie found
    @Test
    void movieSearch_movieFound_returnsFoundMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Found Movie", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = new Movie("M001");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieSearch();

        ArrayList<Movie> result = database.getUsersDataBase().get(0).getSearchedMovie();
        assertEquals(1, result.size());
        assertEquals("Found Movie", result.get(0).getName());
    }

    // ==================== movieRecommend Tests ====================

    // Branch: outer loop doesn't enter (empty users)
    @Test
    void movieRecommend_emptyUsersDataBase_noAction() {
        database.setUsersDataBase(new ArrayList<>());
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().isEmpty());
    }

    // Branch: middle loop doesn't enter (user has no searched movies)
    @Test
    void movieRecommend_userWithNoSearchedMovies_emptyRecommendations() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        users.add(user);
        database.setUsersDataBase(users);
        
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie", "Action"));
        database.setMoviesDataBase(movies);
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Branch: inner loop doesn't enter (empty movies database)
    @Test
    void movieRecommend_emptyMoviesDatabase_noRecommendations() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovie("M001", "Test", "Action");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Branch: if condition FALSE - genreCompare returns false (no matching genre)
    @Test
    void movieRecommend_noMatchingGenre_noRecommendations() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Searched Movie", "Action"));
        movies.add(createMovie("M002", "Other Movie", "Comedy")); // Different genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovie("M001", "Searched Movie", "Action");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // M002 has different genre, should not be recommended
        ArrayList<Movie> recommendations = database.getUsersDataBase().get(0).getRecommendedMovies();
        assertTrue(recommendations.isEmpty());
    }

    // Branch: if condition FALSE - flag is true (same movie ID, should not recommend itself)
    @Test
    void movieRecommend_sameMovieId_notRecommended() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Searched Movie", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovie("M001", "Searched Movie", "Action");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // Should not recommend the same movie
        ArrayList<Movie> recommendations = database.getUsersDataBase().get(0).getRecommendedMovies();
        assertTrue(recommendations.isEmpty());
    }

    // Branch: if condition TRUE - matching genre AND different movie ID
    @Test
    void movieRecommend_matchingGenreDifferentId_isRecommended() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Searched Movie", "Action"));
        movies.add(createMovie("M002", "Similar Movie", "Action")); // Same genre, different ID
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovie("M001", "Searched Movie", "Action");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        ArrayList<Movie> recommendations = database.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(1, recommendations.size());
        assertEquals("M002", recommendations.get(0).getId());
    }

    // ==================== genreCompare Tests (via movieRecommend) ====================

    // Branch: outer loop doesn't enter (T1 is empty)
    @Test
    void genreCompare_emptyT1_returnsFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1")); // Empty genre
        movies.add(createMovie("M002", "Movie2", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovieWithGenres("M001", "Movie1"); // Empty genre
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // No match because T1 is empty, genreCompare returns false
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Branch: inner loop doesn't enter (T2 is empty)
    @Test
    void genreCompare_emptyT2_returnsFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovieWithGenres("M002", "Movie2")); // Empty genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovie("M001", "Movie1", "Action");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // M002 has empty genre, no match
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Branch: if match found - returns true (multiple genres, match on second)
    @Test
    void genreCompare_matchFoundOnSecondGenre_returnsTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        movies.add(createMovieWithGenres("M002", "Movie2", "Comedy", "Drama")); // Shares "Drama"
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovieWithGenres("M001", "Movie1", "Action", "Drama");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // M002 shares "Drama" genre
        ArrayList<Movie> recommendations = database.getUsersDataBase().get(0).getRecommendedMovies();
        assertEquals(1, recommendations.size());
        assertEquals("M002", recommendations.get(0).getId());
    }

    // Branch: no match found after checking all - returns false
    @Test
    void genreCompare_noMatchAfterCheckingAll_returnsFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        movies.add(createMovieWithGenres("M002", "Movie2", "Comedy", "Horror")); // No overlap
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        Movie searchedMovie = createMovieWithGenres("M001", "Movie1", "Action", "Drama");
        user.setSearchedMovie(searchedMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // M002 has no matching genres
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // ==================== Clear Tests ====================

    @Test
    void clear_setsListsToNull() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Test", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "John"));
        database.setUsersDataBase(users);

        database.Clear();

        assertNull(database.getMoviesDataBase());
        assertNull(database.getUsersDataBase());
    }

    // ==================== Helper Methods ====================

    private Movie createMovie(String id, String name, String genre) {
        ArrayList<String> genres = new ArrayList<>();
        genres.add(genre);
        return new Movie(id, name, genres);
    }

    private Movie createMovieWithGenres(String id, String name, String... genres) {
        ArrayList<String> genreList = new ArrayList<>();
        for (String genre : genres) {
            genreList.add(genre);
        }
        return new Movie(id, name, genreList);
    }
}
