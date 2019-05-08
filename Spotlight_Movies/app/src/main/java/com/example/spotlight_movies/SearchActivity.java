package com.example.spotlight_movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.spotlight_movies.Network.ApiCallbackFactory;
import com.example.spotlight_movies.Network.ApiInterface;
import com.example.spotlight_movies.Network.MovieResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    // ===API variables===
    public static String BASE_URL = "https://api.themoviedb.org/";
    public static int PAGE = 1;
    public static String API_KEY = "94f2d3081ba573d2f171f0f8020eb38a";
    public static String LANGUAGE = "en-US";
    public static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    // ========================

    //variables
    private ArrayList<MovieResults.Result> searchedMovies = new ArrayList<>();
    private ArrayList<String> mImageUrls_searched = new ArrayList<>();

    SearchView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);



        searchButton = findViewById(R.id.searchView2);
        searchButton.setQueryHint("Search movies");

        searchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {

                // Kyle's API Stuff...
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface myInterface = retrofit.create(ApiInterface.class);
                Call<MovieResults> call = myInterface.listOfMoviesSearched(API_KEY,LANGUAGE,PAGE,query);

                call.enqueue(new Callback<MovieResults>() {
                    @Override
                    public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                        MovieResults results = response.body();
                        List<MovieResults.Result> listOfMovies = results.getResults();

                        mImageUrls_searched.clear();
                        for (int i = 0; i < listOfMovies.size(); i++) {
                            mImageUrls_searched.add(BASE_IMAGE_URL + listOfMovies.get(i).getPosterPath());
                        }

                        initRecyclerView();
                    }

                    @Override
                    public void onFailure(Call<MovieResults> call, Throwable t) {
                        t.printStackTrace();

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        LinearLayoutManager layoutManagerSearch = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewSearch = findViewById(R.id.recyclerView_search);
        recyclerViewSearch.setLayoutManager(layoutManagerSearch);
        RecyclerViewAdapter adapterSearch = new RecyclerViewAdapter(this, mImageUrls_searched);
        recyclerViewSearch.setAdapter(adapterSearch);

    }

}
