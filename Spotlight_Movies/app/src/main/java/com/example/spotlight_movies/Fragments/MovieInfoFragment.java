package com.example.spotlight_movies.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotlight_movies.R;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */

public class MovieInfoFragment extends Fragment {

    TextView abouFilmTextView;
    TextView releasedTextView;
    TextView budgetTextView;
    TextView seeAlltextViewMovieInfofragment;
    TextView noReviewMovieTextView;
    TextView noSimilarMoviesTextView;
    TextView revenueTextView;
    RecyclerView similarMoviesRecyclerView;
    Context context;

    InfoAboutMovieFragmentListener infoAboutMovieFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        infoAboutMovieFragmentListener = (InfoAboutMovieFragmentListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface InfoAboutMovieFragmentListener {
        void onSeeAllSimilarMoviesClicked();
    }

    public void setInfoAboutMovieFragmentListener(InfoAboutMovieFragmentListener listener) {
        this.infoAboutMovieFragmentListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_info_movie, container, false);
        abouFilmTextView = (TextView) v.findViewById(R.id.aboutFilmTextView);
        releasedTextView = (TextView) v.findViewById(R.id.releasedTextView);
        budgetTextView = (TextView) v.findViewById(R.id.budgetTextView);
        noReviewMovieTextView = (TextView) v.findViewById(R.id.noReviewMovieTextView);
        revenueTextView = (TextView) v.findViewById(R.id.revenueTextView);
        noSimilarMoviesTextView = (TextView) v.findViewById(R.id.noSimilarMoviesTextView);
        similarMoviesRecyclerView = (RecyclerView) v.findViewById(R.id.similarMoviesRecyclerView);
        seeAlltextViewMovieInfofragment = (TextView) v.findViewById(R.id.seeAllTextViewMovieInfoFragment);
        seeAlltextViewMovieInfofragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoAboutMovieFragmentListener.onSeeAllSimilarMoviesClicked();
            }
        });

        return v;
    }


    public static MovieInfoFragment newInstance() {
        return new MovieInfoFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setUIArguments(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (args.getBoolean("INFO")) {
                    abouFilmTextView.setText(args.getString("OVERVIEW"));
                    String releaseDate = dateGenerator(args.getString("RELEASE_DATE"));
                    releasedTextView.setText(releaseDate);
                    String budget = "£" + String.valueOf(args.getLong("BUDGET"));
                    budgetTextView.setText(budget);
                    String revenue = "£" + String.valueOf(args.getLong("REVENUE"));
                    revenueTextView.setText(revenue);



                }

            }


        });
    }

    private String dateGenerator(String date) {
        if (date.length() == 9 || date.length() == 10) {
            String month = date.substring(5, 7);
            String ans = "";
            switch (month) {
                case "01":
                    ans = "January";
                    break;
                case "02":
                    ans = "February";
                    break;
                case "03":
                    ans = "March";
                    break;
                case "04":
                    ans = "April";
                    break;
                case "05":
                    ans = "May";
                    break;
                case "06":
                    ans = "June";
                    break;
                case "07":
                    ans = "July";
                    break;
                case "08":
                    ans = "August";
                    break;
                case "09":
                    ans = "September";
                    break;
                case "10":
                    ans = "October";
                    break;
                case "11":
                    ans = "November";
                    break;
                case "12":
                    ans = "December";
                    break;

            }
            return (ans + " " + date.substring(8, 10) + "," + " " + date.substring(0, 4));
        } else
            return "NA";
    }
}
