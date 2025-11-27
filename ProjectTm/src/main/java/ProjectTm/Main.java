package ProjectTm;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // we active this line of code when the dataBase class be ready!

        //DataBase db = new DataBase();


        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<User> users = new ArrayList <> ();

        String movieFilePath = "src/main/resources/movies.txt";
        String userFilePath = "src/main/resources/users.txt";
        String resultFilePath = "recommendation.txt";

        ReadFile movieFile = new ReadFile(movieFilePath);
        try {
            movies = movieFile.getMovies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ReadFile userFile = new ReadFile(userFilePath);
        try {
            users = userFile.getUsers();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

// we active this part of code when the dataBase class be ready!


//        db.setMoviesDataBase(movies);
//        db.setUsersDataBase(users);
//
//        db.movieSearch();
//        db.movieRecommend();

        WriteFile result = new WriteFile(resultFilePath,users,movies);
        try {
            result.buildFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

