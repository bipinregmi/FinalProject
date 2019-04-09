package com.example.spotlight_movies.Network;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class MovieAPIHelper {
    private static final String TAG = "MovieHelper";

    private static final String baseApiUrl = "https://api.themoviedb.org/3/movie/550?api_key=94f2d3081ba573d2f171f0f8020eb38a";
    private static final String apiKey = "94f2d3081ba573d2f171f0f8020eb38a";


    /**
     * Searches the YummlyApi recipe database.
     * @param input the ingredient to search in recipes
     * @return string: JSON response with matching recipes
     */
    static String searchMovies(String input) {
        OkHttpClient client = new OkHttpClient();

        // piece together a valid url, starting with the base
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseApiUrl).newBuilder();
        // and adding on a few more parts
        urlBuilder.addQueryParameter("_app_key", apiKey);
        urlBuilder.addQueryParameter("q", input);
        urlBuilder.addQueryParameter("maxResult", "40");
        // until it's ready to assemble
        String url = urlBuilder.build().toString();

        // form the Request with the newly built url
        Request request = new Request.Builder().url(url).build();

        try {
            // ask the server for a response
            Response response = client.newCall(request).execute();
            if (response != null) {
                // response also contains metadata: success vs fail, travel time, etc.
                // only need the search result here
                return response.body().string();
            }
        } catch (IOException e) {
            // log the error to the console window (logcat)
            Log.e(TAG, "searchMovies: ", e);
        }
        return null;
    }

}