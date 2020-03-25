package com.example.emmproject.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments;

    public MainPageAdapter(@NonNull FragmentManager fm, int behavior,ArrayList<Fragment> fragments) {

         super(fm, behavior);
         this.fragments=fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }



    @Override
    public int getCount() {
        return fragments.size();
    }
}
