package com.udacity.android.famousmovies.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.android.famousmovies.data.MoviesRepository;

public class MoviesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MoviesRepository mMoviesRepository;

    public MoviesViewModelFactory(MoviesRepository moviesRepository) {
        this.mMoviesRepository = moviesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MoviesViewModel(mMoviesRepository);
    }
}
