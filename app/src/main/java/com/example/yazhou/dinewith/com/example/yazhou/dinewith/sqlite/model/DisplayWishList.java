package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

import java.util.ArrayList;

/**
 * Created by Yazhou on 11/20/2015.
 */
public class DisplayWishList {
    private int wishLishId;
    private String userName;
    private String date;
    private String restaurant;
    private ArrayList<String> participants;

    public DisplayWishList(){

    }

    public DisplayWishList(int wishLishId,String userName,String date, String restaurant,ArrayList<String> participants){
        this.wishLishId=wishLishId;
        this.userName=userName;
        this.date=date;
        this.restaurant=restaurant;
        this.participants=participants;
    }

    public int getWishLishId(){return wishLishId;}

    public String getUserName(){
        return userName;
    }

    public String getDate(){
        return date;
    }

    public String getRestaurant(){
        return restaurant;
    }

    public ArrayList<String> getParticipants(){
        return participants;
    }

    public String displayParticipants(){
        String participantsString=new String();
        for(int i=0;i<participants.size();i++){
            participantsString=participantsString+participants.get(i)+"   ";
        }
        return participantsString;
    }
}
