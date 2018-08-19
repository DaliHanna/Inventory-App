package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;
import com.example.android.inventoryapp.data.ProductDbHelper;

public class ProductActivity extends AppCompatActivity {
    private ProductDbHelper mProductDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mProductDbHelper = new ProductDbHelper(this);
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mProductDbHelper.getReadableDatabase();
        String[] projection = {ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER};

        Cursor cursor = db.query(ProductEntry.TABLE_NAME,projection,null,null,null,null,null);

        TextView displayView = (TextView) findViewById(R.id.text_view_product);

        try {
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext())
            {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                int price = cursor.getInt(priceColumnIndex);
                int quantity = cursor.getInt(quantityColumnIndex);
                String supplierName= cursor.getString(supplierNameColumnIndex);
                int supplierPhoneNumber = cursor.getInt(supplierPhoneNumberColumnIndex);

                displayView.append("\n"+id + " -- "+name+" -- "+price+" -- "+quantity+" -- "+supplierName+" -- "+supplierPhoneNumber);
            }
        }finally {
            cursor.close();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
