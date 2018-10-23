package com.udacity.android.famousmovies.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(
        tableName = "reviews",
        foreignKeys = @ForeignKey(entity = Movie.class,
                parentColumns = "id",
                childColumns = "movieId",
                onDelete = CASCADE)
)
public class Review {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String author;

    @NonNull
    private String content;

    @NonNull
    private long movieId;

    public Review() {
    }

    public Review(@NonNull String id, @NonNull String author, @NonNull String content, @NonNull long movieId) {
        this.id = id;
        this.author = author;
        this.content = content;
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
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull long movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Review{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", movieId=").append(movieId);
        sb.append('}');
        return sb.toString();
    }
}
