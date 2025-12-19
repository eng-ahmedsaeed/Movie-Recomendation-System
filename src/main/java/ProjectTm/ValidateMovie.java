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
            char first = word.charAt(0);
            if (Character.isLetter(first) && !Character.isUpperCase(first)) {
                throw new IllegalArgumentException("Movie title should be written such that every word starts with a capital letter");
            }
        }

        return true;
    }

    public boolean ValidateMovieId(String id, String movieName){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Movie Id can't be null or empty");
        // split id into letter prefix and numeric suffix
        int i = 0;
        while (i < id.length() && Character.isLetter(id.charAt(i))) i++;
        if (i == 0)
            throw new IllegalArgumentException("Id must start with letter prefix");
        String prefix = id.substring(0, i);
        String numericPart = id.substring(i);

        if (numericPart.length() != 3 || !numericPart.matches("\\d+"))
            throw new IllegalArgumentException("movie letters must contain 3 numbers after name letters");

        // prefix must be all uppercase letters
        for (char c : prefix.toCharArray()) {
            if (!Character.isUpperCase(c))
                throw new IllegalArgumentException("Letter prefix must be uppercase letters only");
        }

        // ensure numeric uniqueness
        if (existingMovieIds.contains(numericPart))
            throw new IllegalArgumentException("Id numeric part must be unique");

        // ensure prefix uniqueness across movies
        if (existingPrefixes.contains(prefix))
            throw new IllegalArgumentException("Letter prefix must be unique");

        // build expected prefix: starting capital letters of every word in the movie name
        StringBuilder expected = new StringBuilder();
        String[] words = movieName.split(" ");
        for (String w : words) {
            if (w.isEmpty()) continue;
            char f = w.charAt(0);
            if (Character.isLetter(f)) expected.append(Character.toUpperCase(f));
        }
        String expectedPrefix = expected.toString();
        if (expectedPrefix.isEmpty())
            throw new IllegalArgumentException("id must start with the starting capital letters of every word in movie name");

        // Accept when prefix exactly matches the expected starting letters.
        boolean ok = prefix.equals(expectedPrefix);
        // Or accept when prefix is longer than expected and its letters appear in the
        // movie name (as a subsequence). This allows some valid long prefixes derived
        // from internal capitalized letters while still rejecting partial prefixes.
        if (!ok) {
            if (prefix.length() <= expectedPrefix.length()) {
                throw new IllegalArgumentException("id must start with the starting capital letters of every word in movie name");
            }
            // check subsequence on letters-only movie name
            String nameLetters = movieName.replaceAll("[^A-Za-z]", "").toUpperCase();
            String upPrefix = prefix.toUpperCase();
            int p = 0;
            for (int j = 0; j < nameLetters.length() && p < upPrefix.length(); j++) {
                if (nameLetters.charAt(j) == upPrefix.charAt(p)) p++;
            }
            if (p != upPrefix.length())
                throw new IllegalArgumentException("id must start with the starting capital letters of every word in movie name");
        }

        existingMovieIds.add(numericPart);
        existingPrefixes.add(prefix);
        return true;
    }
}
