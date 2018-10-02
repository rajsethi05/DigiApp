package com.magnetimarelli.digiapp;

import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MachineFragment extends Fragment {

    View view;
    Context context;

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.fragmentManager = this.getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.machine_list, container, false);

        //TODO: Convert this list to JSON from connector.
        String[] machine_names_list={"300","350","430","500"};

        ListView machineList = view.findViewById(R.id.machineList);

        ListAdapter adapter = new MachineAdapter(context, machine_names_list, R.drawable.machine);
        machineList.setAdapter(adapter);
        machineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new ActivityFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public class MachineAdapter extends ArrayAdapter <String> {

        int imageID;

        public MachineAdapter(Context context, String[] list_names, int imageID) {
            super(context,R.layout.machine_list_adapter, list_names);
            this.imageID=imageID;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View machineList = inflater.inflate(R.layout.machine_list_adapter,parent,false);

            ImageView machineImage = machineList.findViewById(R.id.machineImage);
            TextView machineName = machineList.findViewById(R.id.machineName);

            machineName.setText(getItem(position));
            machineImage.setImageResource(imageID);

            return machineList;
        }
    }
}
