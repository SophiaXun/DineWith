package com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.DisplayWishList;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.Participation;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.Restaurant;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.User;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.WishList;

import java.util.ArrayList;

/**
 * Created by Yazhou on 11/17/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat
    public static final String LOG = "DatabaseHelper";
    public static final int DATABASE_VERSION =2;
    public static final String DATABASE_NAME="DineWith.db";

    //Table names
    public static final String TABLE_USER="User";
    public static final String TABLE_RESTAURANT="Restaurant";
    public static final String TABLE_WISHLIST="Wishlist";
    public static final String TABLE_PARTICIPATION="Participation";

    //Common column names
    public static final String KEY_ID="id";

    //USER Table
    public static final String KEY_USER_USERNAME="userName";
    public static final String KEY_USER_PWD="pwd";

    //RESTAURANT Table
    public static final String KEY_RESTAURANT_RESTAURANTNAME="restaurantName";
    public static final String KEY_RESTAURANT_LOCATION="location";

    //WISHLIST Table
    public static final String KEY_WISHLIST_USERID="userId";
    public static final String KEY_WISHLIST_DATE="date";
    public static final String KEY_WISHLIST_RESTAURANTID="restaurantId";
    public static final String KEY_WISHLIST_COMPLETEFLAG="completeFlag";

    //PARTICIPATION Table
    public static final String KEY_PARTICIPATION_USERID="userId";
    public static final String KEY_PARTICIPATION_WISHLISTID="wishListId";


    //USER table create statement
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_USERNAME + " TEXT,"
            + KEY_USER_PWD + " TEXT" + ")";


    //RESTAURANT table create statement
    public static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_RESTAURANT_RESTAURANTNAME + " TEXT,"
            + KEY_RESTAURANT_LOCATION + " TEXT" + ")";

    //WISHLIST table create statement
    public static final String CREATE_TABLE_WISHLIST = "CREATE TABLE " + TABLE_WISHLIST
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_WISHLIST_USERID + " INTEGER,"
            + KEY_WISHLIST_DATE + " TEXT," + KEY_WISHLIST_RESTAURANTID + " INTEGER,"+ KEY_WISHLIST_COMPLETEFLAG + " INTEGER"+")";

    //PARTICIPATION table create statement
    public static final String CREATE_TABLE_PARTICIPATION = "CREATE TABLE " + TABLE_PARTICIPATION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PARTICIPATION_WISHLISTID + " INTEGER,"
            + KEY_PARTICIPATION_USERID + " INTEGER" +")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_WISHLIST);
        db.execSQL(CREATE_TABLE_PARTICIPATION);
/////////////////////////////////////////////////////////////////////////////////////////
//        onCreate(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPATION);

        // create new tables
        onCreate(db);
    }

    /*
     * Creating a USER TABLE
    */
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_USER_USERNAME, user.getName());
        values.put(KEY_USER_PWD, user.getPwd());
        // insert row
        long longUserId=db.insert(TABLE_USER,null,values);

        return longUserId;
    }

    public long createRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, restaurant.getId());
        values.put(KEY_RESTAURANT_RESTAURANTNAME, restaurant.getName());
        values.put(KEY_RESTAURANT_LOCATION, restaurant.getLocation());

        // insert row
        long longRestaurantId=db.insert(TABLE_RESTAURANT,null,values);

        return longRestaurantId;
    }

    public long createWishlist(WishList wishList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,wishList.getId());
        values.put(KEY_WISHLIST_USERID,wishList.getUserId());
        values.put(KEY_WISHLIST_DATE, wishList.getDate());
        values.put(KEY_WISHLIST_RESTAURANTID, wishList.getRestaurantId());
        values.put(KEY_WISHLIST_COMPLETEFLAG, wishList.getCompleteFlag());

        // insert row
        long longWishlistId=db.insert(TABLE_WISHLIST,null,values);

        return longWishlistId;
    }

    public long createParticipation(Participation participation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, participation.getId());
        values.put(KEY_PARTICIPATION_USERID, participation.getUserId());
        values.put(KEY_PARTICIPATION_WISHLISTID, participation.getWishListId());

        // insert row
        long longParticipationId=db.insert(TABLE_PARTICIPATION,null,values);

        return longParticipationId;
    }

    public boolean insertWishList(int userId, String date,int restaurantId,int completeFlag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WISHLIST_USERID, userId);
        values.put(KEY_WISHLIST_DATE, date);
        values.put(KEY_WISHLIST_RESTAURANTID, restaurantId);
        values.put(KEY_WISHLIST_COMPLETEFLAG, completeFlag);

        long insertResult =db.insert(TABLE_WISHLIST,null,values);

        if(insertResult==-1){
            Log.d("DatabaseHelper.insertWL","The insert of wishlist is failed");
            return false;
        }else{
            Log.d("DatabaseHelper.insertWL","The insert of wishlist is sucess");
            return true;
        }

    }

    public User getUser(long Id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "+ KEY_ID + " = " + Id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User user = new User();
        user.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        user.setName((c.getString(c.getColumnIndex(KEY_USER_USERNAME))));
        user.setPwd(c.getString(c.getColumnIndex(KEY_USER_PWD)));

        return user;
    }

    public boolean Login(String name, String pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT PWD FROM " + TABLE_USER + " WHERE "+ KEY_USER_USERNAME+ " =  '" + name+"'";
//        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE "+KEY_USER_USERNAME+"= 'Sophia'";
        Cursor c=db.rawQuery(selectQuery,null);

        if(c!=null)
            c.moveToFirst();

        String rightPwd = c.getString(c.getColumnIndex(KEY_USER_PWD));
        if(pwd.equals(rightPwd)){
            return true;
        }else{
            return false;
        }
    }

    public Restaurant getRestaurant(long Id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "+ KEY_ID + " = " + Id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Restaurant restaurant= new Restaurant();
        restaurant.setName(c.getString(c.getColumnIndex(KEY_RESTAURANT_RESTAURANTNAME)));
        restaurant.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        restaurant.setLocation(c.getString(c.getColumnIndex(KEY_RESTAURANT_LOCATION)));

        return restaurant;
    }

    public Participation getParticipation(long Id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PARTICIPATION + " WHERE "+ KEY_ID + " = " + Id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Participation participation= new Participation();
        participation.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        participation.setWishListId(c.getInt(c.getColumnIndex(KEY_PARTICIPATION_WISHLISTID)));
        participation.setUserId(c.getInt(c.getColumnIndex(KEY_PARTICIPATION_USERID)));

        return participation;
    }

    public ArrayList<DisplayWishList> displayAllWishList(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_WISHLIST ;
        ArrayList<DisplayWishList> sqlDisplayWishListArrayList=new ArrayList<>();
        Cursor c = db.rawQuery(selectQuery, null);


        if (c==null){
            Log.d("DatabaseHelper","No wish list in DB");
            return null;
        }else{
            c.moveToFirst();

            do{
            int wishListId=c.getInt(c.getColumnIndex(KEY_ID));
            Log.i("wishId",Integer.toString(wishListId));

            int wishListUserId=c.getInt(c.getColumnIndex(KEY_WISHLIST_USERID));
            String wishListDate = c.getString(c.getColumnIndex(KEY_WISHLIST_DATE));
            int wishListRestaurantId = c.getInt(c.getColumnIndex(KEY_WISHLIST_RESTAURANTID));
            String wishListCompleteFlag = Integer.toString(c.getColumnIndex(KEY_WISHLIST_COMPLETEFLAG));
            String wishListUserName;
            String wishListRestaurantName;
            ArrayList<String> wishListParticipants=new ArrayList<>();

            // query the username by userId
            String userName=getUserNameByUserId(wishListUserId,db);
            Log.d("userName",userName);


            // query the username by userId
            String restaurantName=getRestaurantNameByRestaurantId(wishListRestaurantId,db);
            Log.d("restaurantName",restaurantName);

            // query the name or participants by userId
            wishListParticipants=getParticipantsByWishListId(wishListId,db);

            //form a DisplayWishList
            DisplayWishList displayWishList=new DisplayWishList(userName,wishListDate,restaurantName,wishListParticipants);
            sqlDisplayWishListArrayList.add(displayWishList);
            }while(c.moveToNext());



                /*selectQuery="SELECT "+KEY_USER_USERNAME+" FROM "+TABLE_USER+" WHERE "+KEY_ID+" = " +wishListUserId;

                Cursor subC=db.rawQuery(selectQuery,null);
                if(subC==null){
                    Log.d("No user","in user");

                }else{
                    subC.moveToFirst();
                    wishListUserName=subC.getString(subC.getColumnIndex(KEY_USER_USERNAME));
                    Log.d("TUNI",Integer.toString(wishListUserId));
                    Log.d("TUNS",wishListUserName);
                }*/

                // query the restaurant by restaurantId
                /*selectQuery="SELECT "+KEY_RESTAURANT_RESTAURANTNAME+" FROM "+TABLE_RESTAURANT+" WHERE "+KEY_ID+" = " +wishListRestaurantId;
                subC=db.rawQuery(selectQuery,null);
                if(subC==null){
                    Log.d("No res","in res");

                }else{
                    subC.moveToFirst();
                    wishListRestaurantName=subC.getString(subC.getColumnIndex(KEY_RESTAURANT_RESTAURANTNAME));
                    Log.d("TUNI",wishListRestaurantId);
                    Log.d("TUNS",wishListRestaurantName);
                }
                // query the participants by userId
                selectQuery="SELECT "+KEY_PARTICIPATION_USERID+" FROM "+TABLE_PARTICIPATION+" WHERE "+KEY_PARTICIPATION_WISHLISTID+" = " +wishListId;
                subC=db.rawQuery(selectQuery,null);
                if(subC==null){
                    Log.d("No par","in wishlist");
                }else{
                    subC.moveToFirst();
                    do{
                        String wishListParticipantsId=subC.getString(subC.getColumnIndex(KEY_PARTICIPATION_USERID));
//                        selectQuery="SELECT "+KEY_USER_USERNAME+" FROM "+TABLE_USER+" WHERE "+KEY_PARTICIPATION_WISHLISTID+" = " +wishListId;
                        subC=db.rawQuery(selectQuery,null);
                    }while(subC.moveToNext());
                }*/
//            }while(c.moveToNext());
//                    ){
//                WishList wishList= new WishList();
//                wishList.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//                wishList.setUserId(c.getInt(c.getColumnIndex(KEY_WISHLIST_USERID)));
//                wishList.setDate(c.getString(c.getColumnIndex(KEY_WISHLIST_DATE)));
//                wishList.setRestaurantId(c.getInt(c.getColumnIndex(KEY_WISHLIST_RESTAURANTID)));
//                wishList.setCompleteFlag(c.getInt(c.getColumnIndex(KEY_WISHLIST_COMPLETEFLAG)));
//                sqlWishListArrayList.add(wishList);
//            }
            return sqlDisplayWishListArrayList;
        }

    }

    public ArrayList<String> getParticipantsByWishListId(int wishListId,SQLiteDatabase db){
//        String selectQuery="SELECT "+KEY_PARTICIPATION_USERID+" FROM "+TABLE_PARTICIPATION+" WHERE "+KEY_PARTICIPATION_WISHLISTID+" = " +wishListId;
        String selectQuery="SELECT * FROM "+TABLE_PARTICIPATION+" WHERE "+KEY_PARTICIPATION_WISHLISTID+" = " +wishListId;
//        String selectQuery="SELECT * FROM "+TABLE_PARTICIPATION;
        ArrayList<String> sqlUserName=new ArrayList<>();
        Cursor subC=db.rawQuery(selectQuery,null);
        if(subC.getCount()==0){
            Log.d("No participants","in participants");
        }else{
            subC.moveToFirst();
            do {
                int participateUserId = subC.getInt(subC.getColumnIndex(KEY_PARTICIPATION_USERID));
                int participateId = subC.getInt(subC.getColumnIndex(KEY_ID));
                int wishListIdinPar = subC.getInt(subC.getColumnIndex(KEY_PARTICIPATION_WISHLISTID));
                String userName=getUserNameByUserId(participateUserId,db);
                Log.d("***","***");
                Log.d("wishListId",Integer.toString(wishListIdinPar));
                Log.d("participateId",Integer.toString(participateId));
                Log.d("WishParName", userName);

                sqlUserName.add(userName);
            }while(subC.moveToNext());
        }
        return sqlUserName;
    }
    public String getUserNameByUserId(int userId,SQLiteDatabase db){
        String selectQuery="SELECT "+KEY_USER_USERNAME+" FROM "+TABLE_USER+" WHERE "+KEY_ID+" = " +userId;
        String sqlUserName=new String();
        Cursor subC=db.rawQuery(selectQuery,null);
        if(subC==null){
            Log.d("No user","in user");
        }else{
            subC.moveToFirst();
            sqlUserName=subC.getString(subC.getColumnIndex(KEY_USER_USERNAME));
//            Log.d("TUNI",Integer.toString(userId));
//            Log.d("TUNS",sqlUserName);
        }
        return sqlUserName;
    }

    public Cursor getAllRestaurantName(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery="SELECT "+KEY_RESTAURANT_RESTAURANTNAME+" FROM "+TABLE_RESTAURANT;

        Cursor subC=db.rawQuery(selectQuery,null);
        if(subC==null){
            Log.d("NO RES", "in RES");
        }
        return subC;
    }



    public String getRestaurantNameByRestaurantId(int restaurantId,SQLiteDatabase db){
        String selectQuery="SELECT "+KEY_RESTAURANT_RESTAURANTNAME+" FROM "+TABLE_RESTAURANT+" WHERE "+KEY_ID+" = " +restaurantId;
        String sqlRestaurantName=new String();
        Cursor subC=db.rawQuery(selectQuery,null);
        if(subC==null){
            Log.d("No res","in res");
        }else{
            subC.moveToFirst();
            sqlRestaurantName=subC.getString(subC.getColumnIndex(KEY_RESTAURANT_RESTAURANTNAME));
            Log.d("TUNI",Integer.toString(restaurantId));
            Log.d("TUNS",sqlRestaurantName);
        }
        return sqlRestaurantName;
    }



    public void generateMetadata(){



            //create Users
            User user1 = new User(1, "Sophia", "1");
            User user2 = new User(2, "Jason", "2");

            //create WishList
            WishList wishList1 = new WishList(1, 1, "11-15-2015", 1, 0);
            WishList wishList2 = new WishList(2, 1, "11-16-2015", 2, 0);
            WishList wishList3 = new WishList(3, 2, "11-17-2015", 1, 1);

            //create Restaurant
            Restaurant restaurant1 = new Restaurant(1, "Hello", "Beijing");
            Restaurant restaurant2 = new Restaurant(2, "World", "Sydney");

            //create Participation
            Participation participation1 = new Participation(1, 1 ,2 );
            Participation participation2 = new Participation(2, 2, 1);
            Participation participation3 = new Participation(3, 1, 1);


            // Inserting data in db
            //insert user into db
            long user1_id=createUser(user1);
            long user2_id=createUser(user2);

            //insert restaurant into db
            long restaurant1_id=createRestaurant(restaurant1);
            long restaurant2_id=createRestaurant(restaurant2);

            //insert wishList into db
            long wishLish1_id=createWishlist(wishList1);
            long wishList2_id=createWishlist(wishList2);
            long wishList3_id=createWishlist(wishList3);

            //insert participation into db
            long participation1_id=createParticipation(participation1);
            long participation2_id=createParticipation(participation2);
            long participation3_id=createParticipation(participation3);



    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
