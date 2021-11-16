package com.example.myapplication.listView;

import java.io.Serializable;

public class CatBrandDataList implements Serializable {

    private String manufacturer_contraction;


    public CatBrandDataList(String manufacturer_contraction){
        this.manufacturer_contraction=manufacturer_contraction;
    }

    public String getManufacturer_contraction() {
        return manufacturer_contraction;
    }
}

