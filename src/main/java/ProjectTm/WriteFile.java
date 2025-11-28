package ProjectTm;
import java.io.*;
import java.util.ArrayList;
public class WriteFile {
    private String filePath;
    BufferedWriter result;
    ArrayList<User> users;
    ArrayList<Movie> movies;
    public WriteFile(){
        filePath="";
        users = null;
        movies = null;
    }
    public WriteFile(String resultPath, ArrayList<User> users, ArrayList<Movie> movies){
        this.filePath = resultPath;
        this.movies = movies;
        this.users = users;
    }
    public void setUpFile()  {
        try {
            result  = new BufferedWriter(new FileWriter(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("Something wents wrong!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void buildFile() throws IOException {
        setUpFile();
        for(User user :users){
            try {
                result.write(user.getName() + " , " + user.getId());
                result.write("\n");
                int counter=0;
                ArrayList<Movie> movieRec = user.getRecommendedMovies();
                ArrayList<Movie> moviesLiked = user.getSearchedMovie();

                for(Movie movie : moviesLiked){   //arrayList<movie>
                    result.write(movie.getName() );
                    if(movieRec.size()!=0){
                        result.write(" , ");
                    }
                }
                for(Movie movie : movieRec){ //arrayList<movie>
                    try {
                        if (counter== (moviesLiked.size())-1){
                            result.write(movie.getName());
                        }else{
                            result.write(movie.getName() + " , ");
                        }
                        counter++;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                result.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        result.close();
    }
}
