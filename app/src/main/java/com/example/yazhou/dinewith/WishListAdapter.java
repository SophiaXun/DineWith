package com.example.yazhou.dinewith;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

/**
 * Created by Yazhou on 11/18/2015.
 */
public class WishListAdapter extends ArrayAdapter<WishList>{
    public WishListAdapter(Context context, ArrayList<WishList> wishList) {
        super(context, 0, wishList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WishList wishList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_template, parent, false);
        }
        // Lookup view for data population
        Log.i("The position",Integer.toString(getPosition(wishList)));
        Log.i("The id",Integer.toString(wishList.getId()));
        TextView id = (TextView) convertView.findViewById(R.id.idShowTextView);
        TextView userId = (TextView) convertView.findViewById(R.id.userIdShowTextView);
        TextView date = (TextView) convertView.findViewById(R.id.dateShowTextView);
        TextView restaurantId = (TextView) convertView.findViewById(R.id.restaurantIdShowTextView);
        TextView completeFlag = (TextView) convertView.findViewById(R.id.completeFlagShowTextView);
        // Populate the data into the template view using the data object
        id.setText(String.valueOf(wishList.getId()));
        userId.setText(String.valueOf(wishList.getUserId()));
        date.setText(wishList.getDate());
        restaurantId.setText(String.valueOf(wishList.getRestaurantId()));
        completeFlag.setText(String.valueOf(wishList.getCompleteFlag()));
        // Return the completed view to render on screen
        return convertView;
    }
}
