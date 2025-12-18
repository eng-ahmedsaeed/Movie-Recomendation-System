package ProjectTm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReadUser {
    private String filePath,Name,Id;
    BufferedReader reader;
    private String[] favMoviesIds;
    Set<String> existingUserIds = new HashSet<>();
    ArrayList<User> users = new ArrayList<>();
    ValidateUser validUser = new ValidateUser(existingUserIds);
    public ReadUser(String filePath){
        this.filePath = filePath;
    }
    public void setUp() throws FileNotFoundException {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.print(filePath);
            throw e;
        }
    }

    public ArrayList<User> getUsers() throws IOException {
        try{
            setUp();
        }
        catch(FileNotFoundException e){
            System.out.println("File path not found!");
            throw e;
        }
        String line;
        while((line =reader.readLine()) != null){
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("there is missing part in this file!");
                throw new IllegalArgumentException("Error in movie line: " + line);
            }
            this.Name = parts[0].trim();
            validUser.validateUserName(this.Name);
            this.Id = parts[1].trim();
            validUser.validateUserId(this.Id);

            line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Missing genres for user: " + this.Name);
            }
            this.favMoviesIds = line.split(",");   //ids
            User user = new User(this.Id, this.Name);
            for(String id : favMoviesIds){
                Movie m = new Movie(id);
                user.setSearchedMovie(m);
            }
            users.add(user);
        }

        return users;
    }
    public void printUsers(){
        for(User u : users){
            System.out.println("***********************");
            System.out.println(u.getName());
            System.out.println(u.getId());
            for(Movie m:u.getSearchedMovie()){
                System.out.println(m.getId());
            }

        }
    }
}
