package com.example.spotlight_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {
    private static final String NAME_KEY = "MoviesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

    }
}
