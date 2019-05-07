package com.example.spotlight_movies.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */


public interface ApiFactory {

    @GET("popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key,
                                                                         @Query("page") int page);
    @GET("trailer")
    Call<MovieResponse> getTrailer(@Path("id") int id, @Query("api_key") String api_key,
                                         @Query("page") int page);

    @GET("now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String api_key,
                                                                            @Query("page") int page);

    @GET("top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key,
                                                                          @Query("page") int page);

    @GET("upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key,
                                                                          @Query("page") int page);

    @GET("{id}/images")
    Call<ImageResponse> getBannerImages(@Path("id") int id, @Query("api_key") String api_key);

    @GET("{id}")
    Call<AboutMovieResponse> getAboutMovie(@Path("id") int id, @Query("api_key") String api_key,
                                           @Query("append_to_response") String videos);

    @GET("{id}/credits")
    Call<CastResponse> getCredits(@Path("id") int id, @Query("api_key") String api_key);

    @GET("{id}/similar")
    Call<MovieResponse> getSimilarMovies(@Path("id") int id, @Query("api_key") String api_key, @Query("page") int page);




}
