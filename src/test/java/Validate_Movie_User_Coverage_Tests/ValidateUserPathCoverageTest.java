package Validate_Movie_User_Coverage_Tests;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserPathCoverageTest {
    Set<String> ids;
    ValidateUser validator;

    @BeforeEach
    void setUp() {
        ids = new HashSet<>();
        validator = new ValidateUser(ids);
    }

    // ValidateUserName method
    // path 1
    @Test
    void validateUserName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName(null));
    }

    // path 2
    @Test
    void validateUserName_startsWithSpace_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName(" John"));
    }

    // path 3
    @Test
    void validateUserName_containsNumbers_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserName("John123"));
    }

    // path 4
    @Test
    void validateUserName_validName_returnsTrue() {
        assertTrue(validator.validateUserName("John Doe"));
    }

    // ValidateUserId
    // needs 5 paths
    // path 1
    @Test
    void validateUserId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateUserId(null));
    }

    // path 2
    @Test
    void validateUserId_firstCharNotDigit_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("A23456789")  // Starts with 'A'
        );
    }

    // path 3
    @Test
    void validateUserId_hasSpecialCharacter_continuesValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("1@3456789")  // Starts with digit, has @
        );
    }

    // path 4
    @Test
    void validateUserId_duplicateId_throwsException() {
        validator.validateUserId("12345678X");
        assertTrue(ids.contains("12345678X"));
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678X")
        );
    }

    // path 5
    @Test
    void validateUserId_validId_returnsTrue() {
        assertTrue(validator.validateUserId("12345678A"));
    }

}