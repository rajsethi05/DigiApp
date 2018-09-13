package com.example.raj.digiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ActivitiesScreen extends AppCompatActivity {
    String[] act_cat= {"INSPECTION (Q 1)", "CLEANING", "SAFETY", "INSPECTION (R 1)", "LUBRICATION", "LUBRICATION", "LUBRICATION", "LUBRICATION", "Raj"};
    String[] act_prog={"I1", "C1", "S1", "I2", "L1","L2","L3","A2", "R1"};
    String[] act_name={"Check Nozzle for no material Leakage/ Nozzle damage.","Clean Purge shield","Check Purge Shield for Proper Functioning","Check Injection Carriage Bolt Marking"
    , "Greasing in Injection Carriage Channel 1 front.", "Greasing in Injection Carriage Channel 2 front.", "Greasing in Injection Carriage Channel 3 front.", "Hello My name is Raj", "Hello Raj"};
    ListView activities_list;
    private static String shift="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_screen);
        showActivties();
    }

    public void showActivties(){

        setShift();
        TextView shiftName=(TextView)findViewById(R.id.shift_name);
        shiftName.setText(shift);
        activities_list=(ListView)findViewById(R.id.activities_list);

        ListAdapter adapter=new CustomAdapter_activitiesList(this,act_cat,act_name,act_prog);
        activities_list.setAdapter(adapter);
        activities_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value=(String)activities_list.getItemAtPosition(i);
                Toast.makeText(ActivitiesScreen.this,"You Tapped", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setShift(){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        try {

            Date currentTime=format.parse("7:00:01");
            Date shiftAStart=format.parse("7:00:00");
            Date shiftAEnd=format.parse("15:00:00");
            Date shiftBEnd=format.parse("23:30:00");
            Date shiftCStartBrk1=format.parse("23:59:59");
            Date shiftCStartBrk2=format.parse("00:00:00");

            if(currentTime.after(shiftAStart) && currentTime.before(shiftAEnd)) {
                shift="A";
            }else if(currentTime.after(shiftAEnd) && currentTime.before(shiftBEnd)) {
                shift="B";
            }else if(currentTime.after(shiftBEnd) && currentTime.before(shiftCStartBrk1)){
                shift="C";
            }else if(currentTime.after(shiftCStartBrk2)&&currentTime.before(shiftAStart)) {
                shift="C";
            }
        }catch(ParseException e) {
            Log.e("Parse Exception","The shift time cannot be parsed");
        }
    }

}

