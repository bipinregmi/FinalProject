package com.example.spotlight_movies.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotlight_movies.R;
import com.example.spotlight_movies.adapters.MovieCastAdapter;
import com.example.spotlight_movies.models.Cast;

import java.util.ArrayList;


/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class MovieCastFragment extends Fragment {
    RecyclerView recyclerView;
    MovieCastAdapter movieCastAdapter;
    ArrayList<Cast> movieCastMain;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_movies_cast, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.movieCastRecyclerview);
        return v;
    }

    public static MovieCastFragment newInstance() {
        return new MovieCastFragment();
    }

    public void setUIArguments(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieCastMain = (ArrayList<Cast>) args.getSerializable("MOVIE_CAST");

                movieCastAdapter = new MovieCastAdapter(movieCastMain, context);
                recyclerView.setAdapter(movieCastAdapter);

                LinearLayoutManager verticalManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(verticalManager);

            }


        });
    }
}