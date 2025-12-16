package Validate_Movie_User_Coverage_Tests;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateMoviePathCoverageTest {
    Set<String> ids;
    ValidateMovie validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateMovie(ids);
    }

    // ValidateMovieName Method
    // path 1
    @Test
    void validateMovieName_validName_returnsTrue() {
        assertTrue(validator.ValidateMovieName("Harry   Potter"));
    }
    // path 2
    @Test
    void validateMovieName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(null));
    }
    // path 3
    @Test
    void validateMovieName_lowercaseWord_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName("the Dark Knight"));
    }

    // ValidateMovieId
    // path 1
    @Test
    void validateMovieId_ValidId_returnsTrue() {
        assertTrue(validator.ValidateMovieId("HP123", "Harry Potter"));
    }

    // path 2
    @Test
    void validateMovieId_nullId_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validator.ValidateMovieId(null, "Inception")
        );
    }

    // path 3
    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("X123", "Inception"));
    }

    // path 4
    @Test
    void validateMovieId_numericTooShort_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("I12", "Inception"));
    }

    // path 5
    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        ids.add("123");
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("A123", "Avatar")
        );
    }
}