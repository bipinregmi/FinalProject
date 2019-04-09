package com.example.spotlight_movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    //variables
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getImages();



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
