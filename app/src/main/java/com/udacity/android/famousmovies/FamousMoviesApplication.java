package com.udacity.android.famousmovies;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.udacity.android.famousmovies.data.MoviesRepository;
import com.udacity.android.famousmovies.data.database.AppDatabase;
import com.udacity.android.famousmovies.data.network.MovieNetworkDataSource;
import com.udacity.android.famousmovies.ui.viewmodel.MovieDetailViewModelFactory;
import com.udacity.android.famousmovies.ui.viewmodel.MoviesViewModelFactory;
import com.udacity.android.famousmovies.utils.AppExecutors;

public class FamousMoviesApplication extends Application {
    private static final String TAG = FamousMoviesApplication.class.getSimpleName();
    private static FamousMoviesApplication instance = null;

    public static FamousMoviesApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "FamousMoviesApplication.onCreate()");
        instance = this;
    }


    public MoviesRepository provideRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        MovieNetworkDataSource networkDataSource =
                MovieNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return MoviesRepository.getInstance(networkDataSource, database.movieDao(),
                database.videoDao(), database.reviewDao(), executors);
    }

    public MovieNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return MovieNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public MoviesViewModelFactory provideMainActivityViewModelFactory(Context context) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());
        return new MoviesViewModelFactory(repository);
    }


    public MovieDetailViewModelFactory provideDetailActivityViewModelFactory(Context context, long movieId) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());
        return new MovieDetailViewModelFactory(repository, movieId);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "FamousMoviesApplication.onTerminate()");
    }


}
