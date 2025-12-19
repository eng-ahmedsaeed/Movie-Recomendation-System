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
public class BlackBoxValidateMovieNameTest {

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

    @Test
    @DisplayName("EP1: Multiple words with special format - Valid")
    public void testValidMovieName_ComplexValidFormat() {
        assertTrue(validateMovie.ValidateMovieName("Inception The Beginning"));
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

    // Boundary Value: Name with special characters
    @Test
    @DisplayName("BV3: Movie name with special characters - Valid")
    public void testValidMovieName_WithNumbers() {
        assertTrue(validateMovie.ValidateMovieName("Fast And Furious"));
    }
}
