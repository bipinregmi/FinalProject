package com.example.spotlight_movies.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotlight_movies.AboutMovieActivity;
import com.example.spotlight_movies.Network.MovieResponse;
import com.example.spotlight_movies.R;
import com.example.spotlight_movies.utils.OnRecyclerViewitemClicklistener;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 *
 * Help From
 * https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
 * https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder
 */
public class RecyclerViewAdapter_Main extends RecyclerView.Adapter<RecyclerViewAdapter_Main.ViewHolder> implements OnRecyclerViewitemClicklistener {

    private MovieResponse[] mMovies;
    Context mContext;
    private RecyclerViewAdapter recyclerViewAdapter;


    public RecyclerViewAdapter_Main(MovieResponse[] movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_second, parent, false);
        return new ViewHolder(v);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mMovies != null && mMovies.length > position) {
            if (getItemViewType(position) == 0) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Popular Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    recyclerViewAdapter = new RecyclerViewAdapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 1) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Now Playing");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {


                        }
                    });
                    recyclerViewAdapter = new RecyclerViewAdapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 2) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Top Rated Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {


                        }
                    });
                    recyclerViewAdapter = new RecyclerViewAdapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }
            } else if (getItemViewType(position) == 3) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Upcoming Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {

                            //Listener for see all


                        }
                    });
                    recyclerViewAdapter = new RecyclerViewAdapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.length;
    }


    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRecyclerViewItemClicked(int verticalposition, int horizontalPosition, View view) {
        Intent intent = new Intent();
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName()).toBundle();

        intent.setClass(mContext, AboutMovieActivity.class);
        intent.putExtra("movie_id", mMovies[verticalposition].getMovies().get(horizontalPosition).getId());
        intent.putExtra("posterPath", mMovies[verticalposition].getMovies().get(horizontalPosition).getPosterPath());
        intent.putExtra("movieName", mMovies[verticalposition].getMovies().get(horizontalPosition).getTitle());
        mContext.startActivity(intent, bundle);


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieType;
        RecyclerView horizontalRecyclerView;
        View divider;
        TextView seeAlltextView;

        public ViewHolder(View itemView) {
            super(itemView);
            movieType = (TextView) itemView.findViewById(R.id.movieTypeTextView);
            seeAlltextView = (TextView) itemView.findViewById(R.id.seeAllTextView);
            horizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.activityMainRecyclerViewHorizontal);
            divider = (View) itemView.findViewById(R.id.activityMainDivider);

        }
    }


}