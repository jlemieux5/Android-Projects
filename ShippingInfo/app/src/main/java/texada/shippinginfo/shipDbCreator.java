package texada.shippinginfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import texada.shippinginfo.shipDbSchema.shipTable;

/**
 * Created by Josh on 10/18/2016.
 */
public class shipDbCreator extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "shipInfo.db";

    public shipDbCreator(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Create the initial table
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + shipTable.NAME + "(" + " _id integer primary key autoincrement, " + shipTable.Cols.ID + ", " +
        shipTable.Cols.PRODUCT + ", " + shipTable.Cols.DATE + ", " + shipTable.Cols.LONG + ", " +
        shipTable.Cols.LAT + ", " + shipTable.Cols.ELV + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
