package com.example.spotlight_movies.Network;

import com.example.spotlight_movies.models.Cast;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class CastResponse {

    @SerializedName("cast")
    private ArrayList<Cast> cast;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setMovieCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

}
