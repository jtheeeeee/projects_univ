package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.listView.HomeAdapter;
import com.example.myapplication.listView.HomeDataList;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;

public class HomeListActivity extends AppCompatActivity {
    ArrayList<HomeDataList> dataList;
    ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        Intent intent = getIntent();
        dataList = (ArrayList<HomeDataList>) intent.getSerializableExtra("list");
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), dataList );
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String products_id=String.valueOf(dataList.get(position).getProducts_id());
                String price=String.valueOf(dataList.get(position).getPrice());
                Toast.makeText(HomeListActivity.this ,products_id,Toast.LENGTH_LONG).show();
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

}