package com.example.myapplication.listView;

public class IngredientList {
    private String good_or_bad;
    private String name;

    public IngredientList(String good_or_bad, String name){
        this.good_or_bad= good_or_bad;
        this.name=name;

    }

    public String getGood_or_bad() {
        return good_or_bad;
    }

    public String getName() {
        return name;
    }


}
