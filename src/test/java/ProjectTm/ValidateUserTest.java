package ProjectTm;

import ProjectTm.ValidateUser;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateUserTest {

    @Test
    void validateUserId_validId_returnsTrue() {
        Set<String> ids = new HashSet<>();
        ValidateUser validator = new ValidateUser(ids);
        assertTrue(validator.validateUserId("12345678A"));
    }

    @Test
    void validateUserId_nullId_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId(null));
    }

    @Test
    void validateUserId_wrongLength_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("123")); // Too short
    }

    @Test
    void validateUserId_duplicateId_throwsException() {
        Set<String> ids = new HashSet<>();
        ids.add("12345678A");
        ValidateUser validator = new ValidateUser(ids);
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("12345678A"));
    }

    @Test
    void validateUserName_validName_returnsTrue() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertTrue(validator.validateUserName("John Doe"));
    }

    @Test
    void validateUserName_startsWithSpace_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserName(" John"));
    }

    @Test
    void validateUserName_nameWithDigits_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,() -> validator.validateUserName("John Doe1234"));
    }

    @Test
    void validateUserName_nameWithSpecialCharacter_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,() -> validator.validateUserName("John@Doe"));
    }

    @Test
    void validateUserId_startsWithLetter_throwsException() {
        ValidateUser validator = new ValidateUser(new HashSet<>());
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateUserId("A23456789"));
    }

}