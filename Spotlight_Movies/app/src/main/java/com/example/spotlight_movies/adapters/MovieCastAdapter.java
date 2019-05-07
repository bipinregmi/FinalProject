package com.example.spotlight_movies.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotlight_movies.Network.URLConstants;
import com.example.spotlight_movies.R;
import com.example.spotlight_movies.models.Cast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */

//Here is the place where ViewHolder for the cast are populated into the Cast view

/**This method internally calls onBindViewHolder(ViewHolder, int) to update the RecyclerView.ViewHolder
 * contents with the item at the given position and also sets up some private fields to be used by RecyclerView.
 **/
public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.ViewHolder> {
    private ArrayList<Cast> mMovieCast;
    Context mContext;

    public MovieCastAdapter(ArrayList<Cast> movieCastMain, Context context) {
        mMovieCast = movieCastMain;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cast_list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mMovieCast != null) {
            holder.movieCastName.setText(mMovieCast.get(position).getName());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mMovieCast.get(position).getProfile_path()).into(holder.movieCastImage);
            String character = "as " + mMovieCast.get(position).getCharacter();
            holder.movieCastCharacter.setText(character);
        }

    }

    @Override
    public int getItemCount() {
        return mMovieCast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieCastImage;
        TextView movieCastName;
        TextView movieCastCharacter;
        View movieCastDivider;

        ViewHolder(View itemView) {
            super(itemView);
            movieCastImage = (ImageView) itemView.findViewById(R.id.movieCastImage);
            movieCastName = (TextView) itemView.findViewById(R.id.movieCastName);
            movieCastCharacter = (TextView) itemView.findViewById(R.id.moviCastCharacter);
            movieCastDivider = (View) itemView.findViewById(R.id.movieCastDivider);

        }
    }
}
