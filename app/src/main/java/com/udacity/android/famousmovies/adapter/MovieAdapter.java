package com.udacity.android.famousmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.network.ApiUtils;
import com.udacity.android.famousmovies.utils.DeviceUtils;

import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final List<Movie> mMovies;
    private int deviceDensity;
    private MovieOnClickListener movieOnClickListener;

    public MovieAdapter(Context context,List<Movie> movies, MovieOnClickListener movieOnClickListener){
        this.context = context;
        this.mMovies = movies;
        this.movieOnClickListener = movieOnClickListener;
        deviceDensity = DeviceUtils.getDeviceDensity(context);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Movie movie = mMovies.get(position);
        String imageSize = ApiUtils.ImageSize.getSizeByDensity(deviceDensity);
        movieViewHolder.mMoviePosterProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load(movie.getPoster(imageSize))
                .into(movieViewHolder.mMoviePosterImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        movieViewHolder.mMoviePosterProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        movieViewHolder.mMoviePosterProgressBar.setVisibility(View.GONE);
                    }
                });
        if(movieOnClickListener!=null){
            movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieOnClickListener.onMovieClick(movieViewHolder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mMovies==null) return 0;
        return mMovies.size();
    }

    class MovieViewHolder extends  RecyclerView.ViewHolder{
        ImageView mMoviePosterImageView;
        ProgressBar mMoviePosterProgressBar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoviePosterImageView = itemView.findViewById(R.id.iv_movie_poster);
            mMoviePosterProgressBar = itemView.findViewById(R.id.pb_movie_poster);
        }
    }

    public interface MovieOnClickListener{
        void onMovieClick(View view, int position);
    }
}
