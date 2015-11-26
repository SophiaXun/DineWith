package com.example.yazhou.dinewith;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.DisplayWishList;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.Restaurant;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

/**
 * Created by Yazhou on 11/18/2015.
 */
public class WishListAdapter extends ArrayAdapter<DisplayWishList>{
    TextView user;
    TextView date;
    TextView restaurant;
    TextView participant;
    Button joinButton;

    DisplayWishList wishList;
    DatabaseHelper dineWith;

    Context c;

    public WishListAdapter(Context context, ArrayList<DisplayWishList> wishList) {
        super(context, 0, wishList);
        c=context;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        wishList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_template, parent, false);
        }
        // Lookup view for data population
        user = (TextView) convertView.findViewById(R.id.userShowTextView);
        date = (TextView) convertView.findViewById(R.id.dateShowTextView);
        restaurant = (TextView) convertView.findViewById(R.id.restaurantShowTextView);
        participant = (TextView) convertView.findViewById(R.id.participantsShowTextView);
        joinButton=(Button) convertView.findViewById(R.id.joinButton);


        user.setText(String.valueOf(wishList.getUserName()));
        date.setText(wishList.getDate());
        restaurant.setText(String.valueOf(wishList.getRestaurant()));
        participant.setText(String.valueOf(wishList.displayParticipants()));

        restaurant.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                public void onClick(View v){
//                        Intent intent = new Intent(c,"com.google.android.gms.maps.SupportMapFragment");
                        TextView tv=(TextView)v.findViewById(R.id.restaurantShowTextView);
                        String restaurantName=tv.getText().toString();

                        int restaurantId=HomePage.dinewithDb.getRestaurantIdByRestaurantName(restaurantName);
                        Restaurant restaurant=HomePage.dinewithDb.getRestaurant(restaurantId);
                        double latitude=restaurant.getLatitude();
                        double longitude=restaurant.getLongitude();

                        Log.i("Lat", Double.toString(latitude));
                        Log.i("Log",Double.toString(longitude));

                        Intent intent = new Intent(c,MapsActivity.class);
                        intent.putExtra("latitude",latitude);
                        intent.putExtra("long",longitude);
                        intent.putExtra("restaurantName",restaurantName);
                        Log.i("))Long",Double.toString(longitude));
                        Log.i("))Lat",Double.toString(latitude));

                        c.startActivity(intent);
                    }
                }
        );

        joinButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View parentRow=(View) v.getParent();
                        ListView listView=(ListView) parentRow.getParent();
                        final int position = listView.getPositionForView(parentRow);
                        HomePage.join(position+1);
                        notifyDataSetChanged();



                    }
                }
        );
        // Return the completed view to render on screen
        return convertView;
    }


}
