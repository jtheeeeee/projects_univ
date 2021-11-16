package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IngredientResponse {

    @SerializedName("result")
    public ArrayList<Datum> datas;

    public ArrayList<Datum> getData() { return datas; }

    public class Datum {
        public int products_id;
        public int ingredient_id;
        public String small_classification;
        public String good_or_bad;


    }

}
