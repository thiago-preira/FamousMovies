package com.udacity.android.famousmovies.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.famousmovies.FamousMoviesApplication;
import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.adapter.MovieAdapter;
import com.udacity.android.famousmovies.adapter.ReviewAdapter;
import com.udacity.android.famousmovies.adapter.VideoAdapter;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.database.entity.Review;
import com.udacity.android.famousmovies.data.database.entity.Video;
import com.udacity.android.famousmovies.data.network.ApiUtils;
import com.udacity.android.famousmovies.ui.activity.MovieDetailActivity;
import com.udacity.android.famousmovies.ui.viewmodel.MovieDetailViewModel;
import com.udacity.android.famousmovies.ui.viewmodel.MovieDetailViewModelFactory;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.List;


public class MovieDetailFragment extends BaseFragment {

    private static final String TAG = MovieDetailFragment.class.getSimpleName();

    private Movie mMovie;
    private VideoAdapter mVideoAdapter;
    private ReviewAdapter mReviewAdapter;
    private RecyclerView mVideoRecyclerView;
    private RecyclerView mReviewRecyclerView;
    private List<Video> mVideos;
    private MovieDetailViewModel mViewModel;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private FloatingActionButton mFavButton;
    private boolean mFavorite = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));

        TextView movieOverview = view.findViewById(R.id.tv_movie_detail_overview);
        movieOverview.setText(mMovie.getOverview());
        TextView movieReleaseDate = view.findViewById(R.id.tv_movie_detail_release_date);
        movieReleaseDate.setText(simpleDateFormat.format(mMovie.getReleaseDate()).substring(0, 4));
        TextView movieAverage = view.findViewById(R.id.tv_movie_detail_vote_average);
        movieAverage.setText(String.valueOf(mMovie.getVoteAverage()));

        mVideoRecyclerView = (RecyclerView) view.findViewById(R.id.rv_movie_videos);
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mReviewRecyclerView = (RecyclerView) view.findViewById(R.id.rv_movie_reviews);
        mReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mFavButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_favorite);
        mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAsFavorite();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: " + mMovie);
        MovieDetailViewModelFactory modelFactory = FamousMoviesApplication.getInstance().provideDetailActivityViewModelFactory(getContext(), mMovie.getId());
        mViewModel = ViewModelProviders.of(this, modelFactory).get(MovieDetailViewModel.class);
        loadVideos();
        loadReviews();
        mFavorite = mViewModel.isFavorite(mMovie);
        setFabColor(mFavorite);
    }


    private void loadReviews() {
        mViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                mReviewAdapter = new ReviewAdapter(getContext(), reviews);
                mReviewRecyclerView.setAdapter(mReviewAdapter);
            }
        });
    }

    private void loadVideos() {
        mViewModel.getVideos().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(@Nullable List<Video> videos) {
                MovieDetailFragment.this.mVideos = videos;
                mVideoAdapter = new VideoAdapter(getContext(), videos, onVideoClick());
                mVideoRecyclerView.setAdapter(mVideoAdapter);
            }
        });
    }

    private VideoAdapter.VideoOnClickListener onVideoClick() {
        return new VideoAdapter.VideoOnClickListener() {
            @Override
            public void onVideoClick(View view, int position) {
                Video video = mVideos.get(position);
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(video.getUrl())));

            }
        };
    }

    private void saveAsFavorite() {
        mViewModel.saveAsFavorite(mMovie);
        mFavorite=!mFavorite;
        setFabColor(mFavorite);
    }

    private void setFabColor(Boolean isFavorite) {
        if (isFavorite) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mFavButton.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.colorAccent));
            }
            mFavButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.white));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mFavButton.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.colorPrimaryDark));
            }
            mFavButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.white));
        }
    }

}
