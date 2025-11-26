package src.main.java.Entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Movie implements Comparable{
    private String name;
    private String id;
    private int year;
    private ArrayList<String> genre;

    public Movie(String name,int year, String id){
        setName(name);
        setId(id);
        setYear(year);
        this.genre = new ArrayList<String>();
    }

    public Movie(String name, String id){
        setName(name);
        setId(id);
        this.genre = new ArrayList<String>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Movie))
            return false;

        Movie movie = (Movie) o;
        return  id.equals(movie.id) &&
                name.equals(movie.name) &&
                year == movie.year &&
                genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, genre);
    }


    public void addGenre(String genre){
        if(genre == null || genre.isEmpty())
            throw new IllegalArgumentException("genre can't be null or empty");
        this.genre.add(genre);
    }

    public void setName(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Movie name can't be null or empty");

        String[] words = name.split(" ");
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (!Character.isUpperCase(word.charAt(0))) {
                throw new IllegalArgumentException("Movie title should be written such that every word starts with a capital letter");
            }
        }

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


        StringBuilder caps = new StringBuilder();
        for (char c : this.name.toCharArray()) {
            if (Character.isUpperCase(c)) caps.append(c);
        }
        String expectedPrefix = caps.toString();


        if (!id.startsWith(expectedPrefix))
            throw new IllegalArgumentException("id must start with the starting capital letters of every word in movie name");


        String numericPart = id.substring(expectedPrefix.length());
        if (numericPart.length() != 3 || !numericPart.matches("\\d+"))
            throw new IllegalArgumentException("movie letters must contain 3 numbers after name letters");

        this.id = id;
    }

    public ArrayList<String> getGenres() {
        return this.genre;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public int compareTo(Object movie) {
        return this.id.compareTo(((Movie)movie).id);
    }
}
