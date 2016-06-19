package com.ray.joe.barcodescanner;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startScan(View v){
        //{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54"}
        //{"0", "1"}
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> list_id = new ArrayList<>();
        String[] temp = {"0", "1"};
        String[] temp_id = {"jack", "jill"};
        for (int i = 0; i < temp.length; ++i) {
            list.add(temp[i]);
            list_id.add(temp_id[i]);
        }
        HoldValues g = (HoldValues)getApplication();
        g.setCampers(list);
        g.setCampersId(list_id);
        g.reset();
        //Toast.makeText(this, "button", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Scanner.class);
        this.startActivity(i);
    }
}