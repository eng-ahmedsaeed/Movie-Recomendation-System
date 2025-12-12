package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Black Box Testing for ValidateUser class
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateUser")
public class BlackBoxValidateUserTest {

    private ValidateUser validateUser;
    private Set<String> existingUserIds;

    @BeforeEach
    public void setUp() {
        existingUserIds = new HashSet<>();
        validateUser = new ValidateUser(existingUserIds);
    }

    // ==================== USER ID VALIDATION TESTS ====================

    @DisplayName("ValidateUserId - Equivalence Partitioning & Boundary Value")
    public static class UserIdValidationTests {

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

        // Equivalence Partition: Invalid - Uppercase letters
        @Test
        @DisplayName("EP1: Valid - Uppercase letters allowed - Valid")
        public void testValidUserId_UppercaseLetters() {
            assertTrue(validateUser.validateUserId("1A2B3C4D5"));
        }

        // Boundary Value: Maximum valid numeric value
        @Test
        @DisplayName("BV4: Maximum numeric sequence - Valid")
        public void testValidUserId_MaxNumeric() {
            assertTrue(validateUser.validateUserId("999999999"));
        }
    }

    // ==================== USER NAME VALIDATION TESTS ====================

    @DisplayName("ValidateUserName - Equivalence Partitioning & Boundary Value")
    public static class UserNameValidationTests {

        private ValidateUser validateUser;
        private Set<String> existingUserIds;

        @BeforeEach
        public void setUp() {
            existingUserIds = new HashSet<>();
            validateUser = new ValidateUser(existingUserIds);
        }

        // Equivalence Partition: Valid Names
        @Test
        @DisplayName("EP3: Single word name - Valid")
        public void testValidUserName_SingleWord() {
            assertTrue(validateUser.validateUserName("Ahmed"));
        }

        @Test
        @DisplayName("EP3: Multiple word name - Valid")
        public void testValidUserName_MultipleWords() {
            assertTrue(validateUser.validateUserName("Ahmed Saeed"));
        }

        @Test
        @DisplayName("EP3: Name with multiple spaces - Valid")
        public void testValidUserName_MultipleSpaces() {
            assertTrue(validateUser.validateUserName("John Doe Smith"));
        }

        // Boundary Value: Minimum length name
        @Test
        @DisplayName("BV5: Single character name - Minimum")
        public void testValidUserName_MinimumLength() {
            assertTrue(validateUser.validateUserName("A"));
        }

        // Boundary Value: Very long name
        @Test
        @DisplayName("BV6: Long name with multiple words - Valid")
        public void testValidUserName_LongName() {
            assertTrue(validateUser.validateUserName("Muhammad Ahmed Ibrahim Hassan Saeed"));
        }

        // Equivalence Partition: Invalid - Null name
        @Test
        @DisplayName("EP4: Null user name - Invalid")
        public void testInvalidUserName_Null() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName(null);
            });
        }

        // Equivalence Partition: Invalid - Empty name
        @Test
        @DisplayName("EP4: Empty user name - Invalid")
        public void testInvalidUserName_Empty() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName("");
            });
        }

        // Equivalence Partition: Invalid - Starts with space
        @Test
        @DisplayName("EP4: Name starting with space - Invalid")
        public void testInvalidUserName_StartsWithSpace() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName(" Ahmed");
            });
        }

        // Equivalence Partition: Invalid - Contains numbers
        @Test
        @DisplayName("EP4: Name with numbers - Invalid")
        public void testInvalidUserName_WithNumbers() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName("Ahmed123");
            });
        }

        // Equivalence Partition: Invalid - Contains special characters
        @Test
        @DisplayName("EP4: Name with special characters - Invalid")
        public void testInvalidUserName_WithSpecialChars() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName("Ahmed@Saeed");
            });
        }

        @Test
        @DisplayName("EP4: Name with dash - Invalid")
        public void testInvalidUserName_WithDash() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName("Ahmed-Saeed");
            });
        }

        // Equivalence Partition: Valid - Lowercase letters
        @Test
        @DisplayName("EP3: Lowercase letters - Valid")
        public void testValidUserName_Lowercase() {
            assertTrue(validateUser.validateUserName("ahmed saeed"));
        }

        // Equivalence Partition: Valid - Mixed case
        @Test
        @DisplayName("EP3: Mixed case letters - Valid")
        public void testValidUserName_MixedCase() {
            assertTrue(validateUser.validateUserName("Ahmed SAEED"));
        }

        // Boundary Value: Name with single space between words
        @Test
        @DisplayName("BV7: Single space between words - Valid")
        public void testValidUserName_SingleSpace() {
            assertTrue(validateUser.validateUserName("John Doe"));
        }

        // Equivalence Partition: Invalid - Only spaces
        @Test
        @DisplayName("EP4: Only spaces - Invalid")
        public void testInvalidUserName_OnlySpaces() {
            assertThrows(IllegalArgumentException.class, () -> {
                validateUser.validateUserName("   ");
            });
        }
    }
}
