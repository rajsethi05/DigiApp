package com.example.raj.digiapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private static ProgressDialog progress;
    private static View v;
    private static EditText empIDField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The below if/else is to check if internet or wifi connection is available.
        if(!isConnected(DigiAppLogin.this)) buildDialog(DigiAppLogin.this).show();
        else {
//            Toast.makeText(DigiAppLogin.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_digi_app_login);
        }

    }




    public void verifyLoginCredentials(View view){
        v=view;

        //Finding the empid and pwd field by ID from layout
        empIDField=findViewById(R.id.employeeId);
        EditText pwdField=findViewById(R.id.Password_field);

        if(empIDField.getText().length()==0){
            empIDField.setError("Please enter your Employee ID");
        }else{
            String empID=empIDField.getText().toString();
            String pwd=pwdField.getText().toString();
            String type="login";

            //Below is the code to set loading indicator
            progress=new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while we login...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

            //Calling the DBConnection Class which will take the EmpID and Pwd and fetch result from DB
            DBConnection dbConnection=new DBConnection(DigiAppLogin.this);

            //TODO: Remove it formally
//            empID="123456";
//            pwd="hello";
            dbConnection.execute(type,empID,pwd);
        }

        /**Getting the value entered into 2 fields and storing them into variables.
         * Also, the variable "type" is used as flag to be used on DBConnection Class.
         */

    }

    /**
     * The below method calls the LoggedIn page and starts the activity but only after verifying the values from DB.
     *
     * @param ctx
     * @param result
     */
    public void showLoggedInPage(Context ctx, Map<String,String >result){
        Intent intent=new Intent(ctx,LoggedInPage.class);
        if(result.get("status").equals("1")) {
            intent.putExtra("name",result.get("empName"));

            // To dismiss the loading indicator and show login page
            progress.dismiss();
            ctx.startActivity(intent);
        }else if (result.get("status").equals("0")){
            progress.dismiss();
            //TODO: Replace below process by dispaying error msg
            empIDField.setError("Please check ur credentials");
        }else if(result.get("status").equals("-1")){
            progress.dismiss();
            //TODO: Replace below process by dispaying error msg
            empIDField.setError("Credentials do not exist");
        }
}


    /**
     * The below method check for the internet or wifi connection and returns TRUE or FALSE accordingly
     * @param context
     * @return
     */
    public  boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    /**
     * The below method displayed the Alert dialogue if no connection is available in the device.
     * @param c
     * @return
     */
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

}
