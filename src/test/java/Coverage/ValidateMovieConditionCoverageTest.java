package Coverage;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateMovieConditionCoverageTest {

    Set<String> ids;
    ValidateMovie validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateMovie(ids);
    }

    // ValidateMovieName

    //•  Condition A: name == null || name.isEmpty()
    //            •	Subcondition A1: name == null (TRUE/FALSE)
    //            •	Subcondition A2: name.isEmpty() (TRUE/FALSE)
    //            •  Condition B: word.isEmpty() (in loop)
    //            •	TRUE: word is empty
    //            •	FALSE: word is not empty
    // •  Condition C: !Character.isUpperCase(word.charAt(0))
    //            •	TRUE: first character is NOT uppercase
    //            •	FALSE: first character IS uppercase

    // All Conditions are false
    @Test
    void validateMovieName_validSingleWord_returnsTrue() {
        assertTrue(validator.ValidateMovieName("Inception"));
    }

    // A1 is true
    @Test
    void validateMovieName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(null));
    }

    // A1 is false, A2 is true
    // A1 and A2 can't be true at the same time
    @Test
    void validateMovieName_emptyName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName(""));
    }

    // B is true and the rest are false
    @Test
    void validateMovieName_withExtraSpaces_returnsTrue() {
        assertTrue(validator.ValidateMovieName("The   Dark  Knight"));
    }

    // C is true and the rest are false
    @Test
    void validateMovieName_lowercaseWord_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieName("the Dark Knight"));
    }


    // ValidateMovieId

//    	Condition A: id == null || id.isEmpty()
//      	A1: id == null (TRUE/FALSE)
//    	    A2: id.isEmpty() (TRUE/FALSE)
//      Condition B: Character.isUpperCase(c)
//          TRUE: character is uppercase
//          FALSE: character is NOT uppercase
//      Condition C: !id.startsWith(expectedPrefix)
//          TRUE: id doesn't start with prefix
//      	FALSE: id starts with prefix
//  	Condition D: numericPart.length() != 3 || !numericPart.matches("\\d+")
//          D1: numericPart.length() != 3 (TRUE/FALSE)
//          D2: !numericPart.matches("\\d+") (TRUE/FALSE)
//     	Condition E: existingMovieIds.contains(numericPart)
//      	TRUE: numeric part already exists
//      	FALSE: numeric part is unique


    // Condition A1 TRUE
    @Test
    void validateMovieId_nullId_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validator.ValidateMovieId(null, "Inception")
        );
    }

    // Condition A2 TRUE
    // A1 and A2 can't be true at the same time
    @Test
    void validateMovieId_emptyId_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.ValidateMovieId("", "Inception")
        );
        assertTrue(ex.getMessage().contains("Movie Id can't be null or empty"));
    }



    // Condition B FALSE
    @Test
    void validateMovieId_noUppercaseInName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("123", "the movie"));
    }

    // Condition B TRUE
    @Test
    void validateMovieId_withUppercase_returnsTrue() {
        assertTrue(validator.ValidateMovieId("I123", "Inception"));
    }

    // Condition C TRUE
    @Test
    void validateMovieId_wrongPrefix_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("X123", "Inception"));
    }

    // Condition C FALSE
    @Test
    void validateMovieId_correctPrefix_returnsTrue() {
        boolean result = validator.ValidateMovieId("TDK456", "The Dark Knight");
        assertTrue(result);
    }

    // Condition D1 TRUE
    @Test
    void validateMovieId_numericTooShort_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("I12", "Inception"));
    }

    // Condition D2 TRUE
    @Test
    void validateMovieId_numericHasLetters_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("I12A", "Inception"));
    }

    // Condition D1 & D2 FALSE
    @Test
    void validateMovieId_validNumeric_returnsTrue() {
        assertTrue(validator.ValidateMovieId("TDK789", "The Dark Knight"));
        assertTrue(ids.contains("789"));
    }

    // Condition E TRUE
    @Test
    void validateMovieId_duplicateNumeric_throwsException() {
        ids.add("123");
        assertThrows(IllegalArgumentException.class,
                () -> validator.ValidateMovieId("A123", "Avatar")
        );
    }

    // Condition E FALSE
    @Test
    void validateMovieId_uniqueNumeric_returnsTrue() {
        assertTrue(validator.ValidateMovieId("SW999", "Star Wars"));
    }

}