package projectm;
import java.util.ArrayList;
public class Movie extends MockMovie {
    public Movie(int id, String name, int year, ArrayList<String> genre) {
        super(id, name, year, genre);
    }
    public Movie(int id) {
        super(id);
    }
    public Movie() {
        super();
    }



}
