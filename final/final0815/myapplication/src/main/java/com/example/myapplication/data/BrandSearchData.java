package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class BrandSearchData {
    @SerializedName("manufacturer_contraction")
    String manufacturer_contraction;
    @SerializedName("cat_or_dog")
    String cat_or_dog;

    public BrandSearchData(String manufacturer_contraction, String cat_or_dog) {
        this.manufacturer_contraction = manufacturer_contraction;
        this.cat_or_dog=cat_or_dog;
    }
    public String getCat_or_dog() {
        return cat_or_dog;
    }

}
