package com.udacity.android.famousmovies.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.udacity.android.famousmovies.data.database.dao.MovieDao;
import com.udacity.android.famousmovies.data.database.dao.ReviewDao;
import com.udacity.android.famousmovies.data.database.dao.VideoDao;
import com.udacity.android.famousmovies.data.database.entity.Movie;
import com.udacity.android.famousmovies.data.database.entity.Review;
import com.udacity.android.famousmovies.data.database.entity.Video;

@Database(entities = {Movie.class,Video.class, Review.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase  extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "movies";
    private static final Object LOCK = new Object();
    private static AppDatabase sInstance;


    public static AppDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }



    public abstract MovieDao movieDao();

    public abstract VideoDao videoDao();

    public abstract ReviewDao reviewDao();

}
