package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class Restaurant {
    int id;
    String restaurantName;
    String location;
    int rate;


    public Restaurant(){

    }

    public Restaurant(int id,String name,String location){
        this.id=id;
        this.restaurantName=name;
        this.location=location;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.restaurantName=name;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return restaurantName;
    }

    public String getLocation(){
        return location;
    }
}