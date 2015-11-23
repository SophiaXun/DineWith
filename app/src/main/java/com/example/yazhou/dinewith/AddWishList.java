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
import android.widget.AdapterView;
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
    ArrayAdapter adapter;

    //User
    int userId;


    //Date
    CalendarView calendar;
    int chosenYear;
    int chosenMonth;
    int chosenDay;
    TextView dateShowTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish_list);

        dinewithDb=new DatabaseHelper(getBaseContext());
        getUserIdFromHomePage();

        sqlRestaurantName.add("1");
        sqlRestaurantName.add("2");
        sqlRestaurantName.add("3");

        dateShowTextView=(TextView)findViewById(R.id.dateShowTextView);
        submitButton=(Button)findViewById(R.id.submitButton);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Spinnerr View Display
        sp=(Spinner) findViewById(R.id.restaurantNameSpinner);
//        adapter=ArrayAdapter.createFromResource(this,R.array.restaurant_name,R.layout.support_simple_spinner_dropdown_item);
//        sp.setAdapter(adapter);


//        CalendarView Display
        getDate();
        spinnerListeners();

        //Submit Button
        addWishList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void getUserIdFromHomePage(){
        Intent intent=getIntent();
        userId= intent.getIntExtra("userId",-1);
        Log.i("USERID2",Integer.toString(userId));
    }

    public void getDate(){
        calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int dayOfMonth){
                final Calendar c=Calendar.getInstance();
                chosenYear=year;
                chosenMonth=month;
                chosenDay=dayOfMonth;
                String date=dayOfMonth+"/"+month+"/"+year;
                dateShowTextView.setText(date);
            }
        });
    }

    public void spinnerListeners(){
//        sp.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String restaurantName = sp.getSelectedItem().toString();
//                Log.i("The restaurantName", restaurantName);
//            }
//        });
    }

    public ArrayList<String> getAllRestaurantName(){
        ArrayList<String> allRestaurantName=new ArrayList<String>();
        return allRestaurantName;
    }



    public void addWishList(){
        submitButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        String item = sp.getSelectedItem().toString();
                        Log.i("The select",item);


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
