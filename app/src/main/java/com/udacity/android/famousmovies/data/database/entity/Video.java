package com.udacity.android.famousmovies.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.parceler.Parcel;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(
        tableName = "videos",
        foreignKeys = @ForeignKey(entity = Movie.class,
                parentColumns = "id",
                childColumns = "movieId",
                onDelete = CASCADE)
)
public class Video {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String key;

    @NonNull
    private long movieId;

    public Video() {
    }

    public Video(String id, String key, final long movieId) {
        this.id = id;
        this.key = key;
        this.movieId = movieId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getUrl(){
        return String.format("http://www.youtube.com/watch?v=%s",key);
    }

    @NonNull
    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull long movieId) {
        this.movieId = movieId;
    }

    public String getThumbnail(){
        return String.format("https://img.youtube.com/vi/%s/0.jpg",key);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Video{");
        sb.append("id='").append(id).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
