package Validate_Movie_User_Coverage_Tests;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserConditionCoverageTest {
    Set<String> ids;
    ValidateUser validator;

    @BeforeEach
    void setUp() {
        ids = new HashSet<>();
        validator = new ValidateUser(ids);
    }

    // ValidateUserId method
    //    Condition A: id == null || id.length() != 9
    //        A1: id == null (TRUE/FALSE)
    //        A2: id.length() != 9 (TRUE/FALSE)
    //    Condition B: !Character.isDigit(id.charAt(0))
    //        TRUE: first character is NOT a digit
    //        FALSE: first character IS a digit
    //    Condition C: !id.matches("[0-9][a-zA-Z0-9]*")
    //        TRUE: id doesn't match pattern (has special chars)
    //        FALSE: id matches pattern
    //    Condition D: existingUserIds.contains(id)
    //        TRUE: id already exists
    //        FALSE: id is unique

    // Condition A1 TRUE
    @Test
    void validateUserId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserId(null));
    }


    // Condition A2 TRUE
    @Test
    void validateUserId_tooShort_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678"));
    }

    // Condition B TRUE
    @Test
    void validateUserId_firstCharNotDigit_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("A23456789")  // Starts with 'A'
        );
    }


    // Condition B FALSE
    @Test
    void validateUserId_firstCharDigit_continuesValidation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateUserId("1@3456789")  // Starts with digit, has @
        );
    }

    // Condition C TRUE
    @Test
    void validateUserId_containsSpecialChar_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateUserId("1@3456789")  // Contains '@'
        );
    }


    // Condition C FALSE
    @Test
    void validateUserId_validPattern_continuesValidation() {
        assertTrue(validator.validateUserId("12345678A"));
    }


    // Condition D TRUE
    @Test
    void validateUserId_duplicateId_throwsException() {
        validator.validateUserId("12345678B");
        assertTrue(ids.contains("12345678B"));
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678B")
        );
    }

    // Condition D FALSE and all others
    @Test
    void validateUserId_uniqueId_returnsTrue() {
        assertTrue(validator.validateUserId("98765432Z"));
    }


    // ValidateUserName method
    //     Condition A: name == null || name.isEmpty()
    //        A1: name == null (TRUE/FALSE)
    //        A2: name.isEmpty() (TRUE/FALSE)
    //    Condition B: name.startsWith(" ")
    //        TRUE: name starts with space
    //        FALSE: name doesn't start with space
    //    Condition C: !name.matches("[a-zA-Z ]+")
    //        TRUE: name contains non-alphabetic/non-space characters
    //        FALSE: name contains only letters and spaces

    // Condition A1 TRUE
    @Test
    void validateUserName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName(null));
    }

    // Condition A2 TRUE
    @Test
    void validateUserName_emptyName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName(""));
    }

    // Condition B TRUE
    @Test
    void validateUserName_startsWithSpace_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName(" John"));
    }

    // Condition B FALSE
    @Test
    void validateUserName_doesNotStartWithSpace_continuesValidation() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName("John123"));
    }

    // Condition C TRUE
    @Test
    void validateUserName_containsNumbers_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName("John123"));
    }

    // Condition C FALSE
    @Test
    void validateUserName_onlyLetters_returnsTrue() {
        assertTrue( validator.validateUserName("John"));
    }

}