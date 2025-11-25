package src.main.java.Entities;

import java.util.ArrayList;
import java.util.Comparator;

public class Movie implements Comparable{
    private String name;
    private String id;
    private ArrayList<String> genres;

    public Movie(String name, String id){
        setName(name);
        setId(id);

        this.genres = new ArrayList<String>();
    }

    public void addGenre(String genre){
        this.genres.add(genre);
    }

    public void setName(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Movie name can't be null or empty");

        this.name = name;
    }

    public void setId(String id) {
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Movie name can't be null or empty");

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Object movie) {
        return this.id.compareTo(((Movie)movie).id);
    }
}
