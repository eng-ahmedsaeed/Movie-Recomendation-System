package src.main.java.Entities;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {
    private  User user;
    Set<String> existingIds = new HashSet<>();

    @BeforeEach
    public void setUp(){
        // Declare this as well if needed in other tests

        user = new User("john", "12345678a", existingIds);
    }

    @Test
    public void user_WithValidDetails(){
        assertNotNull(user);
    }

    @Test
    public void user_withInvalidName(){
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName(null);
        });;
    }

    @Test
    public void user_withNameStartsWithSpace(){
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName(" john");
        });;
    }


    @Test
    public void setName_NameWithDigits_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName("John123");
        });
    }

    @Test
    void setName_NameWithHyphen_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName("John-Doe");
        });
    }

    @Test
    void setId_NullId_ThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            user.setId(null,existingIds);
        });
    }

    @Test
    void setId_IdTooLong_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
             user.setId("1234567890", existingIds);
        });
    }

    @Test
    void setId_IdNotStartingWithDigit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setId("J23456789", existingIds);
        });
    }

    @Test
    void setId_IdAlreadyExists_ThrowsException() {
        // existingIds contains EXISTING_ID from setup
        existingIds.add("12345678a");
        assertThrows(IllegalArgumentException.class, () -> {
            User user2 = new User("name","12345678a",existingIds);
        });
    }

}