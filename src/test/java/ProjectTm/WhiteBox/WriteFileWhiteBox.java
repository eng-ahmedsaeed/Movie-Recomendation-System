package ProjectTm.WhiteBox;

import ProjectTm.Movie;
import ProjectTm.User;
import ProjectTm.WriteFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriteFileWhiteBox {

    @TempDir
    Path tempDir;


    // PATH 1: setUpFile() → SUCCESS
    @Test
    void path_SetUpFile_Success() throws IOException {
        String path = tempDir.resolve("success.txt").toString();
        WriteFile wf = new WriteFile(path, new ArrayList<>(), new ArrayList<>());
        wf.setUpFile();
        wf.result.close();
    }

    
    // PATH 2: setUpFile() → EXCEPTION

    @Test
    void path_SetUpFile_Exception() {
        String invalidPath = "/invalid_path/output.txt";
        WriteFile wf = new WriteFile(invalidPath, new ArrayList<>(), new ArrayList<>());

        assertThrows(RuntimeException.class, wf::setUpFile);
    }


    // PATH 3: users ≠ empty, movieRec.size() == 0

    @Test
    void path_NoRecommendations() throws Exception {
        String path = tempDir.resolve("no_rec.txt").toString();

        User user = new User("1", "UserA");
        user.setSearchedMovie(new Movie("M1", "Liked"));
        user.setRecommendedMovies(new ArrayList<>());

        WriteFile wf = new WriteFile(
                path,
                new ArrayList<>(List.of(user)),
                new ArrayList<>()
        );

        assertDoesNotThrow(wf::buildFile);
        assertTrue(new File(path).exists());
    }

    // PATH 4: movieRec.size() != 0, counter == last index (TRUE)

    @Test
    void path_WithRecommendations_LastIndexOnly() throws Exception {
        String path = tempDir.resolve("one_rec.txt").toString();

        User user = new User("2", "UserB");
        user.setSearchedMovie(new Movie("M1", "Liked"));

        user.setRecommendedMovies(
                new ArrayList<>(List.of(new Movie("R1", "Rec1")))
        );

        WriteFile wf = new WriteFile(
                path,
                new ArrayList<>(List.of(user)),
                new ArrayList<>()
        );

        assertDoesNotThrow(wf::buildFile);
    }

    // PATH 5: movieRec.size() != 0, counter FALSE → TRUE

    @Test
    void path_WithRecommendations_MultipleIterations() throws Exception {
        String path = tempDir.resolve("multi_rec.txt").toString();

        User user = new User("3", "UserC");
        user.setSearchedMovie(new Movie("M1", "Liked"));

        user.setRecommendedMovies(
                new ArrayList<>(List.of(
                        new Movie("R1", "Rec1"),
                        new Movie("R2", "Rec2")
                ))
        );

        WriteFile wf = new WriteFile(
                path,
                new ArrayList<>(List.of(user)),
                new ArrayList<>()
        );

        assertDoesNotThrow(wf::buildFile);
    }
}
