package ProjectTm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WriteFileWhiteBox {

    @TempDir
    Path tempDir; 

    // --- BRANCH COVERAGE ---

    @Test
    void testSetUpFile_SuccessBranch() {
        String path = tempDir.resolve("success.txt").toString();
        WriteFile wf = new WriteFile(path, new ArrayList<>(), new ArrayList<>());
        
        assertDoesNotThrow(wf::setUpFile);
    }

    @Test
    void testBuildFile_EmptyRecommendationsBranch() throws Exception {
        String path = tempDir.resolve("no_rec.txt").toString();
        User user = new User("1", "UserA");
        user.setSearchedMovie(new Movie("M1", "Action"));
        user.setRecommendedMovies(new ArrayList<>()); 

        WriteFile wf = new WriteFile(path, new ArrayList<>(List.of(user)), new ArrayList<>());
        assertDoesNotThrow(wf::buildFile);
    }

    // --- PATH COVERAGE ---

    @Test
    void testBuildFile_FullLoopLogic() throws Exception {
        String path = tempDir.resolve("full_path.txt").toString();
        User user = new User("2", "UserB");
        
        user.setSearchedMovie(new Movie("M1", "Liked"));
        user.setRecommendedMovies(new ArrayList<>(List.of(
            new Movie("R1", "Rec1"), 
            new Movie("R2", "Rec2")
        )));

        WriteFile wf = new WriteFile(path, new ArrayList<>(List.of(user)), new ArrayList<>());
    
        assertDoesNotThrow(wf::buildFile);
        assertTrue(new File(path).exists());
    }
}