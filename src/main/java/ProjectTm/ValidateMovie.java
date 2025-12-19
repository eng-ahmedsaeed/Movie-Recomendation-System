package ProjectTm;

import java.util.HashSet;
import java.util.Set;

public class ValidateMovie {

    private final Set<String> existingMovieIds;
    private final Set<String> existingPrefixes;

    public ValidateMovie(Set<String> existingMovieIds){
        this.existingMovieIds = existingMovieIds;
        this.existingPrefixes = new HashSet<>();
    }

    public boolean ValidateMovieName(String name){
        if(name == null || name.isEmpty() || name.trim().isEmpty())
            throw new IllegalArgumentException("Movie name can't be null or empty");
        String[] words = name.split(" ");
        for (String word : words) {
            if (word.isEmpty())
                continue;
            if (!Character.isUpperCase(word.charAt(0))) {
                throw new IllegalArgumentException("Movie title should be written such that every word starts with a capital letter");
            }
        }

        return true;
    }

    public boolean ValidateMovieId(String id, String movieName){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Movie Id can't be null or empty");

        StringBuilder caps = new StringBuilder();
        for (char c : movieName.toCharArray()) {
            if (Character.isUpperCase(c))
                caps.append(c);
        }
        String expectedPrefix = caps.toString();
        if (expectedPrefix.isEmpty() || !id.startsWith(expectedPrefix))
            throw new IllegalArgumentException("id must start with the starting capital letters of every word in movie name");

        String numericPart = id.substring(expectedPrefix.length());
        if (numericPart.length() != 3 || !numericPart.matches("\\d+"))
            throw new IllegalArgumentException("movie letters must contain 3 numbers after name letters");
        if (existingMovieIds.contains(numericPart))
            throw new IllegalArgumentException("Id numeric part must be unique");
        if (existingPrefixes.contains(expectedPrefix))
            throw new IllegalArgumentException("Letter prefix must be unique");

        existingMovieIds.add(numericPart);
        existingPrefixes.add(expectedPrefix);
        return true;
    }
}
