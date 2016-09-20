package com.ray.joe.barcodescanner;

//import android.support.v7.app.AppCompatActivity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.view.KeyEvent.KEYCODE_0;
import static android.view.KeyEvent.KEYCODE_ENTER;


public class ContinuousCaptureActivity extends AppCompatActivity {
    private String code = "";
    public EditText ed;
    HoldValues g;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continous_scanner_2);
        g = (HoldValues) getApplication();
        //ed = (EditText) findViewById(R.id.editText);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KEYCODE_ENTER) {
            Toast.makeText(this, "Card Scanned", Toast.LENGTH_LONG).show();
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            g.removeMissing(code);
            code = "";
        }
        if (keyCode == KEYCODE_0) {
            code += "0";

        }
        if (keyCode == KeyEvent.KEYCODE_1) {
            code += "1";

        }
        if (keyCode == KeyEvent.KEYCODE_2) {
            code += "2";

        }
        if (keyCode == KeyEvent.KEYCODE_3) {
            code += "3";

        }
        if (keyCode == KeyEvent.KEYCODE_4) {
            code += "4";

        }
        if (keyCode == KeyEvent.KEYCODE_5) {
            code += "5";

        }
        if (keyCode == KeyEvent.KEYCODE_6) {
            code += "6";

        }
        if (keyCode == KeyEvent.KEYCODE_7) {
            code += "7";

        }
        if (keyCode == KeyEvent.KEYCODE_8) {
            code += "8";

        }
        if (keyCode == KeyEvent.KEYCODE_9) {
            code += "9";

        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ContinuousCapture Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ray.joe.barcodescanner/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ContinuousCapture Page", // TODO: Define a title for the content shown.
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
    public boolean onKey(View v, int keyCode, KeyEvent event){
        Toast.makeText(this, "key pressed", Toast.LENGTH_LONG).show();
        //super.onKeyDown(keyCode, event);

        switch (keyCode){
            case (KeyEvent.KEYCODE_0):
                code += "0";
                return false;
            case (KeyEvent.KEYCODE_1):
                code += "1";
                return false;
            case (KeyEvent.KEYCODE_2):
                code += "2";
                return false;
            case (KeyEvent.KEYCODE_3):
                code += "3";
                return false;
            case (KeyEvent.KEYCODE_4):
                code += "4";
                return false;
            case (KeyEvent.KEYCODE_5):
                code += "5";
                return false;
            case (KeyEvent.KEYCODE_6):
                code += "6";
                return false;
            case (KeyEvent.KEYCODE_7):
                code += "7";
                return false;
            case (KeyEvent.KEYCODE_8):
                code += "8";
                return false;
            case (KeyEvent.KEYCODE_9):
                code += "9";
                return false;
            case (KeyEvent.KEYCODE_ENTER):
                Toast.makeText(this, code, Toast.LENGTH_LONG).show();
                //remove <code
                return false;
            case (KeyEvent.KEYCODE_BACK):
                return false;
        }
        return false;

    }
    */
}
