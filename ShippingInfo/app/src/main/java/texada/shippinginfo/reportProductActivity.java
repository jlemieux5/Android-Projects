package texada.shippinginfo;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class reportProductActivity extends AppCompatActivity {

    private Button mExportButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_product);

        mExportButton = (Button) findViewById(R.id.export_button);
        mExportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent i = new Intent(context, reportProductActivity.class);
        return i;
    }

    // Create a file and save to internal storage
    private void createFile(){
        String filename = "shippingInfo.txt";
        String string = " ";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
