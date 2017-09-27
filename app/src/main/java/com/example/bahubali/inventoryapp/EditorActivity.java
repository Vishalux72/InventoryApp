package com.example.bahubali.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bahubali.inventoryapp.data.ProductContract;
import com.example.bahubali.inventoryapp.data.ProductDbHelper;

import java.util.ArrayList;
import java.util.List;

public class EditorActivity extends AppCompatActivity {

    /*Edit text field to enter the product name*/
    private EditText mNameEditText;

    /*Edit text field to enter the product price*/
    private EditText mPriceEditText;

    /*Edit text field to enter the product quantity*/
    private Spinner mQuantitySpinner;

    /*Edit text field to enter the name of the supplier*/
    private EditText mSupplierText;

    /*Quantity of the product is varying so the default value of the product*/

    private int mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Find all the relevant views that we will need to read the input from
        mNameEditText = (EditText)findViewById(R.id.edit_text_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_text_price);

        mSupplierText = (EditText) findViewById(R.id.edit_text_supplier);
        mQuantitySpinner =(Spinner) findViewById(R.id.spinner_quantity);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        ArrayAdapter<Integer> quantitySpinnnerAdapter = new ArrayAdapter<Integer>(this,
                R.layout.support_simple_spinner_dropdown_item,list);
        mQuantitySpinner.setAdapter(quantitySpinnnerAdapter);


    }

    /**
     * Get user input from editor and save new pet into database.
     */
    private void insertProduct(){
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String productNameString = mNameEditText.getText().toString().trim();
        String productPriceString = mPriceEditText.getText().toString().trim();
        String productSupplierString = mSupplierText.getText().toString().trim();
        int price = Integer.parseInt(productPriceString);

        //Create database helper
        ProductDbHelper productDbHelper = new ProductDbHelper(this);

        //Get the database in the write mode
        SQLiteDatabase db = productDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,productNameString);
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,price);
        contentValues.put(String.valueOf(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY),mQuantity);
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER,productSupplierString);


        //Insert a new row for the product in the database,returning ID of that new row
        long newRowId = db.insert(ProductContract.ProductEntry.TABLE_NAME,null,contentValues);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()){
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertProduct();
                finish();
                return true;
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
