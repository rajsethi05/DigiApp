package com.magnetimarelli.digiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class Login extends Activity {

    //Shared Preferences Class
    private CustomPreferences pref;

    //Login Process Dialog
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new CustomPreferences(getApplicationContext());

        //Check if there is internet connection or not.
        isNetworkConnected(this);

        if(!(pref.outPref("login_stat").equals("OK"))) {
            setContentView(R.layout.login_activity);
        } else {
            gotoHome();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        isNetworkConnected(this);

        if(!(pref.outPref("login_stat").equals("OK"))) {
            setContentView(R.layout.login_activity);
        } else {
            gotoHome();
        }
    }

    public void isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                // Continue if there is a connection.
            } else {
                buildNetworkDialog(context);
            }
        } else {
            buildNetworkDialog(context);
        }
    }

    public void buildNetworkDialog(Context context) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.network_dialog_title);
        builder.setMessage(R.string.network_dialog_message);

        builder.setPositiveButton(R.string.network_dialog_cellular, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                dialog.dismiss();
                startActivity(i);
            }
        });

        builder.setNegativeButton(R.string.network_dialog_wifi, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                dialog.dismiss();
                startActivity(i);
            }
        });

        builder.setNeutralButton(R.string.network_dialog_close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }

    public void verifyLoginCredentials(View view){

        //Declare Emp Id and Pass Fields.

        EditText empIDField=findViewById(R.id.employeeId);
        EditText pwdField=findViewById(R.id.Password_field);

        if(empIDField.getText().length() == 0){
            empIDField.setError("Please enter Employee ID.");
        }else if (pwdField.getText().length() == 0) {
            pwdField.setError("Please enter the Password");
        } else {
            String empID=empIDField.getText().toString();
            String pwd=pwdField.getText().toString();

            // Save to shared preferences.
            pref.inPref("empID", empID);
            pref.inPref("pass", pwd);

            validateLogin();
        }
    }

    /**
     * Validate the Emp ID and Password by doing a DB Call to verify.
     */
    public void validateLogin() {

        //Below is the code to set loading indicator
        progress=new ProgressDialog(this);
        //progress.setTitle("Loading");
        progress.setMessage("Wait while we login...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        //Calling the DBConnection Class which will take the EmpID and Pwd and fetch result from DB
        Connector dbConnection=new Connector(Login.this);

        /**Getting the value entered into 2 fields and storing them into variables.
         * Also, the variable "type" is used as flag to be used on DBConnection Class.
         */
        String type="login";

        try {
            Map<String, String> result = dbConnection.execute(type).get();
            if(result.get("status").equals("1")) {
                progress.dismiss();
                String empName = result.get("empName");
                pref.inPref("empName", empName);
                pref.inPref("login_stat", "OK");
                gotoHome();
            } else {
                progress.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.invalid_cred);

                builder.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gotoHome(){
        Intent intent=new Intent(getApplicationContext(),Home.class);
        getApplication().startActivity(intent);
    }
}
