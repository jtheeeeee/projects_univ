package com.example.myapplication.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.myapplication.R;
import com.example.myapplication.adapter.MyPagerAdapter3;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_3 extends Fragment {


    public Fragment_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_3,null);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("강아지"));
        tabs.addTab(tabs.newTab().setText("고양이"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);
        //Adapter
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        final MyPagerAdapter3 myPagerAdapter = new MyPagerAdapter3(getChildFragmentManager(), 2);
        viewPager.setAdapter(myPagerAdapter);

        //탭 선택 이벤트
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        return view;
    }



}
