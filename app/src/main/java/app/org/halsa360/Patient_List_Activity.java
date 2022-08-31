package app.org.halsa360;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;


import app.org.halsa360.Adapters.list_view_adapter_patient;
import app.org.halsa360.Constants.HelperHttp;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.GlobalVariables;

import app.org.halsa360.pojo.Patient;

/**
 * Created by viji on 7/2/16.
 */
public class Patient_List_Activity extends AppCompatActivity {

    ListView listview;
    list_view_adapter_patient adapter;
    private EditText editsearch;
    private TextView book_dateview,book_delete;
    ProgressDialog mProgressDialog;
    Boolean flag=false;
    private Typeface typeface;
    ArrayList<Patient> arraylist = new ArrayList<Patient>();
    String doctor_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        setSupportActionBar(toolbar);
        typeface = GlobalVariables.getTypeface(Patient_List_Activity.this);

        editsearch = (EditText) findViewById(R.id.search);
        executeAsyncTask();
        book_dateview=(TextView)findViewById(R.id.textdate);
        book_delete=(TextView)findViewById(R.id.textdelete);
        book_delete.setTypeface(typeface);
        book_dateview.setTypeface(typeface);
        book_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;

                executeAsyncTask();
            }
        });
 /*       editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });*/
    }// DownloadJSON AsyncTask

    private void executeAsyncTask() {
        mProgressDialog = new ProgressDialog(Patient_List_Activity.this);
        // Set progressdialog title
        // mProgressDialog.setTitle("Android JSON Parse Tutorial");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptAyncTask async = new GetDeptAyncTask();
        ht.put("user_reg_id", "182");
        ht.put("pat%", "");
        Hashtable[] ht_array = {ht};
        async.execute(ht_array);
    }


    private class GetDeptAyncTask extends AsyncTask<Hashtable<String, String>, Void, String> {

        @Override
        protected String doInBackground(Hashtable<String, String>... params) {
            Hashtable ht = params[0];
//Helperhttp is a global class
            String json = HelperHttp.getJSONResponseFromURL(Http_url.BOOKING_LIST, ht);
            if (json != null) parseJsonString(arraylist, json);
            else {
                return "Invalid Speciality Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<Patient> arraylist, String json) {

            try {
                JSONArray array = new JSONArray(json);
                arraylist.clear();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject j = array.getJSONObject(i);
                    Patient Patientlist =new Patient(j.optString("patient_id"),j.optString("patient_name"),j.optString("mobile_number"),j.optString("age"),j.optString("patient_image")
                            );
                    arraylist.add(Patientlist);


                    Log.e("dghgdh", "ggg");
                }
            } catch (JSONException e) {

            }

        }


        @Override
        protected void onPostExecute(String result) {

            if (result == "SUCCESS") {
                // Locate the listview in listview_main.xml
                listview = (ListView) findViewById(R.id.listviewpatient);
                // Pass the results into ListViewAdapter.java
                adapter = new list_view_adapter_patient(Patient_List_Activity.this, arraylist,flag);
                // Set the adapter to the ListView
                listview.setAdapter(adapter);
                // Close the progressdialog

                mProgressDialog.dismiss();
            }
        }
    }
}


