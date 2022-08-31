package app.org.halsa360;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import app.org.halsa360.Adapters.list_view_adapter_booking;
import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.HelperHttp;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Booking;

public class Booking_List_Activity extends AppCompatActivity {

    ListView listview;
    list_view_adapter_booking adapter;

    private EditText editsearch;
    private ImageButton calender_image;
    private TextView book_dateview,book_delete;
    private Button btn_summary,btn_record;
    ProgressDialog mProgressDialog;
     Boolean flag=false;
    private Typeface typeface;
    String singledate;
   FrameLayout bottomlayout;
    ArrayList<Booking> arraylist = new ArrayList<Booking>();
    String doctor_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        setSupportActionBar(toolbar);
        typeface = GlobalVariables.getTypeface(Booking_List_Activity.this);
        btn_summary=(Button)findViewById(R.id.btnSummary);
        //btn_record=(Button)findViewById(R.id.btnRecord);
        bottomlayout=(FrameLayout)findViewById(R.id.formframe);
        //bottomlayout.getForeground().setAlpha(150);
        calender_image=(ImageButton)findViewById(R.id.calimage);
        editsearch = (EditText) findViewById(R.id.search);
        executeAsyncTask();
        book_dateview=(TextView)findViewById(R.id.textdate);
        book_delete=(TextView)findViewById(R.id.textdelete);
        book_delete.setTypeface(typeface);
        book_dateview.setTypeface(typeface);
       // btn_record.setTypeface(typeface);
        btn_summary.setTypeface(typeface);
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
        mProgressDialog = new ProgressDialog(Booking_List_Activity.this);
        // Set progressdialog title
       // mProgressDialog.setTitle("Android JSON Parse Tutorial");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
        Hashtable<String, String> ht = new Hashtable<String, String>();
        GetDeptAyncTask async = new GetDeptAyncTask();
        ht.put("userreg_id", "182");
        ht.put("location_user_id", "78");
        if(Const_values.Dateflag==1)
        {
            ht.put("book_date", "2016-02-08");
        }
else {
            ht.put("book_date", singledate);
        }
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

        protected void parseJsonString(ArrayList<Booking> arraylist, String json) {

            try {
                JSONArray array = new JSONArray(json);
                arraylist.clear();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject j = array.getJSONObject(i);
                    Booking bookinglist =new Booking(j.optString("book_id"),j.optString("first_name"),j.optString("mobile"),j.optString("age"),
                            j.optString("slot_no"),j.optString("time_slot"),j.optString("image"));
                    arraylist.add(bookinglist);


                }
                for(int i=0; i< arraylist.size();i++)
                {
                    Log.e("myLog", "Array Slot= " +arraylist.get(i).toString());
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
                adapter = new list_view_adapter_booking(Booking_List_Activity.this, arraylist,flag);
                // Set the adapter to the ListView
                listview.setAdapter(adapter);
                // Close the progressdialog

                mProgressDialog.dismiss();
            }
        }
    }
    public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    public void populateSetDate(int year, int month, int day) {
        book_dateview.setText(year + "-" + month + "-" +day);
        singledate=book_dateview.getText().toString();
    }



    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
            Const_values.Dateflag=0;
            executeAsyncTask();
        }
    }
}



