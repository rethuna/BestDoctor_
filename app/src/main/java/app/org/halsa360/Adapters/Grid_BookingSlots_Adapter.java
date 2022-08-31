/*



package app.org.halsa360.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import app.org.halsa360.R;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Free_Booking_Days;
import app.org.halsa360.pojo.Free_Booking_Slots;



*/
/**
 * Created by viji on 7/2/16.
 *//*
*/
/*
*//*

*/
/*
*//*




public class Grid_BookingSlots_Adapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    public ArrayList<Free_Booking_Slots> arraylist;
    private ArrayList<Map<Integer, String>> freebookingslotsList;
    Typeface typeface ;
    Boolean flag;
    String mnth;
    public Grid_BookingSlots_Adapter(Context context, ArrayList<Map<Integer, String>> freebookingslotsList) {
        mContext = context;
        this.freebookingslotsList = freebookingslotsList;

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Free_Booking_Slots>();
     //   this.arraylist.addAll(freebookingslotsList);

        typeface = GlobalVariables.getTypeface(mContext);

    }

    public class ViewHolder {
        TextView grid_slot,grid_section;


    }

    @Override
    public int getCount() {
        return freebookingslotsList.size();
    }

    @Override
    public Free_Booking_Slots getItem(int position) {
        return freebookingslotsList.get(position);
    }

    @Override
    public long getItemId(int position) {
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




*/
/*view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, BookingSlots_Activity.class);
                // Pass all data rank
                intent.putExtra("date",
                        (freebookingslotsList.get(position).getCal_book_date()));

                mContext.startActivity(intent);
            }
        });*//*



        return view;
    }

}


*/
