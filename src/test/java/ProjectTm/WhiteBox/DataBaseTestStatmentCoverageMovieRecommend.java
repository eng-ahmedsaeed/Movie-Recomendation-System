package ProjectTm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataBaseTestStatmentCoverageMovieRecommend {
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
        movieList =null;
        userList =null;
    }
  @Test
  // TC-DB-012 Inserting 3 different movies and adding 2 users with one searching
  // a non exist movie and the other searching an existing movie, then calling
  // movieRecommend() to verify correct recommendations are made based on

    void testMovieRecommend()
  {
      // ===== Movies Data =====
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

      User u1 = new User("12345678A", "Ahmed Ali");
      User u2 = new User("87654321B", "Omar Hassan");

      // User 1 searched movies (one exists, one not exist)
      u1.setSearchedMovie(new Movie("AVN001")); // exists
      u1.setSearchedMovie(new Movie("XXX999")); // not exist

      // User 2 searched movies
      u2.setSearchedMovie(new Movie("TIT003")); // exists

      userList.add(u1);
      userList.add(u2);

      // ===== Database setup =====
      DataBase db = new DataBase();
      db.setMoviesDataBase(movieList);
      db.setUsersDataBase(userList);

      // ===== Call method =====
      db.movieSearch();
      db.movieRecommend();

      // ===== Prepare expected recommendations =====
      ArrayList<User> expectedUsers = new ArrayList<>();

      // User 1 expected
      User eu1 = new User("12345678A", "Ahmed Ali");
      ArrayList<Movie> eu1Searched = new ArrayList<>();
      eu1Searched.add(new Movie("AVN001", "Avengers", g1));
      eu1Searched.add(new Movie()); // not found movie
      eu1.setSearchedMovieInst(eu1Searched);

      ArrayList<Movie> eu1Rec = new ArrayList<>();
      eu1Rec.add(new Movie("GLD004", "Gladiator", g1)); // shares Action/Drama with AVN001
      eu1Rec.add(new Movie("TIT003", "Titanic", g3));   // shares Drama with AVN001
      eu1.setRecommendedMovies(eu1Rec);
      // User 2 expected
      User eu2 = new User("87654321B", "Omar Hassan");
      ArrayList<Movie> eu2Searched = new ArrayList<>();
      eu2Searched.add(new Movie("TIT003", "Titanic", g3));
      eu2.setSearchedMovieInst(eu2Searched);

      // User 2 expected recommendations (Omar Hassan)
      ArrayList<Movie> eu2Rec = new ArrayList<>();
      eu2Rec.add(new Movie("AVN001", "Avengers", g1)); // shares Drama with TIT003
      eu2Rec.add(new Movie("GLD004", "Gladiator", g1)); // shares Drama with TIT003
      eu2.setRecommendedMovies(eu2Rec);

      expectedUsers.add(eu1);
      expectedUsers.add(eu2);

      // ===== Assertion =====
      assertEquals(expectedUsers, db.getUsersDataBase());
  }
  }
