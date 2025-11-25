package projectm;

import projectm.Movie;
import projectm.User;
import java.util.ArrayList;

public class MockData {

    public static ArrayList<Movie> getMockMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Action");
        genres1.add("Adventure");
        movies.add(new Movie(1, "Avengers", 2012, genres1));

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Comedy");
        genres2.add("Family");
        movies.add(new Movie(2, "Home Alone", 1990, genres2));

        ArrayList<String> genres3 = new ArrayList<>();
        genres3.add("Action");
        genres3.add("Thriller");
        movies.add(new Movie(3, "Inception", 2010, genres3));

        return movies;
    }


        public static ArrayList<Movie> getMockMovies2() {
            ArrayList<Movie> movies = new ArrayList<>();

            // فيلم 1
            ArrayList<String> genres1 = new ArrayList<>();
            genres1.add("Action");
            genres1.add("Adventure");
            movies.add(new Movie(12, "Avengers: Endgame", 2019, genres1));

            // فيلم 2
            ArrayList<String> genres2 = new ArrayList<>();
            genres2.add("Comedy");
            genres2.add("Family");
            movies.add(new Movie(34, "Home Alone 2", 1992, genres2));

            // فيلم 3
            ArrayList<String> genres3 = new ArrayList<>();
            genres3.add("Action");
            genres3.add("Thriller");
            movies.add(new Movie(31, "Inception", 2010, genres3));

            // فيلم 4
            ArrayList<String> genres4 = new ArrayList<>();
            genres4.add("Horror");
            genres4.add("Mystery");
            movies.add(new Movie(42, "The Conjuring", 2013, genres4));

            // فيلم 5
            ArrayList<String> genres5 = new ArrayList<>();
            genres5.add("Drama");
            genres5.add("Romance");
            movies.add(new Movie(0, "La La Land", 2016, genres5));

            // فيلم 6
            ArrayList<String> genres6 = new ArrayList<>();
            genres6.add("Action");
            genres6.add("Comedy");
            movies.add(new Movie(2, "Guardians of the Galaxy", 2014, genres6));

            // فيلم 7
            ArrayList<String> genres7 = new ArrayList<>();
            genres7.add("Animation");
            genres7.add("Family");
            movies.add(new Movie(13, "Toy Story 4", 2019, genres7));

            return movies;
        }




    public static ArrayList<User> getMockUsers() {
        ArrayList<User> users = new ArrayList<>();

        User u1 = new User(1, "Ahmed");
        u1.setSearchedMove(new Movie(1));

        User u2 = new User(2, "Mona");
        u2.setSearchedMove(new Movie(2));

        users.add(u1);
        users.add(u2);

        return users;
    }

    public static ArrayList<User> getMockUsers2() {
        ArrayList<User> users = new ArrayList<>();

        User u1 = new User(61, "salma");
        u1.setSearchedMove(new Movie(8));

        User u2 = new User(20, "Ziad");
        u2.setSearchedMove(new Movie(2));

        users.add(u1);
        users.add(u2);

        return users;
    }
}
