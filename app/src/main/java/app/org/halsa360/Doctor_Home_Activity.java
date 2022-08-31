package app.org.halsa360;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.HelperHttp;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Booking_Status_Days;

public class Doctor_Home_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String todayAsString, tomorrowAsString, thirddayAsString, fourthdayAsString,year1,year2,year3,year4,userid,doctorname;
    private Button firstdaybtn, seconddaybtn, thirddaybtn, fourthdaybtn,firstdaybtncnt, seconddaybtncnt, thirddaybtncnt, fourthdaybtncnt,butnMoredays,butnDoctor,butnClinic;
    private TextView booking_date1, booking_date2, booking_date3, booking_date4,booking_year,booking_year1,booking_year2,booking_year3,booking_year4,bookingtext,freeslot,bookingfor,text1;
    Date dt = new Date();
    private Typeface typeface;
    private FrameLayout layout_MainMenu;
    private String  bookDates1,bookDates2,bookDates3,bookDates4;
    ArrayList<Booking_Status_Days> arraylist = new ArrayList<Booking_Status_Days>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
        userid = sharedpreferences.getString(User_Login_Activity.USER_ID, "");
        doctorname = sharedpreferences.getString(User_Login_Activity.US_NAME, "");

        executeBookingstatusAsyncTask();  // Method For Getting Webservice using JSON

        Const_values.PRESENTDATE = GlobalVariables.sdf.format(new Date());
        String DayAry[] = Const_values.PRESENTDATE.split("-");
        int year = Integer.parseInt(DayAry[0].trim());
        int month = Integer.parseInt(DayAry[1].trim());
        int day = Integer.parseInt(DayAry[2].trim());

        Const_values.LASTDATEOFMONTH = GlobalVariables.getDate(month, year);
        typeface = GlobalVariables.getTypeface(Doctor_Home_Activity.this);
        layout_MainMenu = (FrameLayout) findViewById(R.id.main_menu);
        layout_MainMenu.getForeground().setAlpha(0);


        /*

        Button Intialization
        */
        firstdaybtn = (Button) findViewById(R.id.firstday);
        seconddaybtn = (Button) findViewById(R.id.secondday);
        thirddaybtn = (Button) findViewById(R.id.thirdday);
        fourthdaybtn = (Button) findViewById(R.id.fourthday);
        firstdaybtncnt = (Button) findViewById(R.id.firstdaytotalbooking);
        seconddaybtncnt = (Button) findViewById(R.id.seconddaytotalbooking);
        thirddaybtncnt = (Button) findViewById(R.id.thirddaytotalbooking);
        fourthdaybtncnt = (Button) findViewById(R.id.fourthdaytotalbooking);
        butnMoredays = (Button) findViewById(R.id.button_moredays);
        butnDoctor = (Button) findViewById(R.id.button_dr);
        butnClinic = (Button) findViewById(R.id.button_clinic);

        /*
        *
        * Textview Intialization
        *
        * */
        booking_date1 = (TextView) findViewById(R.id.booking_date1);
        booking_date2 = (TextView) findViewById(R.id.booking_date2);
        booking_date3 = (TextView) findViewById(R.id.booking_date3);
        booking_date4 = (TextView) findViewById(R.id.booking_date4);
        booking_year = (TextView) findViewById(R.id.booking_year1);
        booking_year1 = (TextView) findViewById(R.id.booking_year2);
        booking_year2 = (TextView) findViewById(R.id.booking_year3);
        booking_year3 = (TextView) findViewById(R.id.booking_year4);
        freeslot = (TextView) findViewById(R.id.freeslot);
        bookingtext = (TextView) findViewById(R.id.bookingtext);
        bookingfor = (TextView) findViewById(R.id.bookingfor);


       /*
        *//*
        *
        * give font to text
        *//**/

        bookingtext.setTypeface(typeface);
        firstdaybtn.setTypeface(typeface);
        seconddaybtn.setTypeface(typeface);
        thirddaybtn.setTypeface(typeface);
        fourthdaybtn.setTypeface(typeface);
        booking_date1.setTypeface(typeface);
        booking_date2.setTypeface(typeface);
        booking_date3.setTypeface(typeface);
        booking_date4.setTypeface(typeface);
        bookingtext.setText(getString(R.string.booking_status));
        bookingtext.setTypeface(typeface);
        freeslot.setTypeface(typeface);
        bookingfor.setTypeface(typeface);
        bookingfor.setTextColor(Color.parseColor("#00a79d"));


        butnMoredays.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Booking_Activity.class);
                startActivity(i);
            }
        });

//        firstdaybtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), Patient_Appoinment_Details.class);
//                startActivity(i);
//            }
//        });
        butnClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final ListView lv;

                layout_MainMenu.getForeground().setAlpha(150);
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.custom, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        GridLayout.LayoutParams.WRAP_CONTENT,
                        GridLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                final ArrayList<String> Loc_usr_Id = new ArrayList<String>();
                final ArrayList<String> Loc_value = new ArrayList<String>();
                for (String stringKey : Const_values.location.keySet()) {
                    Loc_usr_Id.add(stringKey);
                  //  Log.e("Data", stringKey);
                    String value=Const_values.location.get(stringKey);
                    Loc_value.add(value);
                 //   Log.e("Data-value",value);

                }

              //  textView.setTypeface(typeface);
                 lv = (ListView) popupView.findViewById(R.id.listView1);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_text_color,Loc_value);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Const_values.CURRENT_CLINIC = parent.getItemAtPosition(position).toString();
                        Const_values.CURRENT_CLINIC_ID = Loc_usr_Id.get(position);
                        executeBookingstatusAsyncTask();
                        popupWindow.dismiss();
                        layout_MainMenu.getForeground().setAlpha(0);
                    }
                });

                Button cancel = (Button) popupView.findViewById(R.id.butcancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        layout_MainMenu.getForeground().setAlpha(0);
                    }
                });

                popupWindow.showAsDropDown(butnClinic, 80, -340);
                // popupWindow.dismiss();
            }

        });


//

        //
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    private void executeBookingstatusAsyncTask() {
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

        protected void parseJsonString(ArrayList<Booking_Status_Days> arrayList, String json) {
            try {
                JSONArray array = new JSONArray(json);
                arraylist.clear();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject j = array.getJSONObject(i);
                    Log.e("array",j.optString("booking_date"));
                    Booking_Status_Days bookingstatus = new Booking_Status_Days(j.optString("booking_date"), j.optString("total_bokng_count"), j.optString("free_slot_count"));
                    arraylist.add(bookingstatus);

                }
                Log.e("size",arrayList.size()+"");
                Log.e("Data", arraylist.get(0).getBooking_status_date());
                Log.e("Data",arraylist.get(1).getBooking_status_date());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPostExecute(String result) {

                SimpleDateFormat sdef = new SimpleDateFormat("dd-MMM");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formatdates = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date bookDate1=formatdates.parse(arraylist.get(0).getBooking_status_date());
                    Date bookDate2=formatdates.parse(arraylist.get(1).getBooking_status_date());
                    Date bookDate3=formatdates.parse(arraylist.get(2).getBooking_status_date());
                    Date bookDate4=formatdates.parse(arraylist.get(3).getBooking_status_date());
                    todayAsString = sdef.format(bookDate1);
                    tomorrowAsString = sdef.format(bookDate2);
                    thirddayAsString = sdef.format(bookDate3);
                    fourthdayAsString = sdef.format(bookDate4);
                    bookDates1=sd.format(bookDate1);
                    bookDates2=sd.format(bookDate2);
                    bookDates3=sd.format(bookDate3);
                    bookDates4=sd.format(bookDate4);
                    year1 = sdf.format(bookDate1);
                    year2 = sdf.format(bookDate2);
                    year3 = sdf.format(bookDate3);
                    year4 = sdf.format(bookDate4);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            /*
              * set the value for String
               *
              * */
            booking_date1.setText(todayAsString);
            booking_date2.setText(tomorrowAsString);
            booking_date3.setText(thirddayAsString);
            booking_date4.setText(fourthdayAsString);

            booking_year.setText(year1);
            booking_year1.setText(year2);
            booking_year2.setText(year3);
            booking_year3.setText(year4);

            firstdaybtn.setText(arraylist.get(0).getBooking_status_totalbookcount());
            seconddaybtn.setText(arraylist.get(1).getBooking_status_totalbookcount());
            thirddaybtn.setText(arraylist.get(2).getBooking_status_totalbookcount());
            fourthdaybtn.setText(arraylist.get(3).getBooking_status_totalbookcount());


            firstdaybtncnt.setText(arraylist.get(0).getBooking_status_freeslot());
            seconddaybtncnt.setText(arraylist.get(1).getBooking_status_freeslot());
            thirddaybtncnt.setText(arraylist.get(2).getBooking_status_freeslot());
            fourthdaybtncnt.setText(arraylist.get(3).getBooking_status_freeslot());
            butnDoctor.setText(doctorname);
            butnClinic.setText(Const_values.CURRENT_CLINIC);


            firstdaybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const_values.DATE_SELECTED=bookDates1.toString();
                    layout_MainMenu.getForeground().setAlpha(150);
                    LayoutInflater layoutInflater
                            = (LayoutInflater) getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.dialog_popup, null);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            GridLayout.LayoutParams.WRAP_CONTENT,
                            GridLayout.LayoutParams.WRAP_CONTENT,true);

                    popupWindow.setTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setTouchInterceptor(new View.OnTouchListener() { // or whatever you want
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) // here I want to close the pw when clicking outside it but at all this is just an example of how it works and you can implement the onTouch() or the onKey() you want
                            {
                                popupWindow.dismiss();
                                layout_MainMenu.getForeground().setAlpha(0);

                                return true;
                            }
                            layout_MainMenu.getForeground().setAlpha(0);

                            return false;
                        }

                    });
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    popupWindow.setOutsideTouchable(true);

                    // Removes default background.
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    final ImageView bookinglist = (ImageView) popupView.findViewById(R.id.booking_list);
                    final ImageView newbooking = (ImageView) popupView.findViewById(R.id.new_booking);

                    bookinglist.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            newbooking.setBackgroundDrawable(getResources().getDrawable(R.drawable.new_booking));
                            Intent i = new Intent(getApplicationContext(), Booking_List_Activity.class);
                            startActivity(i);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });


                    newbooking.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            bookinglist.setBackgroundDrawable(getResources().getDrawable(R.drawable.booking_list_clr));
                            Intent bookslot = new Intent(getApplicationContext(), Booking_Slots_Activity.class);
                            startActivity(bookslot);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });

                    popupWindow.showAsDropDown(firstdaybtn, 80, -340);

                }

            });
            seconddaybtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Const_values.DATE_SELECTED=bookDates2.toString();
                    layout_MainMenu.getForeground().setAlpha(150);
                    LayoutInflater layoutInflater
                            = (LayoutInflater) getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.dialog_popup, null);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            GridLayout.LayoutParams.WRAP_CONTENT,
                            GridLayout.LayoutParams.WRAP_CONTENT,true);

                    popupWindow.setTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setTouchInterceptor(new View.OnTouchListener() { // or whatever you want
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) // here I want to close the pw when clicking outside it but at all this is just an example of how it works and you can implement the onTouch() or the onKey() you want
                            {layout_MainMenu.getForeground().setAlpha(0);
                                popupWindow.dismiss();


                                return true;
                            }
                            layout_MainMenu.getForeground().setAlpha(0);
                            return false;

                        }

                    });
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    popupWindow.setOutsideTouchable(true);

                    // Removes default background.
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    final ImageView bookinglist = (ImageView) popupView.findViewById(R.id.booking_list);
                    final ImageView newbooking = (ImageView) popupView.findViewById(R.id.new_booking);

                    bookinglist.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            newbooking.setBackgroundDrawable(getResources().getDrawable(R.drawable.new_booking));
                            Intent i = new Intent(getApplicationContext(), Booking_List_Activity.class);
                            startActivity(i);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });


                    newbooking.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            bookinglist.setBackgroundDrawable(getResources().getDrawable(R.drawable.booking_list_clr));
                            Intent bookslot = new Intent(getApplicationContext(), Booking_Slots_Activity.class);
                            startActivity(bookslot);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });

                    popupWindow.showAsDropDown(seconddaybtn, 80, -340);

                }

            });
            thirddaybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const_values.DATE_SELECTED=bookDates3.toString();
                    layout_MainMenu.getForeground().setAlpha(150);
                    LayoutInflater layoutInflater
                            = (LayoutInflater) getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.dialog_popup, null);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            GridLayout.LayoutParams.WRAP_CONTENT,
                            GridLayout.LayoutParams.WRAP_CONTENT,true);

                    popupWindow.setTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setTouchInterceptor(new View.OnTouchListener() { // or whatever you want
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) // here I want to close the pw when clicking outside it but at all this is just an example of how it works and you can implement the onTouch() or the onKey() you want
                            {
                                popupWindow.dismiss();
                                layout_MainMenu.getForeground().setAlpha(0);

                                return true;
                            }
                            layout_MainMenu.getForeground().setAlpha(0);

                            return false;
                        }

                    });
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    popupWindow.setOutsideTouchable(true);

                    // Removes default background.
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    final ImageView bookinglist = (ImageView) popupView.findViewById(R.id.booking_list);
                    final ImageView newbooking = (ImageView) popupView.findViewById(R.id.new_booking);

                    bookinglist.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            newbooking.setBackgroundDrawable(getResources().getDrawable(R.drawable.new_booking));
                            Intent i = new Intent(getApplicationContext(), Booking_List_Activity.class);
                            startActivity(i);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });


                    newbooking.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            bookinglist.setBackgroundDrawable(getResources().getDrawable(R.drawable.booking_list_clr));
                            Intent bookslot = new Intent(getApplicationContext(), Booking_Slots_Activity.class);
                            startActivity(bookslot);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });

                    popupWindow.showAsDropDown(thirddaybtn, 80, -340);

                }

            });
            fourthdaybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const_values.DATE_SELECTED=bookDates4.toString();
                    layout_MainMenu.getForeground().setAlpha(150);
                    LayoutInflater layoutInflater
                            = (LayoutInflater) getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.dialog_popup, null);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            GridLayout.LayoutParams.WRAP_CONTENT,
                            GridLayout.LayoutParams.WRAP_CONTENT,true);


                    popupWindow.setTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setTouchInterceptor(new View.OnTouchListener() { // or whatever you want
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) // here I want to close the pw when clicking outside it but at all this is just an example of how it works and you can implement the onTouch() or the onKey() you want
                            {
                                popupWindow.dismiss();
                                layout_MainMenu.getForeground().setAlpha(0);

                                return true;
                            }
                            layout_MainMenu.getForeground().setAlpha(0);

                            return false;
                        }

                    });
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    popupWindow.setOutsideTouchable(true);

                    // Removes default background.
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    final ImageView bookinglist = (ImageView) popupView.findViewById(R.id.booking_list);
                    final ImageView newbooking = (ImageView) popupView.findViewById(R.id.new_booking);

                    bookinglist.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            bookinglist.setBackgroundDrawable(getResources().getDrawable(R.drawable.booking_list_clr));
                            Intent bookslot = new Intent(getApplicationContext(), Booking_Slots_Activity.class);
                            startActivity(bookslot);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });


                    newbooking.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            bookinglist.setBackgroundDrawable(getResources().getDrawable(R.drawable.booking_list_clr));
                            Intent newbookcalender = new Intent(getApplicationContext(), New_Booking_Calender_Activity.class);
                            startActivity(newbookcalender);
                            popupWindow.dismiss();
                            layout_MainMenu.getForeground().setAlpha(0);
                        }
                    });

                    popupWindow.showAsDropDown(fourthdaybtn, 80, -340);

                }

            });

        }
    }
}