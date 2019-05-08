package com.example.spotlight_movies.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/{category}")
    Call<MovieResults> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/search/movie")
    Call<MovieResults> listOfMoviesSearched(
            //@Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("query") String query
    );

    @GET("/3/movie/{movie_id}/videos")
    Call<VideoResults> listOfVideos(
            @Path("movie_id") int movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
