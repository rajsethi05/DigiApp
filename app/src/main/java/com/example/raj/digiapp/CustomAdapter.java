package com.example.raj.digiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(@NonNull Context context, String[] moulding_machine_names) {
        super(context,R.layout.moulding_machines_list, moulding_machine_names);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View machineListView=inflater.inflate(R.layout.moulding_machines_list,parent,false);

        String singleMachine=getItem(position);

        TextView machineName=(TextView)machineListView.findViewById(R.id.machineName);
        ImageView machineImage=(ImageView)machineListView.findViewById(R.id.machineImage);

        machineName.setText(singleMachine);
        machineImage.setImageResource(R.drawable.moulding_machine);

        return machineListView;
    }
}
