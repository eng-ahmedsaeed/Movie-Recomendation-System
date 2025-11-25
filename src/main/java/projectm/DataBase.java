package projectm;

import projectm.Movie;
import projectm.User;

import java.util.ArrayList;
import java.util.Collections;

public class DataBase {

    private
    //make sure the the names of the classes is correct
    ArrayList<Movie> moviesDataBase;
    public ArrayList<User> usersDataBase;
    private int moviesSize;
    private int usersSize;

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

        Collections.sort(moviesDataBase, (m1, m2) -> Integer.compare(m1.getId(), m2.getId()));

    }

    public void movieSearch() {
        Movie m;
        int index;
/// ///Bug 1////////////////////////
        i had fixed the intial iterrator
        for (int i = 0; i < usersDataBase.size(); i++) {
            //it now carries the id of he movie
            m = usersDataBase.get(i).getSearchedMove();
            index = Collections.binarySearch(moviesDataBase, m, (m1, m2) -> Integer.compare(m1.getId(), m2.getId()));
///////////////////////// Bug2////////////////////////////////
        /// /here i had added a condition it the id is not found to create a movie object with name "Not found "
            if(index<0)
                m=new Movie();
            else
            m = moviesDataBase.get(index);
            usersDataBase.get(i).setSearchedMove(m);


        }
    }

    public void movieRecommend() {

        Movie m;
        ArrayList<String> Genre = new ArrayList<>();
        int index;
        for (int i = 0; i < usersDataBase.size(); i++) {
            ArrayList<Movie> RecommendedMovies = new ArrayList<>();
            m = usersDataBase.get(i).getSearchedMove();
                Genre = m.getGenre();
            for (int j = 0; j < moviesDataBase.size(); j++) {
                if (genreCompare(Genre, moviesDataBase.get(j).getGenre())) RecommendedMovies.add(moviesDataBase.get(j));
            }
            usersDataBase.get(i).SetRecommendedMovies(RecommendedMovies);

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
