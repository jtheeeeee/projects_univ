package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.HomeSearchData;
import com.example.myapplication.data.HomeSearchResponse;
import com.example.myapplication.data.HomeSearchData;
import com.example.myapplication.listView.HomeAdapter;
import com.example.myapplication.listView.HomeDataList;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    TextView textView;
    ServiceApi service;
    ArrayList<HomeDataList> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String searchWord = intent.getExtras().getString("searchWord");

        service = RetrofitClient.getClient().create(ServiceApi.class);
        startSearch(new HomeSearchData(searchWord));

    }

    private void startSearch(HomeSearchData data) {
        service.homeSearchWord(data).enqueue(new Callback<HomeSearchResponse>() {
            @Override
            public void onResponse(Call<HomeSearchResponse> call, Response<HomeSearchResponse> response) {
                HomeSearchResponse result = response.body();
                List<HomeSearchResponse.Datum2> datumList = result.datas;
                dataList = new ArrayList<HomeDataList>();
                for (HomeSearchResponse.Datum2 datum : datumList) {
                    dataList.add(new HomeDataList(datum.products_id, datum.products_name, datum.origin, datum.cat_or_dog, datum.age_contraction, datum.food_sort, datum.price, datum.manufacturer_contraction, datum.animal_sort));
                }
                HomeAdapter adapter = new HomeAdapter(getApplicationContext(), dataList);
                adapter.notifyDataSetChanged();
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String products_id=String.valueOf(dataList.get(position).getProducts_id());
                        String price=String.valueOf(dataList.get(position).getPrice());
//                        Toast.makeText(ListActivity.this ,products_id,Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(), MainActivity3.class);
                        intent.putExtra("id",products_id);
                        intent.putExtra("name",dataList.get(position).getProducts_name());
                        intent.putExtra("age",dataList.get(position).getAge_contraction());
                        intent.putExtra("cat_or_dog",dataList.get(position).getCat_or_dog());
                        intent.putExtra("food_sort",dataList.get(position).getFood_sort());
                        intent.putExtra("origin",dataList.get(position).getOrigin());
                        intent.putExtra("manufacturer",dataList.get(position).getManufacturer_contraction());
                        intent.putExtra("animal_sort",dataList.get(position).getAnimal_sort());
                        intent.putExtra("price",price);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<HomeSearchResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });

    }
}