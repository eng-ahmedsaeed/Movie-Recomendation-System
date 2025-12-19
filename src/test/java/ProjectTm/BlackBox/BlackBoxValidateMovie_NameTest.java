package ProjectTm.BlackBox;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateMovie.ValidateMovieName method
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateMovie Name")
public class BlackBoxValidateMovie_NameTest {

    private ValidateMovie validateMovie;
    private Set<String> existingMovieIds;

    @BeforeEach
    public void setUp() {
        existingMovieIds = new HashSet<>();
        validateMovie = new ValidateMovie(existingMovieIds);
    }

    // Equivalence Partition: Valid Names
    @Test
    @DisplayName("EP1: Single word with capital letter - Valid")
    public void testValidMovieName_SingleCapitalWord() {
        assertTrue(validateMovie.ValidateMovieName("Avatar"));
    }

    @Test
    @DisplayName("EP1: Multiple words with capital letters - Valid")
    public void testValidMovieName_MultipleCapitalWords() {
        assertTrue(validateMovie.ValidateMovieName("The Dark Knight"));
    }


    // Boundary Value: Minimum length name
    @Test
    @DisplayName("BV1: Minimum valid movie name - Single character")
    public void testValidMovieName_MinimumLength() {
        assertTrue(validateMovie.ValidateMovieName("A"));
    }

    // Boundary Value: Very long name
    @Test
    @DisplayName("BV2: Long movie name - Valid")
    public void testValidMovieName_LongName() {
        assertTrue(validateMovie.ValidateMovieName("The Chronicles Of Narnia The Lion The Witch And The Wardrobe"));
    }

    // Equivalence Partition: Valid - Name with numbers
    @Test
    @DisplayName("EP1: Movie name with numbers - Valid")
    public void testValidMovieName_WithNumbers() {
        assertTrue(validateMovie.ValidateMovieName("Oceans11"));
    }

    // Equivalence Partition: Valid - Name with special characters
    @Test
    @DisplayName("EP1: Movie name with apostrophe - Valid")
    public void testValidMovieName_WithApostrophe() {
        assertTrue(validateMovie.ValidateMovieName("Schindler's List"));
    }

    // Equivalence Partition: Valid - Name with hyphen
    @Test
    @DisplayName("EP1: Movie name with hyphen - Valid")
    public void testValidMovieName_WithHyphen() {
        assertTrue(validateMovie.ValidateMovieName("Spider-Man"));
    }

    // Equivalence Partition: Invalid - Null name
    @Test
    @DisplayName("EP2: Null movie name - Invalid")
    public void testInvalidMovieName_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName(null);
        });
    }

    // Equivalence Partition: Invalid - Empty name
    @Test
    @DisplayName("EP2: Empty movie name - Invalid")
    public void testInvalidMovieName_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("");
        });
    }

    // Equivalence Partition: Invalid - Only whitespace
    @Test
    @DisplayName("EP2: Only whitespace - Invalid")
    public void testInvalidMovieName_OnlyWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("   ");
        });
    }

    // Equivalence Partition: Invalid - Lowercase first letter
    @Test
    @DisplayName("EP2: Lowercase first letter - Invalid")
    public void testInvalidMovieName_LowercaseStart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("avatar");
        });
    }

    // Equivalence Partition: Invalid - Lowercase in second word
    @Test
    @DisplayName("EP2: Lowercase in second word - Invalid")
    public void testInvalidMovieName_LowercaseSecondWord() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("The dark Knight");
        });
    }

    /*
    // all of those test spaces, these were vauge and not specified in the description,
     //so i left them there just in case
    // Boundary Value: Multiple consecutive spaces
    @Test
    @DisplayName("BV3: Multiple consecutive spaces - Invalid")
    public void testInvalidMovieName_MultipleSpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("The  Dark  Knight");
        });
    }

    // Boundary Value: Leading space
    @Test
    @DisplayName("BV4: Leading space - Invalid")
    public void testInvalidMovieName_LeadingSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName(" Avatar");
        });
    }

    // Boundary Value: Trailing space
    @Test
    @DisplayName("BV5: Trailing space - Invalid")
    public void testInvalidMovieName_TrailingSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("Avatar ");
        });
    }
*/
    // Equivalence Partition: Invalid - All lowercase
    @Test
    @DisplayName("EP2: All lowercase - Invalid")
    public void testInvalidMovieName_AllLowercase() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieName("the dark knight");
        });
    }

    // Equivalence Partition: Valid - All caps words
    @Test
    @DisplayName("EP1: All uppercase words - Valid")
    public void testValidMovieName_AllUppercase() {
        assertTrue(validateMovie.ValidateMovieName("IT"));
    }

    // Boundary Value: Single letter words
    @Test
    @DisplayName("BV6: Single letter words - Valid")
    public void testValidMovieName_SingleLetterWords() {
        assertTrue(validateMovie.ValidateMovieName("A I"));
    }
}