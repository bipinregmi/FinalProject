package com.example.spotlight_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.spotlight_movies.Network.ApiInterface;
import com.example.spotlight_movies.Network.MovieResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    // API test variables
    public static String BASE_URL = "https://api.themoviedb.org/";
    public static int PAGE = 1;
    public static String API_KEY = "94f2d3081ba573d2f171f0f8020eb38a";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    private TextView myTextView;
    // end API test variables

    //variables
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myTextView = (TextView)findViewById(R.id.resultsTest);


        getImages();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call = myInterface.listOfMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                List<MovieResults.Result> listOfMovies = results.getResults();
                MovieResults.Result firstMovie = listOfMovies.get(0);

                myTextView.setText(firstMovie.getTitle());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }


    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");


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

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        LinearLayoutManager layoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerViewH = findViewById(R.id.recyclerView_H);
        recyclerViewH.setLayoutManager(layoutManagerH);
        RecyclerViewAdapter adapterH = new RecyclerViewAdapter(this, mImageUrls);
        recyclerViewH.setAdapter(adapterH);

        /*
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerViewV = findViewById(R.id.recyclerView_V);
        recyclerViewV.setLayoutManager(layoutManagerV);
        RecyclerViewAdapter adapterV = new RecyclerViewAdapter(this, mImageUrls);
        recyclerViewV.setAdapter(adapterV);
        */
    }

}
