package com.udacity.android.famousmovies.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.udacity.android.famousmovies.data.database.entity.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> loadFavoriteMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Movie> movies);

    @Insert
    void save(Movie movie);

    @Delete
    void remove(Movie movie);

    @Query("SELECT * FROM movies where id=:id")
    Movie loadFavoriteMovie(long id);

}
