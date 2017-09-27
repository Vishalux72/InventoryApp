package com.example.bahubali.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.bahubali.inventoryapp.data.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;

/**
 * Created by bahubali on 9/24/2017.
 */

public class ProductDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ProductDbHelper.class.getSimpleName();

    /*Name of the database file*/
    private static final String DATABASE_NAME = "track.db";

    /*Database version of the file*/
    private static final int DATABASE_VERSION = 1;


    public ProductDbHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    /*This is called when the database is created for the first time*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PRODUCT_TABLE =  "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " ("
                + ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME+ " TEXT NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE+ " INTEGER, "
                + COLUMN_PRODUCT_QUANTITY + " INTEGER "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER + " TEXT NOT NULL);";

        Log.v(LOG_TAG,SQL_CREATE_PRODUCT_TABLE);

        //Execute the sql statement
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);




    }
    //This is called when the database needs to be upgraded.

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
