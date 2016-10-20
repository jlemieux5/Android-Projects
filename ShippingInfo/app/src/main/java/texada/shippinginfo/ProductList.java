package texada.shippinginfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import texada.shippinginfo.shipDbSchema.shipTable;

/**
 * Created by Josh on 10/18/2016.
 */
public class ProductList {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static ProductList sProductList;

    // Constructor for ProductList, will be used to create, add, delete update database
    private ProductList(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new shipDbCreator(mContext).getWritableDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + shipTable.NAME, null);
        if(cursor.getCount() == 0)
            addInitialData();
    }

    // creates a contentValues for updating/adding to database
    private static ContentValues getContentValues(Product product){
        ContentValues values = new ContentValues();
        values.put(shipTable.Cols.ID, product.getId());
        values.put(shipTable.Cols.PRODUCT, product.getProductName());
        values.put(shipTable.Cols.DATE, product.getDate().toString());
        values.put(shipTable.Cols.LONG, product.getLongitude());
        values.put(shipTable.Cols.LAT, product.getLatitude());
        values.put(shipTable.Cols.ELV, product.getElevation());

        return values;
    }

    // Gets a cursor that has selected rows depeding on product name
    public Cursor getDateCursor(String productName){
        String query = "SELECT " + "*" + " FROM " + shipTable.NAME + " WHERE " + shipTable.Cols.PRODUCT + " = " +
                "\"" + productName + "\"";
        Cursor cursor = mDatabase.rawQuery(query, null);
        return cursor;
    }

    // Gets a unique list of product names
    public Cursor getProductCursor(){
        String q = "SELECT " + "*" + " FROM " + shipTable.NAME + " GROUP BY " + shipTable.Cols.PRODUCT;
        Cursor cursor = mDatabase.rawQuery(q, null);

        return cursor;
    }

    // Gets long, latitude and elevation for a specefic db entry
    public Cursor getExtraInfo(String productName, String date){
        String query = "SELECT " + "*" + " FROM " + shipTable.NAME + " WHERE " + shipTable.Cols.DATE + " = " +
                "\"" + date + "\"" + " AND " + shipTable.Cols.PRODUCT + " = " + "\"" + productName + "\"";
        Cursor cursor = mDatabase.rawQuery(query, null);
        return cursor;
    }


    private Cursor queryCrimes(String where, String[] whereArgs) {
        Cursor cursor = mDatabase.query(shipTable.NAME, null, where, whereArgs, null, null, null);
        return cursor;
    }

    // Creates a new singleton ProductList
    public static ProductList get(Context context){
        if(sProductList == null){
            sProductList = new ProductList(context);
        }
        return sProductList;
    }

    // Adds a new product to the db
    public void addProduct(Product product){
        ContentValues values = getContentValues(product);
        mDatabase.insert(shipTable.NAME, null, values);
    }

    // Updates a row in the db depending on the product that was given
    public void updateProduct(Product product){
        String whereClause = shipTable.Cols.PRODUCT + " = "
                + "\"" + product.getProductName() + "\"" + " AND " + shipTable.Cols.DATE + " = " + "\"" + product.getDate().toString() + "\"";
        ContentValues values = getContentValues(product);
        mDatabase.update(shipTable.NAME, values, whereClause, null);
    }

    // Deletes all rows containing the product name
    public void deleteProduct(String productName){
        mDatabase.delete(shipTable.NAME, shipTable.Cols.PRODUCT + " =?" ,new String[]{productName});
    }

    public void addInitialData(){
        Product product = new Product(1, "2016-10-12T12:00:00-05:00", "Cesna 120", 43.559112, -80.286693, 500);
        addProduct(product);
        product = new Product(1, "2016-10-13T12:00:00-05:00", "Cesna 120", 42.559112, -79.286693, 550);
        addProduct(product);
        product = new Product(1, "2016-10-14T12:00:00-05:00", "Cesna 120", 43.559112, -85.286693, 600);
        addProduct(product);
        product = new Product(1, "2016-10-15T12:00:00-05:00", "Cesna 120", 44.559112, -81.286693, 650);
        addProduct(product);
        product = new Product(2, "2016-10-12T12:00:00-05:00", "DC-6 Twin Otter", 43.459112, -80.386693, 500);
        addProduct(product);
        product = new Product(2, "2016-10-13T12:00:00-05:00", "DC-6 Twin Otter", 42.459112, -79.386693, 550);
        addProduct(product);
        product = new Product(2, "2016-10-14T12:00:00-05:00", "DC-6 Twin Otter", 43.459112, -85.386693, 450);
        addProduct(product);
        product = new Product(2, "2016-10-15T12:00:00-05:00", "DC-6 Twin Otter", 44.459112, -81.386693, 400);
        addProduct(product);
        product = new Product(3, "2016-10-15T12:01:00-05:00", "Piper M600", 44.459112, -81.386693, 500);
        addProduct(product);
        product = new Product(3, "2016-10-15T12:02:00-05:00", "Piper M600", 45.459112, -82.386693, 600);
        addProduct(product);
        product = new Product(3, "2016-10-15T12:03:00-05:00", "Piper M600", 46.459112, -83.386693, 700);
        addProduct(product);
        product = new Product(3, "2016-10-15T12:04:00-05:00", "Piper M600", 47.459112, -84.386693, 800);
        addProduct(product);
        product = new Product(3, "2016-10-15T12:05:00-05:00", "Piper M600", 48.459112, -85.386693, 900);
        addProduct(product);
    }
}
