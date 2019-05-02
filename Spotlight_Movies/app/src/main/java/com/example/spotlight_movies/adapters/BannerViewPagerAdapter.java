package com.example.spotlight_movies.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.spotlight_movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */

/**
 *  GOT HELP FROM
 * https://gist.github.com/makunomark/c155e2a9cb63fb839db790422a01709a
 * https://developer.android.com/reference/android/support/v4/view/PagerAdapter
 */
public class BannerViewPagerAdapter extends PagerAdapter {

    Context mContext;
    ArrayList<String> mAllBannerImageFullLinks;

    public BannerViewPagerAdapter(Context context, ArrayList<String> allBannerImageFullLinks) {
        mContext = context;
        mAllBannerImageFullLinks = allBannerImageFullLinks;

    }

    @Override
    public int getCount() {
        return mAllBannerImageFullLinks.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_image_view_layout, container, false);
        ImageView bannerImage = (ImageView) v.findViewById(R.id.bannerImage);
        Picasso.with(mContext).load(mAllBannerImageFullLinks.get(position)).into(bannerImage);
        container.addView(v);
        return v;

    }
}

