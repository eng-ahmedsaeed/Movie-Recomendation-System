package src.main.java.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private String id;
    private ArrayList<String> likedMovies;

    public User(String name, String id){
        setName(name);
        setId(id);

        this.likedMovies = new ArrayList<String>();
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

    public void setName(String name) {
        if ( name == null || name.isEmpty())
            throw new IllegalArgumentException("User name can't be null or empty");

        this.name = name;
    }

    public void setId(String id) {
        if ( id == null || id.isEmpty() )
            throw new IllegalArgumentException("User Id can't be null or empty");
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


}
