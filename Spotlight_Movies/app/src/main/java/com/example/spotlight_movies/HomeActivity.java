package com.example.spotlight_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.spotlight_movies.Network.ApiCallbackFactory;
import com.example.spotlight_movies.Network.ApiInterface;
import com.example.spotlight_movies.Network.MovieResults;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    // ===API test variables===
    public static String BASE_URL = "https://api.themoviedb.org/";
    public static int PAGE = 1;
    public static String API_KEY = "94f2d3081ba573d2f171f0f8020eb38a";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY_1 = "popular";
    public static String CATEGORY_2 = "upcoming";
    public static String CATEGORY_3 = "now_playing";
    public static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    // ========================

    //variables
    private ArrayList<MovieResults.Result> popularMovies = new ArrayList<>();
    private ArrayList<String> mImageUrls_popular = new ArrayList<>();

    private ArrayList<MovieResults.Result> upcomingMovies = new ArrayList<>();
    private ArrayList<String> mImageUrls_upcoming = new ArrayList<>();

    private ArrayList<MovieResults.Result> nowPlayingMovies = new ArrayList<>();
    private ArrayList<String> mImageUrls_nowPlaying = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // API Stuff...
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);
        ApiCallbackFactory factory = new ApiCallbackFactory();
        popularMovies = factory.manageCallback(CATEGORY_1);
        upcomingMovies = factory.manageCallback(CATEGORY_2);
        nowPlayingMovies = factory.manageCallback(CATEGORY_3);


        Call<MovieResults> call = myInterface.listOfMovies(CATEGORY_1,API_KEY,LANGUAGE,PAGE);
        Call<MovieResults> call_2 = myInterface.listOfMovies(CATEGORY_2,API_KEY,LANGUAGE,PAGE);
        Call<MovieResults> call_3 = myInterface.listOfMovies(CATEGORY_3,API_KEY,LANGUAGE,PAGE);

        for (int i = 0; i < 20000; i++) { Log.d(TAG, "waiting for response..."); }

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                //MovieResults results = response.body();
                //List<MovieResults.Result> listOfMovies = results.getResults();
                //firstMovie = listOfMovies.get(0);

                Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

                for (int i = 0; i < popularMovies.size(); i++) {
                    mImageUrls_popular.add(BASE_IMAGE_URL + popularMovies.get(i).getPosterPath());
                }
                for (int i = 0; i < upcomingMovies.size(); i++) {
                    mImageUrls_upcoming.add(BASE_IMAGE_URL + upcomingMovies.get(i).getPosterPath());
                }
                for (int i = 0; i < nowPlayingMovies.size(); i++) {
                    mImageUrls_nowPlaying.add(BASE_IMAGE_URL + nowPlayingMovies.get(i).getPosterPath());
                }

                initRecyclerView();

            }


            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    private void populateRecyclerViews() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        for (int i = 0; i < popularMovies.size(); i++) {
            mImageUrls_popular.add(BASE_IMAGE_URL + popularMovies.get(i).getPosterPath());
        }
        for (int i = 0; i < upcomingMovies.size(); i++) {
            mImageUrls_upcoming.add(BASE_IMAGE_URL + upcomingMovies.get(i).getPosterPath());
        }
        for (int i = 0; i < nowPlayingMovies.size(); i++) {
            mImageUrls_nowPlaying.add(BASE_IMAGE_URL + nowPlayingMovies.get(i).getPosterPath());
        }

        initRecyclerView();
    }

/*UNUSED:
    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        //mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mImageUrls.add(firstMovieImage);

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");


        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");



        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");


        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");



        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");


        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");


        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");


        initRecyclerView();
    }
    */

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        LinearLayoutManager layoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerViewH = findViewById(R.id.recyclerView_H);
        recyclerViewH.setLayoutManager(layoutManagerH);
        RecyclerViewAdapter adapterH = new RecyclerViewAdapter(this, mImageUrls_popular);
        recyclerViewH.setAdapter(adapterH);

        LinearLayoutManager layoutManagerH2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerViewH2 = findViewById(R.id.recyclerView_H2);
        recyclerViewH2.setLayoutManager(layoutManagerH2);
        RecyclerViewAdapter adapterH2 = new RecyclerViewAdapter(this, mImageUrls_upcoming);
        recyclerViewH2.setAdapter(adapterH2);


        LinearLayoutManager layoutManagerH3 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerViewH3 = findViewById(R.id.recyclerView_H3);
        recyclerViewH3.setLayoutManager(layoutManagerH3);
        RecyclerViewAdapter adapterH3 = new RecyclerViewAdapter(this, mImageUrls_nowPlaying);
        recyclerViewH3.setAdapter(adapterH3);

        LinearLayoutManager layoutManagerH4 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerViewH4 = findViewById(R.id.recyclerView_H4);
        recyclerViewH4.setLayoutManager(layoutManagerH4);
        RecyclerViewAdapter adapterH4 = new RecyclerViewAdapter(this, mImageUrls_nowPlaying);
        recyclerViewH4.setAdapter(adapterH4);

        /*
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerViewV = findViewById(R.id.recyclerView_V);
        recyclerViewV.setLayoutManager(layoutManagerV);
        RecyclerViewAdapter adapterV = new RecyclerViewAdapter(this, mImageUrls_upcoming);
        recyclerViewV.setAdapter(adapterV);
*/
    }

}
