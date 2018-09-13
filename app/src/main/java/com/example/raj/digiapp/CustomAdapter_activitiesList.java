package com.example.raj.digiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raj.digiapp.R;

import java.lang.reflect.Array;
import java.util.List;

public class CustomAdapter_activitiesList extends ArrayAdapter<String> {

    private String[] activityCategory;
    private String[] activityName;
    private String[] activityProg;


    public CustomAdapter_activitiesList(@NonNull Context context, @NonNull String[] activityCategory,String[] activityName, String[] activityProg){
        super(context, 0, activityCategory);
        this.activityCategory=activityCategory;
        this.activityName=activityName;
        this.activityProg=activityProg;

    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {





        LayoutInflater inflater=LayoutInflater.from(getContext());
        View activitiesListView=inflater.inflate(R.layout.activities_list_page_layout,parent,false);

//        String singleActivity=getItem(position);

        TextView activity_category=(TextView)activitiesListView.findViewById(R.id.activity_category);
        TextView activity_program=(TextView)activitiesListView.findViewById(R.id.activity_program);
        CheckBox activity_name=(CheckBox)activitiesListView.findViewById(R.id.activity_name);




        activity_category.setText(activityCategory[position]);
        activity_program.setText(activityProg[position]);
        activity_name.setText(activityName[position]);


//        machineName.setText(singleActivity);


        return activitiesListView;
    }
    }

