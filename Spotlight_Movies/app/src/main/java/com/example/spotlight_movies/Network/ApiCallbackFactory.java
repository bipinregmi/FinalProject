package com.example.spotlight_movies.Network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallbackFactory {
    private String API_KEY = "94f2d3081ba573d2f171f0f8020eb38a";
    private static String LANGUAGE = "en-US";
    public static int PAGE = 1;


    public ArrayList<MovieResults.Result> manageCallback(String category) {
        final ArrayList<MovieResults.Result> listOfMovies = new ArrayList<>();
        Call<MovieResults> call = ApiClient.create().listOfMovies(category,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                listOfMovies.addAll(results.getResults());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });

        return listOfMovies;
    }
}
