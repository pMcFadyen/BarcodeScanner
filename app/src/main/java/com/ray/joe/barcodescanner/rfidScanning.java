package com.ray.joe.barcodescanner;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class rfidScanning extends AppCompatActivity {

    String rfidCode = "";
    HoldValues g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfid_scanning);
        rfidCode = "";
        g = (HoldValues)getApplication();
        g.removeMissing("4");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode)
        {
            case(KeyEvent.KEYCODE_ENTER):
                Toast.makeText(getBaseContext(), rfidCode, Toast.LENGTH_SHORT).show();
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                g.removeMissing(rfidCode);
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
            case(KeyEvent.KEYCODE_BACK):
                super.onBackPressed();
                return false;
            default:
                return false;
        }
    }
}
