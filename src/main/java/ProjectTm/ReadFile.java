package ProjectTm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReadFile {
    private String filePath;
    Set<String> existingUserIds = new HashSet<>();
    BufferedReader reader;

    public ReadFile(String filePath){
        this.filePath = filePath;
    }

    public void setUp() {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> getMovies() throws IOException {
        setUp();
        ArrayList<Movie> movies = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            if(line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            String name = parts[0].trim();
            String id = parts[1].trim();

            line = reader.readLine();
            String[] categories = line != null ? line.split(",") : new String[0];

            Movie movie = new Movie(id, name);
            for (String cat : categories) {
                movie.addGenre(cat.trim());
            }
            movies.add(movie);
        }
        return movies;
    }

    public ArrayList<User> getUsers() throws IOException {
        setUp();
        ArrayList<User> users = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            String name = parts[0].trim();
            String id = parts[1].trim();

            line = reader.readLine();
            String[] favMoviesIds = line != null ? line.split(",") : new String[0];

            User user = new User(name, id, existingUserIds);

            ArrayList<Movie> serchedMoviesInfo = new ArrayList<>();
            for(String mid : favMoviesIds){
                Movie m = new Movie(mid.trim());
                serchedMoviesInfo.add(m);
            }
            user.setSearchedMovieInst(serchedMoviesInfo);
            users.add(user);
        }
        return users;
    }
}