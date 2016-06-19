package com.ray.joe.barcodescanner;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
//import com.journeyapps.barcodescanner.BarcodeCallback;
//import com.journeyapps.barcodescanner.BarcodeResult;
//import com.journeyapps.barcodescanner.BarcodeView;
//import com.journeyapps.barcodescanner.CompoundBarcodeView;
//import com.journeyapps.barcodescanner.DecoratedBarcodeView;
//import com.google.zxing.ResultPoint;

public class Scanner extends AppCompatActivity {
    /*private static final String TAG = ContinuousCaptureActivity.class.getSimpleName();
    private DecoratedBarcodeView barcodeView;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                barcodeView.setStatusText(result.getText());
            }
            //Added preview of scanned barcode
            //ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
            //imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };*/

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ArrayAdapter<String> adapter;
    final ArrayList<String> list = new ArrayList<>();
    ListView lv;
    IntentIntegrator integrator = new IntentIntegrator(this);
    public String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        lv = (ListView)findViewById(R.id.listView);
        values = new String[]{"0", "1"};
        HoldValues g = (HoldValues)getApplication();
        //Toast.makeText(this, ("stuff: " + g.getCampersString()), Toast.LENGTH_SHORT).show();
        //Below is the correct list---- Still need to get writers camp numbers and names
        //{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54"}
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        //g.setCampers(list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        //barcodeView.decodeContinuous(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
            else {
                if(list.indexOf(result.getContents()) >= 0) {
                    if (list.size() > 1) {
                        list.remove(list.indexOf(result.getContents()));
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                        integrator.initiateScan();
                    } else {
                        FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
                        fl.setBackgroundColor(0xff00ff00);
                        list.add("All Campers Accounted for!");
                        list.remove(list.indexOf(result.getContents()));
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        Toast.makeText(this, "All Campers Accounted for!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(this, "Barcode not recognized or already scanned", Toast.LENGTH_LONG).show();
                    integrator.initiateScan();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void clickOnList(View v){
        //setContentView(R.layout.continuous_scan);
        integrator.initiateScan();
        //barcodeView = (CompoundBarcodeView) findViewById(R.id.barcode_scanner);
        //barcodeView.decodeContinuous(callback);

    }
    public void clickOnReset(View v){
        list.clear();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
        fl.setBackgroundColor(0xffffffff);
    }

}
