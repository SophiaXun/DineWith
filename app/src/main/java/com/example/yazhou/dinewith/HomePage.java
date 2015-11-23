package com.example.yazhou.dinewith;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.DisplayWishList;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private static DatabaseHelper dinewithDb;
    private static Button addWishLishButton;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getUserIdFromLoginPage();
        if(userId==-1){
//            getUserIdFromAddPage();
        }

        displayWishLish();
        addWishlistButton();
    }

    public void getUserIdFromLoginPage(){
        Intent intent=getIntent();
        userId= intent.getIntExtra("userId",-1);
    }

//    public void getUserIdFromAddPage(){
//        Intent intent=getIntent();
//        userId=intent.getIntExtra("userIdFromAdd",-1);
//    }

    public void displayWishLish(){

        dinewithDb=new DatabaseHelper(this);
        dinewithDb.displayAllWishList();

        ArrayList<DisplayWishList> wishListsArrayList=dinewithDb.displayAllWishList();
        for(int i=0;i<wishListsArrayList.size();i++){
            DisplayWishList wishList=new DisplayWishList();
            wishList=wishListsArrayList.get(0);

            setContentView(R.layout.activity_home_page);
            ListView listView=(ListView)findViewById(R.id.wishListListView);
//            ArrayAdapter<DisplayWishList> adapter=new ArrayAdapter<DisplayWishList>(this,android.R.layout.simple_list_item_1,wishListsArrayList);
            WishListAdapter adapter=new WishListAdapter(this,wishListsArrayList);
            listView.setAdapter(adapter);
        }
    }

    public static void join(){
        Log.i("JOIN!!!!","!!!");
    }


    public void addWishlistButton(){
        addWishLishButton=(Button)findViewById(R.id.addWishlistButton);
        addWishLishButton.setOnClickListener(
//
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.yazhou.dinewith.AddWishList");
                        intent.putExtra("userId",userId);
                        startActivity(intent);
                    }
                }
        );

    }
}
