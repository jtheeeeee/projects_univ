package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class BrandResponse {

    @SerializedName("result")
    public ArrayList<Datum> datas;

    public ArrayList<Datum> getData() { return datas; }

    public class Datum {

        public String manufacturer_contraction;


    }
}