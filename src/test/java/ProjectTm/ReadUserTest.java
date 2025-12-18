package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadUserTest {
    ReadUser reader;
    @BeforeEach
    void setUp() {
        reader = new ReadUser("src/main/resources/testingFiles/testFile1.txt");
    }
    @Test
    void testGetMoviesThrowsException() {
        ReadUser readerTest = new ReadUser("c\\fakePath");
        assertThrows(IOException.class, () -> readerTest.getUsers());
    }
    @Test
    void testGetUsers_UsingFileUser() {
        ArrayList<User> users;
        reader = new ReadUser("src/main/resources/testingFiles/testUserFile.txt");
        try {
            users = reader.getUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String[]> usersInfo = new ArrayList<>();
        for(User u:users){
            String[] categories = new String[u.getSearchedMovie().size()];
            for (int i = 0; i < u.getSearchedMovie().size(); i++) {
                categories[i] = u.getSearchedMovie().get(i).getId();
            }
            String[] userInfo = new String[2 + categories.length];
            userInfo[0] = u.getName();
            userInfo[1] = u.getId();
            for (int i = 0; i < categories.length; i++) {
                userInfo[2 + i] = categories[i];
            }
            usersInfo.add(userInfo);
        }
        assertArrayEquals(
                new String[]{"mario", "467384620","LOTR126","AITS245"},
                usersInfo.get(0),
                "message"
        );
        assertArrayEquals(
                new String[]{"mina ashraf", "63834839u","MTWTD354","MTWTD384"},
                usersInfo.get(1),
                "message"
        );
        assertArrayEquals(
                new String[]{"ahmed sayed", "63745342m","AITS245"},
                usersInfo.get(2),
                "message"
        );
    }

}