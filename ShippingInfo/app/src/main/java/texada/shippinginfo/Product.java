package texada.shippinginfo;

import java.util.Date;

/**
 * Created by Josh on 10/18/2016.
 */
public class Product {
    private int mId;
    private String mDate;
    private String mProductName;
    private double mLongitude;
    private double mLatitude;
    private int mElevation;

    public Product(int id, String date, String productName, double longitude, double latitude, int elevation){
        mId = id;
        mDate = date;
        mProductName = productName;
        mLongitude = longitude;
        mLatitude = latitude;
        mElevation = elevation;
    }

    public int getId(){
        return mId;
    }

    public String getDate(){
        if(mDate == null){
            mDate = new Date().toString();
        }
        return mDate;
    }

    public String getProductName(){
        return mProductName;
    }

    public double getLongitude(){
        return mLongitude;
    }

    public double getLatitude(){
        return mLatitude;
    }

    public int getElevation(){
        return mElevation;
    }
}
