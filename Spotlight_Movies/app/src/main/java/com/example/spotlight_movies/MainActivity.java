package com.example.spotlight_movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.spotlight_movies.adapters.MainFragmentPager;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    View translucentView;
    FloatingActionButton SearchButton;


    /**
     * The Viewpager will host the section contents.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("SPOTLIGHT MOVIES");

        translucentView = (View) findViewById(R.id.translucentView);

        SearchButton = (FloatingActionButton) findViewById(R.id.searchFabButton);


        TabLayout mainTabLayout = (TabLayout) findViewById(R.id.mainTabs);

        mainTabLayout.addTab(mainTabLayout.newTab());
        mainTabLayout.addTab(mainTabLayout.newTab());


        MainFragmentPager mainFragmentPager = new MainFragmentPager(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mainFragmentPager);
        mainTabLayout.setupWithViewPager(mViewPager);

        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Here is the on click listener for search
        SearchButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);

            }
        });


    }


}
