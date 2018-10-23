package com.udacity.android.famousmovies.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieWebservice {


    @GET("{filter}")
    Call<MovieResponse> getMovies(@Path("filter")String filter);

    @GET("{movie_id}/videos")
    Call<VideoResponse> getVideos(@Path("movie_id")long movieId);

    @GET("{movie_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("movie_id")long movieId);

}
