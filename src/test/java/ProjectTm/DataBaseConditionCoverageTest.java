package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Condition Coverage Tests for DataBase class
 * 
 * Conditions to cover (each condition tested for TRUE and FALSE):
 * 
 * 1. movieSearch():
 *    - i < usersDataBase.size() : TRUE/FALSE
 *    - j < reMovie.size() : TRUE/FALSE
 *    - index < 0 : TRUE/FALSE
 * 
 * 2. movieRecommend():
 *    - i < usersDataBase.size() : TRUE/FALSE
 *    - k < reMovie.size() : TRUE/FALSE
 *    - j < moviesDataBase.size() : TRUE/FALSE
 *    - genreCompare(Genre, moviesDataBase.get(j).getGenre()) : TRUE/FALSE
 *    - !(flag) where flag = moviesDataBase.get(j).getId().equalsIgnoreCase(m.getId()) : TRUE/FALSE
 *    - Combined: genreCompare(...) && !(flag) : all combinations
 * 
 * 3. genreCompare():
 *    - i < T1.size() : TRUE/FALSE
 *    - j < T2.size() : TRUE/FALSE
 *    - T1.get(i).equalsIgnoreCase(T2.get(j)) : TRUE/FALSE
 */
public class DataBaseConditionCoverageTest {

    private DataBase database;

    @BeforeEach
    void setUp() {
        database = new DataBase();
    }

    // ==================== movieSearch() Condition Coverage ====================

    // Condition: i < usersDataBase.size() - FALSE (empty list, never enters loop)
    @Test
    void movieSearch_usersSize_conditionFalse() {
        database.setUsersDataBase(new ArrayList<>());
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieSearch();
        
        assertEquals(0, database.getUsersDataBase().size());
    }

    // Condition: i < usersDataBase.size() - TRUE (has users, enters loop)
    @Test
    void movieSearch_usersSize_conditionTrue() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "John"));
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieSearch();
        
        assertEquals(1, database.getUsersDataBase().size());
    }

    // Condition: j < reMovie.size() - FALSE (empty searched movies)
    @Test
    void movieSearch_reMovieSize_conditionFalse() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        users.add(user);
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieSearch();
        
        assertTrue(database.getUsersDataBase().get(0).getSearchedMovie().isEmpty());
    }

    // Condition: j < reMovie.size() - TRUE (has searched movies)
    @Test
    void movieSearch_reMovieSize_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(new Movie("M001"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieSearch();

        assertEquals(1, database.getUsersDataBase().get(0).getSearchedMovie().size());
    }

    // Condition: index < 0 - TRUE (movie not found in database)
    @Test
    void movieSearch_indexLessThanZero_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(new Movie("NOTFOUND"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieSearch();

        assertEquals("Not found", database.getUsersDataBase().get(0).getSearchedMovie().get(0).getName());
    }

    // Condition: index < 0 - FALSE (movie found in database)
    @Test
    void movieSearch_indexLessThanZero_conditionFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Found Movie", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(new Movie("M001"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieSearch();

        assertEquals("Found Movie", database.getUsersDataBase().get(0).getSearchedMovie().get(0).getName());
    }

    // ==================== movieRecommend() Condition Coverage ====================

    // Condition: i < usersDataBase.size() - FALSE
    @Test
    void movieRecommend_usersSize_conditionFalse() {
        database.setUsersDataBase(new ArrayList<>());
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().isEmpty());
    }

    // Condition: i < usersDataBase.size() - TRUE
    @Test
    void movieRecommend_usersSize_conditionTrue() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "John"));
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieRecommend();
        
        assertEquals(1, database.getUsersDataBase().size());
    }

    // Condition: k < reMovie.size() - FALSE
    @Test
    void movieRecommend_reMovieSize_conditionFalse() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "John"));
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>());
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: k < reMovie.size() - TRUE
    @Test
    void movieRecommend_reMovieSize_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertEquals(1, database.getUsersDataBase().get(0).getRecommendedMovies().size());
    }

    // Condition: j < moviesDataBase.size() - FALSE
    @Test
    void movieRecommend_moviesSize_conditionFalse() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);
        database.setMoviesDataBase(new ArrayList<>()); // Empty movies
        
        database.movieRecommend();
        
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: j < moviesDataBase.size() - TRUE
    @Test
    void movieRecommend_moviesSize_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();
        
        // Loop executed (even though no recommendations due to same ID)
        assertNotNull(database.getUsersDataBase().get(0).getRecommendedMovies());
    }

    // Condition: genreCompare() TRUE && flag FALSE (genres match, different ID) -> adds recommendation
    @Test
    void movieRecommend_genreMatchAndDifferentId_bothConditionsTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Action")); // Same genre, different ID
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertEquals(1, database.getUsersDataBase().get(0).getRecommendedMovies().size());
        assertEquals("M002", database.getUsersDataBase().get(0).getRecommendedMovies().get(0).getId());
    }

    // Condition: genreCompare() TRUE && flag TRUE (genres match, same ID) -> no recommendation
    @Test
    void movieRecommend_genreMatchAndSameId_genreTrueFlagFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // Same ID, should not recommend itself
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: genreCompare() FALSE && flag FALSE (no genre match, different ID) -> no recommendation
    @Test
    void movieRecommend_noGenreMatchDifferentId_genreFalseFlagTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Comedy")); // Different genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: genreCompare() FALSE && flag TRUE (no genre match, same ID) -> no recommendation
    @Test
    void movieRecommend_noGenreMatchSameId_bothConditionsFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie = createMovie("M001", "Movie1", "Action");
        movies.add(movie);
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        // Search for same movie but with different genre in search (edge case)
        Movie searchMovie = new Movie("M001", "Movie1", new ArrayList<>());
        user.setSearchedMovie(searchMovie);
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // Empty genre in searched movie -> genreCompare returns false
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // ==================== genreCompare() Condition Coverage (via movieRecommend) ====================

    // Condition: i < T1.size() - FALSE (T1 is empty)
    @Test
    void genreCompare_T1Size_conditionFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1")); // Empty genre
        movies.add(createMovie("M002", "Movie2", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovieWithGenres("M001", "Movie1")); // Empty genre
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: i < T1.size() - TRUE (T1 has elements)
    @Test
    void genreCompare_T1Size_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Action"));
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertEquals(1, database.getUsersDataBase().get(0).getRecommendedMovies().size());
    }

    // Condition: j < T2.size() - FALSE (T2 is empty)
    @Test
    void genreCompare_T2Size_conditionFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovieWithGenres("M002", "Movie2")); // Empty genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: j < T2.size() - TRUE (T2 has elements)
    @Test
    void genreCompare_T2Size_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Drama")); // Has genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        // Different genres, no recommendation
        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Condition: T1.get(i).equalsIgnoreCase(T2.get(j)) - TRUE (genres match)
    @Test
    void genreCompare_equalsIgnoreCase_conditionTrue() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "action")); // Same genre, different case
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertEquals(1, database.getUsersDataBase().get(0).getRecommendedMovies().size());
    }

    // Condition: T1.get(i).equalsIgnoreCase(T2.get(j)) - FALSE (genres don't match)
    @Test
    void genreCompare_equalsIgnoreCase_conditionFalse() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovie("M001", "Movie1", "Action"));
        movies.add(createMovie("M002", "Movie2", "Comedy")); // Different genre
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovie("M001", "Movie1", "Action"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
    }

    // Multiple genres - match found after checking multiple
    @Test
    void genreCompare_multipleGenres_matchFoundOnSecond() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        movies.add(createMovieWithGenres("M002", "Movie2", "Comedy", "Drama")); // Shares Drama
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertEquals(1, database.getUsersDataBase().get(0).getRecommendedMovies().size());
    }

    // Multiple genres - no match after checking all
    @Test
    void genreCompare_multipleGenres_noMatchAfterCheckingAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        movies.add(createMovieWithGenres("M002", "Movie2", "Comedy", "Horror")); // No overlap
        database.setMoviesDataBase(movies);

        ArrayList<User> users = new ArrayList<>();
        User user = new User("U001", "John");
        user.setSearchedMovie(createMovieWithGenres("M001", "Movie1", "Action", "Drama"));
        users.add(user);
        database.setUsersDataBase(users);

        database.movieRecommend();

        assertTrue(database.getUsersDataBase().get(0).getRecommendedMovies().isEmpty());
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
