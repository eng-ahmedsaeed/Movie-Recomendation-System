package ProjectTm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseStatmentcoverage {

    // Test ID: TC-DB-001 | Insert And Get
    @Test
    void insertAndGet() {
        DataBase db = new DataBase();
        Movie m = new Movie("b", "B");
        db.insert(m);
        assertEquals(1, db.getMoviesDataBase().size());
        assertSame(m, db.getMoviesDataBase().get(0));
    }

    // Test ID: TC-DB-002 | Set Movies DB Sorts
    @Test
    void setMoviesDataBase_sorts() {
        DataBase db = new DataBase();
        ArrayList<Movie> list = new ArrayList<>();
        list.add(new Movie("b", "B"));
        list.add(new Movie("a", "A"));
        db.setMoviesDataBase(list);
        ArrayList<Movie> res = db.getMoviesDataBase();
        assertEquals("a", res.get(0).getId());
        assertEquals("b", res.get(1).getId());
    }

    // Test ID: TC-DB-003 | Movie Search Found And Not Found
    @Test
    void movieSearch_found_and_not_found() {
        DataBase db = new DataBase();
        Movie full = new Movie("m1", "Name1", new ArrayList<>(Arrays.asList("G1")));
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(full);
        db.setMoviesDataBase(movies);

        User user = new User("u1", "U1");
        // searchedMovie containing only ids (one exists, one does not)
        user.setSearchedMovie(new Movie("m1"));
        user.setSearchedMovie(new Movie("x"));
        db.getUsersDataBase().add(user);

        db.movieSearch();

        ArrayList<Movie> res = user.getSearchedMovie();
        assertEquals(2, res.size());
        // first should be replaced with full entry from movies DB
        assertEquals("Name1", res.get(0).getName());
        // second not found -> default "Not found"
        assertEquals("Not found", res.get(1).getName());
    }

    // Test ID: TC-DB-004 | Recommend Matching, Exclusion, Null Genres
    @Test
    void movieRecommend_matching_exclusion_and_nullGenres() {
        DataBase db = new DataBase();

        Movie A = new Movie("1", "A", new ArrayList<>(Arrays.asList("Action")));
        Movie B = new Movie("2", "B", new ArrayList<>(Arrays.asList("Action")));
        Movie C = new Movie("3", "C", new ArrayList<>(Arrays.asList("Comedy")));

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(A);
        movies.add(B);
        movies.add(C);
        db.setMoviesDataBase(movies);

        // Case 1: searched movie with genre -> should recommend B (same genre) and exclude itself A
        User u1 = new User("u1", "U1");
        u1.setSearchedMovie(new Movie("1", "A", new ArrayList<>(Arrays.asList("Action"))));
        db.getUsersDataBase().add(u1);

        // Case 2: searched movie with null genre -> no recommendations
        User u2 = new User("u2", "U2");
        u2.setSearchedMovie(new Movie("2")); // constructor leaves genre null
        db.getUsersDataBase().add(u2);

        db.movieRecommend();

        ArrayList<Movie> rec1 = u1.getRecommendedMovies();
        assertEquals(1, rec1.size());
        assertEquals("2", rec1.get(0).getId());

        ArrayList<Movie> rec2 = u2.getRecommendedMovies();
        assertTrue(rec2.isEmpty());
    }

    // Test ID: TC-DB-005 | Clear Sets Null
    @Test
    void clearSetsNull() {
        DataBase db = new DataBase();
        assertNotNull(db.getMoviesDataBase());
        assertNotNull(db.getUsersDataBase());
        db.Clear();
        assertNull(db.getMoviesDataBase());
        assertNull(db.getUsersDataBase());
    }

    // Test ID: TC-DB-006 | Recommend With Empty Genre Lists Produces No Recommendations
    @Test
    void recommendWithEmptyGenreListsProducesNoRecommendations() {
        DataBase db = new DataBase();
        Movie A = new Movie("1", "A", new ArrayList<>());
        Movie B = new Movie("2", "B", new ArrayList<>());
        db.setMoviesDataBase(new ArrayList<>(Arrays.asList(A, B)));

        User u = new User("u", "U");
        // searched movie with empty genre list
        u.setSearchedMovie(new Movie("1", "A", new ArrayList<>()));
        db.getUsersDataBase().add(u);

        db.movieRecommend();
        assertTrue(u.getRecommendedMovies().isEmpty());
    }

    // Test ID: TC-DB-007 | Recommend Excludes Searched Movie Case-Insensitive
    @Test
    void recommendExcludesSearchedMovieCaseInsensitive() {
        DataBase db = new DataBase();
        Movie A = new Movie("AbC", "A", new ArrayList<>(Arrays.asList("G")));
        Movie B = new Movie("b", "B", new ArrayList<>(Arrays.asList("G")));
        db.setMoviesDataBase(new ArrayList<>(Arrays.asList(A, B)));

        User u = new User("u", "U");
        // searched movie id differs in case but should still be excluded
        u.setSearchedMovie(new Movie("aBc", "A", new ArrayList<>(Arrays.asList("G"))));
        db.getUsersDataBase().add(u);

        db.movieRecommend();
        ArrayList<Movie> rec = u.getRecommendedMovies();
        // only B should be recommended
        assertEquals(1, rec.size());
        assertEquals("b", rec.get(0).getId());
    }

    // Test ID: TC-DB-008 | Movie.addGenre Throws On Invalid
    @Test
    void movieAddGenreThrowsOnInvalid() {
        Movie m = new Movie("x", "X");
        assertThrows(IllegalArgumentException.class, () -> m.addGenre(null));
        assertThrows(IllegalArgumentException.class, () -> m.addGenre(""));
    }

    // Test ID: TC-DB-009 | Recommend With DB Movie Null Genre Produces No Recommendations
    @Test
    void recommendWithDbMovieNullGenreProducesNoRecommendations() {
        DataBase db = new DataBase();
        // DB has a movie with null genre
        Movie dbMovie = new Movie("d1");
        db.setMoviesDataBase(new ArrayList<>(Arrays.asList(dbMovie)));

        // searched movie has a non-empty genre
        User u = new User("u3", "U3");
        u.setSearchedMovie(new Movie("s1", "S1", new ArrayList<>(Arrays.asList("Action"))));
        db.getUsersDataBase().add(u);

        db.movieRecommend();
        assertTrue(u.getRecommendedMovies().isEmpty());
    }

    // Test ID: TC-DB-010 | Recommend With Non-Matching Genres Produces No Recommendations
    @Test
    void recommendWithNonMatchingGenresProducesNoRecommendations() {
        DataBase db = new DataBase();
        Movie dbMovie = new Movie("m10", "M10", new ArrayList<>(Arrays.asList("Drama")));
        db.setMoviesDataBase(new ArrayList<>(Arrays.asList(dbMovie)));

        User u = new User("ux", "UX");
        u.setSearchedMovie(new Movie("s10", "S10", new ArrayList<>(Arrays.asList("Action"))));
        db.getUsersDataBase().add(u);

        db.movieRecommend();
        assertTrue(u.getRecommendedMovies().isEmpty());
    }

    
}
