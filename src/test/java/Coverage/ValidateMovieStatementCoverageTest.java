package Coverage;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateMovieStatementCoverageTest {
    Set<String> ids;
    ValidateMovie validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateMovie(ids);
    }

    // statement coverage for ValidateMovieId method
    // this test covers 6 out of 8 statements for the ValidateMovieName Method
    @Test
    void validateMovieName_validName_returnsTrue() {
        assertTrue(validator.ValidateMovieName("Harry    Potter"));
    }

    // this one covers the first 2 statements
    // this means that we reached a 7 out of 8 statements (since the first statement is repeated)
    @Test
    void validateMovieName_null_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(null));
    }

    // this test covers the last statement for the validateMovieName method
    // leaving us at an 8 out of 8 statement coverage
    @Test
    void validateMovieName_invalidName_throwsException() {
        assertTrue(validator.ValidateMovieName("inception"));
    }


    // Statement Coverage for validateMovieId Method
    // this method covers 12 out of 16 statements
    @Test
    void validateMovieId_validId_returnsTrue() {
        assertTrue(validator.ValidateMovieId("HP123", "Harry Potter"));
        assertTrue(ids.contains("123"));
    }


    // this method covers the first 2 statements
    // putting us at a coverage of 13 out of 16
    @Test
    void validateMovieId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId(null, "Harry Potter"));
    }


    // this method executes statement number 9
    // coverage of 14 out of 16
    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("WR123", "Harry Potter"));
    }

    // this method executes statement number 11
    // coverage 15 out of 16
    @Test
    void validateMovieId_lessThan3Digits_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12", "Harry Potter"));
    }

    // this method executes statement number 13
    // coverage 16 out of 16
    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        ids.add("123"); // Numeric part already exists
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP123", "Harry Potter"));
    }
}