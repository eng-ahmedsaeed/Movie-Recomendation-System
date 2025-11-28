package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class Integration_test{
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

        // Preparing the Expected output
        ArrayList<User> finalUsers = new ArrayList<>();

        // Ahmed
        ArrayList<String> genresAvengers = new ArrayList<>();
        genresAvengers.add("Action");
        genresAvengers.add("Adventure");
        Movie avengers = new Movie("A123", "Avengers", genresAvengers);

        ArrayList<String> genresInception = new ArrayList<>();
        genresInception.add("Action");
        genresInception.add("Thriller");
        Movie inception = new Movie("I789", "Inception", genresInception);

        User ahmed = new User("123456789", "Ahmed Mohamed");
        ahmed.setSearchedMovie(avengers);
        ArrayList<Movie> recommendedAhmed = new ArrayList<>();
        recommendedAhmed.add(inception);
        ahmed.setRecommendedMovies(recommendedAhmed);

        // Mona
        ArrayList<String> genresHomeAlone = new ArrayList<>();
        genresHomeAlone.add("Comedy");
        genresHomeAlone.add("Family");
        Movie homeAlone = new Movie("HA456", "Home Alone", genresHomeAlone);

        User mona = new User("234567890", "Mona Ali");
        mona.setSearchedMovie(homeAlone);
        ArrayList<Movie> recommendedMona = new ArrayList<>();
        mona.setRecommendedMovies(recommendedMona);

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

        // Preparing the Expected output
        ArrayList<User> expectedUsers = new ArrayList<>();

        // User Salma
        User salma = new User("345678901", "Salma Hassan");
        salma.setSearchedMovie(new Movie()); // Avengers Endgame
        salma.setRecommendedMovies(new ArrayList<>()); // No recommendations
        expectedUsers.add(salma);

        // User Ziad
        ArrayList<String> genresGuardians = new ArrayList<>();
        genresGuardians.add("Action");
        genresGuardians.add("Comedy");
        Movie guardians = new Movie("GOTG486", "Guardians Of The Galaxy", genresGuardians);
        Movie toystory = new Movie("TSF267", "Toy Story Four", new ArrayList<>(List.of("Animation", "Family")));
        Movie inception = new Movie("I987", "Inception", new ArrayList<>(List.of("Action", "Thriller")));
        Movie homeAlone2 = new Movie("HAT654", "Home Alone Two", new ArrayList<>(List.of("Comedy", "Family")));

        User ziad = new User("456789012", "Ziad Mahmoud");
        ziad.setSearchedMovie(homeAlone2);

        ArrayList<Movie> recommendedZiad = new ArrayList<>();

        recommendedZiad.add(toystory);
        recommendedZiad.add(guardians);

        ziad.setRecommendedMovies(recommendedZiad);
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
        Movie interstellar = new Movie("I741", "Interstellar", new ArrayList<>(List.of("Action", "Adventure")));
        Movie intern = new Movie("TI852", "The Intern", new ArrayList<>(List.of("Comedy", "Drama", "Adventure")));
        Movie shutter = new Movie("SI963", "Shutter Island", new ArrayList<>(List.of("Thriller", "Action")));
        Movie coco = new Movie("C357", "Coco", new ArrayList<>(List.of("Animation", "Family", "Comedy")));

        // User Omar
        User omar = new User("567890123", "Omar Said");
        omar.setSearchedMovie(interstellar);
        ArrayList<Movie> recommendedOmar = new ArrayList<>();
        recommendedOmar.add(intern);    // common genre: Adventure
        recommendedOmar.add(shutter);   // common genre: Action
        omar.setRecommendedMovies(recommendedOmar);
        expectedUsers.add(omar);

        // User Laila
        User laila = new User("678901234", "Laila Nour");
        laila.setSearchedMovie(intern);
        ArrayList<Movie> recommendedLaila = new ArrayList<>();
        recommendedLaila.add(interstellar);  // common genre: Adventure
        recommendedLaila.add(coco);          // common genre: Comedy
        laila.setRecommendedMovies(recommendedLaila);
        expectedUsers.add(laila);

        // User Youssef
        User youssef = new User("789012345", "Youssef Amir");
        youssef.setSearchedMovie(shutter);
        ArrayList<Movie> recommendedYoussef = new ArrayList<>();
        recommendedYoussef.add(interstellar); // common genre: Action
        youssef.setRecommendedMovies(recommendedYoussef);
        expectedUsers.add(youssef);

        // User Nada
        User nada = new User("890123456", "Nada Samir");
        nada.setSearchedMovie(coco);
        ArrayList<Movie> recommendedNada = new ArrayList<>();
        recommendedNada.add(intern);    // common genre: Comedy
        nada.setRecommendedMovies(recommendedNada);
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

    @Test
    void test4() {
        // Set the database with MockMovies4 and MockUsers4
        database.setMoviesDataBase(data.getMockMovies4());
        database.setUsersDataBase(data.getMockUsers4());

        // Run search and recommendation
        database.movieSearch();
        database.movieRecommend();

        // Preparing expected output for MockUsers4 with recommendedMovies
        ArrayList<User> expectedUsers = new ArrayList<>();

        // Movies
        Movie blackPanther = new Movie("BP258", "Black Panther", new ArrayList<>(List.of("Action", "Adventure")));
        Movie jumanji = new Movie("J147", "Jumanji", new ArrayList<>(List.of("Comedy", "Adventure")));
        Movie tenet = new Movie("T369", "Tenet", new ArrayList<>(List.of("Thriller", "Action")));
        Movie ratatouille = new Movie("R654", "Ratatouille", new ArrayList<>(List.of("Animation", "Comedy")));

        // User Sara
        User sara = new User("901234567", "Sara Kamal");
        sara.setSearchedMovie(jumanji);
        ArrayList<Movie> recommendedSara = new ArrayList<>();
        recommendedSara.add(blackPanther);  // common genre: Adventure
        recommendedSara.add(ratatouille);   // common genre: Comedy (shared with Jumanji)
        sara.setRecommendedMovies(recommendedSara);
        expectedUsers.add(sara);

        // User Ali
        User ali = new User("012345678", "Ali Rashed");
        ali.setSearchedMovie(blackPanther);
        ArrayList<Movie> recommendedAli = new ArrayList<>();
        recommendedAli.add(jumanji);       // common genre: Adventure
        recommendedAli.add(tenet);         // common genre: Action
        ali.setRecommendedMovies(recommendedAli);
        expectedUsers.add(ali);

        // User Hana
        User hana = new User("112233445", "Hana Tarek");
        hana.setSearchedMovie(tenet);
        ArrayList<Movie> recommendedHana = new ArrayList<>();
        recommendedHana.add(blackPanther); // common genre: Action
        hana.setRecommendedMovies(recommendedHana);
        expectedUsers.add(hana);

        // User Karim
        User karim = new User("223344556", "Karim Adel");
        karim.setSearchedMovie(jumanji);
        ArrayList<Movie> recommendedKarim = new ArrayList<>();
        recommendedKarim.add(blackPanther);  // common genre: Adventure
        recommendedKarim.add(ratatouille);   // common genre: Comedy
        karim.setRecommendedMovies(recommendedKarim);
        expectedUsers.add(karim);

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