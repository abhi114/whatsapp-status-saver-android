package com.example.whatsappstatussaver.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsappstatussaver.Fragments.ImageFragment;
import com.example.whatsappstatussaver.Fragments.VideoFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private ImageFragment imageFragment;
    private VideoFragment videoFragment;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        imageFragment = new ImageFragment();
        videoFragment = new VideoFragment();

    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return imageFragment;
        }else {
            return videoFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Images";
        }else{
            return "Videos";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
