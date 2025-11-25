package src.main.java.Entities;

import java.util.ArrayList;
import java.util.Comparator;

public class Movie implements Comparable{
    private String name;
    private String id;
    private int year;
    private ArrayList<String> genres;

    public Movie(String name,int year, String id){
        setName(name);
        setId(id);
        setYear(year);
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

    public void setYear(int year) {
        final int minYear = 1888;
        final int currentYear = java.time.Year.now().getValue();

        // Check if the year is outside the acceptable historical and future range
        if (year < minYear || year > currentYear) {
            throw new IllegalArgumentException(
                    "Invalid year: " + year + ". Year must be between " + minYear + " and " + currentYear + ".");
        }
        this.year = year;
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

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Object movie) {
        return this.id.compareTo(((Movie)movie).id);
    }




}
