package com.example.raj.digiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DigiAppLogin extends AppCompatActivity {

    TextView mouldingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_app_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void showLoginPage(View view){

        EditText empIDField=findViewById(R.id.employeeId);
        EditText pwdField=findViewById(R.id.Password_field);

        String empID=empIDField.getText().toString();
        String pwd=pwdField.getText().toString();

        Intent intent = new Intent(this,LoggedInPage.class);
        startActivity(intent);


//        if (empID.equals("123456") && pwd.equals("hello")){
//            startActivity(intent);
//        }
    }

//    public void showMachineListPage(View view){
//        view=getLayoutInflater().inflate(R.layout.activity_moulding_page,null);
//        TextView textView=(TextView)view.findViewById(R.id.moulding);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    Intent intent = new Intent(view.getContext(),MachineListPage.class);
//                startActivity(intent);
//            }
//        });
//    }

}
