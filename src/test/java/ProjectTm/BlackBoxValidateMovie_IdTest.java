package ProjectTm;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateMovie.ValidateMovieId method
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateMovie ID")
public class BlackBoxValidateMovie_IdTest {

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

    // Boundary Value: Numeric part with leading zeros
    @Test
    @DisplayName("BV8: Numeric part with leading zeros - Valid")
    public void testValidMovieId_LeadingZeros() {
        assertTrue(validateMovie.ValidateMovieId("TDK001", "The Dark Knight"));
    }

    // Equivalence Partition: Valid - Very long prefix
    @Test
    @DisplayName("EP3: Very long capital letter prefix - Valid")
    public void testValidMovieId_LongPrefix() {
        assertTrue(validateMovie.ValidateMovieId("TCONTLTWTW001",
                "The Chronicles Of Narnia The Lion The Witch The Wardrobe"));
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

    // Equivalence Partition: Invalid - Missing prefix
    @Test
    @DisplayName("EP4: Only numeric part, no prefix - Invalid")
    public void testInvalidMovieId_NoPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("001", "Avatar");
        });
    }

    // Equivalence Partition: Invalid - Partial prefix match
    @Test
    @DisplayName("EP4: Partial prefix match - Invalid")
    public void testInvalidMovieId_PartialPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TD001", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Extra letters in prefix
    @Test
    @DisplayName("EP4: Extra letters in prefix - Invalid")
    public void testInvalidMovieId_ExtraPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDKX001", "The Dark Knight");
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
    @DisplayName("EP4: Non-numeric suffix instead of numbers - Invalid")
    public void testInvalidMovieId_NonNumericSuffix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDKABC", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Mixed alphanumeric in numeric part
    @Test
    @DisplayName("EP4: Mixed alphanumeric in numeric part - Invalid")
    public void testInvalidMovieId_MixedNumericPart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK01A", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - extra alphanumeric in numeric part
    @Test
    @DisplayName("EP4: added alphanumeric in numeric part - Invalid")
    public void testInvalidMovieId_AddedNumericPart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK001A", "The Dark Knight");
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

    // Equivalence Partition: Invalid - Duplicate number across different movies
    @Test
    @DisplayName("EP4: Duplicate numeric part different movie - Invalid")
    public void testInvalidMovieId_DuplicateNumericDifferentMovie() {
        validateMovie.ValidateMovieId("TDK001", "The Dark Knight");
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("A001", "Avatar");
        });
    }

    // Equivalence Partition: Invalid - Duplicate letters across different movies
    @Test
    @DisplayName("EP4: Duplicate letter prefix - Invalid")
    public void testInvalidMovieId_DuplicateLetters() {
        validateMovie.ValidateMovieId("TDK001", "The Dark Knight");
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK002", "The Deep Knight");
        });
    }

    // Equivalence Partition: Invalid - Lowercase letters in prefix
    @Test
    @DisplayName("EP4: Lowercase letters in prefix - Invalid")
    public void testInvalidMovieId_LowercasePrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("Tdk001", "The Dark Knight");
        });
    }


    // Equivalence Partition: Invalid - Special characters in ID
    @Test
    @DisplayName("EP4: Special characters in ID - Invalid")
    public void testInvalidMovieId_SpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK@01", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Spaces in ID
    @Test
    @DisplayName("EP4: Spaces in ID - Invalid")
    public void testInvalidMovieId_SpacesInId() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK 001", "The Dark Knight");
        });
    }

    // Equivalence Partition: Invalid - Negative numbers
    @Test
    @DisplayName("EP4: Negative numbers in numeric part - Invalid")
    public void testInvalidMovieId_NegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateMovie.ValidateMovieId("TDK-01", "The Dark Knight");
        });
    }

    // Boundary Value: Single digit repeated
    @Test
    @DisplayName("BV9: All same digits in numeric part - Valid")
    public void testValidMovieId_RepeatedDigits() {
        assertTrue(validateMovie.ValidateMovieId("TDK111", "The Dark Knight"));
    }

    // Boundary Value: Sequential numbers
    @Test
    @DisplayName("BV10: Sequential numbers - Valid")
    public void testValidMovieId_SequentialNumbers() {
        assertTrue(validateMovie.ValidateMovieId("TDK123", "The Dark Knight"));
    }
}