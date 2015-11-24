package com.example.yazhou.dinewith;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.DisplayWishList;
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

    public WishListAdapter(Context context, ArrayList<DisplayWishList> wishList) {
        super(context, 0, wishList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
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

        joinButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View parentRow=(View) v.getParent();
                        ListView listView=(ListView) parentRow.getParent();
                        final int position =listView.getPositionForView(parentRow);
                        HomePage.join(position+1);
                    }
                }
        );
        // Return the completed view to render on screen
        return convertView;
    }


}
