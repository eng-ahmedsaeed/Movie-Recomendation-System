package src.main.java.Entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private String id;
    private ArrayList<String> likedMovies;
    private ArrayList<String> searchedMove;
    private ArrayList<String> recommendedMovies;

    public User(String name, String id, Set<String> existingUserIds){
        setName(name);
        setId(id, existingUserIds);

        this.likedMovies = new ArrayList<String>();
        this.searchedMove = new ArrayList<String>();
        this.recommendedMovies = new ArrayList<String>();

    }


    public void printUser(){
        System.out.println(this.name + "," + this.id);

        System.out.println("Liked Movies");
        for(String movie : this.likedMovies){
            System.out.println(movie);
        }
    }

    public void addMovieToLikedMovies(String movie){
        this.likedMovies.add(movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                searchedMove.equals(user.searchedMove) &&
                recommendedMovies.equals(user.recommendedMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, searchedMove, recommendedMovies);
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

    public void setLikedMovies(ArrayList<String> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getLikedMovies() {
        return likedMovies;
    }

    public ArrayList<String> getSearchedMove() {
        return this.searchedMove;
    }
    public ArrayList<String> getRecommendedMovies() {
        return this.recommendedMovies;
    }

    public void setSearchedMove(ArrayList<String> searchedMove) {
        this.searchedMove = searchedMove;
    }

    public void setRecommendedMovies(ArrayList<String> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }
}
