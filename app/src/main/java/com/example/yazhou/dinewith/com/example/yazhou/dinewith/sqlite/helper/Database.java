package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.Participation;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.Restaurant;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.User;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class Database {
    public Database(){

    }

    public DatabaseHelper createMetaData(DatabaseHelper db){


        //create Users
        User user1 = new User(1, "Sophia", "1");
        User user2 = new User(2, "Evan", "2");
        User user3 = new User(3, "Emily", "3");

        //create WishList
        WishList wishList1 = new WishList(1, 1, "11-15-2015", 1, 0);
        WishList wishList2 = new WishList(2, 1, "11-16-2015", 2, 0);
        WishList wishList3 = new WishList(3, 2, "11-17-2015", 1, 1);
        WishList wishList4 = new WishList(4, 3, "11-18-2015", 3, 0);

        //create Restaurant
        Restaurant restaurant1 = new Restaurant(1, "Jim's", "Beijing");
        Restaurant restaurant2 = new Restaurant(2, "Grilled", "Sydney");
        Restaurant restaurant3 = new Restaurant(3, "Burp", "Adelaide");


        //create Participation
        Participation participation1 = new Participation(1, 1, 2 );
        Participation participation2 = new Participation(2, 1, 3);
        Participation participation3 = new Participation(3, 3, 1);

        //insert user into db
        long user1_id=db.createUser(user1);
        long user2_id=db.createUser(user2);
        long user3_id=db.createUser(user3);


        //insert restaurant into db
        long restaurant1_id=db.createRestaurant(restaurant1);
        long restaurant2_id=db.createRestaurant(restaurant2);
        long restaurant3_id=db.createRestaurant(restaurant3);

        //insert wishList into db
        long wishLish1_id=db.createWishlist(wishList1);
        long wishList2_id=db.createWishlist(wishList2);
        long wishList3_id=db.createWishlist(wishList3);
        long wishList4_id=db.createWishlist(wishList4);

        //insert participation into db
        long participation1_id=db.createParticipation(participation1);
        long participation2_id=db.createParticipation(participation2);
        long participation3_id=db.createParticipation(participation3);

        return db;
    }
}
