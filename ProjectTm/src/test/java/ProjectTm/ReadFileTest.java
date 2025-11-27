package ProjectTm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReadFileTest {

    ReadFile reader;
    @BeforeEach
    void setUp() {
        reader = new ReadFile("src/main/resources/testingFiles/testFile1.txt");
    }

    @Test
    void testGetMovies_UsingFileMovie() throws IOException {
        ArrayList<Movie> movies;//[1,2,34,4],[34,59,59],[]
        movies = reader.getMovies();

        ArrayList<String[]> moviesInfo = new ArrayList<>();
        for(Movie m:movies){
            String[] categories = new String[m.getGenres().size()];
            for (int i = 0; i < m.getGenres().size(); i++) {
                categories[i] = m.getGenres().get(i);
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
            String[] categories = new String[u.getSearchedMove().size()];
            for (int i = 0; i < u.getSearchedMove().size(); i++) {
                categories[i] = u.getSearchedMove().get(i).getId();
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