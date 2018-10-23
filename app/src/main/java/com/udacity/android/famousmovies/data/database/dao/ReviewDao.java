package com.udacity.android.famousmovies.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.udacity.android.famousmovies.data.database.entity.Review;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Review> movies);

    @Query("SELECT * FROM reviews WHERE movieId=:movieId")
    LiveData<List<Review>> loadReviewsFromMovie(long movieId);
}
