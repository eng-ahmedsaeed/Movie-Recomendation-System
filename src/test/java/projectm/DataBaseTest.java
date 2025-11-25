package projectm;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import projectm.Movie;
import projectm.User;

import java.util.ArrayList;
import java.util.List;
import projectm.DataBase;
import java.util.Collections;
import java.util.Comparator;
class DataBaseTest {
    static MockData data;
    static DataBase database;

    @BeforeAll
     static void Intilization() {
        data = new MockData();
        database = new DataBase();

    }

    @BeforeEach
    void ClearingDatabase() {
        database.Clear();

    }

    @Test
    void test1() {
        database.setMoviesDataBase(data.getMockMovies());
        database.setUsersDataBase(data.getMockUsers());
        database.movieSearch();
        database.movieRecommend();
        ////peparing the Expecte output
        ArrayList<User> finalUsers = new ArrayList<>();

// Ahmed
        ArrayList<String> genresAvengers = new ArrayList<>();
        genresAvengers.add("Action");
        genresAvengers.add("Adventure");
        Movie avengers = new Movie(1, "Avengers", 2012, genresAvengers);

        ArrayList<String> genresInception = new ArrayList<>();
        genresInception.add("Action");
        genresInception.add("Thriller");
        Movie inception = new Movie(3, "Inception", 2010, genresInception);

        User ahmed = new User(1, "Ahmed");
        ahmed.setSearchedMove(avengers);
        ArrayList<Movie> recommendedAhmed = new ArrayList<>();
        recommendedAhmed.add(avengers);
        recommendedAhmed.add(inception);
        ahmed.SetRecommendedMovies(recommendedAhmed);

// Mona
        ArrayList<String> genresHomeAlone = new ArrayList<>();
        genresHomeAlone.add("Comedy");
        genresHomeAlone.add("Family");
        Movie homeAlone = new Movie(2, "Home Alone", 1990, genresHomeAlone);

        User mona = new User(2, "Mona");
        mona.setSearchedMove(homeAlone);
        ArrayList<Movie> recommendedMona = new ArrayList<>();
        recommendedMona.add(homeAlone);
        mona.SetRecommendedMovies(recommendedMona);

// Add to final ArrayList
        finalUsers.add(ahmed);
        finalUsers.add(mona);

        User[] expectedArray = finalUsers.toArray(new User[0]);
        User[] actualArray = database.getUsersDataBase().toArray(new User[0]);

        for (int i = 0; i < expectedArray.length; i++) {
            Collections.sort(expectedArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
            Collections.sort(actualArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
        }
        assertArrayEquals(expectedArray, actualArray);

    }
    @Test
    void test2() {
        database.setMoviesDataBase(data.getMockMovies2());
        database.setUsersDataBase(data.getMockUsers2());
        database.movieSearch();
        database.movieRecommend();
        ////peparing the Expecte output
        ArrayList<User> expectedUsers = new ArrayList<>();

// User Salma
        User salma = new User(61, "salma");
        salma.setSearchedMove(new Movie()); // id=8 مش موجود
        salma.SetRecommendedMovies(new ArrayList<>()); // مفيش توصيات
        expectedUsers.add(salma);

// User Ziad
        ArrayList<String> genresGuardians = new ArrayList<>();
        genresGuardians.add("Action");
        genresGuardians.add("Comedy");
        Movie guardians = new Movie(2, "Guardians of the Galaxy", 2014, genresGuardians);
        Movie avengers = new Movie(12, "Avengers: Endgame", 2019, new ArrayList<>(List.of("Action","Adventure")));
        Movie inception = new Movie(31, "Inception", 2010, new ArrayList<>(List.of("Action","Thriller")));
        Movie homeAlone2 = new Movie(34, "Home Alone 2", 1992, new ArrayList<>(List.of("Comedy","Family")));

        User ziad = new User(20, "Ziad");
        ziad.setSearchedMove(guardians);

        ArrayList<Movie> recommendedZiad = new ArrayList<>();
        recommendedZiad.add(guardians);
        recommendedZiad.add(inception);
        recommendedZiad.add(avengers);
        recommendedZiad.add(homeAlone2);


        ziad.SetRecommendedMovies(recommendedZiad);
        expectedUsers.add(ziad);


        User[] expectedArray = expectedUsers.toArray(new User[0]);
        User[] actualArray = database.getUsersDataBase().toArray(new User[0]);


        for (int i = 0; i < expectedArray.length; i++) {
            Collections.sort(expectedArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
            Collections.sort(actualArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
        }

        assertArrayEquals(expectedArray, actualArray);

    }



}