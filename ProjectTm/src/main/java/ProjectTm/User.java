package ProjectTm;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private String id;
    private ArrayList<Movie> searchedMovie;
    private ArrayList<Movie> recommendedMovies;

    public User(String id, String name){
        this.name = name;
        this.id = id;
        this.searchedMovie = new ArrayList<Movie>();
        this.recommendedMovies = new ArrayList<Movie>();
    }

    public void printUser(){
        System.out.println(this.name + "," + this.id);
        for( Movie movie : recommendedMovies){
            System.out.println(movie.getName() +","+ movie.getId());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                searchedMovie.equals(user.searchedMovie)&&
                recommendedMovies.equals(user.recommendedMovies);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, searchedMovie, recommendedMovies);
    }



    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public ArrayList<Movie> getSearchedMovie() {
        return this.searchedMovie;
    }
    public ArrayList<Movie> getRecommendedMovies() {
        return this.recommendedMovies;
    }

    public void setRecommendedMovies(ArrayList<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }
    public void setSearchedMovie(Movie movie) { this.searchedMovie.add(movie); }

    public void setSearchedMovieInst(ArrayList<Movie> movie) { this.searchedMovie=movie;}
}







