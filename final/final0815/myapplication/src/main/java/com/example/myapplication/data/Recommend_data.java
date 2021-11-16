package com.example.myapplication.data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.HomeListActivity;
import com.example.myapplication.MainActivity3;
import com.example.myapplication.R;
import com.example.myapplication.listView.HomeAdapter;
import com.example.myapplication.listView.RecomHomeAdapter;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommend_data extends AppCompatActivity {

    ArrayList<RecomDataList> dataList;
    TextView textView;
    ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        dataList = new ArrayList<RecomDataList>();
        textView = findViewById(R.id.textView);
        Intent it_1 = getIntent();
        service = RetrofitClient.getClient().create(ServiceApi.class);


        String age = it_1.getStringExtra("pet_age"); // recommend_table_fin.age_type
        String type = it_1.getStringExtra("food_type"); // recommend_table_fin.food_type
        String capacity = it_1.getStringExtra("capacity"); // recommend_detail.detail
        String dog_or_cat = it_1.getStringExtra("pet_type"); // recommend_table_fin.dog_cat_sort
        String allergy = it_1.getStringExtra("allergy");
        Log.d("allergy",allergy);


        //int age_int = Integer.parseInt(age); // 칼럼 인트화
        //int type_int = Integer.parseInt(type); // 칼럼 인트화
        //int capacity_int = Integer.parseInt(capacity); // 칼럼 인트화
        //int dog_or_cat_int = Integer.parseInt(dog_or_cat); // 칼럼 인트화
        String recomWord = dog_or_cat;
        String ageWord = age;
        String alrWord = allergy;
        String typeWord = type;
        String capacityWord = capacity;
        startSearch(new RecomData(recomWord,ageWord,alrWord,typeWord,capacityWord));
    }

private void startSearch(RecomData data) {
    service.recomWord(data).enqueue(new Callback<RecomResponse>() {
        @Override
        public void onResponse(Call<RecomResponse> call, Response<RecomResponse> response) {
            RecomResponse result = response.body();
            List<RecomResponse.Datum123> datumList = (result.data);
            // ArrayList<RecomDataList> dataList = new ArrayList(result.data);
            dataList = new ArrayList<RecomDataList>();
            //int renum = datumList.size();
            for (RecomResponse.Datum123 datum : datumList) {
                dataList.add(new RecomDataList(datum.products_id, datum.products_name, datum.origin, datum.cat_or_dog, datum.age_contraction, datum.food_sort, datum.price, datum.manufacturer_contraction, datum.animal_sort));
            }
            // textView.setText(displayResponse);
            //Log.d("TAG", datumList.getClass().getName());
            //int size = dataList.size();
            //Log.d("TAG", String.valueOf(renum));
            RecomHomeAdapter adapter = new RecomHomeAdapter(getApplicationContext(), dataList);
            adapter.notifyDataSetChanged();
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String products_id=String.valueOf(dataList.get(position).getProducts_id());
                    String price=String.valueOf(dataList.get(position).getPrice());
                    Toast.makeText(getApplicationContext() ,products_id,Toast.LENGTH_LONG).show();
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
        public void onFailure(Call<RecomResponse> call, Throwable t) {
            t.printStackTrace();
            Toast.makeText(Recommend_data.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
            Log.e("로그인 에러 발생", t.getMessage());
        }
    });

}
    }



