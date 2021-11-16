package com.example.myapplication.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class IngredientAdapter extends BaseAdapter {
    ArrayList<IngredientList> dataList;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;

    public IngredientAdapter(Context context, ArrayList<IngredientList> data) {
        mContext = context;
        dataList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public IngredientList getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.com_list_item, null);
        TextView good_or_bad_tx= view.findViewById(R.id.good_or_bad_tx);
        TextView ingredient_tx=view.findViewById(R.id.ingredient_tx);
        good_or_bad_tx.setText(dataList.get(position).getGood_or_bad());
        ingredient_tx.setText(dataList.get(position).getName());

        return view;
    }
}

