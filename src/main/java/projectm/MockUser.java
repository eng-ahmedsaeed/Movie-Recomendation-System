package projectm;
import java.util.ArrayList;
import java.util.Objects;
public class MockUser {
    private int id;
    private String name;
    private Movie searchedMove;
    private ArrayList<Movie> recommendedMovies;

    public MockUser(int id, String name) {
        this.id = id;
        this.name = name;
        this.recommendedMovies = new ArrayList<>();
    }

    public Movie getSearchedMove() { return searchedMove; }
    public void setSearchedMove(Movie searchedMove) { this.searchedMove = searchedMove; }

    public void SetRecommendedMovies(ArrayList<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }

    public ArrayList<Movie> getRecommendedMovies() { return recommendedMovies; }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getName().equals(user.getName()) &&
                getSearchedMove().equals(user.getSearchedMove()) &&
                getRecommendedMovies().equals(user.getRecommendedMovies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSearchedMove(), getRecommendedMovies());
    }



}
