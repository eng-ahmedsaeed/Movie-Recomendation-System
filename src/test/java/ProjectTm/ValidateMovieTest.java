package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


// unit tests are a type of white box testing

public class ValidateMovieTest {
    Set<String> ids;
    ValidateMovie validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateMovie(ids);
    }

    @Test
    void validateMovieId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId(null, "Harry Potter"));
    }

    @Test
    void validateMovieId_EmptyId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("", "Harry Potter"));
    }

    @Test
    void validateMovieId_validId_returnsTrue() {
        assertTrue(validator.ValidateMovieId("HP123", "Harry Potter"));
        assertTrue(ids.contains("123"));
    }

    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("WR123", "Harry Potter"));
    }

    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        ids.add("123"); // Numeric part already exists
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP123", "Harry Potter"));
    }

    @Test
    void validateMovieId_lessThan3Digits_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12", "Harry Potter"));
    }

    @Test
    void validateMovieId_nonDigitsAfterPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12A", "Harry Potter"));
    }


    @Test
    void validateMovieName_validName_returnsTrue() {
        assertTrue(validator.ValidateMovieName("Harry Potter"));
    }

    @Test
    void validateMovieName_lowercaseWord_throwsException() {
        ValidateMovie validator = new ValidateMovie(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName("harry Potter"));
    }

    @Test
    void validateMovieName_null_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(null));
    }

    @Test
    void validateMovieName_empty_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(""));
    }

}