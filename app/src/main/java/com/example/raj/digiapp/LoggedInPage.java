package com.example.raj.digiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoggedInPage extends AppCompatActivity {
    private static TextView mouldingOption, metallizingOption, hardCoatOption, baseCoatOption, antifogOption, assembleyOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_page);
        showMachineListPage(new View(this));
    }

        public void showMachineListPage(View view){
        mouldingOption=(TextView)findViewById(R.id.moulding);
        mouldingOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                intent.putExtra("Option","Moulding");
                startActivity(intent);
            }
        });

            metallizingOption=(TextView)findViewById(R.id.metallizing);
            metallizingOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                    intent.putExtra("Option","Metallizing");
                    startActivity(intent);
                }
            });

            hardCoatOption=(TextView)findViewById(R.id.hardcoat);
            hardCoatOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                    intent.putExtra("Option","HardCoat");
                    startActivity(intent);
                }
            });

            baseCoatOption=(TextView)findViewById(R.id.basecoat);
            baseCoatOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                    intent.putExtra("Option","BaseCoat");
                    startActivity(intent);
                }
            });

            antifogOption=(TextView)findViewById(R.id.antifog);
            antifogOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                    intent.putExtra("Option","Antifog");
                    startActivity(intent);
                }
            });

            assembleyOption=(TextView)findViewById(R.id.assembly);
            assembleyOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoggedInPage.this,MachineListPage.class);
                    intent.putExtra("Option","Assembly");
                    startActivity(intent);
                }
            });

    }
}
