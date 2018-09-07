package com.example.raj.digiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoggedInPage extends AppCompatActivity {
    private static TextView mouldingOption, metallizingOption, hardCoatOption, baseCoatOption, antifogOption, assembleyOption;
    String optionSelected;

    private static ListView processList;
    private static String[] process_names_list={"Moulding","Metallizing","HardCoat","BaseCoat","Antifog","Assembly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_page);
        showMachineListPage();
    }


    /**
     * Below we are using Custom Adapter to show the list of all the process in ListView.
     * It also sets the extra text for any process selected. This extra text is then verifies by another class
     * and it sets the list of machine names according to the process selected.
     */

    public void showMachineListPage() {
        processList = (ListView) findViewById(R.id.process_listView);
        ListAdapter adapter = new CustomAdapter(this, process_names_list, R.drawable.process);
        processList.setAdapter(adapter);

        processList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) processList.getItemAtPosition(i);
                Intent intent = new Intent(LoggedInPage.this, MachineListPage.class);

                if (value.equals("Moulding")) {
                    intent.putExtra("Option", "Moulding");
                    startActivity(intent);
                } else if (value.equals("Metallizing")) {
                    intent.putExtra("Option", "Metallizing");
                    startActivity(intent);
                } else if (value.equals("HardCoat")) {
                    intent.putExtra("Option", "HardCoat");
                    startActivity(intent);
                } else if (value.equals("BaseCoat")) {
                    intent.putExtra("Option", "BaseCoat");
                    startActivity(intent);
                } else if (value.equals("Antifog")) {
                    intent.putExtra("Option", "Antifog");
                    startActivity(intent);
                } else if (value.equals("Assembly")) {
                    intent.putExtra("Option", "Assembly");
                    startActivity(intent);
                }

            }
        });
    }

}
