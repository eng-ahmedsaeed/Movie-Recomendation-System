package ProjectTm.BlackBox;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateUser.validateUserName method
 * Using Boundary Value Analysis and Equivalence Partitioning
 */
@DisplayName("Black Box Testing - ValidateUser Name")
public class BlackBoxValidateUserNameTest {

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
}
