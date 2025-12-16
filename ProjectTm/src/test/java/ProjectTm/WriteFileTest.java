package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WriteFileTest {
    ArrayList<User> users;
    ArrayList<Movie> movies;
    WriteFile result;

    @BeforeEach
    void setUp() {}
    @Test
    void buildFileUsingDummyData() throws IOException {
        Movie m1 = mock(Movie.class);
        when(m1.getName()).thenReturn("Lord Of The Ring");
        Movie m2 = mock(Movie.class);
        when(m2.getName()).thenReturn("Avatar");
        ArrayList<Movie> moviesLiked = new ArrayList<>();
        moviesLiked.add(m1);
        moviesLiked.add(m2);
        User user = mock(User.class);
        when(user.getName()).thenReturn("Mario");
        when(user.getId()).thenReturn("253644747");
        when(user.getRecommendedMovies()).thenReturn(moviesLiked);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        File tempFile = File.createTempFile("test_output", ".txt");
        WriteFile resultFile = new WriteFile(tempFile.getAbsolutePath(), users, moviesLiked) {
            @Override
            public void setUpFile() {
                try {
                    this.result = new BufferedWriter(new FileWriter(tempFile));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        resultFile.buildFile();
        resultFile.result.close();
        String fileContent = new String(java.nio.file.Files.readAllBytes(tempFile.toPath()));
        String expected = "Mario , 253644747\n" + "Lord Of The Ring , Avatar , \n";
        assertEquals(expected, fileContent);
        tempFile.delete();
    }
}