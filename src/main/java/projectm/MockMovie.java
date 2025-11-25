package projectm;
import java.util.ArrayList;
import java.util.Objects;
public class MockMovie {
    private int id;
    private String name;
    private int year;
    private ArrayList<String> genre = new ArrayList<>();

    public MockMovie(int id, String name, int year, ArrayList<String> genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
    }
    public MockMovie(int id) {
        this.id = id;

    }
    public MockMovie() {
        this.name="Notfound";

    }


    public int getId() { return id; }
    public String getName() { return name; }
    public int getYear() { return year; }
    public ArrayList<String> getGenre() { return genre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getId() == movie.getId() &&
                getYear() == movie.getYear() &&
                getName().equals(movie.getName()) &&
                getGenre().equals(movie.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getYear(), getGenre());
    }




}
