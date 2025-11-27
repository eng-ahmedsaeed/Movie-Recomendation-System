package projectm;
import java.util.ArrayList;
import java.util.Objects;
public class MockUser {
   ///Bug4 he type should be string
    private String id;
    private String name;
    //Bug 5searched move should e array list
    private ArrayList<Movie> searchedMovie ;
    private ArrayList<Movie> recommendedMovies;

    public MockUser(String id, String name) {
        this.id = id;
        this.name = name;
        searchedMovie= new ArrayList<>();
        recommendedMovies = new ArrayList<>();
    }

    public ArrayList<Movie> getSearchedMovie() { return searchedMovie; }
    public void setSearchedMovie(Movie movie) { this.searchedMovie.add(movie); }
    public void setSearchedMovieInst(ArrayList<Movie> movie) { this.searchedMovie=movie; }
    public void SetRecommendedMovies(ArrayList<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }

    public ArrayList<Movie> getRecommendedMovies() { return recommendedMovies; }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getName().equals(user.getName()) &&
                getSearchedMovie().equals(user.getSearchedMovie()) &&
                getRecommendedMovies().equals(user.getRecommendedMovies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSearchedMovie(), getRecommendedMovies());
    }



}
