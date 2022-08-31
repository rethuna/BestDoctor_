package app.org.halsa360;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

import app.org.halsa360.Adapters.GridPatientAdapter;
import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.HelperHttp;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.pojo.Free_Booking_Days;


/**
 * Created by jitha on 3/2/16.
 */

public class New_Booking_Calender_Activity extends AppCompatActivity {

    GridView gridView;
    GridPatientAdapter adapter;
    private Typeface typeface;
    ArrayList<Free_Booking_Days> arraylist = new ArrayList<Free_Booking_Days>();

    String doctor_id,userid;
    ProgressDialog mProgressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_days);
        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
        userid = sharedpreferences.getString(User_Login_Activity.USER_ID, "");
        executeAsyncCalTask();


    }


    private void executeAsyncCalTask() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptAyncTask async = new GetDeptAyncTask();
        //userreg_id=182&&start_date=2016-02-01&&end_date=2016-02-05&&loc_user_id=78
        ht.put("userreg_id", userid);
        ht.put("start_date", Const_values.PRESENTDATE);
        ht.put("end_date",Const_values.LASTDATEOFMONTH );
        ht.put("loc_user_id","78");
        Hashtable[] ht_array = {ht};
        async.execute(ht_array);
    }


    private class GetDeptAyncTask extends AsyncTask<Hashtable<String, String>, Void, String> {

        @Override
        protected String doInBackground(Hashtable<String, String>... params) {
            Hashtable ht = params[0];
//Helperhttp is a global class
            String json = HelperHttp.getJSONResponseFromURL(Http_url.BOOKING_CALENDER_VIEW, ht);
            if (json != null) parseJsonString(arraylist, json);
            else {
                return "Invalid Speciality Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<Free_Booking_Days> arraylist, String json) {

            try {
                JSONArray array = new JSONArray(json);
                arraylist.clear();

                for (int i = 0; i < array.length(); i++) {
//booking_date":"2016-02-02","total_bokng_count":1,"free_slot_count":-1}
                    JSONObject j = array.getJSONObject(i);
                   Free_Booking_Days Free_Booking_Days = new Free_Booking_Days(j.optString("booking_date"), j.optString("total_bokng_count"), j.optString("free_slot_count")
                            );
                    arraylist.add(Free_Booking_Days);



                }
            } catch (JSONException e) {

            }

        }


        @Override
        protected void onPostExecute(String result) {

            if (result == "SUCCESS") {
                // Locate the listview in listview_main.xml
                gridView = (GridView) findViewById(R.id.gridview_bookingdate);
                // Pass the results into ListViewAdapter.java
                adapter = new GridPatientAdapter(New_Booking_Calender_Activity.this, arraylist);
                // Set the adapter to the ListView//this, R.layout.row_grid, gridArray);
                gridView.setAdapter(adapter);
                // Close the progressdialog


            }
        }
    }
}




