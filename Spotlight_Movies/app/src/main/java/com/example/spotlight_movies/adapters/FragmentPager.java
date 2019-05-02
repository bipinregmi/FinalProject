package com.example.spotlight_movies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.spotlight_movies.Fragments.MovieCastFragment;
import com.example.spotlight_movies.Fragments.MovieInfoFragment;

import java.util.HashMap;


/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */

/**
 * Help From
 * https://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter.html
 * https://developer.android.com/reference/java/util/HashMap
 * https://abhiandroid.com/java/hashmap
 */

//This is the adapter for the about movies which has 3 fragments (info, reviews and cast)

public class FragmentPager extends FragmentPagerAdapter {
    HashMap<Integer, Fragment> map = new HashMap<>();


    public FragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = MovieInfoFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }

            case 1:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = MovieCastFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }


            case 2:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = MovieCastFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }

        }
        return null;

    }



    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CAST";
            case 2:
                return "REVIEWS";
        }
        return null;
    }

    public Fragment function(int position) {

        Fragment fragment = map.get(position);
        if (fragment == null) {
            return getItem(position);
        } else {
            return fragment;
        }
    }
}
