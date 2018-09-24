package com.example.raj.digiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

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

public class DBConnection extends AsyncTask<String, Void, Map<String,String>> {

    AlertDialog alertDialog;


    public Map<String,String> result =new HashMap<>();

    Context context;


    DBConnection(Context context){
        this.context=context;
    }

    /**
     * This is the doInBG method. It takes multiple arguments as parameter. So always send 'type' as the first parameter.
     * @param params
     * @return
     */
    @Override
    protected Map<String, String> doInBackground(String... params) {

        //Getting the value entered into 2 fields and storing them into variables.
        //The index of the arguments should be in the same sequence as it is passed from previous class
        String type = params[0];
        String empID = params[1];
        String pwd = params[2];

        if (type.equals("login")) {
            verifyLoginCred(empID, pwd);
            return result;
        }else{
            return null;
        }
    }



        @Override
    protected void onPostExecute(Map<String,String> result) {

        new DigiAppLogin().showLoggedInPage(context,result);
        }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("LoginStatus");
    }

    public void verifyLoginCred(String empId, String pwd){
        String parameters = "empID=" + empId + "&pwd=" + pwd;
        String baseUrl = "http://digiappcom.000webhostapp.com/test.php?";
        String finalURL = baseUrl + parameters;

        String line = "";
        String lineString="";
        try {
            URL url = new URL(finalURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            InputStream inputStreamReader = httpURLConnection.getInputStream();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader, "iso-8859-1"));
                while ((line = bufferedReader.readLine()) != null) {
                    lineString += line;
                }
                JSONObject js=jsonParser(lineString);
                result.put("status",js.getString("loginStatus"));
                result.put("empName",js.getString("firstName")+" "+js.getString("lastName"));
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject jsonParser(String jsonResponse) throws JSONException {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        };
        return jsonObject;
    }

}
