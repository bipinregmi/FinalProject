package com.example.spotlight_movies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.spotlight_movies.Fragments.FavFragment;
import com.example.spotlight_movies.Fragments.MoviesFragment;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
//The main page has fragments Movies and favourites...
public class MainFragmentPager extends FragmentPagerAdapter {
    public MainFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "Favorite";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;

            case 1:
                FavFragment favFragment = new FavFragment();
                return favFragment;

        }
        return null;
    }

}
