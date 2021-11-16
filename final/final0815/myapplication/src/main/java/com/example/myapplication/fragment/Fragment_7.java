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
import com.example.myapplication.data.CatBrandData;
import com.example.myapplication.data.CatBrandResponse;
import com.example.myapplication.listView.CatBrandAdapter;
import com.example.myapplication.listView.CatBrandDataList;
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
public class Fragment_7 extends Fragment {
    ServiceApi service;
    ArrayList<CatBrandDataList> dataList;
    LayoutInflater mLayoutInflater = null;
    ListView listView;
    public Fragment_7() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_7, null);
        listView = view.findViewById(R.id.listView);
        dataList = new ArrayList<CatBrandDataList>();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        String str = "";
        int renum = dataList.size();

        startSearch(new CatBrandData(str));

        return view;
//        return inflater.inflate(R.layout.fragment_fragment_1, container,false);

    }


    private void startSearch(CatBrandData data) {
        service.brandWord(data).enqueue(new Callback<CatBrandResponse>() {
            @Override
            public void onResponse(Call<CatBrandResponse> call, Response<CatBrandResponse> response) {
                CatBrandResponse result = response.body();
                List<CatBrandResponse.Datum3> Datum3List = result.datas;
                dataList = new ArrayList<CatBrandDataList>();
                for (CatBrandResponse.Datum3 Datum3 : Datum3List) {
                    Log.d("Tab", Datum3.manufacturer_contraction);
                    dataList.add(new CatBrandDataList(Datum3.manufacturer_contraction));
                }
                CatBrandAdapter adapter = new CatBrandAdapter(getContext(), dataList);
                adapter.notifyDataSetChanged();

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String manufacturer_contraction = String.valueOf(dataList.get(position).getManufacturer_contraction());
                        String cat_or_dog="고양이";
                        Intent intent= new Intent(getContext(), BrandListActivity.class);
                        intent.putExtra("manufacturer_contraction",manufacturer_contraction);
                        intent.putExtra("cat_or_dog",cat_or_dog);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<CatBrandResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });


    }
}

