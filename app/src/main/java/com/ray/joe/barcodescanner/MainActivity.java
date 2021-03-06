package com.ray.joe.barcodescanner;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnKeyListener;

import java.util.ArrayList;

//{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54"}
//{"0", "1"}
public class MainActivity extends AppCompatActivity {

    String rfidCode="";
    TextView tv;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HoldValues g = (HoldValues)getApplication();
        rl = (RelativeLayout)findViewById(R.id.rL);
        g.reset();
        tv= (TextView)findViewById(R.id.textView);

    }


    public void startScan(View v){
        Intent i = new Intent(this, Scanner.class);
        this.startActivity(i);
    }

    public void secret(View v){
        Toast.makeText(this, "You found the Secret!", Toast.LENGTH_SHORT).show();
    }


    public boolean onKey(int keyCode, KeyEvent event){
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
            default:
                return false;
        }
    }

}
