package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecomResponse {
    @SerializedName("name")
    private String result;

    public String getName() {
        return result;
    }

    @SerializedName("result")
    public List<Datum123> data;

    public List<Datum123> getData() { return data; }

    public class Datum123 {

        @SerializedName("products_id")
        public Integer products_id;
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
