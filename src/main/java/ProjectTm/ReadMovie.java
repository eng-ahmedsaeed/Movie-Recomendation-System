package ProjectTm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReadMovie {
    private String filePath,Name,Id;
    BufferedReader reader;
    Set<String> existingMovieIds = new HashSet<>();
    ValidateMovie validMovie = new ValidateMovie(existingMovieIds);
    ArrayList<Movie> movies = new ArrayList<>();

    public ReadMovie(String filePath){
        this.filePath = filePath;
    }


    public void setUp() throws FileNotFoundException {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.print(filePath);
            throw e;
        }
    }
    public ArrayList<Movie> getMovies() throws FileNotFoundException,IOException {
        try {
            setUp();
        } catch (FileNotFoundException e) {
            System.out.println("File path not found!");
            throw e;
        }

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");


            if (parts.length != 2) {
                System.out.println("There is a missing part in this file!");
                throw new IllegalArgumentException("Error in movie line: " + line);
            }
            String name = parts[0].trim();
            validMovie.ValidateMovieName(name);

            String id = parts[1].trim();
            validMovie.ValidateMovieId(id, name);

            line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Missing genres for movie: " + name);
            }

            String[] categories = line.split(",");
            Movie movie = new Movie(id, name);
            for (int i = 0; i < categories.length; i++) {
                movie.addGenre(categories[i].trim());
            }
            movies.add(movie);
        }

        return movies;
    }

    public void printMovies(){
        for(Movie m : movies){
            System.out.println(m.getName());
            System.out.println(m.getId());
            System.out.println(m.getGenre());
        }
    }
}
