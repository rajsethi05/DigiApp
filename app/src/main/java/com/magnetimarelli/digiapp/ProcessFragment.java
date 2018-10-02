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

public class ProcessFragment extends Fragment {

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
        view = inflater.inflate(R.layout.process_list, container, false);

        //TODO: Convert this list to JSON from connector.
        String[] process_names_list={"Moulding","Metallizing","HardCoat","BaseCoat","Antifog","Assembly"};

        ListView processList = view.findViewById(R.id.processList);

        ListAdapter adapter = new ProcessAdapter(context, process_names_list, R.drawable.process);
        processList.setAdapter(adapter);

        processList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new MachineFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public class ProcessAdapter extends ArrayAdapter<String> {

        int imageID;

        public ProcessAdapter(Context context, String[] list_names, int imageID) {
            super(context,R.layout.process_list_adapter, list_names);
            this.imageID=imageID;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View processList = inflater.inflate(R.layout.process_list_adapter,parent,false);

            ImageView processImage = processList.findViewById(R.id.processImage);
            TextView processName = processList.findViewById(R.id.processName);

            processName.setText(getItem(position));
            processImage.setImageResource(imageID);

            return processList;
        }
    }
}
