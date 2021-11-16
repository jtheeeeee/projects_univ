package com.example.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.data.RecomData;
import com.example.myapplication.data.Recommend_data;
import com.example.myapplication.network.ServiceApi;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_2 extends Fragment {

    TextView textView;
    Button button;
    ServiceApi service;
    RadioGroup pet_type, pet_age, food_type, capacity,allergy;

    CheckBox chicken, fish, grain, cow, milk;
    String pet_type_str="", pet_age_str="", food_type_str="", capacity_str="",allergy_str="";
    String ch="", f="", g="", c="", m="";
    String result="";
    public Fragment_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =getLayoutInflater().inflate(R.layout.fragment_fragment_2,null);
        pet_type=view.findViewById(R.id.pet_type);
        pet_age=view.findViewById(R.id.pet_age);
        food_type=view.findViewById(R.id.food_type);
        capacity=view.findViewById(R.id.capacity);
        allergy=view.findViewById(R.id.allergy);

        button=view.findViewById(R.id.recommend);

        pet_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId ==R.id.dog) {
                    pet_type_str="0";
                } else {
                    pet_type_str="1";
                }

            }
        });
        pet_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.all_age){
                    pet_age_str="3";
                }else if(checkedId==R.id.under_one){
                    pet_age_str="0";
                }else if(checkedId==R.id.over_seven) {
                    pet_age_str = "2";
                }else{
                    pet_age_str="1";
                }
            }
        });
        food_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==(R.id.dry)){
                    food_type_str="0";
                }else{
                    food_type_str="1";
                }

            }
        });
        capacity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.joint){
                    capacity_str="1";
                }else if(checkedId==R.id.prebiotics){
                    capacity_str="2";
                }else if(checkedId==R.id.immune){
                    capacity_str="3";
                }else if(checkedId==R.id.hair){
                    capacity_str="4";
                }else {
                    capacity_str="5";
                }
            }
        });
        allergy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.chicken){
                    allergy_str=" and recommend_table_fin.chicken=0";
                }else if(checkedId==R.id.fish){
                    allergy_str=" and recommend_table_fin.fish=0";
                }else if(checkedId==R.id.grain){
                    allergy_str=" and recommend_table_fin.grain=0";
                }else if(checkedId==R.id.cow){
                    allergy_str=" and recommend_table_fin.cow=0";
                }else {
                    allergy_str=" and recommend_table_fin.milk=0";
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), Recommend_data.class);
                intent.putExtra("pet_type",pet_type_str);
                intent.putExtra("pet_age",pet_age_str);
                intent.putExtra("food_type",food_type_str);
                intent.putExtra("capacity",capacity_str);
                intent.putExtra("allergy", allergy_str);
                Log.d("TAb",pet_age_str+pet_age_str+food_type_str+capacity_str+allergy_str);
                startActivity(intent);
            }
        });

        return view;
    }


}
