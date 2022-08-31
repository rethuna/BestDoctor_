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



import app.org.halsa360.Booking_Slots_Activity;
import app.org.halsa360.R;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Free_Booking_Days;


/**
 * Created by viji on 6/2/16.
 */

public class GridPatientAdapter extends BaseAdapter {
    Context mContext;
    String date_bookslot;
    LayoutInflater inflater;
    public ArrayList<Free_Booking_Days> arraylist;
    private List<Free_Booking_Days> freebookingdaysList;
    Typeface typeface ;
    Boolean flag;
    String mnth;
    public GridPatientAdapter(Context context, ArrayList<Free_Booking_Days> freebookingdaysList) {
        mContext = context;
        this.freebookingdaysList = freebookingdaysList;

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Free_Booking_Days>();
        this.arraylist.addAll(freebookingdaysList);

        typeface = GlobalVariables.getTypeface(mContext);

    }

    public class ViewHolder {
        TextView grid_booking;
        TextView grid_slotfree;
        Button grid_bookingdate;
        TextView grid_totalbooking;
        TextView grid_totalfreeslots;
        Button grid_bookmonth;


    }

    @Override
    public int getCount() {
        return freebookingdaysList.size();
    }

    @Override
    public Free_Booking_Days getItem(int position) {
        return freebookingdaysList.get(position);
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
            view = inflater.inflate(R.layout.gridviewadaptor_booking_days, null);
            // Locate the TextViews in listview_item.xml
            holder.grid_booking=(TextView)view.findViewById(R.id.grid_book_textview);
            holder.grid_slotfree=(TextView)view.findViewById(R.id.grid_freeslot_textview);
            holder.grid_bookingdate=(Button)view.findViewById(R.id.grid_bookdate);
            holder.grid_bookmonth=(Button)view.findViewById(R.id.grid_bookmonth);
            holder.grid_totalbooking=(TextView)view.findViewById(R.id.grid_totalbooking_button);
            holder.grid_totalfreeslots=(TextView)view.findViewById(R.id.grid_freeslot_button);
            holder.grid_booking.setTypeface(typeface);
            holder.grid_slotfree.setTypeface(typeface);
            holder.grid_bookingdate.setTypeface(typeface);
            holder.grid_bookmonth.setTypeface(typeface);
            holder.grid_totalbooking.setTypeface(typeface);
            holder.grid_totalfreeslots.setTypeface(typeface);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String datebooking=freebookingdaysList.get(position).getCal_book_date();
        String DayAry[] = datebooking.split("-");
        int year = Integer.parseInt(DayAry[0].trim());
        int month = Integer.parseInt(DayAry[1].trim());
       String day = String.valueOf(Integer.parseInt(DayAry[2].trim()));
        String[] monthss = {"Jan", "Feb", "Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov" ,"Dec"};
        if (month>= 1 && month <= 12) {
            mnth=monthss[month-1];

        }
        holder.grid_bookingdate.setText(day);
        holder.grid_bookmonth.setText(mnth);
        holder.grid_totalbooking.setText(freebookingdaysList.get(position).getCal_total_book_count());
        holder.grid_totalfreeslots.setText(freebookingdaysList.get(position).getCal_free_count());



        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, Booking_Slots_Activity.class);
                // Pass all data rank
                intent.putExtra("date",
                        (freebookingdaysList.get(position).getCal_book_date()));

                mContext.startActivity(intent);
            }
        });

        return view;
    }

    }

