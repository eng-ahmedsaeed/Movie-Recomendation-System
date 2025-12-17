package ProjectTm;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class ReadUserBlackBox {
    @Test           //Equivalent  Partition Test
    void ECP_ValidUsersFile() throws IOException {
        ReadUser reader = new ReadUser("src/main/resources/users.txt");
        ArrayList<User> users = reader.getUsers();
        assertDoesNotThrow(()->users.size());
    }
    @Test
    void testFakeInvalidUserFile() throws IOException {
        ReadUser reader = new ReadUser("fake.txt");
        assertThrows(FileNotFoundException.class,()->reader.getUsers());
    }
    @Test           //Decision table
    void AcceptedUsersFile() throws IOException {
        ReadUser reader = new ReadUser("src/main/resources/users.txt");
        ArrayList<User> users = reader.getUsers();
        assertDoesNotThrow(()->users.size());
    }
    @Test
    void testMissingName() {
        ReadUser userFile = new ReadUser("src/main/resources/testingFiles/BlackBoxUserMissingName.txt");
        assertThrows(IllegalArgumentException.class, () -> userFile.getUsers());
    }
    @Test
    void testMissingUserId() {
        ReadUser reader = new ReadUser("src/main/resources/testingFiles/BlackBoxUserWithMissingId.txt");
        assertThrows(IllegalArgumentException.class,() -> reader.getUsers());
    }
    @Test
    void testUserWithoutMovies() {
        ReadUser reader = new ReadUser("src/main/resources/testingFiles/BlackBoxUserWithMissingMovies.txt");
        assertThrows(IllegalArgumentException.class, () -> reader.getUsers());
    }
}
