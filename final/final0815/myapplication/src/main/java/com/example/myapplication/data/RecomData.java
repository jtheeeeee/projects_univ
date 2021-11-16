package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class RecomData {
    @SerializedName("recomWord")
    String recomWord;
    @SerializedName("ageWord")
    String ageWord;
    @SerializedName("alrWord")
    String alrWord;
    @SerializedName("typeWord")
    String typeWord;
    @SerializedName("capacityWord")
    String capacityWord;

    public RecomData(String recomWord, String ageWord, String alrWord, String typeWord, String capacityWord) {
        this.recomWord = recomWord;
        this.ageWord = ageWord;
        this.alrWord = alrWord;
        this.typeWord = typeWord;
        this.capacityWord = capacityWord;
    }

}
