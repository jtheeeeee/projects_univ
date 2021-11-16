package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class BrandData {
    @SerializedName("manufacturer_contraction")
    String manufacturer_contraction;

    public BrandData(String manufacturer_contraction) {
        this.manufacturer_contraction=manufacturer_contraction;

    }

    public String getManufacturer_contraction() {
        return manufacturer_contraction;
    }


}
