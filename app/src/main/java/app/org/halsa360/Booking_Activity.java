package app.org.halsa360;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Booking_Status_Days;

public class Booking_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String userid,doctorname;
    private Button booking;
    private TextView datetext, datemonthtxt, datemonthyear, texttime,slot,textviewcount,daytext,docname,clinicname,location,patientname,age,locationname;
    Date dt = new Date();
    private Typeface typeface;
    private FrameLayout layout_MainMenu;
    ArrayList<Booking_Status_Days> arraylist = new ArrayList<Booking_Status_Days>();
    Date dateParse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
        userid = sharedpreferences.getString(User_Login_Activity.USER_ID, "");
      //  doctorname = sharedpreferences.getString(User_Login_Activity.US_NAME, "");

        typeface = GlobalVariables.getTypeface(Booking_Activity.this);


        /*

        Button Intialization
        */
        booking = (Button) findViewById(R.id.savebtn);


        /*
        *
        * Textview Intialization
        *
        * */
        datetext = (TextView) findViewById(R.id.datetxt);
        datemonthtxt = (TextView) findViewById(R.id.datemnthtxt);
        datemonthyear = (TextView) findViewById(R.id.datemnthyr);
        daytext = (TextView) findViewById(R.id.daytxt);
        textviewcount = (TextView) findViewById(R.id.textViewcount);
        texttime = (TextView) findViewById(R.id.txtTime);
        slot = (TextView) findViewById(R.id.txttoken);
        docname = (TextView) findViewById(R.id.doctornametxt);
        clinicname = (TextView) findViewById(R.id.clinicname);
        location = (TextView) findViewById(R.id.locationtxtview);
        patientname = (TextView) findViewById(R.id.patientnametxt);
        age = (TextView) findViewById(R.id.agetxt);
        locationname = (TextView) findViewById(R.id.Locationtxt);
       /*
        *//*
        *
        * give font to text
        *//**/

        datetext.setTypeface(typeface);
        datemonthtxt.setTypeface(typeface);
        datemonthyear.setTypeface(typeface);
        daytext.setTypeface(typeface);
        textviewcount.setTypeface(typeface);
        texttime.setTypeface(typeface);
        slot.setTypeface(typeface);
        docname.setTypeface(typeface);
        clinicname.setTypeface(typeface);
        location.setTypeface(typeface);
        patientname.setTypeface(typeface);
        age.setTypeface(typeface);
        locationname.setTypeface(typeface);
        booking.setTypeface(typeface);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                executeBookingstatusAsyncTask();
            }

        });

        String dataEx="16-02-2016";
        String DayAry[] = dataEx.split("-");
        int year1 = Integer.parseInt(DayAry[2].trim());
        int month1 = Integer.parseInt(DayAry[1].trim());
        int day1 = Integer.parseInt(DayAry[0].trim());
        Calendar c = Calendar.getInstance();
        c.set(year1, month1, day1);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK);
        System.out.println("day_of_week = " + day_of_week);
        String dayName = new DateFormatSymbols().getWeekdays()[c.get(day_of_week)];
        System.out.println("dayName = " + dayName);

        SimpleDateFormat formatdates = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdef = new SimpleDateFormat("dd-MMM-yyyy");
            try{
                dateParse=formatdates.parse(dataEx);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        String data=sdef.format(dateParse);
        Log.e("Data",data);
        String day=data.substring(0,2);

        String month=data.substring(3,6);

        String year=data.substring(7,11);
        String loc=Const_values.CURRENT_CLINIC;
        String []clinicarray=loc.split(" ");
        datetext.setText(day);
        daytext.setText(dayName);
        datemonthtxt.setText(month);
        datemonthyear.setText(year);
        docname.setText(Const_values.CURRENT_DOCTOR);
        clinicname.setText(clinicarray[0]);
        location.setText(clinicarray[1]);
        patientname.setText("RAJ");
        age.setText("25");
        locationname.setText("Ernakulum");

    }

    private void executeBookingstatusAsyncTask() {
        RequestParams params = new RequestParams();
        params.put("patreg_id", "1");
        params.put("userreg_id", userid);
        params.put("locuser_id", Const_values.CURRENT_CLINIC_ID);
        params.put("booking_date", Const_values.DATE_SELECTED);
        params.put("section_slot", "6");
        params.put("section", "3:15 am");
        invokeWS(params);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor__home_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_patients) {
            Intent Patients = new Intent(getApplicationContext(), Add_Patient_Activity.class);
            startActivity(Patients);


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

        private void invokeWS(RequestParams params) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(Http_url.Booking_Post_Detail, params, new AsyncHttpResponseHandler() {
                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(String response) {
                    // Hide Progress Dialog
                    try {
                        JSONObject obj = new JSONObject(response);
                        // JSON Object
                        if (obj.getBoolean("status")) {

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder((Booking_Activity.this));
                            alertDialog.setMessage("Your Appoinment is Sucessfully Submited. Do You Want to Take a New Appoinment");
                            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), Booking_Activity.class);
                                    startActivity(i);
                                }
                            });
                            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();
                                }
                            });
                            alertDialog.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
e.printStackTrace();
                    }
                }

                // When the response returned by REST has Http response code other than '200'
                @Override
                public void onFailure(int statusCode, Throwable error,
                                      String content) {

                    if (statusCode == 404) {

                    } else {

                    }
                }
            });
        }

    protected void onPostExecute(String result) {

        if (result == "SUCCESS") {
            Intent i =new Intent(getApplicationContext(),Doctor_Home_Activity.class);
            startActivity(i);

        } else {
        }
    }

}