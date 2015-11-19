package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class Participation {
    int id;
    int wishListId;
    int userId;

    public Participation(){
    }

    public Participation(int id, int wishListId, int userId){
        this.id=id;
        this.wishListId=wishListId;
        this.userId=userId;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setWishListId(int wishListId){
        this.wishListId=wishListId;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getId(){
        return id;
    }
    public int getWishListId(){
        return wishListId;
    }
    public int getUserId(){
        return userId;
    }
}
