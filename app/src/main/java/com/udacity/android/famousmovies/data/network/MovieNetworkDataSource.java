package com.udacity.android.famousmovies.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.database.entity.Review;
import com.udacity.android.famousmovies.data.database.entity.Video;
import com.udacity.android.famousmovies.utils.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetworkDataSource {

    private static final String TAG = MovieNetworkDataSource.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static MovieNetworkDataSource sInstance;
    private final Context mContext;


    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<List<Movie>> mDownloadedMovies;
    private final AppExecutors mExecutors;

    private MovieNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedMovies = new MutableLiveData<>();
    }

    public static MovieNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MovieNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return fetchMovies(ApiUtils.POPULAR_MOVIES_FILTER);
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return fetchMovies(ApiUtils.TOP_RATED_MOVIES_FILTER);
    }

    private LiveData<List<Movie>> fetchMovies(final String filter) {
        MovieWebservice client = ApiUtils.Client.getInstance();
        MutableLiveData<List<Movie>> result = new MutableLiveData<>();
        mExecutors.networkIO().execute(() -> {
            client.getMovies(filter).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: Data requested from network!");
                        result.postValue(response.body().getResults());
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: Failure to request data from network", t);
                }
            });
        });
        return result;
    }

    public LiveData<List<Video>> getVideosFromMovie(long movieId){
        MovieWebservice client = ApiUtils.Client.getInstance();
        MutableLiveData<List<Video>> result = new MutableLiveData<>();
        mExecutors.networkIO().execute(() -> {
            client.getVideos(movieId).enqueue(new Callback<VideoResponse>() {
                @Override
                public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: Data requested from network!");
                        result.postValue(response.body().getResults());
                    }
                }
                @Override
                public void onFailure(Call<VideoResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: Failure to request data from network", t);
                }
            });
        });
        return result;
    }

    public LiveData<List<Review>> getReviewsFromMovie(long movieId) {
        MovieWebservice client = ApiUtils.Client.getInstance();
        MutableLiveData<List<Review>> result = new MutableLiveData<>();
        mExecutors.networkIO().execute(() -> {
            client.getReviews(movieId).enqueue(new Callback<ReviewResponse>() {
                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: Data requested from network!");
                        result.postValue(response.body().getResults());
                    }
                }
                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: Failure to request data from network", t);
                }
            });
        });
        return result;
    }
}
