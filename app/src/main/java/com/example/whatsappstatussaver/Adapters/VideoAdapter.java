package com.example.whatsappstatussaver.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappstatussaver.Fragments.ImageFragment;
import com.example.whatsappstatussaver.Fragments.VideoFragment;
import com.example.whatsappstatussaver.Models.StatusModel;
import com.example.whatsappstatussaver.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    private final List<StatusModel> videosList;
    Context context;
    VideoFragment videoFragment;


    public VideoAdapter(Context context, List<StatusModel> videosList, VideoFragment videoFragment){
        this.context = context;
        this.videoFragment = videoFragment;
        this.videosList = videosList;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_status,viewGroup,false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        StatusModel statusModel = videosList.get(position);
        holder.ivThumbnailImageView.setImageBitmap(statusModel.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivThumbnail)
        ImageView ivThumbnailImageView;
        @BindView(R.id.ibSaveToGallery)
        ImageButton imageButtonDownload;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
