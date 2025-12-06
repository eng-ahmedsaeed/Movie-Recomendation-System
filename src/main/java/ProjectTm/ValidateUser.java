package ProjectTm;

import java.util.Set;


public class ValidateUser {

    private final Set<String> existingUserIds;

    public ValidateUser(Set<String> existingUserIds){
        this.existingUserIds = existingUserIds;
    }


    public Boolean validateUserId(String id) {
        if (id == null || id.length() != 9)
            throw new IllegalArgumentException("User Id can't be null or empty and must be 9 characters long");
        if (!Character.isDigit(id.charAt(0)))
            throw new IllegalArgumentException("id must start with numbers");
        if (!id.matches("[0-9][a-zA-Z0-9]*"))
            throw new IllegalArgumentException("id mustn't contain special characters only characters and numbers are allowed");
        if (existingUserIds.contains(id))
            throw new IllegalArgumentException("ERROR: User Id " + id + " is wrong");

        existingUserIds.add(id);
        return true;
    }

    public boolean validateUserName(String name){
        if ( name == null || name.isEmpty())
            throw new IllegalArgumentException("User name can't be null or empty");
        if (name.startsWith(" "))
            throw new IllegalArgumentException("User name can't start with a space");
        if (!name.matches("[a-zA-Z ]+"))
            throw new IllegalArgumentException("name must be Alphabetic Characters");

        return true;
    }
}
