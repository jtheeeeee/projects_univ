package com.example.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.BrandListActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.BrandData;
import com.example.myapplication.data.BrandResponse;
import com.example.myapplication.listView.BrandAdapter;
import com.example.myapplication.listView.BrandDataList;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_6 extends Fragment {
    ServiceApi service;
    ArrayList<BrandDataList> dataList;
    LayoutInflater mLayoutInflater = null;
    ListView listView;
    public Fragment_6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_6, null);
        listView = view.findViewById(R.id.listView);
        dataList = new ArrayList<BrandDataList>();
        service = RetrofitClient.getClient().create(ServiceApi.class);
        String str = "";
        int renum = dataList.size();

        startSearch(new BrandData(str));


        return view;
//        return inflater.inflate(R.layout.fragment_fragment_1, container,false);

    }


    private void startSearch(BrandData data) {
        service.brandWord(data).enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                BrandResponse result = response.body();
                List<BrandResponse.Datum> datumList = result.datas;
                dataList = new ArrayList<BrandDataList>();
                for (BrandResponse.Datum datum : datumList) {
                    Log.d("Tab", datum.manufacturer_contraction);
                    dataList.add(new BrandDataList(datum.manufacturer_contraction));
                }
                BrandAdapter adapter = new BrandAdapter(getContext(), dataList);
                adapter.notifyDataSetChanged();

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String manufacturer_contraction = String.valueOf(dataList.get(position).getManufacturer_contraction());
                        String cat_or_dog="강아지";
                        Intent intent= new Intent(getContext(), BrandListActivity.class);
                        intent.putExtra("manufacturer_contraction",manufacturer_contraction);
                        intent.putExtra("cat_or_dog",cat_or_dog);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
        }
        });


    }
}

