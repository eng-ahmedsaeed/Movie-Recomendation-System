package ProjectTm;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WriteFileBlackBox {

    private ArrayList<User> createTestUsers() {
        User user = new User("123456789", "TestUser");
        user.setSearchedMovie(new Movie("LKD01", "Liked1"));
        user.setRecommendedMovies(new ArrayList<>(List.of(new Movie("REC01", "Rec1"))));
        return new ArrayList<>(List.of(user));
    }

    // --- Equivalence Partitioning (EP) ---
    @Test
    void testMinUserListSize() {

        ArrayList<User> testUsers = new ArrayList<>();
        assertDoesNotThrow(() -> {
            new WriteFile("some/valid/path.txt", testUsers, new ArrayList<>());
        }, "Constructor call with minimal input should not throw an exception.");
    }

    @Test
    void testMinMovieListsSize() {
        ArrayList<User> testUsers = createTestUsers();

        assertDoesNotThrow(() -> {
            new WriteFile("some/valid/path.txt", testUsers, new ArrayList<>());
        }, "Constructor call with 1 user and 1 movie each should be successful.");
    }


    @Test
    void testMultipleValidUsers() {

        ArrayList<User> testUsers = createTestUsers();
        testUsers.add(new User("987654321", "AnotherUser"));

        assertDoesNotThrow(() -> {
            new WriteFile("some/valid/path.txt", testUsers, new ArrayList<>());
        }, "Constructor call with multiple users should be successful.");
    }

}