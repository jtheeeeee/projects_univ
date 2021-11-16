package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.BrandSearchData;
import com.example.myapplication.data.BrandSearchResponse;
import com.example.myapplication.listView.HomeAdapter;
import com.example.myapplication.listView.HomeDataList;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandListActivity extends AppCompatActivity {

    TextView textView;
    ServiceApi service;
    ArrayList<HomeDataList> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String manufacturer_contraction = intent.getExtras().getString("manufacturer_contraction");
//        Log.d("TAG",searchWord);
//        textView.setText(manufacturer_contraction);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        String cat_or_dog=intent.getExtras().getString("cat_or_dog");
        startSearch(new BrandSearchData(manufacturer_contraction,cat_or_dog));

    }


    private void startSearch(BrandSearchData data) {
        service.brandSearchWord(data).enqueue(new Callback<BrandSearchResponse>() {
            @Override
            public void onResponse(Call<BrandSearchResponse> call, Response<BrandSearchResponse> response) {
                BrandSearchResponse result = response.body();
                List<BrandSearchResponse.Datum2> datumList = result.datas;
                dataList = new ArrayList<HomeDataList>();
                for (BrandSearchResponse.Datum2 datum : datumList) {
                    Log.d("brand", datum.products_name);
                    dataList.add(new HomeDataList(datum.products_id, datum.products_name, datum.origin, datum.cat_or_dog, datum.age_contraction, datum.food_sort, datum.price, datum.manufacturer_contraction, datum.animal_sort));
                }
                HomeAdapter adapter = new HomeAdapter(getApplicationContext(), dataList);
                adapter.notifyDataSetChanged();
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String products_id = String.valueOf(dataList.get(position).getProducts_id());
                        String price = String.valueOf(dataList.get(position).getPrice());
//                        Toast.makeText(BrandListActivity.this, products_id, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                        intent.putExtra("id", products_id);
                        intent.putExtra("name", dataList.get(position).getProducts_name());
                        intent.putExtra("age", dataList.get(position).getAge_contraction());
                        intent.putExtra("cat_or_dog", dataList.get(position).getCat_or_dog());
                        intent.putExtra("food_sort", dataList.get(position).getFood_sort());
                        intent.putExtra("origin", dataList.get(position).getOrigin());
                        intent.putExtra("manufacturer", dataList.get(position).getManufacturer_contraction());
                        intent.putExtra("animal_sort", dataList.get(position).getAnimal_sort());
                        intent.putExtra("price", price);
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onFailure(Call<BrandSearchResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
};


