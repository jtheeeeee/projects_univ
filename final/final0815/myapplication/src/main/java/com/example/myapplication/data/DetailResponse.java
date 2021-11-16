package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailResponse {

    @SerializedName("result")
    public ArrayList<Datum> datas;

    public ArrayList<Datum> getData() { return datas; }

    public class Datum {

        @SerializedName("products_name")
        public String products_name;
        @SerializedName("origin")
        public String origin;
        @SerializedName("cat_or_dog")
        public String cat_or_dog;
        @SerializedName("age")
        public String age;
        @SerializedName("food_sort")
        public String food_sort;
        @SerializedName("price")
        public int price;
        @SerializedName("manufacturer_contraction")
        public String manufacturer_contraction;
        @SerializedName("animal_sort")
        public String animal_sort;



    }
}
