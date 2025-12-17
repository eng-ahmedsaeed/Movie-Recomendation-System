package ProjectTm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class ReadMovieWhiteBox {


    private final String VALID_FILE =
            "src/main/resources/movies.txt";

    private final String FILE_WITH_EMPTY_LINE =
            "src/main/resources/movies_with_empty_line.txt";

    private final String FILE_MISSING_PART =
            "src/main/resources/movies_missing_part.txt";

    private final String FILE_MISSING_GENRE =
            "src/main/resources/movies_missing_genre.txt";

    private final String INVALID_PATH =
            "src/test/resources/not_existing.txt";

    // ===== Existing correct path test =====
    @Test
    void testReadMovieContent() throws IOException {
        ReadMovie rm = new ReadMovie(VALID_FILE);
        ArrayList<Movie> results = rm.getMovies();

        assertEquals("Lord Of The Ring", results.get(0).getName());
        assertEquals("LOTR126", results.get(0).getId());

        assertTrue(results.get(0).getGenre().contains("action"));
        assertTrue(results.get(0).getGenre().contains("fantasia"));

        assertEquals("Dragons Village2", results.get(3).getName());
    }

    // ===== File not found  =====
    @Test
    void testFileNotFound() {
        ReadMovie rm = new ReadMovie(INVALID_PATH);

        assertThrows(FileNotFoundException.class, rm::getMovies);
    }

    // ===== Empty line handling  =====
    @Test
    void testEmptyLine() throws Exception {
        ReadMovie rm = new ReadMovie(FILE_WITH_EMPTY_LINE);
        ArrayList<Movie> movies = rm.getMovies();

        assertEquals(1, movies.size());
        assertEquals("Test Movie", movies.get(0).getName());
    }

    // ===== Missing movie part  =====
    @Test
    void testMissingMovie() {
        ReadMovie rm = new ReadMovie(FILE_MISSING_PART);

        assertThrows(IllegalArgumentException.class, rm::getMovies);
    }

    // ===== Missing genres =====
    @Test
    void testMissingGenres() {
        ReadMovie rm = new ReadMovie(FILE_MISSING_GENRE);

        assertThrows(IllegalArgumentException.class, rm::getMovies);
    }

    // ===== printMovies() coverage =====
    @Test
    void testPrintMovies() throws Exception {
        ReadMovie rm = new ReadMovie(VALID_FILE);
        rm.getMovies();

        assertDoesNotThrow(rm::printMovies);
    }
}
