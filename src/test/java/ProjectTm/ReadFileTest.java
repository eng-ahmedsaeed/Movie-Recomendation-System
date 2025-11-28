package ProjectTm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class ReadFileTest {
    ReadFile reader;
    @BeforeEach
    void setUp() {
        reader = new ReadFile("src/main/resources/testingFiles/testFile1.txt");
    }
    @Test
    void testGetMovies_UsingFileMovie() throws IOException {
        ArrayList<Movie> movies;
        movies = reader.getMovies();
        ArrayList<String[]> moviesInfo = new ArrayList<>();
        for(Movie m:movies){
            String[] categories = new String[m.getGenre().size()];
            for (int i = 0; i < m.getGenre().size(); i++) {
                categories[i] = m.getGenre().get(i);
            }
            String[] movieInfo = new String[2 + categories.length];
            movieInfo[0] = m.getName();
            movieInfo[1] = m.getId();
            for (int i = 0; i < categories.length; i++) {
                movieInfo[2 + i] = categories[i];
            }
            moviesInfo.add(movieInfo);
        }
        assertArrayEquals(
                new String[]{"Lord Of The Ring", "LOTR123","Action","Fantasia"},
                moviesInfo.get(0),
                "message"
        );
        assertArrayEquals(
                new String[]{"Dark Knight", "DK102","Action"},
                moviesInfo.get(1),
                "message"
        );
        assertArrayEquals(
                new String[]{"Elsanafer", "E264","Carton"},
                moviesInfo.get(2),
                "message"
        );
    }
    @Test
    void testGetUsers_UsingFileUser() throws IOException {
        ArrayList<User> users;
        reader = new ReadFile("src/main/resources/testingFiles/testUserFile.txt");
        users = reader.getUsers();

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