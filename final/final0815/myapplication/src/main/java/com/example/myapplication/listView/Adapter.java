package com.example.myapplication.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter {
    ArrayList<DataList> dataList;
    public Adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int pos = position;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_item_view, parent, false);

        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
//        TextView id = convertView.findViewById(R.id.id);
        TextView name = convertView.findViewById(R.id.name);


        DataList list_view_item = (DataList) getItem(position);

        imageView.setImageResource(R.drawable.ic_launcher_background);
//        id.setText(Integer.toString(list_view_item.getId()));
        name.setText(list_view_item.getName());;

        return convertView;
    }


}
