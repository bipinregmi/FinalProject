package com.example.spotlight_movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        final Button review = findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CastActivity.this, ReviewActivity.class));

            }
        });

        final Button info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CastActivity.this, MovieActivity.class));

            }
        });

        final Button home2 = findViewById(R.id.home2);
        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CastActivity.this, HomeActivity.class));

            }
        });




    }





}
