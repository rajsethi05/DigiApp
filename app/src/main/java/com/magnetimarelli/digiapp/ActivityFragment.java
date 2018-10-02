package com.magnetimarelli.digiapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityFragment extends Fragment {

    View view;
    Context context;

    FragmentTransaction fragmentTransaction;

    //TODO: Convert lists to json using connector in constructor.
    String[] act_cat= {"INSPECTION (Q1)", "CLEANING", "SAFETY", "INSPECTION (R1)",
            "LUBRICATION", "LUBRICATION", "LUBRICATION", "LUBRICATION", "Raj"};

    String[] act_prog={"I1", "C1", "S1", "I2", "L1","L2","L3","A2", "R1"};

    String[] act_inst={"Check Nozzle for no material Leakage/ Nozzle damage.",
            "Clean Purge shield","Check Purge Shield for Proper Functioning",
            "Check Injection Carriage Bolt Marking",
            "Greasing in Injection Carriage Channel 1 front.",
            "Greasing in Injection Carriage Channel 2 front.",
            "Greasing in Injection Carriage Channel 3 front.",
            "Hello My name is Raj", "Hello Raj"};

    /**
     * Since we have a list of ites on activities page and not just one we create a class which
     * has al the necessary items for one item in list view.
      */
    public class ActivityClassList {

        String category;
        String program;
        String instruction;

        ActivityClassList(String inCat, String inProg, String inInst){
            this.category = inCat;
            this.program =  inProg;
            this.instruction = inInst;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentTransaction = this.getFragmentManager().beginTransaction();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activities_list, container, false);

        ListView activityList = view.findViewById(R.id.activitiesList);

        ArrayList <ActivityClassList> ac = new ArrayList<>();

        for (int i = 0; i < act_cat.length; i++) {
            ActivityClassList temp = new ActivityClassList(act_cat[i], act_prog[i], act_inst[i]);
            ac.add(temp);
        }
        ListAdapter adapter = new ActivityFragment.ActivitiesAdaptor(context, ac);
        activityList.setAdapter(adapter);
        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentTransaction.replace(R.id.home_fragment, new ActivityFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public class ActivitiesAdaptor extends ArrayAdapter<ActivityClassList> {

        public ActivitiesAdaptor(Context context, ArrayList<ActivityClassList> ac) {
            super(context,R.layout.activities_list_adaptor, ac);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View activityList = inflater.inflate(R.layout.activities_list_adaptor,parent,false);

            TextView activityCategory = activityList.findViewById(R.id.activity_category);
            TextView activityProgram = activityList.findViewById(R.id.activity_program);
            TextView activityInstructionLink = activityList.findViewById(R.id.instruction);
            CheckBox activityChecked = activityList.findViewById(R.id.activity_checked);

            ActivityClassList item = getItem(position);
            activityCategory.setText(item.category);
            activityProgram.setText(item.program);
            activityInstructionLink.setText(item.instruction);
            activityCategory.setText(item.category);

            return activityList;
        }
    }
}
