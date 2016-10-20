package texada.shippinginfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import static texada.shippinginfo.shipDbSchema.*;

public class viewProductActivity extends AppCompatActivity {

    private String productName;
    private String mdate;
    private static final String PRODUCT_NAME_STRING = "ShippingInfo.product_name";
    private TextView mproductText;
    private TextView mLatText;
    private TextView mLongText;
    private TextView mElvText;
    private Spinner mDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product);
        productName = getIntent().getStringExtra(PRODUCT_NAME_STRING);
        mproductText = (TextView) findViewById(R.id.product_name);
        mproductText.setText(productName);

        // Creating the cursor and simpleCursorAdapter to populate the spinner
        mDateList = (Spinner) findViewById(R.id.date_spinner);
        Cursor cursor = ProductList.get(this).getDateCursor(productName);
        String[] columns = new String[]{shipTable.Cols.DATE};
        int [] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter scursorAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, columns, to);
        scursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateList.setAdapter(scursorAdapter);

        mDateList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                attachText();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mElvText = (TextView) findViewById(R.id.elevation_text);
        mLatText = (TextView) findViewById(R.id.latitude_text);
        mLongText = (TextView) findViewById(R.id.longitude_text);
    }

    public static Intent newIntent(Context context, String productName){
        Intent i = new Intent(context, viewProductActivity.class);
        i.putExtra(PRODUCT_NAME_STRING, productName);
        return i;
    }

    // Attatch the longitude, latitude and elevation to the text fields based on the Product and Time selected
    private void attachText(){
        TextView textView = (TextView)mDateList.getSelectedView();
        mdate = textView.getText().toString();
        Cursor cursor = ProductList.get(this).getExtraInfo(productName, mdate);
        cursor.moveToFirst();
        String longitude = cursor.getString(cursor.getColumnIndex(shipTable.Cols.LONG));
        String latitude = cursor.getString(cursor.getColumnIndex(shipTable.Cols.LAT));
        String elevation = cursor.getString(cursor.getColumnIndex(shipTable.Cols.ELV));
        mLongText.setText(longitude);
        mElvText.setText(elevation);
        mLatText.setText(latitude);
    }
}
