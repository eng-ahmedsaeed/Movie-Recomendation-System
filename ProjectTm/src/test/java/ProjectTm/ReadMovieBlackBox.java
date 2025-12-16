package ProjectTm;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ReadMovieBlackBox {
    //Equivalence Class Partitioning black box techniques

    @Test
    void ECP_FileNotFound() {
        ReadMovie rm = new ReadMovie("wrong_path.txt");
        assertThrows(FileNotFoundException.class, () -> rm.getMovies());
    }
    @Test
    void ECP_InvalidMovieFile() {
        ReadMovie rm = new ReadMovie("src/main/resources/testingFiles/blackBoxInvalidMovies.txt");
        assertThrows(IllegalArgumentException.class, () -> rm.getMovies());
    }
    //comman
    @Test
    void ECP_ValidMovieFile() throws Exception {
        ReadMovie rm = new ReadMovie("src/main/resources/movies.txt");
        assertDoesNotThrow(() -> rm.getMovies());
    }

    //Dession Table
    @Test
    void testMissingName() {
        ReadMovie rm = new ReadMovie("src/main/resources/testingFiles/BlackBoxMissingName.txt");
        assertThrows(IllegalArgumentException.class, () -> rm.getMovies());
    }
    @Test
    void testMissingId() {
        ReadMovie rm = new ReadMovie("src/main/resources/testingFiles/BlackBoxMovieWithMissingId.txt");
        assertThrows(IllegalArgumentException.class, () -> rm.getMovies());
    }
    @Test
    void testMissingGenres() {
        ReadMovie rm = new ReadMovie("src/main/resources/testingFiles/BlackBoxMovieWithMissingGener.txt");
        assertThrows(IllegalArgumentException.class, () -> rm.getMovies());
    }

}