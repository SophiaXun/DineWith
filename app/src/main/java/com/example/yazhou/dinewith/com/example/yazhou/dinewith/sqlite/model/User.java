package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class User {
    int id;
    String userName;
    String pwd;

    public User(){

    }

    public User(int id, String name,String pwd){
        this.id=id;
        this.userName=name;
        this.pwd=pwd;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.userName=name;
    }
    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return userName;
    }
    public String getPwd(){
        return pwd;
    }
}