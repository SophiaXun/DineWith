package com.example.yazhou.dinewith;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.DisplayWishList;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

/**
 * Created by Yazhou on 11/18/2015.
 */
public class WishListAdapter extends ArrayAdapter<DisplayWishList>{
    public WishListAdapter(Context context, ArrayList<DisplayWishList> wishList) {
        super(context, 0, wishList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DisplayWishList wishList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_template, parent, false);
        }
        // Lookup view for data population
        Log.i("The position",Integer.toString(getPosition(wishList)));
        Log.i("The user",(wishList.getUserName()));
//        TextView id = (TextView) convertView.findViewById(R.id.idShowTextView);
        TextView user = (TextView) convertView.findViewById(R.id.userShowTextView);
        TextView date = (TextView) convertView.findViewById(R.id.dateShowTextView);
        TextView restaurant = (TextView) convertView.findViewById(R.id.restaurantShowTextView);
        TextView participant = (TextView) convertView.findViewById(R.id.participantsShowTextView);
        // Populate the data into the template view using the data object
//        id.setText(String.valueOf(wishList.getId()));
        user.setText(String.valueOf(wishList.getUserName()));
        date.setText(wishList.getDate());
        restaurant.setText(String.valueOf(wishList.getParticipants()));
        participant.setText(String.valueOf(wishList.displayParticipants()));
        // Return the completed view to render on screen
        return convertView;
    }
}
