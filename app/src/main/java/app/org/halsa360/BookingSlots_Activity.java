/*

        package app.org.halsa360;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
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

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Hashtable;
        import java.util.Map;
        import java.util.TreeMap;

        import app.org.halsa360.Constants.Const_values;
        import app.org.halsa360.Constants.HALSA_ID;
        import app.org.halsa360.Constants.HelperHttp;
        import app.org.halsa360.Constants.Http_url;


        */
/**
         * Created by viji on 11/2/16.
         *//*


        public class BookingSlots_Activity extends AppCompatActivity {
            GridView gridView;
            String slotdate,userid,time_twelve_hour;
            Grid_BookingSlots_Adapter adapter;
            private Typeface typeface;
            ArrayList<Map<Integer, String>> arraylist = new ArrayList<>();
            ArrayList<HALSA_ID> parameterList = new ArrayList<HALSA_ID>();
            String doctor_id;
            ProgressDialog mProgressDialog;

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_patient_booking_slots);
                Intent i = getIntent();
                // Get the results of rank
                slotdate = i.getStringExtra("date");
                Log.e("sf", slotdate);
                SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
                userid = sharedpreferences.getString(User_Login_Activity.USER_ID, "");
                //Clearing hashmap value
                Const_values.session1.clear();
                Const_values.session2.clear();
                Const_values.session3.clear();
                Const_values.session4.clear();
                Const_values.session1tokens.clear();
                Const_values.session2tokens.clear();
                Const_values.session3tokens.clear();
                Const_values.session4tokens.clear();
                executeAsyncSlotsTask();


            }

            private void executeAsyncSlotsTask() {
                Hashtable<String, String> ht = new Hashtable<String, String>();
                GetDeptSlotAyncTask async = new GetDeptSlotAyncTask();
                //loc_user_id=78&&date=2016-02-08&&user_reg_id=182
                ht.put("user_reg_id",userid);
                ht.put("date", "2016-02-08");
                ht.put("loc_user_id","78");
                Hashtable[] ht_array = {ht};
                async.execute(ht_array);
            }

            private class GetDeptSlotAyncTask extends AsyncTask<Hashtable<String,String>,Void,String> {

                @Override
                protected String doInBackground(Hashtable<String,String>... params) {
                    Hashtable ht=params[0];

                    String json= HelperHttp.getJSONResponseFromURL(Http_url.SLOT_BOOKING, ht);
                    if(json!=null) parseJsonString(parameterList,json);
                    else{
                        return "Invalid Company Id";
                    }
                    return "SUCCESS";
                }

                protected void parseJsonString(ArrayList<HALSA_ID> parameterList,String json)
                {
                    try {
                        JSONArray array=new JSONArray(json);
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject j=array.getJSONObject(i);
                            JSONArray array1 = j.getJSONArray("ses1_tokens");
                            */
/*
                            *
                            * parsing session1 array and ses1 token
                            *
                            *
                            * *//*

                            for(int k=0;k<array1.length();k++)
                            {
                                String value =  array1.getString(k);
                                Const_values.session1tokens.put(k,value);
                                Log.d("PLACE ", value);

                            }
                            JSONArray array11 = j.getJSONArray("session1");
                            for(int n=0;n<array11.length();n++)
                            {
                                String value =  array11.getString(n);
                                //
                                //convert the time value into am/pm format
                                //
                                try {
                                    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                                    final Date dateObj = sdf.parse(value);
                                    System.out.println(dateObj);
                                    System.out.println(new SimpleDateFormat("hh:mm aa").format(dateObj));
                                    time_twelve_hour=new SimpleDateFormat("hh:mm aa").format(dateObj);
                                } catch (final ParseException e) {
                                    e.printStackTrace();
                                }
                                Const_values.session1.put(n,time_twelve_hour);
                                Log.d("PLACE ", Const_values.session1.toString());
                            }
                              */
/*
                            *
                            * parsing session2 array and ses2 token
                            *
                            *
                            * *//*

                            JSONArray array21 = j.getJSONArray("ses2_tokens");
                            for(int k1=0;k1<array21.length();k1++)
                            {
                                String value =  array21.getString(k1);
                                Const_values.session2tokens.put(k1,value);
                                Log.d("PLACE ", value);
                            }
                            JSONArray array22 = j.getJSONArray("session2");
                            for(int n1=0;n1<array22.length();n1++)
                            {
                                String value =  array22.getString(n1);
                                //
                                //convert the time value into am/pm format
                                //
                                try {
                                    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                                    final Date dateObj = sdf.parse(value);
                                    System.out.println(dateObj);
                                    System.out.println(new SimpleDateFormat("hh:mm aa").format(dateObj));
                                    time_twelve_hour=new SimpleDateFormat("hh:mm aa").format(dateObj);
                                } catch (final ParseException e) {
                                    e.printStackTrace();
                                }
                                Const_values.session2.put(n1, time_twelve_hour);

                                Log.d("PLACE ", value);
                            }
                             */
/*
                            *
                            * parsing session3 array and ses3 token
                            *
                            *
                            * *//*

                            JSONArray array31 = j.getJSONArray("ses3_tokens");
                            for(int k2=0;k2<array31.length();k2++)
                            {
                                String value =  array31.getString(k2);
                                Const_values.session3tokens.put(k2,value);
                                Log.d("PLACE ", value);
                            }
                            JSONArray array32 = j.getJSONArray("session3");
                            for(int n2=0;n2<array32.length();n2++)
                            {
                                String value =  array32.getString(n2);
                                //
                                //convert the time value into am/pm format
                                //
                                try {
                                    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                                    final Date dateObj = sdf.parse(value);
                                    System.out.println(dateObj);
                                    System.out.println(new SimpleDateFormat("hh:mm aa").format(dateObj));
                                    time_twelve_hour=new SimpleDateFormat("hh:mm aa").format(dateObj);
                                } catch (final ParseException e) {
                                    e.printStackTrace();
                                }
                                Const_values.session3.put(n2,time_twelve_hour);
                                Log.d("PLACE ", value);
                                Log.d("PLACE ", value);
                            }
                             */
/*
                            *
                            * parsing session4 array and ses4 token
                            *
                            *
                            * *//*

                            JSONArray array41 = j.getJSONArray("ses4_tokens");
                            for(int k3=0;k3<array41.length();k3++)
                            {
                                String value =  array41.getString(k3);
                                Const_values.session4tokens.put(k3,value);
                                Log.d("PLACE ", value);
                            }
                            JSONArray array42 = j.getJSONArray("session4");
                            for(int n3=0;n3<array42.length();n3++)
                            {
                                String value =  array42.getString(n3);
                                //
                                //convert the time value into am/pm format
                                //
                                try {
                                    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                                    final Date dateObj = sdf.parse(value);
                                    System.out.println(dateObj);
                                    System.out.println(new SimpleDateFormat("hh:mm aa").format(dateObj));
                                    time_twelve_hour=new SimpleDateFormat("hh:mm aa").format(dateObj);
                                } catch (final ParseException e) {
                                    e.printStackTrace();
                                }
                                Const_values.session4.put(n3, time_twelve_hour);
                                Log.d("PLACE ", value);

                            }

                        }
                      final  HashMap<Integer,String>ses1=new HashMap<Integer, String>();
                        final  HashMap<Integer,String>ses2=new HashMap<Integer, String>();
                        final  HashMap<Integer,String>ses3=new HashMap<Integer, String>();
                        final  HashMap<Integer,String>ses4=new HashMap<Integer, String>();
                        for(final Integer key:Const_values.session1.keySet())
                        {
                           if(Const_values.session1tokens.containsKey(key))
                           {
                               ses1.put(Integer.parseInt(Const_values.session1tokens.get(key)),Const_values.session1.get(key));
                           }

                        }for(final Integer key:Const_values.session2.keySet())
                        {
                            if(Const_values.session2tokens.containsKey(key))
                            {
                                ses2.put(Integer.parseInt(Const_values.session2tokens.get(key)),Const_values.session2.get(key));
                            }

                        }for(final Integer key:Const_values.session3.keySet())
                        {
                            if(Const_values.session3tokens.containsKey(key))
                            {
                                ses3.put(Integer.parseInt(Const_values.session3tokens.get(key)), Const_values.session3.get(key));
                            }

                        }
                        for(final Integer key:Const_values.session4.keySet())
                        {
                            if(Const_values.session4tokens.containsKey(key))
                            {
                                ses4.put(Integer.parseInt(Const_values.session4tokens.get(key)),Const_values.session4.get(key));
                            }

                        }

                        Map<Integer,String>map1 = new TreeMap<Integer, String>(ses1);
                        Map<Integer,String>map2 = new TreeMap<Integer, String>(ses2);
                        Map<Integer,String>map3 = new TreeMap<Integer, String>(ses3);
                        Map<Integer,String>map4 = new TreeMap<Integer, String>(ses4);
                        int mapsize1=map1.size();
                        int mapsize2=map2.size();
                        int mapsize3=map3.size();
                        int mapsize4=map4.size();

                        boolean val1=map1.isEmpty();
                        boolean val2=map2.isEmpty();
                        boolean val3=map3.isEmpty();
                        boolean val4=map4.isEmpty();


                       // Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(mapsize1,"");

                        arraylist.add(mapsize1,null);
                        arraylist.add(mapsize2,null);
                        arraylist.add(mapsize3,null);
                        arraylist.add(mapsize4,null);
                        arraylist.add(map1);
                        arraylist.add(map2);
                        arraylist.add(map3);
                        arraylist.add(map4);

                        for(int w=1;w<=arraylist.get(0, )



                       */
/* for (int w = 1; w <= 4; w++) {
                            if ("val"+w == "false")
                            {int size="mapsize"+w;
                                for (int wi = 1; wi <=; wi++) {
                                    String key = (String) map1.keySet().toArray()[wi];
                                    String value = map1.get(key);
                                   Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
                            );
                    arraylist.add(Free_Booking_Slots);

                                }
                          //  }

                        }
*//*

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                protected void onPostExecute(String result){

                    if(result=="SUCCESS")
                    {
                        gridView = (GridView) findViewById(R.id.gridview_bookingdate);
                        // Pass the results into ListViewAdapter.java
                        adapter = new Grid_BookingSlots_Adapter(BookingSlots_Activity.this, arraylist);
                        // Set the adapter to the ListView//this, R.layout.row_grid, gridArray);
                        gridView.setAdapter(adapter);
                    }
                    else{}
                }

            }
        }

*/
