package ProjectTm;
import java.util.ArrayList;
import java.util.Objects;
public class Movie implements Comparable {
    private String name;
    private String id;
    private ArrayList<String> genre;

    public Movie(){
        this.name = "Not found";
        ///i added these to solve a thrown null exception in dataBAse logic
        this.id = "Not found";
        this.genre =new ArrayList<String>();
    }

    public Movie(String id ,String name,ArrayList<String> genre){
        this.name = name;
        this.id = id;
        this.genre = genre;
    }

    public Movie(String id){
        this.id=id;
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
                genre.equals(movie.genre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre);
    }


    public void addGenre(String genre){
        if(genre == null || genre.isEmpty())
            throw new IllegalArgumentException("genre can't be null or empty");
        this.genre.add(genre);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getGenre(){
        return this.genre;
    }
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    @Override
    public int compareTo(Object movie) {
        return this.id.compareTo(((Movie) movie).id);
    }
}


