package ProjectTm.WhiteBox;

import ProjectTm.Movie;
import ProjectTm.ReadMovie;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

//                      All-DU Coverage

class ReadMovieWhiteBoxTest {

    ReadMovie reader;
    @Test//Test Path(0,1,2,3,5,6,8,10,12,13,12,14) normal formate file
    void WhiteBoxNormalCase(){
        reader = new ReadMovie("src/main/resources/WhiteBoxTest/WhiteBoxReadMovieNormalCase.txt");
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
                new String[]{"Lord Of The Ring", "LOTR123","action","fantasia"},
                moviesInfo.get(0),
                "message"
        );
        assertArrayEquals(
                new String[]{"Dark Knight", "DK102","action"},
                moviesInfo.get(1),
                "message"
        );
        assertArrayEquals(
                new String[]{"Elsanafer", "E264","carton"},
                moviesInfo.get(2),
                "message"
        );
    }

    @Test //Test Path(6,7) Missing id or name in Movie file.
    void WhiteBoxReadMovieWithMissingPart(){

        String path = "src/main/resources/WhiteBoxTest/WhiteBoxReadMovieWithMissingPart.txt";
        ReadMovie reader = new ReadMovie(path);
        assertThrows(IllegalArgumentException.class, () -> reader.getMovies());

    }
    @Test //Test Path(8,9) missing geners in movie File.
    void WhiteBoxReadMovieWithoutGeners() {
        ReadMovie reader = new ReadMovie("src/main/resources/WhiteBoxTest/whiteBoxReadMovieWihoutGeners.txt");
        assertThrows(IllegalArgumentException.class, () -> reader.getMovies());
    }

    @Test //Test Path(0,1,2,3,4) empty file (empty lines).
    void WhiteBoxReadMovieEmpty() throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<Movie> actual = new ArrayList<>();
        ReadMovie reader = new ReadMovie("src/main/resources/WhiteBoxTest/whiteBoxReadMovieEmpty.txt");
        assertEquals(actual,(movies = reader.getMovies()));
    }
    @Test //Test Path(0,1) path not found
    void WhiteBoxFakeMovieFile() throws IOException {
        ReadMovie reader = new ReadMovie("fake.txt");
        assertThrows(FileNotFoundException.class,()->reader.getMovies());
    }

}