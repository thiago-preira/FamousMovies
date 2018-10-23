package com.udacity.android.famousmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.data.database.entity.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final Context mContext;
    private final List<Video> mVideos;
    private final VideoOnClickListener videoOnClickListener;

    public VideoAdapter(Context context, List<Video> videos, VideoOnClickListener movieOnClickListener) {
        this.mContext = context;
        this.mVideos = videos;
        this.videoOnClickListener = movieOnClickListener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_video, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int position) {
        Video video = mVideos.get(position);
        Picasso.with(mContext)
                .load(video.getThumbnail())
                .into(videoViewHolder.mVideoThumbnailImageView);
        if (videoOnClickListener != null) {
            videoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoOnClickListener.onVideoClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mVideos == null)
            return 0;
        return mVideos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView mVideoThumbnailImageView;

        public VideoViewHolder(@NonNull View view) {
            super(view);
            mVideoThumbnailImageView = view.findViewById(R.id.iv_movie_video_thumbnail);
        }

    }


    public interface VideoOnClickListener {
        void onVideoClick(View view, int position);
    }
}
