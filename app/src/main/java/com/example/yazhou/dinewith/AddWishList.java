package com.example.yazhou.dinewith;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

    Button locationButton;
    TextView latitude;
    TextView longtitude;
    LocationManager locationManager;
    String PROVIDER = LocationManager.GPS_PROVIDER;
    Context context;
    boolean isGPSEnabled=false;
    boolean isNetworkEnabled=false;
    boolean canGetLocation=false;

    double latitudeDouble;
    double longtitudeDouble;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=10;
    private static final long MIN_TIME_BW_UPDATES=1000*60*1;





    Spinner sp;
    ArrayList<String> sqlRestaurantName = new ArrayList<String>();
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

        dinewithDb = new DatabaseHelper(getBaseContext());
        getUserIdFromHomePage();

        sqlRestaurantName.add("1");
        sqlRestaurantName.add("2");
        sqlRestaurantName.add("3");

        dateShowTextView = (TextView) findViewById(R.id.dateShowTextView);
        submitButton = (Button) findViewById(R.id.submitButton);








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

    public void showLocation(Location location){
        if(location==null){
            Log.i("$$","location null");
            Toast.makeText(getApplicationContext(),"Cannot find your current location", Toast.LENGTH_LONG).show();
        }else{
            latitude.setText("Latitude"+location.getLatitude());
            longtitude.setText("Longtitude"+location.getLongitude());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        locationManager.requestLocationUpdates(PROVIDER,0,0,locationListener);

    }

    private LocationListener locationListener= new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    public void getUserIdFromHomePage(){
        Intent intent=getIntent();
        userId= intent.getIntExtra("userId",-1);
    }

    public void getDate(){
        calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int dayOfMonth){
                final Calendar c=Calendar.getInstance();
                chosenYear=year;
                chosenMonth=month+1;
                chosenDay=dayOfMonth;
                String date=(month+1)+"-"+dayOfMonth+"-"+year;
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
                        String restaurantNameFromSpinner = sp.getSelectedItem().toString();
                        int restaurantId=dinewithDb.getRestaurantIdByRestaurantName(restaurantNameFromSpinner);
                        String date=dateShowTextView.getText().toString();
                        int completeFlag=0;
                        boolean wishLishInsert=dinewithDb.insertWishList(userId,date,restaurantId,completeFlag);





//                        int userId=Integer.parseInt(userIdEditText.getText().toString());
//                        int restaurantId=Integer.parseInt(restaurantIdEditText.getText().toString());
//                        int completeFlag=Integer.parseInt(completeFlagEditText.getText().toString());

                        Intent intent=new Intent("com.example.yazhou.dinewith.HomePage");
//                        intent.putExtra("userIdFromAdd",userId);
                        startActivity(intent);
                    }

                }
        );
    }

}
