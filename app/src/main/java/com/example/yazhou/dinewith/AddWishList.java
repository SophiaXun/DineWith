package com.example.yazhou.dinewith;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.Database;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;

public class AddWishList extends AppCompatActivity {

    DatabaseHelper dinewithDb;

    EditText userIdEditText;
    EditText dateEditText;
    EditText restaurantIdEditText;
    EditText completeFlagEditText;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish_list);

        dinewithDb=new DatabaseHelper(this);

        userIdEditText=(EditText)findViewById(R.id.userIdEditText);
        dateEditText=(EditText)findViewById(R.id.dateEditText);
        restaurantIdEditText=(EditText)findViewById(R.id.restaurantIdEditText);
        completeFlagEditText=(EditText)findViewById(R.id.completeFlagEditText);
        submitButton=(Button)findViewById(R.id.submitButton);

        addWishList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addWishList(){
        submitButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        int userId=Integer.parseInt(userIdEditText.getText().toString());
                        int restaurantId=Integer.parseInt(restaurantIdEditText.getText().toString());
                        int completeFlag=Integer.parseInt(completeFlagEditText.getText().toString());
                        String date=dateEditText.getText().toString();

                        boolean wishLishInsert=dinewithDb.insertWishList(userId,date,restaurantId,completeFlag);
                        if(wishLishInsert==true){
                            Log.d("Wish List Insert"," true");
                        }else{
                            Log.d("Wish List Insert"," false");
                        }

                    }

                }
        );
    }

}
