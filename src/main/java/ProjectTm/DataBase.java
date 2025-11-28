package ProjectTm;

import java.util.ArrayList;
import java.util.Collections;

public class DataBase {
    private
    //make sure the names of the classes is correct
    ArrayList<Movie> moviesDataBase;
    public ArrayList<User> usersDataBase;
    public DataBase() {
        moviesDataBase = new ArrayList<>();
        usersDataBase = new ArrayList<>();
    }
    public ArrayList<Movie> getMoviesDataBase() {
        return moviesDataBase;
    }
    public ArrayList<User> getUsersDataBase() {
        return usersDataBase;
    }
    public void setMoviesDataBase(ArrayList<Movie> movie) {
        moviesDataBase = movie;
        movieSort();
    }

    public void setUsersDataBase(ArrayList<User> user) {
        usersDataBase = user;
    }

    public void insert(Movie movie) {
        moviesDataBase.add(movie);


    }

    private void movieSort() {

        ///Without implementing compare to
        //Collections.sort(moviesDataBase, (m1, m2) -> m1.getYear() - m2.getYear());

        Collections.sort(moviesDataBase, (m1, m2) -> CharSequence.compare(m1.getId(), m2.getId()));

    }

    public void movieSearch() {
        Movie m;
        /// Bug5 the searched movies are arraylist
        ArrayList<Movie> reMovie;
        int index;
        /// ///Bug 1////////////////////////
        // i had fixed the intial iterrator
        for (int i = 0; i < usersDataBase.size(); i++) {
            //it now carries the id of the movie

            ////remove carry serched Movie of user[i] which is an empty arry with id
            reMovie = usersDataBase.get(i).getSearchedMovie();
            ArrayList<Movie> removiefulldata=new ArrayList<>();

            for (int j = 0; j < reMovie.size(); j++) {
                m=reMovie.get(j);
                index = Collections.binarySearch(moviesDataBase, m, (m1, m2) -> CharSequence.compare(m1.getId(), m2.getId()));
                ///////////////////////// Bug2////////////////////////////////
                /// /here I had added a condition it the id is not found to create a movie object with name "Not found "
                if (index < 0) m = new Movie();
                else m = moviesDataBase.get(index);
                removiefulldata.add(m);

            }
            usersDataBase.get(i).setSearchedMovieInst(removiefulldata);

        }
    }
    public void movieRecommend() {
        ArrayList<Movie> reMovie;
        Movie m;
        ArrayList<String> Genre = new ArrayList<>();
        int index;
        for (int i = 0; i < usersDataBase.size(); i++) {
            ArrayList<Movie> RecommendedMovies = new ArrayList<>();
            reMovie = usersDataBase.get(i).getSearchedMovie();
            for (int k = 0; k < reMovie.size(); k++) {
                m=reMovie.get(k);
                Genre = m.getGenre();
                /// add some lines to make sure not to include the searched movies in  the recommendation class
                for (int j = 0; j < moviesDataBase.size(); j++) {
                    boolean flag=moviesDataBase.get(j).getId().equalsIgnoreCase(m.getId());
                    if (genreCompare(Genre, moviesDataBase.get(j).getGenre()) && !(flag)) {
                        RecommendedMovies.add(moviesDataBase.get(j));
                    }
                }
            }
            usersDataBase.get(i).setRecommendedMovies(RecommendedMovies);

        }
    }
    private boolean genreCompare(ArrayList<String> T1, ArrayList<String> T2) {
        for (int i = 0; i < T1.size(); i++) {
            for (int j = 0; j < T2.size(); j++) {
                if (T1.get(i).equalsIgnoreCase(T2.get(j))) return true;
            }

        }
        return false;
    }
    public void Clear() {
        moviesDataBase = null;
        usersDataBase = null;

    }


}
