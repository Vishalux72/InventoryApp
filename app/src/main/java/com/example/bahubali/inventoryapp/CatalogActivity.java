package com.example.bahubali.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bahubali.inventoryapp.data.ProductContract;
import com.example.bahubali.inventoryapp.data.ProductDbHelper;

public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private ProductDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

      //Setup fab to open editor activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this,EditorActivity.class);
                startActivity(intent);
            }
        });
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.












         mDbHelper = new ProductDbHelper(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + ProductContract.ProductEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_inventory);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /*helper method to insert hardcoded data in the database*/
    private void insertProduct(){
        //Get the database in the write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //Create a contentValues where  column names are the keys,
        //and the boat attributes are values
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,"Boat");
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,600);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,4);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER,"Amazon");

        /*
        Insert a new row in the database ,returning the id of that new row
        The first argument for the db.insert() is the products table name
        The second arguments provides the name of the column in which the framework
        can insert Null if the event that the content values is empty(if this is set to "null",then
        the framework will not insert a row when there is no values.
         */
        long newRowId = db.insert(ProductContract.ProductEntry.TABLE_NAME,null,values);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()){
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_new_data:
                insertProduct();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                //Do nothing for now;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
