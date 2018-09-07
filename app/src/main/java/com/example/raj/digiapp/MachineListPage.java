package com.example.raj.digiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MachineListPage extends AppCompatActivity {
    String optionSelected;
    TextView pageTitle;
    private static ListView mouldingMachineList;
    private static String[] machine_names_list={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moulding_page);
        showMouldingMachineList();
    }

    /**
     * The below method is identifying which option is Selected on LoggedInPage (Process Page) and sets the variables accordingly
     */
    public void setOptionList(){
        optionSelected=getIntent().getExtras().getString("Option");
        pageTitle=(TextView)findViewById(R.id.pageTitle);
        pageTitle.setText(optionSelected);
        switch (optionSelected) {
            case "Moulding":
                machine_names_list=new String[]{"300","350","430","500"};
                break;
            case "Metallizing":
                machine_names_list=new String[]{"Ar24ffp1","Ar24ffp2"};
                break;
            case "HardCoat":
                machine_names_list=new String[]{"Hard coat1"};
                break;
            case "BaseCoat":
                machine_names_list=new String[]{"Base coat1"};
                break;
            case "Antifog":
                machine_names_list=new String[]{"Antifog1"};
                break;
            case "Assembly":
                machine_names_list=new String[]{"YBA line1","YBA line2","B562 line","YRC line1","YSD line1"};
                break;
        }

    }

    public void showMouldingMachineList(){
        setOptionList();
        mouldingMachineList=(ListView)findViewById(R.id.machine_list);
        ListAdapter adapter=new CustomAdapter(this, machine_names_list,R.drawable.moulding_machine);
        mouldingMachineList.setAdapter(adapter);

        mouldingMachineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value=(String)mouldingMachineList.getItemAtPosition(i);
                Toast.makeText(MachineListPage.this,"You Tapped",Toast.LENGTH_LONG).show();
            }
        });
    }

}
