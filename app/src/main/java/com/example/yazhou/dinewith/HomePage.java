package com.example.yazhou.dinewith;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    DatabaseHelper dinewithDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        displayWishLish();
    }

    public void displayWishLish(){

        dinewithDb=new DatabaseHelper(this);
        ArrayList<WishList> wishListsArrayList=new ArrayList<>();
        wishListsArrayList=dinewithDb.listAllWishList();
        for(int i=0;i<wishListsArrayList.size();i++){
            Log.i("The wish list id", Integer.toString(wishListsArrayList.get(i).getId()));
            Log.i("The wish list userId", Integer.toString(wishListsArrayList.get(i).getUserId()));
            Log.i("The wish list date", wishListsArrayList.get(i).getDate());
            Log.i("The wish list restId", Integer.toString(wishListsArrayList.get(i).getRestaurantId()));
            Log.i("The wish list comFlag", Integer.toString(wishListsArrayList.get(i).getCompleteFlag()));
        }
        String[] items=new String[]{
                "apple","avocado","banana"
        };
        String[] items1=new String[]{
                "1","2","3"
        };
        WishList wishList=new WishList();
        wishList=wishListsArrayList.get(0);
        setContentView(R.layout.activity_home_page);
//        ArrayAdapter<WishList> adapter=new ArrayAdapter<WishList>(this,android.R.layout.,items);
        WishListAdapter adapter=new WishListAdapter(this,wishListsArrayList);
        ListView listView=(ListView)findViewById(R.id.wishListListView);
        listView.setAdapter(adapter);

    }
}
