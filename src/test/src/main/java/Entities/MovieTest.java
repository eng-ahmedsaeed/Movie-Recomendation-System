package src.main.java.Entities;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.HashSet;
import java.util.Set;


public class MovieTest {
    Movie movie;

    @BeforeEach
    public void setUp(){
        movie = new Movie("I002","Inceptions");
    }

    @Test
    public void movie_WithProperMovieDetails_ToBeSuccessful(){
        assertNotNull(movie);
    }

    @Test
    public void movie_WithNullName_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setName(null);} );
    }

    @Test
    public void movie_WithNullID_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setId(null);} );
    }

    @Test
    public void movie_ID_NoLettersPrefix_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setId("456");} );
    }


    @Test
    public void movie_ID_StartingWithInvalidPrefix_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setId("TS456");} );
    }

    @Test
    public void movie_ID_WithMoreThanThreeNumbers_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setId("I4565623");} );
    }

    @Test
    public void movie_ID_WithCharactersInNumericPart_ToFail(){
        assertThrows(IllegalArgumentException.class,()-> {movie.setId("I45s");} );
    }

}