package com.ray.joe.barcodescanner;

import android.app.Application;

import java.util.ArrayList;


public class HoldValues extends Application {

    //{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56"}
    //"Jack Jones", "Jill Jones", "Bobby Smith", "Joe Evens", "Your Mom", "My Mom"
    private String[] temp = {"Abby Alford", "Gabby Bass", "Hannah Battey", "Taylor Battey", "Eden Bedsaul", "Sicily Brocato", "Vinny Brocato", "Austin Burns", "Joy Carino", "Chikira Clark", "Taylor Clark", "Edie Kay Conner", "Emma Crumpton", "Kelsey Damms", "Olivia Dinep-Schneider", "Kenzie Dixon", "Brendon Dobbs", "Asia Ellis", "Glen Fisher", "Spencer Floyd", "Emma Gousset", "Jasper Gray", "Sawyer Gray", "Abigayle Green", "Erin Harrison", "Sarah Heard", "Ashleigh Herrera", "Chris Hillhouse", "Taylor Howard", "Noah Hunt", "Brooklin Jammison", "Abby Johnson", "Phillip Johnson", "Chera Jones", "Elizabeth Jones", "Noah Knox", "Sean Mackin", "Josh Martin", "Meredith McLauren", "Alander Neal", "Daniel Nicholson", "John Alex Nunnery", "Laura Patino", "Mabry Patrick", "Grant Peterson", "Anna Pierce", "Sarah Rendon", "Jada Richmond", "Joel David Rutherford", "Sarah Swiderski", "Sam Turner", "Jasmen Warnock", "Kyran Warnock", "Drew Watkins", "Chael Williams", "Emma Witty"};
    private String[] temp_id = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56"};
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
        if(tempInt >= 0) {
            missing_id.remove(tempInt);
            missing.remove(tempInt);
        }
    }

    public void removeMissingPopup(String s){
        int tempInt = missing.indexOf(s);
        if(tempInt >= 0) {
            missing_id.remove(tempInt);
            missing.remove(tempInt);
        }
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



