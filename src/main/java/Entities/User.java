package src.main.java.Entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private String id;
    private ArrayList<Movie> searchedMovie;
    private ArrayList<Movie> recommendedMovies;

    public User(String id, String name, Set<String> existingUserIds){
        setName(name);
        setId(id, existingUserIds);

        this.searchedMovie = new ArrayList<Movie>();
        this.recommendedMovies = new ArrayList<Movie>();
        existingUserIds.add(id);
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
                searchedMovie.equals(user.searchedMovie) &&
                recommendedMovies.equals(user.recommendedMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, searchedMovie, recommendedMovies);
    }





    public void setName(String name) {
        if ( name == null || name.isEmpty())
            throw new IllegalArgumentException("User name can't be null or empty");
        if (name.startsWith(" "))
            throw new IllegalArgumentException("User name can't start with a space");
        if (!name.matches("[a-zA-Z ]+"))
            throw new IllegalArgumentException("name must be Alphabetic Characters");

        this.name = name;
    }

    public void setId(String id, Set<String> existingIds) {
        if (id == null || id.length() != 9)
            throw new IllegalArgumentException("User Id can't be null or empty and must be 9 characters long");
        if (!Character.isDigit(id.charAt(0)))
            throw new IllegalArgumentException("id must start with numbers");
        if (!id.matches("[0-9][a-zA-Z0-9]*"))
            throw new IllegalArgumentException("id mustn't contain special characters only characters and numbers are allowed");
        if (existingIds.contains(id))
            throw new IllegalArgumentException("ERROR: User Id " + id + " is wrong");

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
    public void setSearchedMovieInst(ArrayList<Movie> movie) { this.searchedMovie=movie; }
}
