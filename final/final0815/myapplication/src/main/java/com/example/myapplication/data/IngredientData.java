package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class IngredientData {
    @SerializedName("products_id")
    int products_id;
    public IngredientData(int products_id) {
        this.products_id=products_id;
    }

    public int getProducts_id() {
        return products_id;
    }
}
