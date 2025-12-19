package ProjectTm;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataBaseTestStatmentCoverageMovieSearchMethod {
    static ArrayList<Movie> movieList;
    static  ArrayList<User> userList;
    static  DataBase db;
    @BeforeAll
    static void setUp()
    {
         movieList =new ArrayList<>();
       userList =new ArrayList<>();
       db =new DataBase();

    }
    @AfterEach
    void tearDown()
    {
        movieList.clear();
        userList.clear();
    }
    @AfterAll
    static void setUpEach()
    {
            movieList = null;
            userList = null;
    }
    // TC-DB-012 Inserting 3 different movies and adding 2 users with one searching
    // a non exist movie and the other searching an existing movie, then calling
    // movieRecommend() to verify correct recommendations are made based on

    @Test
    void testMovieSearch()
    {
      
//=============Movie Data================
        ArrayList<String> g1 = new ArrayList<>();
        g1.add("Action");
        g1.add("Drama");

        ArrayList<String> g2 = new ArrayList<>();
        g2.add("Horror");

        ArrayList<String> g3 = new ArrayList<>();
        g3.add("Drama");
        g3.add("Romance");

        movieList.add(new Movie("AVN001", "Avengers", g1));
        movieList.add(new Movie("CNJ002", "Conjuring", g2));
        movieList.add(new Movie("TIT003", "Titanic", g3));
        movieList.add(new Movie("GLD004", "Gladiator", g1));

        // ===== Users Data =====
        ArrayList<User> userList = new ArrayList<>();

        User u1 = new User("12345678A", "Ahmed Ali");
        User u2 = new User("87654321B", "Omar Hassan");


        u1.setSearchedMovie(new Movie("AVN001"));   // Exist
        u1.setSearchedMovie(new Movie("XXX999"));   // NotExist

        u2.setSearchedMovie(new Movie("TIT003"));   // Exist

        userList.add(u1);
        userList.add(u2);


        db.setMoviesDataBase(movieList);
        db.setUsersDataBase(userList);
        db.movieSearch();
        ArrayList<User> expectedUsers = new ArrayList<>();

// ===== User 1 =====
        User eu1 = new User("12345678A", "Ahmed Ali");

        ArrayList<Movie> eu1Searched = new ArrayList<>();
        eu1Searched.add(
                new Movie("AVN001", "Avengers",
                        new ArrayList<>(List.of("Action", "Drama")))
        );
        eu1Searched.add(new Movie()); // Not found movie

        eu1.setSearchedMovieInst(eu1Searched);

// ===== User 2 =====
        User eu2 = new User("87654321B", "Omar Hassan");

        ArrayList<Movie> eu2Searched = new ArrayList<>();
        eu2Searched.add(
                new Movie("TIT003", "Titanic",
                        new ArrayList<>(List.of("Drama", "Romance")))
        );

        eu2.setSearchedMovieInst(eu2Searched);

// add users
        expectedUsers.add(eu1);
        expectedUsers.add(eu2);


        //assertion
        assertEquals(expectedUsers,db.getUsersDataBase());
    }


}