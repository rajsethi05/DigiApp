package com.example.raj.digiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DigiAppLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_app_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void verifyLoginCredentials(View view){

        //Finding the empid and pwd field by ID from layout

        EditText empIDField=findViewById(R.id.employeeId);
        EditText pwdField=findViewById(R.id.Password_field);

        /**Getting the value entered into 2 fields and storing them into variables.
         * Also, the variable "type" is used as flag to be used on DBConnection Class.
         */
        String empID=empIDField.getText().toString();
        String pwd=pwdField.getText().toString();
        String type="login";

        //Calling the DBConnection Class which will take the EmpID and Pwd and fetch result from DB
        DBConnection dbConnection=new DBConnection(DigiAppLogin.this);
        dbConnection.execute(type,empID,pwd);
    }

    /**
     * The below method calls the LoggedIn page and starts the activity but only after verifying the values from DB.
     *
     * @param ctx
     * @param result
     */
    public void showLoggedInPage(Context ctx, Map<String,String >result){
        Intent intent=new Intent(ctx,LoggedInPage.class);
        if(result.get("empId").equals("id:123456")) {
            intent.putExtra("name",result.get("empName"));
            ctx.startActivity(intent);
        }
}



}
