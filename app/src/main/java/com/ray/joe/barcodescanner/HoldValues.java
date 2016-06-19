package com.ray.joe.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Application;

import java.util.ArrayList;

public class HoldValues extends Application {

    private ArrayList<String> campers;
    private ArrayList<String> campers_id;
    private ArrayList<String> missing;
    private ArrayList<String> missing_id;

    public ArrayList<String> getCampers() {
        return campers;
    }

    public int getCampersIndex(Object o){
        return campers.indexOf(o);
    }

    public int getMissingIndex(Object o){
        return missing.indexOf(o);
    }

    public void reset(){
        missing = campers;
        missing_id = campers_id;
    }

    public void removeMissing(String s){
        int tempInt = missing_id.indexOf(s);
        missing_id.remove(tempInt);
        missing.remove(tempInt);
    }

    public int getMissingSize() {
        return missing.size();
    }

    public void setCampers(ArrayList<String> someVariable) {
        this.campers = someVariable;
    }

    public void setCampersId(ArrayList<String> someVariable) {
        this.campers_id = someVariable;
    }

    public String getCampersString(){
        return campers.get(0);
    }

    public ArrayList<String> getMissing() {
        return missing;
    }

    public void removeOneMissing(int i){
        missing.remove(i);
    }

    public void setMissing(ArrayList<String> someVariable) {
        this.missing = someVariable;
    }
}
