package app.org.halsa360.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.R;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Free_Booking_Days;
import app.org.halsa360.pojo.Free_Booking_Slots;


/**
 * Created by viji on 12/2/16.
 */
public class grid_slot extends BaseAdapter {

            LayoutInflater inflater;
            public ArrayList<Free_Booking_Slots> arraylist;
            private List<Free_Booking_Slots> freebookingslotsList;
            private  Context mContext;



        public grid_slot(Context context, ArrayList<Free_Booking_Slots> freebookingslotsList) {
            mContext = context;
            this.freebookingslotsList = freebookingslotsList;

            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<Free_Booking_Slots>();
            this.arraylist.addAll(freebookingslotsList);

            // TODO Auto-generated constructor stub
            this.mContext = context;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        }

    public class ViewHolder {
        TextView grid_slot,grid_section;


    }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return freebookingslotsList.size();
        }
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }



    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            mContext = parent.getContext();
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.gridviewadaptor_booking_slots, null);
            // Locate the TextViews in listview_item.xml
            holder.grid_slot=(TextView)view.findViewById(R.id.booking_slot_text);
            holder.grid_section=(TextView)view.findViewById(R.id.booking_section_text);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.grid_slot.setText(freebookingslotsList.get(position).getSlot_token());
        holder.grid_section.setText(freebookingslotsList.get(position).getSlot_time());

        return view;
    }

}