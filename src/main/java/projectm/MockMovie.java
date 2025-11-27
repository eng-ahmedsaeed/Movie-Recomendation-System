package projectm;
import java.util.ArrayList;
import java.util.Objects;
public class MockMovie {
   /// Bug3 id is of type string
    private String id;
    private String name;
    /// remove year
    private int year;
    private ArrayList<String> genre = new ArrayList<>();

    public MockMovie(String id, String name, int year, ArrayList<String> genre) {
        this.id = id;
        this.name = name;
        //remove the year
        this.year = year;
        this.genre = genre;
    }
    public MockMovie(String id) {
        this.id = id;

    }
    public MockMovie() {
        this.name="Notfound";

    }


    public String getId() { return id; }
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
