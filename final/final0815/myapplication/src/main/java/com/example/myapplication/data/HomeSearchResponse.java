package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeSearchResponse {

    @SerializedName("result")
    public ArrayList<Datum2> datas;

    public ArrayList<Datum2> getData() { return datas; }

    public class Datum2 {

        @SerializedName("products_id")
        public int products_id;
        @SerializedName("products_name")
        public String products_name;
        @SerializedName("origin")
        public String origin;
        @SerializedName("cat_or_dog")
        public String cat_or_dog;
        @SerializedName("age_contraction")
        public String age_contraction;
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
