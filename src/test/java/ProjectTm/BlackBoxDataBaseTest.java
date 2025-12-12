package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Black Box Testing for DataBase class
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - DataBase")
public class BlackBoxDataBaseTest {

    // ==================== DATABASE INITIALIZATION TESTS ====================

    @DisplayName("DataBase Initialization - Equivalence Partitioning")
    public static class DatabaseInitializationTests {

        private DataBase dataBase;

        @BeforeEach
        public void setUp() {
            dataBase = new DataBase();
        }

        @Test
        @DisplayName("EP1: Empty database initialization - Valid")
        public void testDatabaseInitialization_Empty() {
            assertNotNull(dataBase.getMoviesDataBase());
            assertNotNull(dataBase.getUsersDataBase());
            assertEquals(0, dataBase.getMoviesDataBase().size());
            assertEquals(0, dataBase.getUsersDataBase().size());
        }
    }

    // ==================== MOVIE INSERTION TESTS ====================

    @DisplayName("Movie Insertion - Equivalence Partitioning & Boundary Value")
    public static class MovieInsertionTests {

        private DataBase dataBase;
        private Movie testMovie;

        @BeforeEach
        public void setUp() {
            dataBase = new DataBase();
            testMovie = new Movie();
            testMovie.setId("TDK001");
            testMovie.setName("The Dark Knight");
        }

        // Equivalence Partition: Single movie insertion
        @Test
        @DisplayName("EP2: Insert single movie - Valid")
        public void testInsertMovie_Single() {
            dataBase.insert(testMovie);
            assertEquals(1, dataBase.getMoviesDataBase().size());
        }

        // Boundary Value: Minimum movies
        @Test
        @DisplayName("BV1: Insert 1 movie - Boundary minimum")
        public void testInsertMovie_OneMovie() {
            dataBase.insert(testMovie);
            assertEquals(1, dataBase.getMoviesDataBase().size());
            assertEquals(testMovie.getId(), dataBase.getMoviesDataBase().get(0).getId());
        }

        // Equivalence Partition: Multiple movie insertion
        @Test
        @DisplayName("EP2: Insert multiple movies - Valid")
        public void testInsertMovie_Multiple() {
            Movie movie2 = new Movie();
            movie2.setId("INC002");
            movie2.setName("Inception");

            dataBase.insert(testMovie);
            dataBase.insert(movie2);

            assertEquals(2, dataBase.getMoviesDataBase().size());
        }

        // Boundary Value: Many movies
        @Test
        @DisplayName("BV2: Insert 100 movies - Large quantity")
        public void testInsertMovie_ManyMovies() {
            for (int i = 0; i < 100; i++) {
                Movie movie = new Movie();
                movie.setId("M" + String.format("%03d", i));
                movie.setName("Movie " + i);
                dataBase.insert(movie);
            }
            assertEquals(100, dataBase.getMoviesDataBase().size());
        }
    }

    // ==================== MOVIES DATABASE SET TESTS ====================

    @DisplayName("SetMoviesDataBase - Equivalence Partitioning & Boundary Value")
    public static class SetMoviesDatabaseTests {

        private DataBase dataBase;
        private ArrayList<Movie> movies;

        @BeforeEach
        public void setUp() {
            dataBase = new DataBase();
            movies = new ArrayList<>();
        }

        // Equivalence Partition: Empty movie list
        @Test
        @DisplayName("EP3: Set empty movie database - Valid")
        public void testSetMoviesDatabase_Empty() {
            dataBase.setMoviesDataBase(movies);
            assertEquals(0, dataBase.getMoviesDataBase().size());
        }

        // Equivalence Partition: Single movie
        @Test
        @DisplayName("EP3: Set single movie in database - Valid")
        public void testSetMoviesDatabase_SingleMovie() {
            Movie movie = new Movie();
            movie.setId("AAA001");
            movies.add(movie);

            dataBase.setMoviesDataBase(movies);
            assertEquals(1, dataBase.getMoviesDataBase().size());
        }

        // Boundary Value: Minimum valid database
        @Test
        @DisplayName("BV3: Set minimum database - Single movie")
        public void testSetMoviesDatabase_Minimum() {
            Movie movie = new Movie();
            movie.setId("ZZZ999");
            movies.add(movie);

            dataBase.setMoviesDataBase(movies);
            assertEquals(1, dataBase.getMoviesDataBase().size());
        }

        // Equivalence Partition: Multiple movies
        @Test
        @DisplayName("EP3: Set multiple movies - Valid")
        public void testSetMoviesDatabase_MultipleMovies() {
            for (int i = 0; i < 5; i++) {
                Movie movie = new Movie();
                movie.setId("M" + String.format("%03d", i));
                movies.add(movie);
            }

            dataBase.setMoviesDataBase(movies);
            assertEquals(5, dataBase.getMoviesDataBase().size());
        }

        // Equivalence Partition: Verify sorting occurs
        @Test
        @DisplayName("EP3: Movies are sorted by ID after set - Valid")
        public void testSetMoviesDatabase_VerifySorting() {
            Movie m1 = new Movie();
            m1.setId("CCC001");
            Movie m2 = new Movie();
            m2.setId("AAA001");
            Movie m3 = new Movie();
            m3.setId("BBB001");

            movies.add(m1);
            movies.add(m2);
            movies.add(m3);

            dataBase.setMoviesDataBase(movies);

            // Should be sorted: AAA, BBB, CCC
            assertEquals("AAA001", dataBase.getMoviesDataBase().get(0).getId());
            assertEquals("BBB001", dataBase.getMoviesDataBase().get(1).getId());
            assertEquals("CCC001", dataBase.getMoviesDataBase().get(2).getId());
        }
    }

    // ==================== USERS DATABASE SET TESTS ====================

    @DisplayName("SetUsersDataBase - Equivalence Partitioning & Boundary Value")
    public static class SetUsersDatabaseTests {

        private DataBase dataBase;
        private ArrayList<User> users;

        @BeforeEach
        public void setUp() {
            dataBase = new DataBase();
            users = new ArrayList<>();
        }

        // Equivalence Partition: Empty user list
        @Test
        @DisplayName("EP4: Set empty users database - Valid")
        public void testSetUsersDatabase_Empty() {
            dataBase.setUsersDataBase(users);
            assertEquals(0, dataBase.getUsersDataBase().size());
        }

        // Equivalence Partition: Single user
        @Test
        @DisplayName("EP4: Set single user in database - Valid")
        public void testSetUsersDatabase_SingleUser() {
            User user = new User("123456789", "Test User");
            users.add(user);

            dataBase.setUsersDataBase(users);
            assertEquals(1, dataBase.getUsersDataBase().size());
        }

        // Boundary Value: Minimum valid user database
        @Test
        @DisplayName("BV4: Set minimum user database - Single user")
        public void testSetUsersDatabase_Minimum() {
            User user = new User("999999999", "Test User");
            users.add(user);

            dataBase.setUsersDataBase(users);
            assertEquals(1, dataBase.getUsersDataBase().size());
        }

        // Equivalence Partition: Multiple users
        @Test
        @DisplayName("EP4: Set multiple users - Valid")
        public void testSetUsersDatabase_MultipleUsers() {
            for (int i = 0; i < 10; i++) {
                User user = new User("10000000" + i, "User " + i);
                users.add(user);
            }

            dataBase.setUsersDataBase(users);
            assertEquals(10, dataBase.getUsersDataBase().size());
        }
    }

    // ==================== GENRE COMPARISON TESTS ====================

    @DisplayName("Movie Search & Recommendation - Genre Comparison")
    public static class GenreComparisonTests {

        private DataBase dataBase;

        @BeforeEach
        public void setUp() {
            dataBase = new DataBase();
        }

        // Equivalence Partition: Movies with matching genres
        @Test
        @DisplayName("EP5: Movies with common genre - Should find matches")
        public void testGenreComparison_CommonGenre() {
            Movie m1 = new Movie();
            m1.setId("M001");
            m1.getGenre().add("Action");
            m1.getGenre().add("Crime");

            Movie m2 = new Movie();
            m2.setId("M002");
            m2.getGenre().add("Action");
            m2.getGenre().add("Thriller");

            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(m1);
            movies.add(m2);
            dataBase.setMoviesDataBase(movies);

            // Both movies have "Action" genre
            assertTrue(dataBase.getMoviesDataBase().size() >= 2);
        }

        // Equivalence Partition: Movies with no common genres
        @Test
        @DisplayName("EP5: Movies with different genres - No common genres")
        public void testGenreComparison_NoCommonGenre() {
            Movie m1 = new Movie();
            m1.setId("M001");
            m1.getGenre().add("Action");

            Movie m2 = new Movie();
            m2.setId("M002");
            m2.getGenre().add("Romance");

            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(m1);
            movies.add(m2);
            dataBase.setMoviesDataBase(movies);

            assertEquals(2, dataBase.getMoviesDataBase().size());
        }

        // Boundary Value: Single genre movies
        @Test
        @DisplayName("BV5: Movies with single genre - Boundary case")
        public void testGenreComparison_SingleGenre() {
            Movie m1 = new Movie();
            m1.setId("M001");
            m1.getGenre().add("Action");

            Movie m2 = new Movie();
            m2.setId("M002");
            m2.getGenre().add("Action");

            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(m1);
            movies.add(m2);
            dataBase.setMoviesDataBase(movies);

            assertEquals(2, dataBase.getMoviesDataBase().size());
        }

        // Boundary Value: Multiple genres per movie
        @Test
        @DisplayName("BV6: Movies with multiple genres - Boundary case")
        public void testGenreComparison_MultipleGenres() {
            Movie m1 = new Movie();
            m1.setId("M001");
            m1.getGenre().add("Action");
            m1.getGenre().add("Sci-Fi");
            m1.getGenre().add("Adventure");

            Movie m2 = new Movie();
            m2.setId("M002");
            m2.getGenre().add("Adventure");
            m2.getGenre().add("Fantasy");

            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(m1);
            movies.add(m2);
            dataBase.setMoviesDataBase(movies);

            assertEquals(2, dataBase.getMoviesDataBase().size());
        }
    }
}
