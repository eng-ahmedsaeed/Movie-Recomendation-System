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
 * Black Box Testing for ValidateUser.validateUserId method
 * Using Boundary Value Analysis and Equivalence Partitioning
 *
 * Requirements:
 * - Must be exactly 9 characters
 * - Must be alphanumeric
 * - Must start with numbers
 * - May end with only one alphabetic character
 * - Must be unique
 */
@DisplayName("Black Box Testing - ValidateUser ID")
public class BlackBoxValidateUser_IdTest {

    private ValidateUser validateUser;
    private Set<String> existingUserIds;

    @BeforeEach
    public void setUp() {
        existingUserIds = new HashSet<>();
        validateUser = new ValidateUser(existingUserIds);
    }

    // ==================== EQUIVALENCE PARTITION 1: Valid User IDs ====================

    @Test
    @DisplayName("EP1: Valid user ID - All 9 digits")
    public void testValidUserId_AllDigits() {
        assertTrue(validateUser.validateUserId("122456789"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - 8 digits + 1 upper letter at end")
    public void testValidUserId_EightDigitsOneLetter() {
        assertTrue(validateUser.validateUserId("12345678A"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - 8 digits + lowercase letter at end")
    public void testValidUserId_EightDigitsLowercaseLetter() {
        assertTrue(validateUser.validateUserId("12345678z"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - Starting with 0")
    public void testValidUserId_StartingWithZero() {
        assertTrue(validateUser.validateUserId("012345678"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - All zeros")
    public void testValidUserId_AllZeros() {
        assertTrue(validateUser.validateUserId("000000000"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - 8 zeros + letter")
    public void testValidUserId_ZerosWithLetter() {
        assertTrue(validateUser.validateUserId("00000000B"));
    }

    @Test
    @DisplayName("EP1: Valid user ID - Mixed digits with uppercase at end")
    public void testValidUserId_MixedDigitsUppercase() {
        assertTrue(validateUser.validateUserId("98765432Z"));
    }

    // ==================== BOUNDARY VALUE 1: Length ====================

    @Test
    @DisplayName("BV1: Exactly 9 characters - Valid boundary")
    public void testValidUserId_ExactlyNineChars() {
        assertTrue(validateUser.validateUserId("123456789"));
    }

    @Test
    @DisplayName("BV2: 8 characters - Below boundary (Invalid)")
    public void testInvalidUserId_EightChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("12345678");
        });
    }

    @Test
    @DisplayName("BV3: 10 characters - Above boundary (Invalid)")
    public void testInvalidUserId_TenChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1234567890");
        });
    }

    @Test
    @DisplayName("BV4: 1 character - Minimum boundary (Invalid)")
    public void testInvalidUserId_OneChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1");
        });
    }

    @Test
    @DisplayName("BV5: 0 characters - Empty (Invalid)")
    public void testInvalidUserId_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("");
        });
    }

    // ==================== EQUIVALENCE PARTITION 2: Invalid Starting Character ====================

    @Test
    @DisplayName("EP2: Starting with uppercase letter - Invalid")
    public void testInvalidUserId_StartsWithUppercase() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("A23456789");
        });
    }

    @Test
    @DisplayName("EP2: Starting with lowercase letter - Invalid")
    public void testInvalidUserId_StartsWithLowercase() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("a23456789");
        });
    }

    @Test
    @DisplayName("EP2: Starting with special character - Invalid")
    public void testInvalidUserId_StartsWithSpecialChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("@23456789");
        });
    }

    @Test
    @DisplayName("EP2: Starting with space - Invalid")
    public void testInvalidUserId_StartsWithSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId(" 23456789");
        });
    }

    // ==================== EQUIVALENCE PARTITION 3: Invalid Characters ====================

    @Test
    @DisplayName("EP3: Special character @ in middle - Invalid")
    public void testInvalidUserId_SpecialCharAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123@56789");
        });
    }

    @Test
    @DisplayName("EP3: Special character # in middle - Invalid")
    public void testInvalidUserId_SpecialCharHash() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123#56789");
        });
    }

    @Test
    @DisplayName("EP3: Space character in middle - Invalid")
    public void testInvalidUserId_SpaceInMiddle() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123 56789");
        });
    }

    @Test
    @DisplayName("EP3: Dash character in middle - Invalid")
    public void testInvalidUserId_DashInMiddle() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123-56789");
        });
    }

    @Test
    @DisplayName("EP3: Underscore character - Invalid")
    public void testInvalidUserId_UnderscoreChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123_56789");
        });
    }

    @Test
    @DisplayName("EP3: Dot character - Invalid")
    public void testInvalidUserId_DotChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123.56789");
        });
    }

    // ==================== EQUIVALENCE PARTITION 4: Invalid Letter Positions ====================

    @Test
    @DisplayName("EP4: Letter in middle position - Invalid")
    public void testInvalidUserId_LetterInMiddle() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1234A6789");
        });
    }

    @Test
    @DisplayName("EP4: Letter in second position - Invalid")
    public void testInvalidUserId_LetterInSecondPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1A3456789");
        });
    }

    @Test
    @DisplayName("EP4: Two letters at end - Invalid")
    public void testInvalidUserId_TwoLettersAtEnd() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1234567AB");
        });
    }

    @Test
    @DisplayName("EP4: Multiple letters scattered - Invalid")
    public void testInvalidUserId_MultipleLettersScattered() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("1A3B5C7D9");
        });
    }

    @Test
    @DisplayName("EP4:Start with letter - Invalid")
    public void testInvalidUserId_StartWithLetter() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("A12345678");
        });
    }


    // ==================== EQUIVALENCE PARTITION 5: Null and Edge Cases ====================

    @Test
    @DisplayName("EP5: Null user ID - Invalid")
    public void testInvalidUserId_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId(null);
        });
    }

    @Test
    @DisplayName("EP5: Only spaces - Invalid")
    public void testInvalidUserId_OnlySpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("         ");
        });
    }

    @Test
    @DisplayName("EP5: Only letters - Invalid")
    public void testInvalidUserId_OnlyLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("ABCDEFGHI");
        });
    }

    @Test
    @DisplayName("EP5: All special characters - Invalid")
    public void testInvalidUserId_AllSpecialChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("@#$%^&*()");
        });
    }

    // ==================== EQUIVALENCE PARTITION 6: Duplicate IDs ====================

    @Test
    @DisplayName("EP6: Duplicate user ID - Invalid")
    public void testInvalidUserId_Duplicate() {
        validateUser.validateUserId("123456789");
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("123456789");
        });
    }

    @Test
    @DisplayName("EP6: Duplicate with letter - Invalid")
    public void testInvalidUserId_DuplicateWithLetter() {
        validateUser.validateUserId("12345678A");
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserId("12345678A");
        });
    }

    @Test
    @DisplayName("EP6: Case-sensitive uniqueness test")
    public void testValidUserId_CaseSensitiveUnique() {
        validateUser.validateUserId("12345678A");
        // Should be valid if case matters for uniqueness
        assertTrue(validateUser.validateUserId("12345678a"));
    }

    // ==================== BOUNDARY VALUE 6: Numeric Boundaries ====================

    @Test
    @DisplayName("BV6: Maximum numeric sequence 999999999 - Valid")
    public void testValidUserId_MaxNumeric() {
        assertTrue(validateUser.validateUserId("999999999"));
    }

    @Test
    @DisplayName("BV7: Minimum numeric sequence 000000000 - Valid")
    public void testValidUserId_MinNumeric() {
        assertTrue(validateUser.validateUserId("000000000"));
    }

    @Test
    @DisplayName("BV8: Maximum with ending Z - Valid")
    public void testValidUserId_MaxWithZ() {
        assertTrue(validateUser.validateUserId("99999999Z"));
    }

    @Test
    @DisplayName("BV9: Maximum with ending A - Valid")
    public void testValidUserId_MaxWithA() {
        assertTrue(validateUser.validateUserId("99999999A"));
    }

    // ==================== EQUIVALENCE PARTITION 7: Mixed Valid Cases ====================

    @Test
    @DisplayName("EP7: Sequential digits - Valid")
    public void testValidUserId_SequentialDigits() {
        assertTrue(validateUser.validateUserId("123456789"));
    }

    @Test
    @DisplayName("EP7: Reverse sequential digits - Valid")
    public void testValidUserId_ReverseSequential() {
        assertTrue(validateUser.validateUserId("987654321"));
    }

    @Test
    @DisplayName("EP7: Alternating digits - Valid")
    public void testValidUserId_AlternatingDigits() {
        assertTrue(validateUser.validateUserId("101010101"));
    }

    @Test
    @DisplayName("EP7: Random valid pattern - Valid")
    public void testValidUserId_RandomPattern() {
        assertTrue(validateUser.validateUserId("192837465"));
    }
}