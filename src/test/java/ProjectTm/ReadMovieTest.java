package ProjectTm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadMovieTest {
    ReadMovie reader;
    @BeforeEach
    void setUp() {
        reader = new ReadMovie("src/main/resources/testingFiles/testFile1.txt");
    }
    @Test
    void testGetMoviesThrowsException() {
        ReadMovie readerTest = new ReadMovie("c\\fakePath");
        assertThrows(FileNotFoundException.class, () -> readerTest.getMovies());
    }
    @Test
    void testGetMovies_UsingFileMovie() {
        ArrayList<Movie> movies;
        try {
            movies = reader.getMovies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

}