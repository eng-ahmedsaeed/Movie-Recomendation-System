package ProjectTm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class ReadFile {
    private String filePath,Name,Id;
    private String[] favMoviesIds;
    Set<String> existingUserIds = new HashSet<>();
    BufferedReader reader;
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    public ReadFile(){
        filePath = "";
    }
    public ReadFile(String filePath){
        this.filePath = filePath;
    }
    public void setUp() {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Movie> getMovies() throws IOException {
        setUp();
        ArrayList<Movie> movies = new ArrayList<>();
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
            String id = parts[1].trim();
            line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Missing genres for movie: " + name);
            }
            String[] categories = line.split(",");
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
        String line;
        while((line =reader.readLine()) != null){
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("there is missing part in this file!");
                throw new IllegalArgumentException("Error in movie line: " + line);
            }
            this.Name = parts[0].trim();
            this.Id = parts[1].trim();
            line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Missing genres for user: " + this.Name);
            }
            this.favMoviesIds = line.split(",");   //ids
            User user = new User(this.Id, this.Name);
            for(String id : favMoviesIds){
                Movie m = new Movie(id);
                user.setSearchedMovie(m);
            }
            users.add(user);
        }
        return users;
    }
    public void printMovies(){
        for(Movie m : movies){
            System.out.println(m.getName());
            System.out.println(m.getId());
            System.out.println(m.getGenre());
        }
    }
    public void printUsers(){
        for(User u : users){
            System.out.println(u.getName());
            System.out.println(u.getId());
            System.out.println(u.getSearchedMovie().get(0).getId());
        }
    }
}

