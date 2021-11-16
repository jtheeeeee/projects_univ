package com.example.myapplication.listView;

import java.io.Serializable;

public class HomeDataList implements Serializable {
    private int products_id;
    private String products_name;
    private String origin;
    private String cat_or_dog;
    private String age_contraction;
    private String food_sort;
    private int price;
    private String manufacturer_contraction;
    private String animal_sort;

    public HomeDataList(int products_id, String products_name, String origin, String cat_or_dog, String age_contraction, String food_sort, int price, String manufacturer_contraction, String animal_sort){
        this.products_id=products_id;
        this.products_name=products_name;
        this.origin=origin;
        this.cat_or_dog=cat_or_dog;
        this.age_contraction=age_contraction;
        this.food_sort=food_sort;
        this.price=price;
        this.manufacturer_contraction=manufacturer_contraction;
        this.animal_sort=animal_sort;
    }

    public int getProducts_id() {
        return products_id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCat_or_dog() {
        return cat_or_dog;
    }

    public String getAge_contraction() {
        return age_contraction;
    }

    public String getFood_sort() {
        return food_sort;
    }

    public int getPrice() {
        return price;
    }

    public String getManufacturer_contraction() {
        return manufacturer_contraction;
    }

    public String getAnimal_sort() {
        return animal_sort;
    }
}
