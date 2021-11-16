package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {
    @SerializedName("name")
    private String result;

    public String getName() {
        return result;
    }

    @SerializedName("result")
    public ArrayList<Datum> data;

    public ArrayList<Datum> getData() { return data; }

    public class Datum {

        @SerializedName("id")
        public Integer id;
        @SerializedName("name")
        public String name;
        @SerializedName("img")
        public String img;

    }

}
