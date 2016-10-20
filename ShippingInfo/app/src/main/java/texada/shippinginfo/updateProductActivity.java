package texada.shippinginfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class updateProductActivity extends AppCompatActivity {

    private String mProductName;
    private static final String PRODUCT_NAME_STRING = "ShippingInfo.product_name";
    private TextView mProductText;
    private Spinner mDateSpinner;
    private Button mUpdateButton;
    private EditText mLongText;
    private EditText mLatText;
    private EditText mElvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_product);
        mProductName = getIntent().getStringExtra(PRODUCT_NAME_STRING);
        mProductText = (TextView) findViewById(R.id.product_update);
        mProductText.setText(mProductName);
        mUpdateButton = (Button) findViewById(R.id.confirm_update_button);
        mElvText = (EditText) findViewById(R.id.elevation_update);
        mLongText = (EditText) findViewById(R.id.longitude_update);
        mLatText = (EditText) findViewById(R.id.latitude_update);


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = createProduct();
                try {
                ProductList.get(getApplicationContext()).updateProduct(product); }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Make sure all fields are filled", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Setting the dates for the spinner from the database
        mDateSpinner = (Spinner) findViewById(R.id.date_update_spinner);
        Cursor cursor = ProductList.get(this).getDateCursor(mProductName);
        String[] columns = new String[]{shipDbSchema.shipTable.Cols.DATE};
        int [] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter scursorAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursor, columns, to);
        scursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateSpinner.setAdapter(scursorAdapter);

    }

    public Product createProduct(){
        Product product;
        TextView textView = (TextView)mDateSpinner.getSelectedView();
        String mdate = textView.getText().toString();
        product = new Product(10, mdate, mProductName, Double.valueOf(mLongText.getText().toString()), Double.valueOf(mLatText.getText().toString()), Integer.valueOf(mElvText.getText().toString()));
        return product;
    }

    public static Intent newIntent(Context context, String productName){
        Intent i = new Intent(context, updateProductActivity.class);
        i.putExtra(PRODUCT_NAME_STRING, productName);
        return i;
    }
}
