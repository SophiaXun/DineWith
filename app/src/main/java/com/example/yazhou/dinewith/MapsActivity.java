package com.example.yazhou.dinewith;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng current;

    LocationManager locationManager;
    String PROVIDER = LocationManager.GPS_PROVIDER;
    Context context;
    boolean isGPSEnabled=false;
    boolean isNetworkEnabled=false;
    boolean canGetLocation=false;

    double currentlatitude;
    double currentlongitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=10;
    private static final long MIN_TIME_BW_UPDATES=1000*60*1;
    GPSTracker gps;


//    private static final LatLng MAVELIKARA = new LatLng(39.884502,116.461314);


//    public void processMap(){
//        if(mMap== null){
//            mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
//        }else{
//            mMap.addMarker(new MarkerOptions().position(MAVELIKARA).title("This is the marked area"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MAVELIKARA,13));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findCurrentLocation();

    }

    public void findCurrentLocation(){
        gps=new GPSTracker(MapsActivity.this);

        if(gps.canGetLocation()){
            double latitude=gps.getLatitude();
            double longitude=gps.getLongitude();
            current=new LatLng(latitude,longitude);
            Toast.makeText(getApplicationContext(),
                    "Click Marker",
                    Toast.LENGTH_LONG).show();

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(39.884502, 116.461314);
//        LatLng sydney=MAVELIKARA;
        Intent intent=getIntent();
        double latitude=intent.getDoubleExtra("latitude", 39.884502);
        double longitude=intent.getDoubleExtra("long", 116.461314);
        String restaurantName=intent.getStringExtra("restaurantName");
        LatLng restaurant = new LatLng(latitude,longitude);

        Log.i("+++Lat", Double.toString(latitude));
        Log.i("+++Lon", Double.toString(longitude));
        Log.i("+++++++", restaurantName);
//        LatLng restaurant = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(restaurant).title(restaurantName));
        mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant,15.0f));

    }
}
