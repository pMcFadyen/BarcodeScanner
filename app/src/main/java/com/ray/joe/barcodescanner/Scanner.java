package com.ray.joe.barcodescanner;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.nfc.NfcAdapter;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import android.content.Intent;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scanner extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //Declarations
    private GoogleApiClient client;
    PopupWindow popup = new PopupWindow();
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
    EditText rfid; //= new EditText(findViewById(R.id.editText));
    String rfidCode="";
    //private NfcAdapter mNfcAdapter;

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
                if(((TextView)v).getText().toString() == "All Campers Accounted for!"){}
                else{showPopup(position);}
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode)
        {
            case(KeyEvent.KEYCODE_ENTER):
                Toast.makeText(getBaseContext(), rfidCode, Toast.LENGTH_SHORT).show();
                rfidCode = "";
                return true;
            case (KeyEvent.KEYCODE_0):
                rfidCode = rfidCode + "0";
                return true;
            case (KeyEvent.KEYCODE_1):
                rfidCode = rfidCode + "1";
                return true;
            case (KeyEvent.KEYCODE_2):
                rfidCode = rfidCode + "2";
                return true;
            case (KeyEvent.KEYCODE_3):
                rfidCode = rfidCode + "3";
                return true;
            case (KeyEvent.KEYCODE_4):
                rfidCode = rfidCode + "4";
                return true;
            case (KeyEvent.KEYCODE_5):
                rfidCode = rfidCode + "5";
                return true;
            case (KeyEvent.KEYCODE_6):
                rfidCode = rfidCode + "6";
                return true;
            case (KeyEvent.KEYCODE_7):
                rfidCode = rfidCode + "7";
                return true;
            case (KeyEvent.KEYCODE_8):
                rfidCode = rfidCode + "8";
                return true;
            case (KeyEvent.KEYCODE_9):
                rfidCode = rfidCode + "9";
                return true;
            case (KeyEvent.KEYCODE_BACK):
                super.onBackPressed();
                return false;
            default:
                return false;
        }
    }

    //Display Popup to remove items from the ListView
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
        PopupWindow popup2 = new PopupWindow();
        LinearLayout viewGroup2 = (LinearLayout) findViewById(R.id.llrfid);
        LayoutInflater layoutInflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater2.inflate(R.layout.activity_rfid_scanning, viewGroup2);
        //final PopupWindow popup = new PopupWindow();
        popup2.setContentView(layout);
        popup2.setFocusable(true);
        popup2.setWidth(400);
        popup2.setHeight(300);
        popup2.setBackgroundDrawable(new BitmapDrawable());
        popup2.showAtLocation(layout, Gravity.CENTER, 30,30);
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
