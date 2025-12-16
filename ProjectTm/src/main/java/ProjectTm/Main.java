package ProjectTm;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<User> users = new ArrayList <> ();

        String movieFilePath = "src/main/resources/movies.txt";
        String userFilePath = "src/main/resources/users.txt";
        String resultFilePath = "recommendation.txt";
        ReadMovie movieFile = new ReadMovie(movieFilePath);
        ReadUser userFile = new ReadUser(userFilePath);
        try {
            movies = movieFile.getMovies();
            users = userFile.getUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        db.setMoviesDataBase(movies);
        db.setUsersDataBase(users);
        db.movieSearch();
        db.movieRecommend();
        WriteFile result = new WriteFile(resultFilePath,users,movies);
        try {
            result.buildFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

