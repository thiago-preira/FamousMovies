package com.udacity.android.famousmovies.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.android.famousmovies.data.MoviesRepository;

public class MovieDetailViewModelFactory  extends ViewModelProvider.NewInstanceFactory {


    private MoviesRepository mMoviesRepository;
    private long mMovieId;

    public MovieDetailViewModelFactory(MoviesRepository moviesRepository,long mMovieId) {
        this.mMoviesRepository = moviesRepository;
        this.mMovieId = mMovieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDetailViewModel(mMoviesRepository,mMovieId);
    }
}
