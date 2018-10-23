package com.udacity.android.famousmovies.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.udacity.android.famousmovies.data.database.entity.Video;

import java.util.List;

@Dao
public interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Video> movies);

    @Query("SELECT * FROM videos WHERE movieId=:movieId")
    LiveData<List<Video>> loadVideosFromMovie(long movieId);
}
