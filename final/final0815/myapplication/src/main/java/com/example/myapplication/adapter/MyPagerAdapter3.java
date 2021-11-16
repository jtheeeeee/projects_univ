package com.example.myapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.Fragment_6;
import com.example.myapplication.fragment.Fragment_7;


public class MyPagerAdapter3 extends FragmentPagerAdapter {

    int mNumOfTabs; //탭의 갯수

    public MyPagerAdapter3(FragmentManager fm, int numTabs) {
        super(fm,numTabs);
        this.mNumOfTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Fragment_6 tab6 = new Fragment_6();
                return tab6;
            case 1:
                Fragment_7 tab7 = new Fragment_7();
                return tab7;

            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}