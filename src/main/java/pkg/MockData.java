package pkg;




import java.util.ArrayList;

    public class MockData {
        static ArrayList<Movie> movies = new ArrayList<>();
        static ArrayList<User> users;

        public static ArrayList<Movie> getMockMovies() {
            movies = new ArrayList<>();

            ArrayList<String> genres1 = new ArrayList<>();
            genres1.add("Action");
            genres1.add("Adventure");
            movies.add(new Movie("A123", "Avengers", genres1));

            ArrayList<String> genres2 = new ArrayList<>();
            genres2.add("Comedy");
            genres2.add("Family");
            movies.add(new Movie("HA456", "Home Alone", genres2));

            ArrayList<String> genres3 = new ArrayList<>();
            genres3.add("Action");
            genres3.add("Thriller");
            movies.add(new Movie("I789", "Inception", genres3));

            return movies;
        }

        public static ArrayList<User> getMockUsers() {
            users = new ArrayList<>();

            User u1 = new User("123456789", "Ahmed Mohamed");
            u1.setSearchedMovie(new Movie("A123"));

            User u2 = new User("234567890", "Mona Ali");
            u2.setSearchedMovie(new Movie("HA456"));

            users.add(u1);
            users.add(u2);

            return users;
        }

        // Testing data 2
        public static ArrayList<Movie> getMockMovies2() {
            movies = new ArrayList<>();

            // Movie 1
            ArrayList<String> genres1 = new ArrayList<>();
            genres1.add("Action");
            genres1.add("Adventure");
            movies.add(new Movie("AE321", "Avengers Endgame", genres1));

            // Movie 2
            ArrayList<String> genres2 = new ArrayList<>();
            genres2.add("Comedy");
            genres2.add("Family");
            movies.add(new Movie("HAT654", "Home Alone Two", genres2));

            // Movie 3
            ArrayList<String> genres3 = new ArrayList<>();
            genres3.add("Action");
            genres3.add("Thriller");
            movies.add(new Movie("I987", "Inception", genres3));

            // Movie 4
            ArrayList<String> genres4 = new ArrayList<>();
            genres4.add("Horror");
            genres4.add("Mystery");
            movies.add(new Movie("TC159", "The Conjuring", genres4));

            // Movie 5
            ArrayList<String> genres5 = new ArrayList<>();
            genres5.add("Drama");
            genres5.add("Romance");
            movies.add(new Movie("LLL753", "La La Land", genres5));

            // Movie 6
            ArrayList<String> genres6 = new ArrayList<>();
            genres6.add("Action");
            genres6.add("Comedy");
            movies.add(new Movie("GOTG486", "Guardians Of The Galaxy", genres6));

            // Movie 7
            ArrayList<String> genres7 = new ArrayList<>();
            genres7.add("Animation");
            genres7.add("Family");
            movies.add(new Movie("TSF267", "Toy Story Four", genres7));

            return movies;
        }

        public static ArrayList<User> getMockUsers2() {
            users = new ArrayList<>();

            User u1 = new User("345678901", "Salma Hassan");
            u1.setSearchedMovie(new Movie("AF321"));

            User u2 = new User("456789012", "Ziad Mahmoud");
            u2.setSearchedMovie(new Movie("HAT654"));

            users.add(u1);
            users.add(u2);

            return users;
        }

        // Testing data 3
        public static ArrayList<Movie> getMockMovies3() {
            movies = new ArrayList<>();

            // Movie 1
            ArrayList<String> g1 = new ArrayList<>();
            g1.add("Action");
            g1.add("Adventure");
            movies.add(new Movie("I741", "Interstellar", g1));

            // Movie 2
            ArrayList<String> g2 = new ArrayList<>();
            g2.add("Comedy");
            g2.add("Drama");
            g2.add("Adventure");
            movies.add(new Movie("TI852", "The Intern", g2));

            // Movie 3
            ArrayList<String> g3 = new ArrayList<>();
            g3.add("Thriller");
            g3.add("Action");
            movies.add(new Movie("SI963", "Shutter Island", g3));

            // Movie 4
            ArrayList<String> g4 = new ArrayList<>();
            g4.add("Animation");
            g4.add("Family");
            g4.add("Comedy");
            movies.add(new Movie("C357", "Coco", g4));

            return movies;
        }

        public static ArrayList<User> getMockUsers3() {
            users = new ArrayList<>();

            // User Omar
            User u1 = new User("567890123", "Omar Said");
            u1.setSearchedMovie(new Movie("I741"));

            // User Laila
            User u2 = new User("678901234", "Laila Nour");
            u2.setSearchedMovie(new Movie("TI852"));

            // User Youssef
            User u3 = new User("789012345", "Youssef Amir");
            u3.setSearchedMovie(new Movie("SI963"));

            // User Nada
            User u4 = new User("890123456", "Nada Samir");
            u4.setSearchedMovie(new Movie("C357"));

            users.add(u1);
            users.add(u2);
            users.add(u3);
            users.add(u4);

            return users;
        }

        // Testing data 4
        public static ArrayList<Movie> getMockMovies4() {
            movies = new ArrayList<>();

            // Movie 1
            ArrayList<String> g1 = new ArrayList<>();
            g1.add("Action");
            g1.add("Adventure");
            movies.add(new Movie("BP258", "Black Panther", g1));

            // Movie 2
            ArrayList<String> g2 = new ArrayList<>();
            g2.add("Comedy");
            g2.add("Adventure");
            movies.add(new Movie("J147", "Jumanji", g2));

            // Movie 3
            ArrayList<String> g3 = new ArrayList<>();
            g3.add("Thriller");
            g3.add("Action");
            movies.add(new Movie("T369", "Tenet", g3));

            // Movie 4
            ArrayList<String> g4 = new ArrayList<>();
            g4.add("Animation");
            g4.add("Comedy");
            movies.add(new Movie("R654", "Ratatouille", g4));

            return movies;
        }

        public static ArrayList<User> getMockUsers4() {
            users = new ArrayList<>();

            // User Sara
            User u1 = new User("901234567", "Sara Kamal");
            u1.setSearchedMovie(new Movie("J147"));

            // User Ali
            User u2 = new User("012345678", "Ali Rashed");
            u2.setSearchedMovie(new Movie("BP258"));

            // User Hana
            User u3 = new User("112233445", "Hana Tarek");
            u3.setSearchedMovie(new Movie("T369"));

            // User Karim
            User u4 = new User("223344556", "Karim Adel");
            u4.setSearchedMovie(new Movie("J147"));

            users.add(u1);
            users.add(u2);
            users.add(u3);
            users.add(u4);

            return users;
        }
    }



