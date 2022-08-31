package app.org.halsa360.Webservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.HALSA_ID;
import app.org.halsa360.Constants.HelperHttp;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Doctor_Home_Activity;
import app.org.halsa360.R;
import app.org.halsa360.User_Login_Activity;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Booking_Status_Days;

/**
 * Created by viji on 8/2/16.
 */
    public class login_splash extends AppCompatActivity {

    String userid, currenttime;
    ArrayList<HALSA_ID> parameterList = new ArrayList<HALSA_ID>();
    ArrayList<Booking_Status_Days> arraylist = new ArrayList<Booking_Status_Days>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //taking current date
        Const_values.PRESENTDATE = GlobalVariables.sdf.format(new Date());
        //taking current time
        currenttime = GlobalVariables.sdftime.format(new Date());
        //userreg_id value
        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
        userid = sharedpreferences.getString(User_Login_Activity.USER_ID, "");
            /*
            * calling async task for getting current clinic
            *
            * */
        executeCurrentClinicAsyncTask();
    }

    private void executeCurrentClinicAsyncTask() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptCurrentClinicAyncTask async = new GetDeptCurrentClinicAyncTask();
        //  //?userreg_id=182&&date=2016-02-08&&current_time=08:00:00

        ht.put("userreg_id", userid);
        ht.put("date", Const_values.PRESENTDATE);
        ht.put("current_time", currenttime);
        Hashtable[] ht_array = {ht};
        async.execute(ht_array);
    }

    private class GetDeptCurrentClinicAyncTask extends AsyncTask<Hashtable<String, String>, Void, String> {

        @Override
        protected String doInBackground(Hashtable<String, String>... params) {
            Hashtable ht = params[0];
            //Helperhttp is a global class
            String json = HelperHttp.getJSONResponseFromURL(Http_url.CURRENT_CLINIC, ht);
            if (json != null) parseJsonString(parameterList, json);
            else {
                //Calling all clinic async task
                executeAllClinicAsyncTask();
                // return "Invalid Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<HALSA_ID> parameterList, String json) {
            try {
                JSONArray array = new JSONArray(json);
                //[{"location_user_id":78,"locid":120,"location_name":"abc clinic"}]
                for (int i = 0; i < array.length(); i++) {
                    JSONObject j = array.getJSONObject(i);
                    HALSA_ID d = new HALSA_ID();
                    d.current_location_user_id = j.optString("location_user_id", "");
                    d.current_loc_id = j.optString("locid", "");
                    d.current_location_name = j.optString("location_name", "");
                    parameterList.add(d);
                    //save to a global class
                    Const_values.CURRENT_CLINIC = d.current_location_name;
                    Const_values.CURRENT_CLINIC_ID = d.current_location_user_id;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPostExecute(String result) {

            if (result == "SUCCESS") {
                Log.e("sucess", result);
                executeAllClinicAsyncTask();

            } else {
            }
        }


    }

    private void executeAllClinicAsyncTask() {

        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptAllClinicAyncTask async = new GetDeptAllClinicAyncTask();
        ht.put("userreg_id", userid);
        Hashtable[] ht_array = {ht};
        async.execute(ht_array);
    }

    private class GetDeptAllClinicAyncTask extends AsyncTask<Hashtable<String, String>, Void, String> {

        @Override
        protected String doInBackground(Hashtable<String, String>... params) {
            Hashtable ht = params[0];
            //Helperhttp is a global class
            String json = HelperHttp.getJSONResponseFromURL(Http_url.CLINIC_DETAIL, ht);
            Log.e("sygc",json);
            if (json != null) parseJsonString(parameterList, json);
            else {

                return "Invalid Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<HALSA_ID> parameterList, String json) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject j = array.getJSONObject(i);
                    HALSA_ID d = new HALSA_ID();

                    d.location_user_id = j.optString("loc_user_id", "");
                    d.clinic_name = j.optString("location_name", "");
                    d.location_name = j.optString("clinic_locality", "");
                    String clinic_full = d.clinic_name + " " + d.location_name;
                    parameterList.add(d);
                    //save to a hashmap
                    Const_values.location.put(d.location_user_id, clinic_full);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPostExecute(String result) {

            if (result == "SUCCESS") {

                Log.e("sucess", result);
                //Checking if there is any current clinic

               //  Log.e("cl",Const_values.CURRENT_CLINIC_ID);


                if (Const_values.CURRENT_CLINIC_ID==null) {
                    String key=(String)Const_values.location.keySet().toArray()[0];
                    String value=Const_values.location.get(key);
                    Const_values.CURRENT_CLINIC = value;
                    Const_values.CURRENT_CLINIC_ID = key;
                    Log.e("key",key);
                    Log.e("value",value);
                } else {
                }
                Intent i = new Intent(getApplicationContext(), Doctor_Home_Activity.class);
                startActivity(i);
            } else {
            }
        }


    }

    /*private void executeBookingstatusAsyncTask() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptBookingstatusAyncTask async = new GetDeptBookingstatusAyncTask();
        //userreg_id=182&&start_date=2016-02-08&&num_of_days=2&&loc_user_id=78
        ht.put("userreg_id", userid);
        ht.put("start_date", Const_values.PRESENTDATE);
        ht.put("num_of_days", "4");
        ht.put("loc_user_id", Const_values.CURRENT_CLINIC_ID);
        Hashtable[] ht_array = {ht};
        async.execute(ht_array);
    }

    private class GetDeptBookingstatusAyncTask extends AsyncTask<Hashtable<String, String>, Void, String> {

        @Override
        protected String doInBackground(Hashtable<String, String>... params) {
            Hashtable ht = params[0];
            //Helperhttp is a global class
            String json = HelperHttp.getJSONResponseFromURL(Http_url.BOOKING_STATUS, ht);
            if (json != null) parseJsonString(arraylist, json);
            else {

                return "Invalid Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<Booking_Status_Days>arrayList, String json) {
            try {
                JSONArray array = new JSONArray(json);
                arraylist.clear();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject j = array.getJSONObject(i);

                    Booking_Status_Days bookingstatuslist =new Booking_Status_Days(j.optString("booking_date"),j.optString("total_bokng_count"),j.optString("free_slot_count"));
                    arraylist.add(bookingstatuslist);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } Intent i = new Intent(getApplicationContext(), Doctor_Home_Activity.class);
                startActivity(i);


        @Override
        protected void onPostExecute(String result) {

            if (result == "SUCCESS") {
                Log.e("sucess", result);


                //Checking if there is any current clinic


            }*/


        }




