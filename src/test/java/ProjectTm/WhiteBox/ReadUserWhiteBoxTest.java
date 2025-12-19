package ProjectTm.WhiteBox;

import ProjectTm.ReadUser;
import ProjectTm.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

//                      All-DU Coverage

class ReadUserWhiteBoxTest {
    ReadUser reader;
    @Test//Test Path(0,1,2,3,5,6,8,10,11,14,12) normal formate file
    void WhiteBoxNormalCase(){
        ArrayList<User> users;
        reader = new ReadUser("src/main/resources/WhiteBoxTest/WhiteBoxReadUserNormalPath.txt");
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
    }

    @Test //Test Path(6,7) Missing id or name in user file.
    void WhiteBoxUserWithMissingPart(){

        String path = "src/main/resources/WhiteBoxTest/WhiteBoxReadUserWithMissingPart.txt";
        ReadUser reader = new ReadUser(path);
        assertThrows(IllegalArgumentException.class, () -> reader.getUsers());

    }
    @Test //Test Path(8,9) missing geners in user File.
    void WhiteBoxReadUserWithoutGeners() {
        ReadUser reader = new ReadUser("src/main/resources/WhiteBoxTest/whiteBoxReadUserWihoutGeners.txt");
        assertThrows(IllegalArgumentException.class, () -> reader.getUsers());
    }

    @Test //Test Path(0,1,2,3,4) empty file (empty lines).
    void WhiteBoxReadUserEmpty() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> actual = new ArrayList<>();
        ReadUser reader = new ReadUser("src/main/resources/WhiteBoxTest/whiteBoxReadUserEmpty.txt");
        assertEquals(actual,(users = reader.getUsers()));

    }
    @Test //Test Path(0,1) path not found
    void WhiteBoxFakeUserFile() throws IOException {
        ReadUser reader = new ReadUser("fake.txt");
        assertThrows(FileNotFoundException.class,()->reader.getUsers());
    }



}