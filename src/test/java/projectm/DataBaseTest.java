package projectm;

import org.junit.jupiter.api.BeforeAll;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
        Movie avengers = new Movie("1", "Avengers", 2012, genresAvengers);

        ArrayList<String> genresInception = new ArrayList<>();
        genresInception.add("Action");
        genresInception.add("Thriller");
        Movie inception = new Movie("3", "Inception", 2010, genresInception);

        User ahmed = new User("1", "Ahmed");
        ahmed.setSearchedMovie(avengers);
        ArrayList<Movie> recommendedAhmed = new ArrayList<>();
        recommendedAhmed.add(avengers);
        recommendedAhmed.add(inception);
        ahmed.SetRecommendedMovies(recommendedAhmed);

// Mona
        ArrayList<String> genresHomeAlone = new ArrayList<>();
        genresHomeAlone.add("Comedy");
        genresHomeAlone.add("Family");
        Movie homeAlone = new Movie("2", "Home Alone", 1990, genresHomeAlone);

        User mona = new User("2", "Mona");
        mona.setSearchedMovie(homeAlone);
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
        User salma = new User("61", "salma");
        salma.setSearchedMovie(new Movie()); // id=8 مش موجود
        salma.SetRecommendedMovies(new ArrayList<>()); // مفيش توصيات
        expectedUsers.add(salma);

// User Ziad
        ArrayList<String> genresGuardians = new ArrayList<>();
        genresGuardians.add("Action");
        genresGuardians.add("Comedy");
        Movie guardians = new Movie("2", "Guardians of the Galaxy", 2014, genresGuardians);
        Movie avengers = new Movie("12", "Avengers: Endgame", 2019, new ArrayList<>(List.of("Action", "Adventure")));
        Movie inception = new Movie("31", "Inception", 2010, new ArrayList<>(List.of("Action", "Thriller")));
        Movie homeAlone2 = new Movie("34", "Home Alone 2", 1992, new ArrayList<>(List.of("Comedy", "Family")));

        User ziad = new User("20", "Ziad");
        ziad.setSearchedMovie(guardians);

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

    @Test
    void test3() {
        database.setMoviesDataBase(data.getMockMovies3());
        database.setUsersDataBase(data.getMockUsers3());
        database.movieSearch();
        database.movieRecommend();
// Preparing expected output for MockUsers3 with recommendedMovies based on common genres
        ArrayList<User> expectedUsers = new ArrayList<>();

// Movies
        Movie interstellar = new Movie("12A", "Interstellar", 2014, new ArrayList<>(List.of("Action", "Adventure")));
        Movie intern = new Movie("7B", "The Intern", 2015, new ArrayList<>(List.of("Comedy", "Drama", "Adventure")));
        Movie shutter = new Movie("55C", "Shutter Island", 2010, new ArrayList<>(List.of("Thriller", "Action")));
        Movie coco = new Movie("89D", "Coco", 2017, new ArrayList<>(List.of("Animation", "Family", "Comedy")));

// User Omar
        User omar = new User("101h", "Omar");
        omar.setSearchedMovie(interstellar);
        ArrayList<Movie> recommendedOmar = new ArrayList<>();
        recommendedOmar.add(interstellar);
        recommendedOmar.add(intern);    // common genre: Adventure
        recommendedOmar.add(shutter);   // common genre: Action
        omar.SetRecommendedMovies(recommendedOmar);
        expectedUsers.add(omar);

// User Laila
        User laila = new User("102f", "Laila");
        laila.setSearchedMovie(intern);
        ArrayList<Movie> recommendedLaila = new ArrayList<>();
        recommendedLaila.add(intern);
        recommendedLaila.add(interstellar);  // common genre: Adventure
        recommendedLaila.add(coco);          // common genre: Comedy
        laila.SetRecommendedMovies(recommendedLaila);
        expectedUsers.add(laila);

// User Youssef
        User youssef = new User("103u", "Youssef");
        youssef.setSearchedMovie(shutter);
        ArrayList<Movie> recommendedYoussef = new ArrayList<>();
        recommendedYoussef.add(shutter);
        recommendedYoussef.add(interstellar); // common genre: Action
        youssef.SetRecommendedMovies(recommendedYoussef);
        expectedUsers.add(youssef);

// User Nada
        User nada = new User("104m", "Nada");
        nada.setSearchedMovie(coco);
        ArrayList<Movie> recommendedNada = new ArrayList<>();
        recommendedNada.add(coco);
        recommendedNada.add(intern);    // common genre: Comedy
        nada.SetRecommendedMovies(recommendedNada);
        expectedUsers.add(nada);

// Convert to array for assertion
        User[] expectedArray = expectedUsers.toArray(new User[0]);
        User[] actualArray = database.getUsersDataBase().toArray(new User[0]);

// Sort recommendedMovies by ID for all users
        for (int i = 0; i < expectedArray.length; i++) {
            Collections.sort(expectedArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
            Collections.sort(actualArray[i].getRecommendedMovies(), Comparator.comparing(Movie::getId));
        }

// Assertion
        assertArrayEquals(expectedArray, actualArray);


    }


}