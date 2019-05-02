package com.example.spotlight_movies.Network;

import com.example.spotlight_movies.models.Movie;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class MovieResponse implements Serializable {


    @SerializedName("results")
    private ArrayList<Movie> movies;


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }


}
