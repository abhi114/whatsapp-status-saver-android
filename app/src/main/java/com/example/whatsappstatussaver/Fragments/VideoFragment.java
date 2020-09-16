package com.example.whatsappstatussaver.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappstatussaver.Adapters.ImageAdapter;
import com.example.whatsappstatussaver.Adapters.VideoAdapter;
import com.example.whatsappstatussaver.Models.StatusModel;
import com.example.whatsappstatussaver.R;
import com.example.whatsappstatussaver.Utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {

    @BindView(R.id.recyclerViewVideo)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarVideo)
    ProgressBar progressBar;

    Handler handler = new Handler();
    ArrayList<StatusModel> videoModelArrayList;

    VideoAdapter videoAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        videoModelArrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3) );

        getStatusVideos();
    }

    private void getStatusVideos() {
        if(MyConstants.STATUS_DIRECTORY.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORY.listFiles();

                    if(statusFiles != null && statusFiles.length > 0){
                        Arrays.sort(statusFiles);

                        for(final File statusFile:statusFiles){
                            StatusModel statusModel = new StatusModel(statusFile,statusFile.getName(),statusFile.getAbsolutePath());

                            statusModel.setThumbnail(getThumbnail(statusModel));

                            if(statusModel.isVideo()){

                                videoModelArrayList.add(statusModel);

                            }
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                videoAdapter = new VideoAdapter(getContext(),videoModelArrayList,VideoFragment.this);
                                recyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            }
                        });


                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(),"dir not exist",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
            }).start();
        }
    }


    private Bitmap getThumbnail(StatusModel statusModel) {

        if(statusModel.isVideo())
        {
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
        }
        else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),MyConstants.THUMBSIZE,MyConstants.THUMBSIZE);

        }
    }
}
