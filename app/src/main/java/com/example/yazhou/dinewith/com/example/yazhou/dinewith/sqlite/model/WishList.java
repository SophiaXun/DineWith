package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class WishList {
    int id;
    int userId;
    String date;
    int restaurantId;
    int completeFlag;

    public WishList(){

    }

    public WishList(int id,int userId,String date,int restaurantId,int completeFlag){
        this.id=id;
        this.userId=userId;
        this.date=date;
        this.restaurantId=restaurantId;
        this.completeFlag=completeFlag;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setRestaurantId(int restaurantId){
        this.restaurantId=restaurantId;
    }
    public void setCompleteFlag(int completeFlag){
        this.completeFlag=completeFlag;
    }
    public int getId(){
        return this.id;
    }
    public String getDate(){
        return this.date;
    }
    public int getUserId(){
        return this.userId;
    }
    public int getRestaurantId(){
        return this.restaurantId;
    }
    public int getCompleteFlag(){
        return this.completeFlag;
    }
}
