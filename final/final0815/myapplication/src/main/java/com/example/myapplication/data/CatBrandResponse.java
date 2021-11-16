package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatBrandResponse {

    @SerializedName("result")
    public ArrayList<Datum3> datas;

    public ArrayList<Datum3> getData() { return datas; }

    public class Datum3 {

        public String manufacturer_contraction;


    }
}