package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Black Box Testing for ValidateMovie.ValidateMovieId method
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateMovie ID")
public class BlackBoxValidateMovieIdTest {

    private ValidateMovie validateMovie;
    private Set<String> existingMovieIds;

    @BeforeEach
    public void setUp() {
        existingMovieIds = new HashSet<>();
        validateMovie = new ValidateMovie(existingMovieIds);
    }

    // Equivalence Partition: Valid IDs
    @Test
    @DisplayName("EP3: Valid movie ID format - Correct structure")
    public void testValidMovieId_CorrectFormat() {
        assertTrue(validateMovie.ValidateMovieId("TDK001", "The Dark Knight"));
    }

    @Test
    @DisplayName("EP3: Valid movie ID - Single letter prefix")
    public void testValidMovieId_SingleLetterPrefix() {
        assertTrue(validateMovie.ValidateMovieId("A001", "Avatar"));
    }

    @Test
    @DisplayName("EP3: Valid movie ID - Multiple capital letters")
    public void testValidMovieId_MultipleLetters() {
        assertTrue(validateMovie.ValidateMovieId("ITBI001", "Inception The Beginning Id"));
    }

    // Boundary Value: Minimum numeric part
    @Test
    @DisplayName("BV4: Minimum numeric sequence - 000")
    public void testValidMovieId_MinimumNumeric() {
        assertTrue(validateMovie.ValidateMovieId("TDK000", "The Dark Knight"));
    }

    // Boundary Value: Maximum numeric part
    @Test
    @DisplayName("BV5: Maximum numeric sequence - 999")
    public void testValidMovieId_MaximumNumeric() {
        assertTrue(validateMovie.ValidateMovieId("TDK999", "The Dark Knight"));
    }

    // Equivalence Partition: Invalid - Null ID
    @Test
    @DisplayName("EP4: Null movie ID - Invalid")
    public void testInvalidMovieId_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId(null, "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Empty ID
    @Test
    @DisplayName("EP4: Empty movie ID - Invalid")
    public void testInvalidMovieId_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Wrong prefix
    @Test
    @DisplayName("EP4: Wrong prefix letters - Invalid")
    public void testInvalidMovieId_WrongPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("XYZ001", "The Dark Knight");
        });
    }

    // Boundary Value: Numeric part too short
    @Test
    @DisplayName("BV6: Less than 3 numeric digits - Invalid")
    public void testInvalidMovieId_TooFewNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK01", "The Dark Knight");
        });
    }

    // Boundary Value: Numeric part too long
    @Test
    @DisplayName("BV7: More than 3 numeric digits - Invalid")
    public void testInvalidMovieId_TooManyNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK0001", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Non-numeric suffix
    @Test
    @DisplayName("EP4: Non-numeric numeric part - Invalid")
    public void testInvalidMovieId_NonNumericSuffix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDKABC", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Duplicate ID
    @Test
    @DisplayName("EP4: Duplicate numeric part - Invalid")
    public void testInvalidMovieId_DuplicateNumeric() {
        validateMovie.ValidateMovieId("TDK001", "The Dark Knight");
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK001", "The Dark Knight");
        });
    }

    // Boundary Value: Numeric part with leading zeros
    @Test
    @DisplayName("BV8: Numeric part with leading zeros - Valid")
    public void testValidMovieId_LeadingZeros() {
        assertTrue(validateMovie.ValidateMovieId("TDK001", "The Dark Knight"));
    }
}
