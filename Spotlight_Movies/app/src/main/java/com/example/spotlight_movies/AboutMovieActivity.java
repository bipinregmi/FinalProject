package com.example.spotlight_movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.spotlight_movies.Fragments.MovieCastFragment;
import com.example.spotlight_movies.Fragments.MovieInfoFragment;
import com.example.spotlight_movies.Network.AboutMovieResponse;
import com.example.spotlight_movies.Network.ApiFactory;
import com.example.spotlight_movies.Network.CastResponse;
import com.example.spotlight_movies.Network.ImageResponse;
import com.example.spotlight_movies.Network.MovieResponse;
import com.example.spotlight_movies.Network.URLConstants;
import com.example.spotlight_movies.adapters.BannerViewPagerAdapter;
import com.example.spotlight_movies.adapters.FragmentPager;
import com.example.spotlight_movies.models.BackdropImage;
import com.example.spotlight_movies.models.Cast;
import com.example.spotlight_movies.models.Genre;
import com.example.spotlight_movies.models.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 *
 *
 * Got Help From
 * https://guides.codepath.com/android/ViewPager-with-FragmentPagerAdapter
 * https://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter
 * more to include
 */
public class AboutMovieActivity extends AppCompatActivity implements MovieInfoFragment.InfoAboutMovieFragmentListener {


    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private ArrayList<String> allBannerImageFullLinks;
    ImageView poster;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private FragmentPager fragmentPager = new FragmentPager(getSupportFragmentManager());
    VideoView videoTrailerVideoView;
    TextView movieNameTextView;
    TextView genreTextView;
    TextView releaseDateTextView;
    TextView runTimeTextView;
    RadioGroup radioGroup;
    int movie_id;
    String movieName;
    ArrayList<Movie> mainSimilarMovies = new ArrayList<>();
    AboutMovieResponse aboutMovieResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie);
        setTitle("");

        Intent intent = getIntent();

        movie_id = intent.getIntExtra("movie_id", 0);
        final String posterPath = intent.getStringExtra("posterPath");
        movieName = intent.getStringExtra("movieName");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieInfoFragment movieInfoFragment = (MovieInfoFragment) fragmentPager.function(0);
        movieInfoFragment.setInfoAboutMovieFragmentListener(AboutMovieActivity.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        allBannerImageFullLinks = new ArrayList<String>();


        /*
      The  ViewPager  will host the section contents.
     */
        ViewPager mBannerViewPager = (ViewPager) findViewById(R.id.pager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        bannerViewPagerAdapter = new BannerViewPagerAdapter(this, allBannerImageFullLinks);
        mBannerViewPager.setAdapter(bannerViewPagerAdapter);



        poster = (ImageView) findViewById(R.id.posterWithBannerImageView);
        Picasso.with(this).load(URLConstants.IMAGE_BASE_URL + posterPath).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int color = palette.getDarkMutedColor(Color.parseColor("#424242"));
                        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
                        collapsingToolbarLayout.setBackgroundColor(color);
                        collapsingToolbarLayout.setContentScrimColor(color);
                        tabLayout.setBackgroundColor(palette.getMutedColor(Color.parseColor("#424242")));


                    }
                });

                poster.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        videoTrailerVideoView = (VideoView) findViewById(R.id.videoViewTrailer);

        movieNameTextView = (TextView) findViewById(R.id.nameTextView);
        movieNameTextView.setText(movieName);
        genreTextView = (TextView) findViewById(R.id.genreTextView);
        releaseDateTextView = (TextView) findViewById(R.id.releaseDateTextView);
        runTimeTextView = (TextView) findViewById(R.id.runTimeTextView);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.container);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentPager = new FragmentPager(getSupportFragmentManager());

        mViewPager.setAdapter(fragmentPager);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiFactory service = retrofit.create(ApiFactory.class);
        Call<ImageResponse> call = service.getBannerImages(movie_id, URLConstants.API_KEY);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ArrayList<BackdropImage> bannerImagesLinksList = response.body().getBannerImageLinks();
                if (bannerImagesLinksList == null) {
                    return;
                }
                for (int i = 0; i < bannerImagesLinksList.size(); i++) {
                    if (i < 8) {
                        allBannerImageFullLinks.add(URLConstants.BANNER_BASE_URL + bannerImagesLinksList.get(i).getBannerImageLink());
                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioGroup.addView(radioButton);
                    } else
                        break;
                }
                bannerViewPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });


        Call<AboutMovieResponse> call1 = service.getAboutMovie(movie_id, URLConstants.API_KEY, "videos");
        call1.enqueue(new Callback<AboutMovieResponse>() {
            @Override
            public void onResponse(Call<AboutMovieResponse> call, Response<AboutMovieResponse> response) {
                ArrayList<Genre> genres = response.body().getGenres();
                for (int i = 0; i < genres.size(); i++) {
                    if (i < genres.size() - 1)
                        genreTextView.append(genres.get(i).getName() + ", ");
                    else
                        genreTextView.append(genres.get(i).getName());
                }

                aboutMovieResponse = new AboutMovieResponse();

                aboutMovieResponse.setOverview(response.body().getOverview());
                aboutMovieResponse.setReleaseDate(response.body().getReleaseDate());
                aboutMovieResponse.setRunTimeOfMovie(response.body().getRunTimeOfMovie());
                aboutMovieResponse.setBudget(response.body().getBudget());
                aboutMovieResponse.setRevenue(response.body().getRevenue());
                aboutMovieResponse.setGenres(response.body().getGenres());


                if (aboutMovieResponse.getReleaseDate().length() >= 5)
                    releaseDateTextView.setText(aboutMovieResponse.getReleaseDate().substring(0, 4));
                runTimeTextView.setText(aboutMovieResponse.getRunTimeOfMovie() / 60 + "hrs " + aboutMovieResponse.getRunTimeOfMovie() % 60 + "mins");

                Bundle bundle = new Bundle();
                bundle.putBoolean("INFO", true);
                bundle.putString("OVERVIEW", aboutMovieResponse.getOverview());
                bundle.putString("RELEASE_DATE", aboutMovieResponse.getReleaseDate());
                bundle.putLong("BUDGET", aboutMovieResponse.getBudget());
                bundle.putLong("REVENUE", aboutMovieResponse.getRevenue());


                MovieInfoFragment obj1 = (MovieInfoFragment) fragmentPager.function(0);
                obj1.setUIArguments(bundle);

            }

            @Override
            public void onFailure(Call<AboutMovieResponse> call, Throwable t) {

            }
        });


        Call<MovieResponse> call2 = service.getSimilarMovies(movie_id, URLConstants.API_KEY, 1);
        call2.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> similarMoviesList = response.body().getMovies();
                if (similarMoviesList == null) {
                    return;
                }

                for (Movie object : similarMoviesList) {
                    mainSimilarMovies.add(object);
                }

                Bundle bundle = new Bundle();
                bundle.putBoolean("SIMILAR", true);
                bundle.putSerializable("SIMILAR_MOVIES", similarMoviesList);

                MovieInfoFragment obj1 = (MovieInfoFragment) fragmentPager.function(0);
                obj1.setUIArguments(bundle);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        Call<CastResponse> call3 = service.getCredits(movie_id, URLConstants.API_KEY);
        call3.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                ArrayList<Cast> movieCast = response.body().getCast();
                if (movieCast == null) {
                    return;
                }

                Bundle args = new Bundle();
                args.putSerializable("MOVIE_CAST", movieCast);
                MovieCastFragment obj = (MovieCastFragment) fragmentPager.function(1);
                obj.setUIArguments(args);

            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });
/*
        Call<MovieResponse> callTrailer = service.getTrailer(movie_id, URLConstants.API_KEY, 1);
        callTrailer.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> trailerList = response.body().getMovies();
                if (trailerList == null) {
                    return;
                }

                for (Movie object : trailerList) {
                    //videoTrailerVideoView.add(object);
                    return;
                }
/*
                Bundle bundle = new Bundle();
                bundle.putBoolean("SIMILAR", true);
                bundle.putSerializable("SIMILAR_MOVIES", trailerList);

                MovieInfoFragment obj1 = (MovieInfoFragment) fragmentPager.function(0);
                obj1.setUIArguments(bundle);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSeeAllSimilarMoviesClicked() {
        Intent intent = new Intent();
        intent.putExtra("movie_id", movie_id);
        intent.putExtra("movieName", movieName);
        startActivity(intent);

    }
}


