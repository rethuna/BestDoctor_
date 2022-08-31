

package app.org.halsa360;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import app.org.halsa360.Adapters.grid_slot;
import app.org.halsa360.pojo.Free_Booking_Slots;


/**
 * Created by viji on 7/2/16.
 */


public class Booking_Slots_Activity extends AppCompatActivity {
    GridView gridView;
    private Typeface typeface;
    String doctor_id, slotdate;
    ProgressDialog mProgressDialog;
    grid_slot adapter;
    ArrayList<Free_Booking_Slots> arraylist = new ArrayList<Free_Booking_Slots>();

    ArrayList<Integer> arrayList_size = new ArrayList<Integer>();
    Map<Integer, String> map1 = new TreeMap<Integer, String>();
    Map<Integer, String> map2 = new TreeMap<Integer, String>();
    Map<Integer, String> map3 = new TreeMap<Integer, String>();
    Map<Integer, String> map4 = new TreeMap<Integer, String>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_slots);
        // executeAsyncCalTask();
        Intent iw = getIntent();
        // Get the results of rank
        slotdate = iw.getStringExtra("date");

        map1.put(10, "8:45");
        map1.put(8, "8:45");
        map2.put(5, "8:45");
        map2.put(15, "8:45");
        map2.put(20, "8:45");
        map4.put(1, "8:45");
        map4.put(2, "8:45");
        map4.put(3, "8:45");
        map4.put(8, "8:45");
        map4.put(9, "8:45");
        int mapsize1 = map1.size();
        int mapsize2 = map2.size();
        int mapsize3 = map3.size();
        int mapsize4 = map4.size();


        arrayList_size.add(mapsize1);
        arrayList_size.add(mapsize2);
        arrayList_size.add(mapsize3);
        arrayList_size.add(mapsize4);


        Set set = map1.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            String key = String.valueOf(mentry.getKey());


            String value = String.valueOf(mentry.getValue());
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
        Set set1 = map2.entrySet();
        Iterator iterator1 = set1.iterator();
        while(iterator1.hasNext()) {
            Map.Entry mentry1 = (Map.Entry)iterator1.next();
            String key = String.valueOf(mentry1.getKey());


            String value = String.valueOf(mentry1.getValue());
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);
            System.out.print("key is: " + mentry1.getKey() + " & Value is: ");
            System.out.println(mentry1.getValue());
        }
        Set set2 = map3.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry mentry2 = (Map.Entry)iterator2.next();
            String key = String.valueOf(mentry2.getKey());


            String value = String.valueOf(mentry2.getValue());
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);
            System.out.print("key is: " + mentry2.getKey() + " & Value is: ");
            System.out.println(mentry2.getValue());
        }
        Set set3 = map4.entrySet();
        Iterator iterator3= set3.iterator();
        while(iterator3.hasNext()) {
            Map.Entry mentry3 = (Map.Entry)iterator3.next();
            String key = String.valueOf(mentry3.getKey());


            String value = String.valueOf(mentry3.getValue());
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);
            System.out.print("key is: " + mentry3.getKey() + " & Value is: ");
            System.out.println(mentry3.getValue());
        }
         /*   Set setOfKeys = map1.keySet();
            Iterator iterator = setOfKeys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();


               String value = (String)map1.get(key);
                Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
                arraylist.add(Free_Booking_Slots);

                System.out.println("Key: "+ key+", Value: "+ value);
            }*/
      /*  Set setOfKeys1 = map2.keySet();
        Iterator iterator1 = setOfKeys1.iterator();
        while (iterator1.hasNext()) {
            String key = (String) iterator1.next();


            String value = (String)map2.get(key);
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);

            System.out.println("Key: "+ key+", Value: "+ value);
        }
*/

        /*for (int w = 0; w <= arrayList_size.get(2); w++) {


            String key = (String) map3.keySet().toArray()[w];
            String value = map3.get(w);
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);

        }

        for (int w = 0; w <= arrayList_size.get(3); w++) {


            String key = (String) map4.keySet().toArray()[w];
            String value = map4.get(w);
            Free_Booking_Slots Free_Booking_Slots = new Free_Booking_Slots(key, value);
            arraylist.add(Free_Booking_Slots);
        }*/
Log.d("ssdgdgg", String.valueOf(arraylist));

        // Locate the listview in listview_main.xml
        gridView = (GridView) findViewById(R.id.gridview_bookingdate_session2);
        // Pass the results into ListViewAdapter.java
        adapter = new grid_slot(Booking_Slots_Activity.this,arraylist);
        // Set the adapter to the ListView
        gridView.setAdapter(adapter);
        // Close the progressdialog

    }

}




