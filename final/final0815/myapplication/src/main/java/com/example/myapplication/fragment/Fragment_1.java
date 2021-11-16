package com.example.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.myapplication.HomeListActivity;
import com.example.myapplication.ListActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.HomeSearchData;
import com.example.myapplication.data.HomeSearchResponse;
import com.example.myapplication.listView.HomeDataList;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_1 extends Fragment {
    EditText editText;
    Button btn;
    public Fragment_1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_1,null);

        btn= view.findViewById(R.id.search_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = view.findViewById(R.id.ediText);
                Intent intent = new Intent(getContext(), ListActivity.class);
                intent.putExtra("searchWord", editText.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

}
