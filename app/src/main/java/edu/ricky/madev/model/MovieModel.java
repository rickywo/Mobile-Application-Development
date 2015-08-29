package edu.ricky.madev.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ricky Wu on 2015/8/26.
 */
public class MovieModel {
    // Singleton
    private static MovieModel singleton = null;

    // Model
    private Map<String, Movie> movieMap;
    private Map<String, Event> eventMap;




    public static MovieModel getSingleton()
    {
        if(singleton == null)
            singleton = new MovieModel();
        return singleton;
    }



    // Construction
    public MovieModel()
    {
        this.movieMap = new HashMap<>();
        int i;
        for (i = 0; i < MovieSamples.numMovies; i++)
        {
            Movie tm = parseJsonToMovie(MovieSamples.mvJsons[i], MovieSamples.image[i]);
            tm.partyEvent = Arrays.asList(MovieSamples.events[i]);
            this.movieMap.put(tm.getImdbId(), tm);
        }

    }

    // Model Access
    public Movie getMovieById(String imdbId)
    {
        return movieMap.get(imdbId);
    }

    public List<Movie> getAllMovies()
    {

        return new ArrayList(movieMap.values());
    }

    private Movie parseJsonToMovie(String js, int imgSrc) {
        Map<String, String> map = new Gson().fromJson(js, new TypeToken<Map<String, String>>() {
        }.getType());
        Movie m = new Movie(
                    map.get("imdbID"),
                    map.get("Title"),
                    map.get("Year"),
                    map.get("Genre"),
                    map.get("imdbRating"),
                    map.get("Plot"));
        m.setImg(imgSrc);

        return m;
    }

}
