package com.udacity.android.famousmovies.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;
import java.util.Objects;

@Parcel
@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    @NonNull
    private long id;

    @SerializedName("vote_count")
    @NonNull
    private Integer voteCount;

    @NonNull
    private boolean video;

    @SerializedName("vote_average")
    @NonNull
    private float voteAverage;

    @NonNull
    private String title;

    @NonNull
    private double popularity;

    @SerializedName("poster_path")
    @NonNull
    private String posterPath;

    @SerializedName("original_language")
    @NonNull
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("backdropPath")
    private String backdropPath;

    @NonNull
    private String overview;

    @SerializedName("release_date")
    @NonNull
    private Date releaseDate;

    public Movie() {
    }

    public Movie(@NonNull long id, @NonNull Integer voteCount, @NonNull boolean video, @NonNull float voteAverage, @NonNull String title, @NonNull double popularity, @NonNull String posterPath, @NonNull String originalLanguage, String originalTitle, String backdropPath, @NonNull String overview, @NonNull Date releaseDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(@NonNull Integer voteCount) {
        this.voteCount = voteCount;
    }

    @NonNull
    public boolean isVideo() {
        return video;
    }

    public void setVideo(@NonNull boolean video) {
        this.video = video;
    }

    @NonNull
    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(@NonNull float voteAverage) {
        this.voteAverage = voteAverage;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(@NonNull double popularity) {
        this.popularity = popularity;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@NonNull String posterPath) {
        this.posterPath = posterPath;
    }

    @NonNull
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(@NonNull String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @NonNull
    public String getOverview() {
        return overview;
    }

    public void setOverview(@NonNull String overview) {
        this.overview = overview;
    }

    @NonNull
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NonNull Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("id=").append(id);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", video=").append(video);
        sb.append(", voteAverage=").append(voteAverage);
        sb.append(", title='").append(title).append('\'');
        sb.append(", popularity=").append(popularity);
        sb.append(", posterPath='").append(posterPath).append('\'');
        sb.append(", originalLanguage='").append(originalLanguage).append('\'');
        sb.append(", originalTitle='").append(originalTitle).append('\'');
        sb.append(", backdropPath='").append(backdropPath).append('\'');
        sb.append(", overview='").append(overview).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append('}');
        return sb.toString();
    }

    public String getPoster(String size){
        return String.format("http://image.tmdb.org/t/p/%s/%s",size,posterPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

}
