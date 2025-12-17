package ProjectTm;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateUser.validateUserId method
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateUser ID")
public class BlackBoxValidateUserIdTest {

    private ValidateUser validateUser;
    private Set<String> existingUserIds;

    @BeforeEach
    public void setUp() {
        existingUserIds = new HashSet<>();
        validateUser = new ValidateUser(existingUserIds);
    }

    // Equivalence Partition: Valid User IDs
    @Test
    @DisplayName("EP1: Valid user ID - 9 digits")
    public void testValidUserId_AllDigits() {
        assertTrue(validateUser.validateUserId("123456789"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - Numbers and letters")
    public void testValidUserId_MixedAlphanumeric() {
        assertTrue(validateUser.validateUserId("1A2B3C4D5"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - Starting with 0")
    public void testValidUserId_StartingWithZero() {
        assertTrue(validateUser.validateUserId("012345678"));
    }

    // Boundary Value: Exactly 9 characters
    @Test
    @DisplayName("BV1: Exactly 9 characters - Boundary value")
    public void testValidUserId_ExactlyNineChars() {
        assertTrue(validateUser.validateUserId("1a2b3c4d5"));
    }

    // Boundary Value: Less than 9 characters
    @Test
    @DisplayName("BV2: 8 characters - Below boundary")
    public void testInvalidUserId_TooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("12345678");
        });
    }

    // Boundary Value: More than 9 characters
    @Test
    @DisplayName("BV3: 10 characters - Above boundary")
    public void testInvalidUserId_TooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1234567890");
        });
    }

    // Equivalence Partition: Invalid - Null ID
    @Test
    @DisplayName("EP2: Null user ID - Invalid")
    public void testInvalidUserId_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId(null);
        });
    }

    // Equivalence Partition: Invalid - Empty ID
    @Test
    @DisplayName("EP2: Empty user ID - Invalid")
    public void testInvalidUserId_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("");
        });
    }

    // Equivalence Partition: Invalid - Non-digit first character
    @Test
    @DisplayName("EP2: Non-digit first character - Invalid")
    public void testInvalidUserId_NonDigitStart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("A23456789");
        });
    }

    // Equivalence Partition: Invalid - Special characters
    @Test
    @DisplayName("EP2: Special characters in ID - Invalid")
    public void testInvalidUserId_SpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123@56789");
        });
    }

    @Test
    @DisplayName("EP2: Special character - Space in ID - Invalid")
    public void testInvalidUserId_SpaceCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123 56789");
        });
    }

    @Test
    @DisplayName("EP2: Special character - Dash in ID - Invalid")
    public void testInvalidUserId_DashCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123-56789");
        });
    }

    // Equivalence Partition: Invalid - Duplicate ID
    @Test
    @DisplayName("EP2: Duplicate user ID - Invalid")
    public void testInvalidUserId_Duplicate() {
        validateUser.validateUserId("123456789");
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123456789");
        });
    }

    // Boundary Value: Maximum valid numeric value
    @Test
    @DisplayName("BV4: Maximum numeric sequence - Valid")
    public void testValidUserId_MaxNumeric() {
        assertTrue(validateUser.validateUserId("999999999"));
    }
}
