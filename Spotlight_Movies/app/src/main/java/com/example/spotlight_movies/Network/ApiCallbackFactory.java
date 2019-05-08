package com.example.spotlight_movies.Network;

import com.example.spotlight_movies.SearchActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.spotlight_movies.SearchActivity.BASE_IMAGE_URL;

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
/*
    public ArrayList<MovieResults.Result> manageCallbackSearch(String category, String query, ) {
        final ArrayList<MovieResults.Result> listOfMovies = new ArrayList<>();
        Call<MovieResults> call = ApiClient.create().listOfMovies(category,API_KEY,LANGUAGE,PAGE,query);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                listOfMovies.addAll(results.getResults());

                for (int i = 0; i < listOfMovies.size(); i++) {
                    mImageUrls_searched.add(BASE_IMAGE_URL + listOfMovies.get(i).getPosterPath());
                }

                SearchActivity.initRecyclerView();
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });

        return listOfMovies;
    }
    */
}
