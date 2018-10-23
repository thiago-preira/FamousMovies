package com.udacity.android.famousmovies.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.udacity.android.famousmovies.data.database.dao.MovieDao;
import com.udacity.android.famousmovies.data.database.dao.ReviewDao;
import com.udacity.android.famousmovies.data.database.dao.VideoDao;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.database.entity.Review;
import com.udacity.android.famousmovies.data.database.entity.Video;
import com.udacity.android.famousmovies.data.network.MovieNetworkDataSource;
import com.udacity.android.famousmovies.utils.AppExecutors;

import java.util.List;


public class MoviesRepository {

    private static final String TAG = MoviesRepository.class.getSimpleName();

    private final MovieNetworkDataSource mMovieNetworkDataSource;
    private final MovieDao mMovieDao;
    private final VideoDao mVideoDao;
    private final ReviewDao mReviewDao;
    private final AppExecutors mExecutor;

    private LiveData<List<Movie>> mFavoriteMovies;

    private static final Object LOCK = new Object();
    private static MoviesRepository sInstance;

    private MoviesRepository(MovieNetworkDataSource mMovieNetworkDataSource,
                             MovieDao movieDao,
                             VideoDao videoDao,
                             ReviewDao reviewDao,
                             AppExecutors mExecutor) {
        this.mMovieNetworkDataSource = mMovieNetworkDataSource;
        this.mMovieDao = movieDao;
        this.mVideoDao = videoDao;
        this.mReviewDao = reviewDao;
        this.mExecutor = mExecutor;
        this.mFavoriteMovies = mMovieDao.loadFavoriteMovies();
    }

    public synchronized static MoviesRepository getInstance(MovieNetworkDataSource mMovieNetworkDataSource,
                                                            MovieDao movieDao,
                                                            VideoDao videoDao,
                                                            ReviewDao reviewDao,
                                                            AppExecutors mExecutor) {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MoviesRepository(mMovieNetworkDataSource, movieDao,
                        videoDao,
                        reviewDao,
                        mExecutor);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return mMovieNetworkDataSource.getPopularMovies();
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return mMovieNetworkDataSource.getTopRatedMovies();
    }


    public LiveData<List<Video>> getVideosFromMovie(long movieId) {
        return mMovieNetworkDataSource.getVideosFromMovie(movieId);
    }

    private void deleteOldData() {

    }


    public LiveData<List<Review>> getReviewsFromMovie(long movieId) {
        return mMovieNetworkDataSource.getReviewsFromMovie(movieId);
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return mFavoriteMovies;
    }

    public boolean isFavorite(Movie movie) {
        return mFavoriteMovies.getValue().contains(movie);
    }

    public void saveAsFavorite(Movie movie) {
        mExecutor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Movie favoriteMovie = mMovieDao.loadFavoriteMovie(movie.getId());
                if (favoriteMovie != null) {
                    mMovieDao.remove(movie);
                } else {
                    mMovieDao.save(movie);
                }
            }
        });

    }

}
