package projectm;

import java.util.ArrayList;

public class MockData {
    static ArrayList<Movie> movies = new ArrayList<>();
    static ArrayList<User> users ;



    public static ArrayList<Movie> getMockMovies() {
        movies = new ArrayList<>();

        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Action");
        genres1.add("Adventure");
        movies.add(new Movie("1", "Avengers", 2012, genres1));

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Comedy");
        genres2.add("Family");
        movies.add(new Movie("2", "Home Alone", 1990, genres2));

        ArrayList<String> genres3 = new ArrayList<>();
        genres3.add("Action");
        genres3.add("Thriller");
        movies.add(new Movie("3", "Inception", 2010, genres3));

        return movies;
    }


    public static ArrayList<User> getMockUsers() {

        users = new ArrayList<>();
        User u1 = new User("1", "Ahmed");
        u1.setSearchedMovie(new Movie("1"));

        User u2 = new User("2", "Mona");
        u2.setSearchedMovie(new Movie("2"));

        users.add(u1);
        users.add(u2);

        return users;
    }

    //!////////////////////////////////////////////!testing data2//////////////////////////
    public static ArrayList<Movie> getMockMovies2() {

        movies = new ArrayList<>();
        // فيلم 1
        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Action");
        genres1.add("Adventure");
        movies.add(new Movie("12", "Avengers: Endgame", 2019, genres1));

        // فيلم 2
        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Comedy");
        genres2.add("Family");
        movies.add(new Movie("34", "Home Alone 2", 1992, genres2));

        // فيلم 3
        ArrayList<String> genres3 = new ArrayList<>();
        genres3.add("Action");
        genres3.add("Thriller");
        movies.add(new Movie("31", "Inception", 2010, genres3));

        // فيلم 4
        ArrayList<String> genres4 = new ArrayList<>();
        genres4.add("Horror");
        genres4.add("Mystery");
        movies.add(new Movie("42", "The Conjuring", 2013, genres4));

        // فيلم 5
        ArrayList<String> genres5 = new ArrayList<>();
        genres5.add("Drama");
        genres5.add("Romance");
        movies.add(new Movie("0", "La La Land", 2016, genres5));

        // فيلم 6
        ArrayList<String> genres6 = new ArrayList<>();
        genres6.add("Action");
        genres6.add("Comedy");
        movies.add(new Movie("2", "Guardians of the Galaxy", 2014, genres6));

        // فيلم 7
        ArrayList<String> genres7 = new ArrayList<>();
        genres7.add("Animation");
        genres7.add("Family");
        movies.add(new Movie("13", "Toy Story 4", 2019, genres7));

        return movies;
    }

    public static ArrayList<User> getMockUsers2() {

        users = new ArrayList<>();
        User u1 = new User("61", "salma");
        u1.setSearchedMovie(new Movie("8"));

        User u2 = new User("20", "Ziad");
        u2.setSearchedMovie(new Movie("2"));

        users.add(u1);
        users.add(u2);

        return users;
    }
//!////////////////////////////////////////////!testing data3//////////////////////////
public static ArrayList<Movie> getMockMovies3() {

    movies = new ArrayList<>();
    // Movie 1
    ArrayList<String> g1 = new ArrayList<>();
    g1.add("Action");
    g1.add("Adventure");   // تغيير علشان يكون common مع فيلم آخر
    movies.add(new Movie("12A", "Interstellar", 2014, g1));

    // Movie 2
    ArrayList<String> g2 = new ArrayList<>();
    g2.add("Comedy");
    g2.add("Drama");
    g2.add("Adventure");   // common genre مع Interstellar
    movies.add(new Movie("7B", "The Intern", 2015, g2));

    // Movie 3
    ArrayList<String> g3 = new ArrayList<>();
    g3.add("Thriller");
    g3.add("Action");      // common genre مع Interstellar
    movies.add(new Movie("55C", "Shutter Island", 2010, g3));

    // Movie 4
    ArrayList<String> g4 = new ArrayList<>();
    g4.add("Animation");
    g4.add("Family");
    g4.add("Comedy");       // common genre مع The Intern
    movies.add(new Movie("89D", "Coco", 2017, g4));

    return movies;
}

    public static ArrayList<User> getMockUsers3() {
        users = new ArrayList<>();

        // User Omar
        User u1 = new User("101h", "Omar");
        u1.setSearchedMovie(new Movie("12A")); // ID بس

        // User Laila
        User u2 = new User("102f", "Laila");
        u2.setSearchedMovie(new Movie("7B"));

        // User Youssef
        User u3 = new User("103u", "Youssef");
        u3.setSearchedMovie(new Movie("55C"));

        // User Nada
        User u4 = new User("104m", "Nada");
        u4.setSearchedMovie(new Movie("89D"));

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);

        return users;
    }

    //!////////////////////////////////////////////!testing data4//////////////////////////
    public static ArrayList<Movie> getMockMovies4() {

        movies = new ArrayList<>();
        // Movie 1
        ArrayList<String> g1 = new ArrayList<>();
        g1.add("Action");
        g1.add("Adventure"); // مشتركة مع Interstellar
        movies.add(new Movie("231F", "Black Panther", 2018, g1));

        // Movie 2
        ArrayList<String> g2 = new ArrayList<>();
        g2.add("Comedy");
        g2.add("Adventure"); // مشتركة مع The Intern
        movies.add(new Movie("412G", "Jumanji", 2017, g2));

        // Movie 3
        ArrayList<String> g3 = new ArrayList<>();
        g3.add("Thriller");
        g3.add("Action"); // مشتركة مع Shutter Island
        movies.add(new Movie("103A", "Tenet", 2020, g3));

        // Movie 4
        ArrayList<String> g4 = new ArrayList<>();
        g4.add("Animation");
        g4.add("Comedy"); // مشتركة مع Coco
        movies.add(new Movie("113L", "Ratatouille", 2007, g4));

        return movies;
    }

    public static ArrayList<User> getMockUsers4() {
        users = new ArrayList<>();

        // User Sara
        User u1 = new User("241P", "Sara");
        u1.setSearchedMovie(new Movie("412G"));

        // User Ali
        User u2 = new User("564Q", "Ali");
        u2.setSearchedMovie(new Movie("231F"));

        // User Hana
        User u3 = new User("981Y", "Hana");
        u3.setSearchedMovie(new Movie("103A"));

        // User Karim
        User u4 = new User("216M", "Karim");
        u4.setSearchedMovie(new Movie("412G"));

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);

        return users;
    }




}
