package com.example.spotlight_movies;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MovieActivity extends AppCompatActivity {
    /*
    private static final String NAME_KEY = "MoviesActivity";
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        final Button cast = findViewById(R.id.cast);
        cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieActivity.this, CastActivity.class));

            }
        });

        final Button review = findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieActivity.this, ReviewActivity.class));

            }
        });

        final Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieActivity.this, HomeActivity.class));

            }
        });

    }

/*

        Uri uri = Uri.parse(path);

        VideoView vv = (VideoView) findViewById(R.id.videoView);
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer arg0, int arg1, int arg2) {return false;}
        });
        try {vv.setVideoURI(uri);} catch (Exception e) {}
        try {vv.start();} catch (Exception e) {}
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setOnCompletionListener(null);
            }

        });*/

        /*
        System.out.println("TESTING MOVIE API...");
        MovieAPIHelper apiHelper = new MovieAPIHelper();
        String testResult = apiHelper.searchMovies("jaws");
        System.out.println(testResult);
        */

}
