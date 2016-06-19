package com.ray.joe.barcodescanner;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    final PopupWindow popup = new PopupWindow();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    final ArrayList<String> list = new ArrayList<>();
    ListView lv;
    IntentIntegrator integrator = new IntentIntegrator(this);
    public String[] values;
    HoldValues g;
    Button b;
    int tempPosition =0;
    String tempString="";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        lv = (ListView)findViewById(R.id.listView);
        values=  new String[]{"All Campers Accounted for!"};
        list.add(values[0]);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        g = (HoldValues)getApplication();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, g.getMissing());
        lv.setAdapter(adapter);
        b = (Button)findViewById(R.id.button3);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                showPopup(position);
                //Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void showPopup(int positionText){
        tempPosition = positionText;
        tempString = g.getMissingFromID(positionText);
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.continuous_scan, viewGroup);
        //final PopupWindow popup = new PopupWindow();
        popup.setContentView(layout);
        popup.setFocusable(true);
        popup.setWidth(400);
        popup.setHeight(300);
        popup.setBackgroundDrawable(new BitmapDrawable());
        ((TextView)popup.getContentView().findViewById(R.id.popupText)).setText("Mark " + g.getMissingFromID(positionText) + " as accounted for?");
        popup.showAtLocation(layout, Gravity.CENTER, 0,0);

    }

    public void popupRemove(View v){
        popup.dismiss();
        g.removeMissingPopup(tempString);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        if(g.getMissingSize() == 0) {
            FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
            fl.setBackgroundColor(0xff00ff00);
            adapter = adapter2;
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
            Toast.makeText(this, "All Campers Accounted for!", Toast.LENGTH_LONG).show();
            b.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
            else {
                if(g.getMissing_idIndex(result.getContents()) >= 0) {
                    if (g.getMissingSize() > 1) {
                        g.removeMissing(result.getContents());
                        //list.remove(list.indexOf(result.getContents()));
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                        integrator.initiateScan();
                    } else {
                        FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
                        fl.setBackgroundColor(0xff00ff00);
                        adapter = adapter2;
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        Toast.makeText(this, "All Campers Accounted for!", Toast.LENGTH_LONG).show();
                        b.setEnabled(false);
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
        integrator.initiateScan();
    }
    public void clickOnReset(View v){
        g.reset();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, g.getMissing());
        lv.setAdapter(adapter);
        FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
        fl.setBackgroundColor(0xffffffff);
        b.setEnabled(true);
    }
}
