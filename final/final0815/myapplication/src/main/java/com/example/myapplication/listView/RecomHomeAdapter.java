package com.example.myapplication.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.RecomDataList;

import java.util.ArrayList;

public class RecomHomeAdapter extends BaseAdapter {
    ArrayList<RecomDataList> dataList;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;

    public RecomHomeAdapter(Context context, ArrayList<RecomDataList> data) {
        mContext = context;
        dataList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() { return dataList.size(); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecomDataList getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item_view, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView products_name = view.findViewById(R.id.name);
        TextView origin = view.findViewById(R.id.origin);
        TextView cat_or_dog=view.findViewById(R.id.cat_or_dog);
        TextView age = view.findViewById(R.id.age);
        TextView food_sort=view.findViewById(R.id.food_sort);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        products_name.setText(dataList.get(position).getProducts_name());
        origin.setText(dataList.get(position).getOrigin());
        cat_or_dog.setText(dataList.get(position).getCat_or_dog());
        age.setText(dataList.get(position).getAge_contraction());
        food_sort.setText(dataList.get(position).getFood_sort());
        if (cat_or_dog.getText().equals("강아지")){
            imageView.setImageResource(R.drawable.dog2);
        }else if (cat_or_dog.getText().equals("고양이")){
            imageView.setImageResource(R.drawable.cat);
        }

        return view;
    }
}

