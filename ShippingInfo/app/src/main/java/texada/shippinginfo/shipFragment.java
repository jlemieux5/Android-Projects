package texada.shippinginfo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Josh on 10/18/2016.
 */
public class shipFragment extends Fragment{
    private Button mAddButton;
    private Button mUpdateButton;
    private Button mViewButton;
    private Button mReportButton;
    private Button mTrackButton;
    private Button mDeleteButton;
    private Spinner mProductList;
    private Cursor cursor;
    private SimpleCursorAdapter scursorAdapter;

    public static shipFragment newInstance(){
        return new shipFragment();
    }

    @Override
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);
    }

    @Override
    public void onResume(){
        super.onResume();

        // Update the contents of the product drop down
        Cursor cursor = ProductList.get(getActivity()).getProductCursor();
        String[] columns = new String[]{shipDbSchema.shipTable.Cols.PRODUCT};
        int [] to = new int[] {android.R.id.text1};
        scursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, cursor, columns, to);
        scursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductList.setAdapter(scursorAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState){
        View view = inflater.inflate(R.layout.activity_ship_fragment, container, false);

        // Creating the cursor and cursor adapter for the spinner
        Cursor cursor = ProductList.get(getActivity()).getProductCursor();
        String[] columns = new String[]{shipDbSchema.shipTable.Cols.PRODUCT};
        int [] to = new int[] {android.R.id.text1};
        scursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, cursor, columns, to);
        scursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Assigning button variables to their xml layout
        mAddButton = (Button) view.findViewById(R.id.add_button);
        mViewButton = (Button) view.findViewById(R.id.view_button);
        mUpdateButton = (Button) view.findViewById(R.id.update_button);
        mDeleteButton = (Button) view.findViewById(R.id.delete_button);
        mReportButton = (Button) view.findViewById(R.id.report_button);

        mProductList = (Spinner) view.findViewById(R.id.product_spinner);
        mProductList.setAdapter(scursorAdapter);

        // Open the Add Product Page if the add button is selected
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = addProductActivity.newIntent(getActivity());
                startActivity(i);
            }
        });

        // Open the view product page and send the product name that they selected to populate the data
        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)mProductList.getSelectedView();
                String result = textView.getText().toString();
                Intent i = viewProductActivity.newIntent(getActivity(), result);
                startActivity(i);
            }
        });


        // Open the update product page with the product name passed in to populate the data
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)mProductList.getSelectedView();
                String result = textView.getText().toString();
                Intent i = updateProductActivity.newIntent(getActivity(), result);
                startActivity(i);
            }
        });

        // Delete the product from the database, create message to inform user
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)mProductList.getSelectedView();
                String result = textView.getText().toString();
                ProductList.get(getActivity()).deleteProduct(result);
                Toast.makeText(getActivity(), "Deleted " + result + " from Database", Toast.LENGTH_LONG).show();
            }
        });

        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = reportProductActivity.newIntent(getActivity());
                startActivity(i);
            }
        });

        return view;
    }


}
