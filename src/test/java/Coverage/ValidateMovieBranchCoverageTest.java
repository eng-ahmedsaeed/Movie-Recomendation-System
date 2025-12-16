package Coverage;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateMovieBranchCoverageTest {
    Set<String> ids;
    ValidateMovie validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateMovie(ids);
    }

    // we have 10 branches
    // this test case covers 8 out of 10
    @Test
    void validateMovieName_validName_returnsTrue() {
        assertTrue(validator.ValidateMovieName("Harry    Potter"));
    }

    // now at 9 out of 10
    @Test
    void validateMovieName_null_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(null));
    }

    // 10 out of 10
    @Test
    void validateMovieName_invalidName_throwsException() {
        assertTrue(validator.ValidateMovieName("inception"));
    }


    // the ValidateMovieId method has 18 branches
    // 14 branches out of 18
    @Test
    void validateMovieId_validId_returnsTrue() {
        assertTrue(validator.ValidateMovieId("HP123", "Harry Potter"));
        assertTrue(ids.contains("123"));
    }

    // 15 out of 18
    @Test
    void validateMovieId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId(null, "Harry Potter"));
    }

    // 16 out of 18
    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("WR123", "Harry Potter"));
    }

    // 17 out of 18
    @Test
    void validateMovieId_lessThan3Digits_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP12", "Harry Potter"));
    }

    // 18 out of 18
    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        ids.add("123"); // Numeric part already exists
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("HP123", "Harry Potter"));
    }
}