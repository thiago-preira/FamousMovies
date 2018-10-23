package com.udacity.android.famousmovies.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.udacity.android.famousmovies.data.MoviesRepository;
import com.udacity.android.famousmovies.data.database.entity.Movie;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    private static final String TAG = MoviesViewModel.class.getSimpleName();

    private final MoviesRepository mMoviesRepository;
    private LiveData<List<Movie>> mPopularMovies;
    private LiveData<List<Movie>> mTopRatedMovies;
    private LiveData<List<Movie>> mFavoriteMovies;

    public MoviesViewModel(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
        mPopularMovies = mMoviesRepository.getPopularMovies();
        mTopRatedMovies = mMoviesRepository.getTopRatedMovies();
        mFavoriteMovies = mMoviesRepository.getFavoriteMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        Log.d(TAG, "getFavoriteMovies");
        return mFavoriteMovies;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        Log.d(TAG, "getPopularMovies");
        return mPopularMovies;
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        Log.d(TAG, "getTopRatedMovies");
        return mTopRatedMovies;
    }
}
