package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.ComData;
import com.example.myapplication.data.ComResponse;
import com.example.myapplication.data.IngredientData;
import com.example.myapplication.data.IngredientResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {
    ServiceApi service;
    TextView protein_com_tx;
    TextView fat_com_tx;
    TextView fiber_com_tx;
    TextView ca_com_tx;
    TextView p_com_tx;
    TextView mineral_com_tx;
    TextView moist_com_tx;
    PieChart pieChart;

    int good, plain, bad=0;
    int[] colorArray = new int[]{Color.rgb(224,238,224), Color.rgb(220,224,238),Color.rgb(255,228,225)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        pieChart = (PieChart)findViewById(R.id.picChart);
        Intent intent = getIntent();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        TextView name_tx= findViewById(R.id.product_name);
        TextView price_tx = findViewById(R.id.price);
        TextView cat_or_dog_tx=findViewById(R.id.cat_or_dog);
        TextView manufacturer_tx=findViewById(R.id.manufacturer);
        TextView age_tx = findViewById(R.id.age);
        TextView origin_tx=findViewById(R.id.origin);
        TextView food_sort_tx=findViewById(R.id.food_sort);
        TextView animal_sort_tx=findViewById(R.id.animal_sort);

        int products_id= Integer.parseInt(intent.getExtras().getString("id"));

        String name = intent.getExtras().getString("name");
        String price = intent.getExtras().getString("price");
        String cat_or_dog=intent.getExtras().getString("cat_or_dog");
        String manufacturer = intent.getExtras().getString("manufacturer");
        String age = intent.getExtras().getString("age");
        String origin= intent.getExtras().getString("origin");
        String food_sort= intent.getExtras().getString("food_sort");
        String animal_sort=intent.getExtras().getString("animal_sort");
        name_tx.setText(name);
        price_tx.setText(price);
        cat_or_dog_tx.setText(cat_or_dog);
        manufacturer_tx.setText(manufacturer);
        age_tx.setText(age);
        origin_tx.setText(origin);
        food_sort_tx.setText(food_sort);
        animal_sort_tx.setText(animal_sort);

        protein_com_tx=findViewById(R.id.protein_com);
        fat_com_tx= findViewById(R.id.fat_com);
        fiber_com_tx=findViewById(R.id.fiber_com);
        ca_com_tx=findViewById(R.id.ca_com);
        p_com_tx=findViewById(R.id.p_com);
        mineral_com_tx=findViewById(R.id.mineral_com);
        moist_com_tx=findViewById(R.id.moist_com);
        Log.d("id",String.valueOf(products_id));
        startSearch(new ComData(products_id));
        startSearch2(new IngredientData(products_id));
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IngredientActivity.class);
                intent.putExtra("products_id", String.valueOf(products_id));
                startActivity(intent);
            }
        });


    }
    private ArrayList<PieEntry> data1(int good_chart, int plain_chart, int bad_chart) {
        ArrayList<PieEntry> datavalue = new ArrayList<>();
        good_chart=this.good;
        plain_chart=this.plain;
        bad_chart=this.bad;
        datavalue.add(new PieEntry(good_chart,"좋은 성분"));
        datavalue.add(new PieEntry(plain_chart,"보통 성분"));
        datavalue.add(new PieEntry(bad_chart,"주의 성분", Color.BLACK));
        //value부분에 성분갯수카운트

        return  datavalue;
    }
    private void startSearch(ComData data) {
        Log.d("tab","I=OK");
        Log.d("tab",String.valueOf(data.getProducts_id()));
        service.products_id(data).enqueue(new Callback<ComResponse>() {
            @Override
            public void onResponse(Call<ComResponse> call, Response<ComResponse> response) {
                Log.d("pird",String.valueOf(data.getProducts_id()));
                String displayResponse = "";
                ComResponse result = response.body();
                List<ComResponse.Datum4> datumList = result.datas;
                Log.d("TAG", datumList.getClass().getName());
                for (ComResponse.Datum4 datum : datumList) {
                    String product_name = datum.products_name;
                    protein_com_tx.setText(String.valueOf(datum.protein_com));
                    fat_com_tx.setText(String.valueOf(datum.fat_com));
                    fiber_com_tx.setText(String.valueOf(datum.fiber_com));
                    ca_com_tx.setText(String.valueOf(datum.ca_com));
                    p_com_tx.setText(String.valueOf(datum.p_com));
                    mineral_com_tx.setText(String.valueOf(datum.mineral_com));
                    moist_com_tx.setText(String.valueOf(datum.moist_com));

                }


                Log.d("TAG", datumList.getClass().getName());
            }

            @Override
            public void onFailure(Call<ComResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity3.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
    private void startSearch2(IngredientData data) {
        service.products_id(data).enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.d("pird2",String.valueOf(data.getProducts_id()));
                String displayResponse = "";
                String good_String="";
                IngredientResponse result2 = response.body();
                List<IngredientResponse.Datum> datumList2 = result2.datas;
                Log.d("TAG2", datumList2.getClass().getName());
                for (IngredientResponse.Datum datum : datumList2) {
                    displayResponse += datum.ingredient_id+" "+datum.small_classification+" "+datum.good_or_bad+"\n";
                    good_String+=datum.good_or_bad+",";
                }
                Log.d("NAME",displayResponse);
//                textView2.setText(displayResponse);
                good=0;
                plain=0;
                bad=0;
                String [] goods =  good_String.split(",");
                Log.d("good",goods[3].trim());
                for (int i=0 ; i<goods.length; i++){
                    if (goods[i].trim().equals("good")){
                        good+=1;
                        Log.d("good",String.valueOf(good));
                    }else if(goods[i].trim().equals("plain")){
                        plain+=1;
                        Log.d("plain",String.valueOf(plain));
                    }else if(goods[i].trim().equals("bad")){
                        bad+=1;
                    }
                };
                String state = "good : "+good+", plain : "+plain+", bad : "+bad;
//                textView3.setText(state);
                PieDataSet pieDataSet = new PieDataSet(data1(good, plain, bad), "");
                pieDataSet.setColors(colorArray);
                PieData pieData = new PieData(pieDataSet);
                pieChart.setDrawEntryLabels(true);
                pieChart.setUsePercentValues(true);
                pieData.setValueTextSize(30);
                pieChart.setCenterTextSize(35);
                pieChart.setHoleRadius(30);
                pieChart.setData(pieData);
                pieChart.invalidate();
                Log.d("TAG", datumList2.getClass().getName());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity3.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
}