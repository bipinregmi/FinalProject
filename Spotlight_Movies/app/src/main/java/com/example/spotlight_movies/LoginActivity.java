package com.example.spotlight_movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private CardView loginButton;
    private TextView sampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (CardView) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        sampleButton = (TextView) findViewById(R.id.sample);
        sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMovieActivity();
            }
        });
    }

    public void openHomeActivity(){
        Intent intent= new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void openMovieActivity(){
        Intent intent= new Intent(this,MovieActivity.class);
        startActivity(intent);
    }
}
