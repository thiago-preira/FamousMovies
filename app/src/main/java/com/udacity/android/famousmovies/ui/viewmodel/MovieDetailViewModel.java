package com.udacity.android.famousmovies.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.udacity.android.famousmovies.data.MoviesRepository;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.database.entity.Review;
import com.udacity.android.famousmovies.data.database.entity.Video;

import java.util.List;

public class MovieDetailViewModel extends ViewModel {

    private static final String TAG = MovieDetailViewModel.class.getSimpleName();

    private MoviesRepository mMoviesRepository;
    private final LiveData<List<Video>> mVideos;
    private final LiveData<List<Review>> mReviews;
    private final long mMovieId;
    private boolean favorite;

    public MovieDetailViewModel(MoviesRepository moviesRepository, final long movieId) {
        Log.d(TAG, "MovieDetailViewModel: new instance created for id"+ movieId);
        this.mMovieId = movieId;
        this.mMoviesRepository = moviesRepository;
        this.mVideos = mMoviesRepository.getVideosFromMovie(mMovieId);
        this.mReviews = mMoviesRepository.getReviewsFromMovie(mMovieId);
    }

    public LiveData<List<Video>> getVideos() {
        return mVideos;
    }

    public LiveData<List<Review>> getReviews() {
        return mReviews;
    }

    public boolean isFavorite(Movie movie){
        return mMoviesRepository.isFavorite(movie);
    }

    public void saveAsFavorite(Movie movie){
        mMoviesRepository.saveAsFavorite(movie);
    }
}
