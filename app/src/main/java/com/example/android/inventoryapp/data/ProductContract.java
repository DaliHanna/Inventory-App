package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class ProductContract {
    public static final class ProductEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "products";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME="Name";
        public final static String COLUMN_PRODUCT_PRICE="Price";
        public final static String COLUMN_PRODUCT_QUANTITY="Quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME="SupplierName";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "SupplierPhoneNumber";

    }
}
