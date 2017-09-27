package com.example.bahubali.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by bahubali on 9/24/2017.
 */

public class ProductContract implements BaseColumns {


    /*Name of the database table
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.*/

    private ProductContract(){}
    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class ProductEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID number for the product (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME =" Product name";

        /**
         * Price of the Product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "Price";

       /*
       *Name of the Supplier of the  product
       *
       * Type:TEXT
        */
       public final static String COLUMN_PRODUCT_SUPPLIER = "Supplier";

       /*
       The quantity of the product

       Type:INTEGER
        */
       public final static String COLUMN_PRODUCT_QUANTITY = "Quantity";




    }


}

