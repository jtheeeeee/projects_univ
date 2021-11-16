package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class HomeSearchData {
    @SerializedName("homeSearchWord")
    String homeSearchWord;
    public HomeSearchData(String homeSearchWord) {
        this.homeSearchWord=homeSearchWord;
    }

}
