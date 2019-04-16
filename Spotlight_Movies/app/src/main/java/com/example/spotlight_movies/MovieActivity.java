package com.example.spotlight_movies;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.VideoView;

public class MovieActivity extends AppCompatActivity {
    private static final String NAME_KEY = "MoviesActivity";
    private String path ="https://www.youtube.com/watch?v=zK0LNzU2TQI";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

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
        });

        /*
        System.out.println("TESTING MOVIE API...");
        MovieAPIHelper apiHelper = new MovieAPIHelper();
        String testResult = apiHelper.searchMovies("jaws");
        System.out.println(testResult);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_menu, menu);
        return true;
    }

}
