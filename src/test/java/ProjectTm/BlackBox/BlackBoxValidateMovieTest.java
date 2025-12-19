package ProjectTm.BlackBox;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ProjectTm.ValidateMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateMovie class
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateMovie")
public class BlackBoxValidateMovieTest {

    private ValidateMovie validateMovie;
    private Set<String> existingMovieIds;

    @BeforeEach
    public void setUp() {
        existingMovieIds = new HashSet<>();
        validateMovie = new ValidateMovie(existingMovieIds);
    }

    // ==================== MOVIE NAME VALIDATION TESTS ====================

    @Nested
    @DisplayName("ValidateMovieName - Equivalence Partitioning & Boundary Value")
    class MovieNameValidationTests {

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

        // Equivalence Partition: Multiple spaces - Valid (empty tokens are skipped)
        @Test
        @DisplayName("EP1: Double spaces in name - Valid")
        public void testValidMovieName_DoubleSpaces() {
            assertTrue(validateMovie.ValidateMovieName("The  Dark Knight"));
        }

        // Equivalence Partition: Invalid - Lowercase in second word
        @Test
        @DisplayName("EP2: Lowercase in second word - Invalid")
        public void testInvalidMovieName_LowercaseSecondWord() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateMovie.ValidateMovieName("The dark Knight");
            });
        }

        // Boundary Value: Name with numbers
        @Test
        @DisplayName("BV3: Movie name with numbers - Valid")
        public void testValidMovieName_WithNumbers() {
            assertTrue(validateMovie.ValidateMovieName("Ocean's Eleven"));
        }
    }

    // ==================== MOVIE ID VALIDATION TESTS ====================

    @Nested
    @DisplayName("ValidateMovieId - Equivalence Partitioning & Boundary Value")
    class MovieIdValidationTests {

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
}
