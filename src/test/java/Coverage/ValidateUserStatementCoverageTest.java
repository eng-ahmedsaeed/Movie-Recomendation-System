package Coverage;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserStatementCoverageTest {
    Set<String> ids;
    ValidateUser validator;

    @BeforeEach
    void setUp(){
        ids = new HashSet<>();
        validator = new ValidateUser(ids);
    }

    // this test case achieves 6 out of 10 statements
    @Test
    void validateUserId_validId_returnsTrue() {
        assertTrue(validator.validateUserId("12345678A"));
    }

    // covers statement number 2
    // 7 out of 10 statements
    @Test
    void validateUserId_nullId_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId(null));
    }

    // covers statement number 4
    // 8 out of 10 statements
    @Test
    void validateUserId_startsWithLetter_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("A23456789"));
    }

    // covers statement number 6
    // 9 out of 10 coverage
    @Test
    void validateUserId_containsSpecialCharacter_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345@78A"));
    }

    // covers statement number 8
    // 10 out of 10 coverage
    @Test
    void validateUserId_duplicateId_throwsException() {
        ids.add("12345678A");
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678A"));
    }



    // the method validateUserName
    // 4 out of 7
    @Test
    void validateUserName_validName_returnsTrue() {
        assertTrue(validator.validateUserName("John Doe"));
    }

    // executes statement number 2
    // 5 out of 7
    @Test
    void validateUserName_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserName(null));
    }

    // executes statement number 4
    // 6 out of 7
    @Test
    void validateUserName_startsWithSpace_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserName(" John"));
    }

    // executes statement number 6
    // 7 out of 7
    @Test
    void validateUserName_nameWithDigits_throwsException() {
        assertThrows(IllegalArgumentException.class,() -> validator.validateUserName("John Doe1234"));
    }
}