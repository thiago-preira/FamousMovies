package com.udacity.android.famousmovies.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.network.ApiUtils;
import com.udacity.android.famousmovies.fragments.MovieDetailFragment;

import org.parceler.Parcels;

public class MovieDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        setUpToolbar();

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        getSupportActionBar().setTitle(movie.getTitle());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView appBar = (ImageView) findViewById(R.id.iv_app_bar);
        Picasso.with(getContext()).load(movie.getPoster(ApiUtils.ImageSize.W780)).into(appBar);

        if(savedInstanceState == null){
            MovieDetailFragment frag = new MovieDetailFragment();
            frag.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.movie_detail_fragment,frag)
                    .commit();
        }
    }
}
