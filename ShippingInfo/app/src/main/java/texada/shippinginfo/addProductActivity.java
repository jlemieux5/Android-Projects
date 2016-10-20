package texada.shippinginfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addProductActivity extends AppCompatActivity {
    private Button mDateButton;
    private Button mConfirmButton;
    private EditText mProductText;
    private EditText mLongitudeText;
    private EditText mLatitudeText;
    private EditText mElevationText;
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        // Link variables to their xml id
        mProductText = (EditText) findViewById(R.id.product_edit);
        mElevationText = (EditText) findViewById(R.id.elevation_edit);
        mLatitudeText = (EditText) findViewById(R.id.latitude_edit);
        mLongitudeText = (EditText) findViewById(R.id.longitude_edit);
        mConfirmButton = (Button) findViewById(R.id.confirm_button);
        mDateButton = (Button) findViewById(R.id.date_button);

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADD Date and ID functionality
                // Check to make sure there is text
                try {
                mProduct = new Product(5, null, mProductText.getText().toString(), Double.valueOf(mLongitudeText.getText().toString()),
                        Double.valueOf(mLatitudeText.getText().toString()), Integer.valueOf(mElevationText.getText().toString()));
                ProductList.get(getApplicationContext()).addProduct(mProduct);}
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Make sure all fields are filled" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent i = new Intent(context, addProductActivity.class);
        return i;
    }
}
