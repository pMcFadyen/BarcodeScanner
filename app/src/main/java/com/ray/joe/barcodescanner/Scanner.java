package com.ray.joe.barcodescanner;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
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

import android.content.Intent;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.view.KeyEvent.KEYCODE_0;
import static android.view.KeyEvent.KEYCODE_ENTER;

public class Scanner extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //Declarations
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
                if(((TextView)v).getText().toString() == "All Campers Accounted for!"){}
                else{showPopup(position);}
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        popup.setWidth(600);
        popup.setHeight(600);
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

    public String code;

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
                        //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
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
        Intent i = new Intent(this, ContinuousCaptureActivity.class);
        this.startActivity(i);

    }
    public void clickOnReset(View v){
        g.reset();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, g.getMissing());
        lv.setAdapter(adapter);
        FrameLayout fl = (FrameLayout)findViewById(R.id.fl);
        fl.setBackgroundColor(0xffffffff);
        b.setEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Scanner Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ray.joe.barcodescanner/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Scanner Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ray.joe.barcodescanner/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KEYCODE_ENTER){
            Toast.makeText(this, "enter pressed", Toast.LENGTH_LONG).show();
            g.removeMissing(code);
            //Scanner.class.
        }
        if (keyCode == KEYCODE_0) {
            code+="0";

        }
        if (keyCode == KeyEvent.KEYCODE_1) {
            code+="1";

        }
        if (keyCode == KeyEvent.KEYCODE_2) {
            code+="2";

        }
        if (keyCode == KeyEvent.KEYCODE_3) {
            code+="3";

        }
        if (keyCode == KeyEvent.KEYCODE_4) {
            code+="4";

        }
        if (keyCode == KeyEvent.KEYCODE_5) {
            code+="5";

        }
        if (keyCode == KeyEvent.KEYCODE_6) {
            code+="6";

        }
        if (keyCode == KeyEvent.KEYCODE_7) {
            code+="7";

        }
        if (keyCode == KeyEvent.KEYCODE_8) {
            code+="8";

        }
        if (keyCode == KeyEvent.KEYCODE_9) {
            code+="9";

        }

        return super.onKeyDown(keyCode, event);

    }*/
}
