package Validate_Movie_User_Coverage_Tests;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserBranchCoverageTest {
    Set<String> ids;
    ValidateUser validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateUser(ids);
    }

    // 6 out of 10 branches
    @Test
    void validateUserId_validId_returnsTrue() {
        assertTrue(validator.validateUserId("12345678A"));
    }

    // 7 out of 10 branches
    @Test
    void validateUserId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId(null));
    }

    // 8 out of 10 branches
    @Test
    void validateUserId_startsWithLetter_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("A23456789"));
    }

    // 9 out of 10 branches
    @Test
    void validateUserId_containsSpecialCharacter_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345@78A"));
    }

    // 10 out of 10 branches
    @Test
    void validateUserId_duplicateId_throwsException() {
        ids.add("12345678A");
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678A"));
    }



    // the method validateUserName
    // 4 out of 7 branches
    @Test
    void validateUserName_validName_returnsTrue() {
        assertTrue(validator.validateUserName("John Doe"));
    }

    // 5 out of 7 branches
    @Test
    void validateUserName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserName(null));
    }

    // 6 out of 7 branches
    @Test
    void validateUserName_startsWithSpace_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserName(" John"));
    }

    // 7 out of 7 branches
    @Test
    void validateUserName_nameWithDigits_throwsException() {
        assertThrows(IllegalArgumentException.class,() -> validator.validateUserName("John Doe1234"));
    }
}