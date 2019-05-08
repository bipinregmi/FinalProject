package com.example.spotlight_movies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

import java.lang.reflect.Method;
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

@SuppressLint("SetJavaScriptEnabled")
public class AboutMovieActivity extends AppCompatActivity implements MovieInfoFragment.InfoAboutMovieFragmentListener {
/*
    private VideoView videoView;
    private MediaController mediaController;
    String TAG = "VideoPlayer";
*//*
    private WebView mWebView;
    private boolean mIsPaused = false;*/


    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private ArrayList<String> allBannerImageFullLinks;
    ImageView poster;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private FragmentPager fragmentPager = new FragmentPager(getSupportFragmentManager());
    TextView movieNameTextView;
    TextView genreTextView;
    TextView releaseDateTextView;
    TextView runTimeTextView;
    RadioGroup radioGroup;
    int movie_id;
    String movieName;
    ArrayList<Movie> mainSimilarMovies = new ArrayList<>();
    AboutMovieResponse aboutMovieResponse;

    String myVideoYoutubeId = "eOrNdBpGMv8";

    private WebView webView;
    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;
    private myWebChromeClient mWebChromeClient;
    private myWebViewClient mWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie);
        //configureVideoView();
        setTitle("");


        String media_url = "https://www.youtube.com/embed/" + myVideoYoutubeId;
/*
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebChromeClient(new WebChromeClient());


        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);

        mIsPaused = true;
        resumeBrowser();
        mWebView.loadUrl(media_url);*/

        customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);
        webView = (WebView) findViewById(R.id.webView);

        mWebViewClient = new myWebViewClient();
        webView.setWebViewClient(mWebViewClient);

        mWebChromeClient = new myWebChromeClient();
        webView.setWebChromeClient(mWebChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSaveFormData(true);
        webView.loadUrl(media_url);




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
                aboutMovieResponse.setMovieKey(response.body().getMovieKey());


                if (aboutMovieResponse.getReleaseDate().length() >= 5)
                    releaseDateTextView.setText(aboutMovieResponse.getReleaseDate().substring(0, 4));
                runTimeTextView.setText(aboutMovieResponse.getRunTimeOfMovie() / 60 + "hrs " + aboutMovieResponse.getRunTimeOfMovie() % 60 + "mins");

                Bundle bundle = new Bundle();
                bundle.putBoolean("INFO", true);
                bundle.putString("OVERVIEW", aboutMovieResponse.getOverview());
                bundle.putString("RELEASE_DATE", aboutMovieResponse.getReleaseDate());
                bundle.putLong("BUDGET", aboutMovieResponse.getBudget());
                bundle.putLong("REVENUE", aboutMovieResponse.getRevenue());

                //myVideoYoutubeId = aboutMovieResponse.getMovieKey();


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

    }


/*

    @Override
    protected void onPause()
    {
        pauseBrowser();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        resumeBrowser();
        super.onResume();
    }

    private void pauseBrowser()
    {
        if (!mIsPaused)
        {
            // pause flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onPause");
            mWebView.pauseTimers();
            mIsPaused = true;
        }
    }

    private void resumeBrowser()
    {
        if (mIsPaused)
        {
            // resume flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onResume");
            mWebView.resumeTimers();
            mIsPaused = false;
        }
    }

    private void callHiddenWebViewMethod(final WebView wv, final String name)
    {
        try
        {
            final Method method = WebView.class.getMethod(name);
            method.invoke(mWebView);
        } catch (final Exception e)
        {}
    }

*/

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



    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        if (inCustomView()) {
            hideCustomView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (inCustomView()) {
                hideCustomView();
                return true;
            }

            if ((mCustomView == null) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    class myWebChromeClient extends WebChromeClient {
        private Bitmap mDefaultVideoPoster;
        private View mVideoProgressView;

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            onShowCustomView(view, callback);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onShowCustomView(View view,CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            webView.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }

        @Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(AboutMovieActivity.this);
                mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
            }
            return mVideoProgressView;
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();    //To change body of overridden methods use File | Settings | File Templates.
            if (mCustomView == null)
                return;

            webView.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);

            // Remove the custom view from its container.
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}


