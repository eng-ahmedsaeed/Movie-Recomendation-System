package ProjectTm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class ReadUserWhiteBox {

    // ===== File paths =====
    private final String VALID_FILE =
            "src/main/resources/users_valid.txt";

    private final String FILE_WITH_EMPTY_LINE =
            "src/main/resources/users_with_empty_line.txt";

    private final String FILE_MISSING_PART =
            "src/main/resources/users_missing_part.txt";

    private final String FILE_MISSING_FAVORITES =
            "src/main/resources/users_missing_favorites.txt";

    private final String INVALID_PATH =
            "src/test/resources/users_not_found.txt";

    // ===== correct path =====
    @Test
    void testReadUsersContent() throws IOException {
        ReadUser ru = new ReadUser(VALID_FILE);
        ArrayList<User> users = ru.getUsers();

        assertEquals(1, users.size());
        assertEquals("Ahmed", users.get(0).getName());
        assertEquals("U001", users.get(0).getId());
        assertEquals(2, users.get(0).getSearchedMovie().size());
    }

    // ===== File not found  =====
    @Test
    void testFileNotFound() {
        ReadUser ru = new ReadUser(INVALID_PATH);

        assertThrows(FileNotFoundException.class, ru::getUsers);
    }

    // ===== Empty line  =====
    @Test
    void testEmptyLine() throws IOException {
        ReadUser ru = new ReadUser(FILE_WITH_EMPTY_LINE);
        ArrayList<User> users = ru.getUsers();

        assertEquals(1, users.size());
    }

    // ===== Missing user part =====
    @Test
    void testMissingUser() {
        ReadUser ru = new ReadUser(FILE_MISSING_PART);

        assertThrows(IllegalArgumentException.class, ru::getUsers);
    }

    // ===== Missing favorite movies line =====
    @Test
    void testMissingFavoriteMovies() {
        ReadUser ru = new ReadUser(FILE_MISSING_FAVORITES);

        assertThrows(IllegalArgumentException.class, ru::getUsers);
    }

    // ===== printUsers =====
    @Test
    void testPrintUsers() throws IOException {
        ReadUser ru = new ReadUser(VALID_FILE);
        ru.getUsers();

        assertDoesNotThrow(ru::printUsers);
    }
}

