package ProjectTm;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateMovieTest {

    @Test
    void validateMovieId_validId_returnsTrue() {
        Set<String> ids = new HashSet<>();
        ValidateMovie validator = new ValidateMovie(ids);
        assertTrue(validator.ValidateMovieId("HP123", "Harry Potter"));
        assertTrue(ids.contains("123"));
    }

    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("WR123", "Harry Potter"));
    }

    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        Set<String> ids = new HashSet<>();
        ids.add("123"); // Numeric part already exists
        ValidateMovie validator = new ValidateMovie(ids);
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP123", "Harry Potter"));
    }

    @Test
    void validateMovieName_validName_returnsTrue() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertTrue(validator.ValidateMovieName("Harry Potter"));
    }

    @Test
    void validateMovieName_lowercaseWord_throwsException() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName("harry Potter"));
    }

    @Test
    void validateMovieId_lessThan3Digits_throwsException() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12", "Harry Potter"));
    }

    @Test
    void validateMovieId_nonDigitsAfterPrefix_throwsException() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12A", "Harry Potter"));
    }
}