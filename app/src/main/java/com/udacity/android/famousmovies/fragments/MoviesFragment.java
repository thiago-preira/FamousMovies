package com.udacity.android.famousmovies.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.android.famousmovies.FamousMoviesApplication;
import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.adapter.MovieAdapter;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.network.ApiUtils;
import com.udacity.android.famousmovies.ui.activity.MovieDetailActivity;
import com.udacity.android.famousmovies.ui.viewmodel.MoviesViewModel;
import com.udacity.android.famousmovies.ui.viewmodel.MoviesViewModelFactory;
import com.udacity.android.famousmovies.utils.DeviceUtils;

import org.parceler.Parcels;

import java.util.List;

public class MoviesFragment extends BaseFragment {

    private static final String TAG = MoviesFragment.class.getSimpleName();

    RecyclerView mMovieRecyclerView;
    MovieAdapter mMovieAdapter;
    List<Movie> mMovies;

    private String mType;
    private static final String TYPE_PARAM = "type";
    private MoviesViewModel mViewModel;


    public static MoviesFragment newInstance(String type) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(TYPE_PARAM, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mType = getArguments().getString(TYPE_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        int mNoOfColumns = DeviceUtils.numberOfColumns(getActivity());
        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.rv_movies);
        mMovieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));
        mMovieRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MoviesViewModelFactory factory = FamousMoviesApplication.getInstance().provideMainActivityViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel.class);
        Log.d(TAG, "onActivityCreated: " + mType);
        loadMovies();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!DeviceUtils.hasInternet(getContext())) {
            snack(view, getString(R.string.no_internet));
        }
    }

    private void loadMovies() {
        switch (this.mType) {
            case ApiUtils
                    .POPULAR_MOVIES_FILTER:
                loadPopularMovies();
                break;
            case ApiUtils.TOP_RATED_MOVIES_FILTER:
                loadTopRatedMovies();
                break;
            case ApiUtils.FAVORITE_MOVIES_FILTER:
                loadFavoriteMovies();
                break;
        }
    }

    private void loadPopularMovies() {
        mViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                MoviesFragment.this.mMovies = movies;
                Log.d(TAG, "onChanged: data loaded " + movies);
                mMovieAdapter = new MovieAdapter(getContext(), movies, onMovieClick());
                mMovieRecyclerView.setAdapter(mMovieAdapter);
            }
        });
    }

    private void loadTopRatedMovies() {
        mViewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                MoviesFragment.this.mMovies = movies;
                Log.d(TAG, "onChanged: data loaded " + movies);
                mMovieAdapter = new MovieAdapter(getContext(), movies, onMovieClick());
                mMovieRecyclerView.setAdapter(mMovieAdapter);
            }
        });
    }

    private void loadFavoriteMovies() {
        mViewModel.getFavoriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                MoviesFragment.this.mMovies = movies;
                Log.d(TAG, "onChanged: data loaded " + movies);
                mMovieAdapter = new MovieAdapter(getContext(), movies, onMovieClick());
                mMovieRecyclerView.setAdapter(mMovieAdapter);
            }
        });
    }

    private MovieAdapter.MovieOnClickListener onMovieClick() {
        return new MovieAdapter.MovieOnClickListener() {
            @Override
            public void onMovieClick(View view, int position) {
                Movie movie = mMovies.get(position);
                Intent movieDetailIntent = new Intent(getContext(), MovieDetailActivity.class);
                movieDetailIntent.putExtra("movie", Parcels.wrap(movie));
                startActivity(movieDetailIntent);
            }
        };
    }

}
