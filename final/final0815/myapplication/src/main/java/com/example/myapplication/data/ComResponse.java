package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComResponse {

    @SerializedName("result")
    public ArrayList<Datum4> datas;

    public ArrayList<Datum4> getData() { return datas; }

    public class Datum4 {
        public int products_id;
        public String products_name;
        public float protein_com;
        public float fat_com;
        public float fiber_com;
        public float mineral_com;
        public float moist_com;
        public float ca_com;
        public float p_com;

    }

}
