package com.udacity.android.famousmovies.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.data.database.entity.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private final Context mContext;
    private final List<Review> mReviews;


    public ReviewAdapter(Context context, List<Review> reviews) {
        this.mContext = context;
        this.mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_review, viewGroup, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int position) {
        Review review = mReviews.get(position);
        reviewViewHolder.mAuthorTextView.setText(review.getAuthor());
        reviewViewHolder.mContentTextView.setText(Html.fromHtml(review.getContent()));
    }

    @Override
    public int getItemCount() {
        if (mReviews == null)
            return 0;
        return mReviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView mAuthorTextView;
        TextView mContentTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthorTextView = itemView.findViewById(R.id.tv_movie_review_author);
            mContentTextView = itemView.findViewById(R.id.tv_movie_review_content);
        }
    }

}
