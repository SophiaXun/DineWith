package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class Restaurant {
    int id;
    String restaurantName;
    double longitude;
    double latitude;


    public Restaurant(){

    }

    public Restaurant(int id,String name,double longitude,double latitude  ){
        this.id=id;
        this.restaurantName=name;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.restaurantName=name;
    }

    public void setLongitude(double longitude){
        this.longitude=longitude;
    }
    public void setLatitude(double latitude){
        this.latitude=latitude;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return restaurantName;
    }

    public double getLongitude(){
        return longitude;
    }
    public double getLatitude(){
        return latitude;
    }
}