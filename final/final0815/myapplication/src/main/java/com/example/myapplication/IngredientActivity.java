package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.data.IngredientData;
import com.example.myapplication.data.IngredientResponse;
import com.example.myapplication.listView.HomeAdapter;
import com.example.myapplication.listView.HomeDataList;
import com.example.myapplication.listView.IngredientAdapter;
import com.example.myapplication.listView.IngredientList;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientActivity extends AppCompatActivity {
    ServiceApi service;
    PieChart pieChart;
    int good, plain, bad=0;
    int[] colorArray = new int[]{Color.rgb(224,238,224), Color.rgb(220,224,238),Color.rgb(255,228,225)};
    ArrayList<IngredientList> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Intent intent = getIntent();
        pieChart = (PieChart)findViewById(R.id.picChart);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        int products_id= Integer.parseInt(intent.getExtras().getString("products_id"));
        startSearch2(new IngredientData(products_id));

    }
    private ArrayList<PieEntry> data1(int good_chart, int plain_chart, int bad_chart) {
        ArrayList<PieEntry> datavalue = new ArrayList<>();
        good_chart=this.good;
        plain_chart=this.plain;
        bad_chart=this.bad;
        datavalue.add(new PieEntry(good_chart,"좋은 성분",Color.rgb(224,238,224)));
        datavalue.add(new PieEntry(plain_chart,"보통 성분",Color.rgb(220,224,238)));
        datavalue.add(new PieEntry(bad_chart,"주의 성분",Color.rgb(255,228,225)));
        //value부분에 성분갯수카운트

        return  datavalue;
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
                dataList = new ArrayList<IngredientList>();
                for (IngredientResponse.Datum datum : datumList2) {
                    dataList.add(new IngredientList(datum.good_or_bad, datum.small_classification));
                    displayResponse += datum.ingredient_id+" "+datum.small_classification+" "+datum.good_or_bad+"\n";
                    good_String+=datum.good_or_bad+",";
                }
                IngredientAdapter adapter = new IngredientAdapter(getApplicationContext(), dataList);
                adapter.notifyDataSetChanged();
                ListView listView = findViewById(R.id.ingredient_list);
                listView.setAdapter(adapter);
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
                PieDataSet pieDataSet = new PieDataSet(data1(good, plain, bad),"");
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
                Toast.makeText(IngredientActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
}