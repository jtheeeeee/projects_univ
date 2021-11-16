package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class DetailData {

    @SerializedName("detailWord")
    String detailWord;
    public DetailData(String detailWord) {
        this.detailWord=detailWord;
    }
}
