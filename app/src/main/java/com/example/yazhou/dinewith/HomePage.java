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
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private static DatabaseHelper dinewithDb;
    private static Button addWishLishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        displayWishLish();

        addWishlistButton();
    }

    public void displayWishLish(){

        dinewithDb=new DatabaseHelper(this);
        dinewithDb.displayAllWishList();

//        ArrayList<WishList> wishListsArrayList=dinewithDb.displayAllWishList();
//        for(int i=0;i<wishListsArrayList.size();i++){
//            Log.i("The wish list id", Integer.toString(wishListsArrayList.get(i).getId()));
//            Log.i("The wish list userId", Integer.toString(wishListsArrayList.get(i).getUserId()));
//            Log.i("The wish list date", wishListsArrayList.get(i).getDate());
//            Log.i("The wish list restId", Integer.toString(wishListsArrayList.get(i).getRestaurantId()));
//            Log.i("The wish list comFlag", Integer.toString(wishListsArrayList.get(i).getCompleteFlag()));
//        }

//        WishList wishList=new WishList();
//        wishList=wishListsArrayList.get(0);
//        setContentView(R.layout.activity_home_page);
////        ArrayAdapter<WishList> adapter=new ArrayAdapter<WishList>(this,android.R.layout.,items);
//        WishListAdapter adapter=new WishListAdapter(this,wishListsArrayList);
//        ListView listView=(ListView)findViewById(R.id.wishListListView);
//        listView.setAdapter(adapter);

    }

    public void addWishlistButton(){
        addWishLishButton=(Button)findViewById(R.id.addWishlistButton);
        addWishLishButton.setOnClickListener(
//
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Hello","World");
                        Intent intent = new Intent("com.example.yazhou.dinewith.AddWishList");
                        startActivity(intent);
                    }
                }
        );

    }
}
