package com.udacity.android.famousmovies.data.network;

import com.udacity.android.famousmovies.data.database.entity.Review;

import java.util.List;

public class ReviewResponse {
    private List<Review> results;

    public ReviewResponse() {
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReviewResponse{");
        sb.append("results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
