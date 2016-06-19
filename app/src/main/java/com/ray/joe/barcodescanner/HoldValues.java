package com.ray.joe.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class HoldValues extends Application {

    private String[] temp = {"Jack Jones", "Jill Jones", "Bobby Smith"};
    private String[] temp_id = {"0", "1", "2"};
    private ArrayList<String> campers = new ArrayList<>();
    private ArrayList<String> campers_id = new ArrayList<>();
    private ArrayList<String> missing;
    private ArrayList<String> missing_id;
    private ArrayList<String> nullList;

    public void reset(){
        campers.clear();
        campers_id.clear();
        for(int x = 0; x<temp.length;x++){
            campers.add(temp[x]);
            campers_id.add(temp_id[x]);
        }
        missing = campers;
        missing_id = campers_id;
    }

    public void removeMissing(String s){
        int tempInt = missing_id.indexOf(s);
        missing_id.remove(tempInt);
        missing.remove(tempInt);
    }

    public void removeMissingPopup(String s){
        int tempInt = missing.indexOf(s);
        missing_id.remove(tempInt);
        missing.remove(tempInt);
    }

    public int getMissing_idIndex(Object o){
        return missing_id.indexOf(o);
    }

    public int getMissingSize() {
        return missing.size();
    }

    public ArrayList<String> getMissing() {
        return missing;
    }

    public String getMissingFromID(int x){
        return missing.get(x).toString();

    }
}



