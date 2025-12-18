package ProjectTm;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Black Box Testing for ValidateUser.validateUserName method
 * Using Boundary Value Analysis and Equivalence Partitioning
 *
 * Requirements:
 * - Must be alphabetic characters and spaces only
 * - Must not start with space
 * - Can contain multiple words separated by spaces
 */
@DisplayName("Black Box Testing - ValidateUser Name")
public class BlackBoxValidateUser_NameTest {

    private ValidateUser validateUser;
    private Set<String> existingUserIds;

    @BeforeEach
    public void setUp() {
        existingUserIds = new HashSet<>();
        validateUser = new ValidateUser(existingUserIds);
    }

    // ==================== EQUIVALENCE PARTITION 1: Valid Names ====================

    @Test
    @DisplayName("EP1: Single word name - Valid")
    public void testValidUserName_SingleWord() {
        assertTrue(validateUser.validateUserName("Ahmed"));
    }

    @Test
    @DisplayName("EP1: Two word name - Valid")
    public void testValidUserName_TwoWords() {
        assertTrue(validateUser.validateUserName("Ahmed Sayed"));
    }

    @Test
    @DisplayName("EP1: Three word name - Valid")
    public void testValidUserName_ThreeWords() {
        assertTrue(validateUser.validateUserName("Ahmed Ali Sayed"));
    }

    @Test
    @DisplayName("EP1: Multiple words (5+) - Valid")
    public void testValidUserName_MultipleWords() {
        assertTrue(validateUser.validateUserName("Muhammad Ahmed Ibrahim Hassan Saeed"));
    }

    @Test
    @DisplayName("EP1: Name with single space between words - Valid")
    public void testValidUserName_SingleSpaceBetween() {
        assertTrue(validateUser.validateUserName("John Doe"));
    }

    @Test
    @DisplayName("EP1: Uppercase name - Valid")
    public void testValidUserName_Uppercase() {
        assertTrue(validateUser.validateUserName("AHMED SAEED"));
    }

    @Test
    @DisplayName("EP1: Lowercase name - Valid")
    public void testValidUserName_Lowercase() {
        assertTrue(validateUser.validateUserName("ahmed saeed"));
    }

    @Test
    @DisplayName("EP1: Mixed upper and lower case name - Valid")
    public void testValidUserName_MixedCase() {
        assertTrue(validateUser.validateUserName("Ahmed SAEED"));
    }

    @Test
    @DisplayName("EP1: Alternating case - Valid")
    public void testValidUserName_AlternatingCase() {
        assertTrue(validateUser.validateUserName("aHmEd SaEeD"));
    }

    // ==================== BOUNDARY VALUE 1: Length Boundaries ====================

    @Test
    @DisplayName("BV1: Single character - Minimum length (Valid)")
    public void testValidUserName_SingleChar() {
        assertTrue(validateUser.validateUserName("A"));
    }


    @Test
    @DisplayName("BV3: Very long name (50+ chars) - Valid")
    public void testValidUserName_VeryLongName() {
        assertTrue(validateUser.validateUserName("Muhammad Ahmed Ibrahim Hassan Mahmoud Ali Omar Khalid"));
    }



    // ==================== EQUIVALENCE PARTITION 2: Invalid - Null and Empty ====================

    @Test
    @DisplayName("EP2: Null user name - Invalid")
    public void testInvalidUserName_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName(null);
        });
    }

    @Test
    @DisplayName("EP2: Empty string - Invalid")
    public void testInvalidUserName_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("");
        });
    }

    @Test
    @DisplayName("EP2: Only multiple spaces - Invalid")
    public void testInvalidUserName_OnlySpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("     ");
        });
    }

    // ==================== EQUIVALENCE PARTITION 3: Invalid - Starts with Space ====================

    @Test
    @DisplayName("EP3: Single leading space - Invalid")
    public void testInvalidUserName_LeadingSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName(" Ahmed");
        });
    }

    @Test
    @DisplayName("EP3: Multiple leading spaces - Invalid")
    public void testInvalidUserName_MultipleLeadingSpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("   Ahmed");
        });
    }

    @Test

    @DisplayName("EP3: Tab at start - Invalid")
    public void testInvalidUserName_LeadingTab() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("\tAhmed");
        });
    }

    // ==================== EQUIVALENCE PARTITION 4: Invalid - Contains Numbers ====================

    @Test
    @DisplayName("EP4: Single digit at end - Invalid")
    public void testInvalidUserName_DigitAtEnd() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed1");
        });
    }

    @Test
    @DisplayName("EP4: Single digit at beginning - Invalid")
    public void testInvalidUserName_DigitAtStart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("1Ahmed");
        });
    }

    @Test
    @DisplayName("EP4: Digit in middle - Invalid")
    public void testInvalidUserName_DigitInMiddle() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ah3med");
        });
    }

    @Test
    @DisplayName("EP4: Multiple digits - Invalid")
    public void testInvalidUserName_MultipleDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed123");
        });
    }

    @Test
    @DisplayName("EP4: Digits between words - Invalid")
    public void testInvalidUserName_DigitsBetweenWords() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed 123 Saeed");
        });
    }

    @Test
    @DisplayName("EP4: Only digits - Invalid")
    public void testInvalidUserName_OnlyDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("123456");
        });
    }

    // ==================== EQUIVALENCE PARTITION 5: Invalid - Special Characters ====================

    @Test
    @DisplayName("EP5: At sign (@) - Invalid")
    public void testInvalidUserName_AtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed@Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Hash (#) - Invalid")
    public void testInvalidUserName_Hash() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed#Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Dollar sign ($) - Invalid")
    public void testInvalidUserName_DollarSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed$Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Percent (%) - Invalid")
    public void testInvalidUserName_Percent() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed%Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Ampersand (&) - Invalid")
    public void testInvalidUserName_Ampersand() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed&Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Asterisk (*) - Invalid")
    public void testInvalidUserName_Asterisk() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed*Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Plus (+) - Invalid")
    public void testInvalidUserName_Plus() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed+Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Equals (=) - Invalid")
    public void testInvalidUserName_Equals() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed=Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Underscore (_) - Invalid")
    public void testInvalidUserName_Underscore() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed_Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Hyphen/Dash (-) - Invalid")
    public void testInvalidUserName_Hyphen() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed-Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Period/Dot (.) - Invalid")
    public void testInvalidUserName_Period() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed.Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Comma (,) - Invalid")
    public void testInvalidUserName_Comma() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed,Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Semicolon (;) - Invalid")
    public void testInvalidUserName_Semicolon() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed;Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Colon (:) - Invalid")
    public void testInvalidUserName_Colon() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed:Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Quote (\") - Invalid")
    public void testInvalidUserName_Quote() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed\"Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Apostrophe (') - Invalid")
    public void testInvalidUserName_Apostrophe() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed'Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Forward slash (/) - Invalid")
    public void testInvalidUserName_ForwardSlash() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed/Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Backslash (\\) - Invalid")
    public void testInvalidUserName_Backslash() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed\\Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Pipe (|) - Invalid")
    public void testInvalidUserName_Pipe() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed|Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Brackets ([]) - Invalid")
    public void testInvalidUserName_Brackets() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed[Saeed]");
        });
    }

    @Test
    @DisplayName("EP5: Parentheses - Invalid")
    public void testInvalidUserName_Parentheses() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed(Saeed)");
        });
    }

    @Test
    @DisplayName("EP5: Exclamation (!) - Invalid")
    public void testInvalidUserName_Exclamation() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed!Saeed");
        });
    }

    @Test
    @DisplayName("EP5: Question mark (?) - Invalid")
    public void testInvalidUserName_QuestionMark() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed?Saeed");
        });
    }

    // ==================== EQUIVALENCE PARTITION 6: Invalid - Mixed Invalid Cases ====================

    @Test
    @DisplayName("EP6: Numbers and special characters - Invalid")
    public void testInvalidUserName_NumbersAndSpecialChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed123@#$");
        });
    }

    @Test
    @DisplayName("EP6: Special character at start - Invalid")
    public void testInvalidUserName_SpecialCharAtStart() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("@Ahmed");
        });
    }

    @Test
    @DisplayName("EP6: Special character at end - Invalid")
    public void testInvalidUserName_SpecialCharAtEnd() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateUser.validateUserName("Ahmed!");
        });
    }

    // ==================== BOUNDARY VALUE  6: Spaces ====================

    @Test
    @DisplayName("BV6: Multiple spaces between words - Valid")
    public void testValidUserName_MultipleSpacesBetween() {
        assertTrue(validateUser.validateUserName("Ahmed    Saeed"));
    }

    @Test
    @DisplayName("BV7: Trailing space - Valid")
    public void testValidUserName_TrailingSpace() {
        assertTrue(validateUser.validateUserName("Ahmed Saeed "));
    }

    @Test
    @DisplayName("BV8: Multiple trailing spaces - Valid")
    public void testValidUserName_MultipleTrailingSpaces() {
        assertTrue(validateUser.validateUserName("Ahmed Saeed   "));
    }

    // ==================== EQUIVALENCE PARTITION 7: Edge Cases with Alphabetic ====================

    @Test
    @DisplayName("EP7: Single letter repeated - Valid")
    public void testValidUserName_RepeatedLetter() {
        assertTrue(validateUser.validateUserName("AAAA"));
    }

    @Test
    @DisplayName("EP7: All same word repeated - Valid")
    public void testValidUserName_RepeatedWord() {
        assertTrue(validateUser.validateUserName("Ahmed Ahmed Ahmed"));
    }
}