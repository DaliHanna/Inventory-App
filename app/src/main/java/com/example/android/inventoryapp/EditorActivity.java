package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;
import com.example.android.inventoryapp.data.ProductDbHelper;

public class EditorActivity extends AppCompatActivity {
    private EditText mProductName;
    private EditText mPrice;
    private EditText mQuantity;
    private EditText mSupplierName;
    private EditText mSupplierPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        mProductName = findViewById(R.id.edit_product_name);
        mPrice = findViewById(R.id.edit_price);
        mQuantity = findViewById(R.id.edit_quantity);
        mSupplierName = findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumber = findViewById(R.id.edit_supplier_phone_number);

    }

    private void insertProduct() {
        String productName = mProductName.getText().toString().trim();
        String priceString = mPrice.getText().toString().trim();
        String quantityString = mQuantity.getText().toString().trim();
        String supplierName = mSupplierName.getText().toString().trim();
        String suplierPhoneNumberString = mSupplierPhoneNumber.getText().toString().trim();

        int price = Integer.parseInt(priceString);
        int quantity = Integer.parseInt(quantityString);
        int supplierPhoneNumber = Integer.parseInt(suplierPhoneNumberString);
        ProductDbHelper productDbHelper = new ProductDbHelper(this);

        SQLiteDatabase db = productDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierName);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, supplierPhoneNumber);

        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.addedProduct) + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save product to database
                insertProduct();
                // Exit activity
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
