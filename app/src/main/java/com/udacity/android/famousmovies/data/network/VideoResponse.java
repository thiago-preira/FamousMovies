package com.udacity.android.famousmovies.data.network;

import com.udacity.android.famousmovies.data.database.entity.Video;

import java.util.List;

class VideoResponse {
    private List<Video> results;

    public VideoResponse() {
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VideoResponse{");
        sb.append("results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
