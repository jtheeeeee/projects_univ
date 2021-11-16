package com.example.myapplication.listView;

public class DataList {
    private int id;
    private String name;
    private int img;

    public DataList (int id, String name, int img){
        this.id=id;
        this.name=name;
        this.img=img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
}
