package com.example.yazhou.dinewith;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.Database;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class AddWishList extends AppCompatActivity {

    DatabaseHelper dinewithDb;

    Button submitButton;

    Spinner sp;
    ArrayList<String> sqlRestaurantName=new ArrayList<String>();
    ArrayAdapter<String> adapter;


    //Date
    CalendarView calendar;
    int year;
    int month;
    int day;
    TextView dateShowTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish_list);

        dinewithDb=new DatabaseHelper(getBaseContext());

        sqlRestaurantName.add("1");
        sqlRestaurantName.add("2");
        sqlRestaurantName.add("3");


//        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sqlRestaurantName);



        dateShowTextView=(TextView)findViewById(R.id.dateShowTextView);
        submitButton=(Button)findViewById(R.id.submitButton);


        addWishList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Spinnerr View Display
//        sp=(Spinner) findViewById(R.id.restaurantNameSpinner);
//        spinnerListeners();

//        CalendarView Display
        calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
                public void onSelectedDayChange(CalendarView view,int year,int month,int dayOfMonth){
//                Toast.makeText(getApplicationContext(),day+"/"+month+"/"+year,Toast.LENGTH_LONG).show();
                final Calendar c=Calendar.getInstance();

                Log.i("the year", Integer.toString(year));
                Log.i("the month", Integer.toString(month));
                Log.i("the day", Integer.toString(dayOfMonth));
                String date=dayOfMonth+"/"+month+"/"+year;
                dateShowTextView.setText(date);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }



    public void spinnerListeners(){
        sp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sp.setAdapter(adapter);
                    }
                }
        );
    }

    public ArrayList<String> getAllRestaurantName(){
        ArrayList<String> allRestaurantName=new ArrayList<String>();
        return allRestaurantName;
    }



    public void addWishList(){
        submitButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

//                        int userId=Integer.parseInt(userIdEditText.getText().toString());
//                        int restaurantId=Integer.parseInt(restaurantIdEditText.getText().toString());
//                        int completeFlag=Integer.parseInt(completeFlagEditText.getText().toString());
                        String date=dateShowTextView.getText().toString();

//                        boolean wishLishInsert=dinewithDb.insertWishList(userId,date,restaurantId,completeFlag);
//                        if(wishLishInsert==true){
//                            Log.d("Wish List Insert"," true");
//                        }else{
//                            Log.d("Wish List Insert"," false");
//                        }

                        Log.d("Hello","World");
                        Intent intent=new Intent("com.example.yazhou.dinewith.HomePage");
                        startActivity(intent);
                    }

                }




        );
    }

}
