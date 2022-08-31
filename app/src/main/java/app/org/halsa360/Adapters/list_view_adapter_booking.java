package app.org.halsa360.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.org.halsa360.Constants.ImageLoader;
import app.org.halsa360.R;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Booking;


/**
 * Created by viji on 4/2/16.
 */

public class list_view_adapter_booking extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Booking> arraylist;
    ImageLoader imageLoader;
    private List<Booking> bookingList = null;
     Typeface typeface ;
    Boolean flag;
    public list_view_adapter_booking(Context context,
                                     ArrayList<Booking> bookingList,Boolean DeleteFlag) {
        mContext = context;
        this.bookingList = bookingList;
        this.flag=DeleteFlag;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Booking>();
        this.arraylist.addAll(bookingList);
        imageLoader = new ImageLoader(mContext);
        typeface = GlobalVariables.getTypeface(mContext);

    }

    public class ViewHolder {
        TextView mobile;
        TextView patientname;
        TextView patientlastname;
        TextView age;
        TextView section;
        TextView slot;
        TextView sex;
        ImageView patientimage;
        RelativeLayout booking_delete;
        CheckBox checkdelete;
    }

    @Override
    public int getCount() {
        return bookingList.size();
    }

    @Override
    public Booking getItem(int position) {
        return bookingList.get(position);
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
            view = inflater.inflate(R.layout.listview_booking, null);
            // Locate the TextViews in listview_item.xml

            holder.patientimage = (ImageView) view.findViewById(R.id.imageView_book);
            holder.patientname = (TextView) view.findViewById(R.id.text_bookname);
            holder.age = (TextView) view.findViewById(R.id.text_bookage);
            holder.mobile = (TextView) view.findViewById(R.id.text_bookphoneno);
            holder.slot=(TextView)view.findViewById(R.id.booking_slot_text);
            holder.section=(TextView)view.findViewById(R.id.booking_section_text);
            holder.patientname.setTypeface(typeface);
            holder.age.setTypeface(typeface);
            holder.mobile.setTypeface(typeface);
           /* holder.section.setTypeface(typeface);
            holder.slot.setTypeface(typeface);*/
            holder.checkdelete=(CheckBox)view.findViewById(R.id.delete_checkbox);
            if(flag==true)
            {
                holder.checkdelete.setVisibility(View.VISIBLE);

            }
            else{
                holder.checkdelete.setVisibility(View.GONE);
            }
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(bookingList.get(position).getBookimage()==null||bookingList.get(position).getBookimage()=="")
        {
            try {
            String name = bookingList.get(position).getBookname();

            //get first letter of each String item
            String firstLetter = String.valueOf(name.charAt(0));

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
            // generate random color
            int color = generator.getColor(getItem(position));

            TextDrawable drawable = TextDrawable.builder()
                    .buildRect(firstLetter, color);

        holder.patientimage.setImageDrawable(drawable);

            }
        catch(Exception e)
        {

        }

        }
        else{
            imageLoader.DisplayImage(bookingList.get(position).getBookimage(), holder.patientimage);
        }
        holder.patientname.setText(bookingList.get(position).getBookname());
        holder.age.setText(bookingList.get(position).getBookage()+" years");
        holder.mobile.setText(bookingList.get(position).getBookmobile());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String timme=bookingList.get(position).getBooktime();
        Date fechaNueva = null;
        try {
            fechaNueva = format.parse(timme);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        DateFormat outputFormat = new SimpleDateFormat("KK:mm a");
        System.out.println(format.format(fechaNueva));
        String h=outputFormat .format(fechaNueva);
        Log.e("hfghjdsgufdgud", h);

        holder.slot.setText("   "+bookingList.get(position).getBookslot());
        holder.section.setText(h);

       /* view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank",
                        (worldpopulationlist.get(position).getRank()));
                // Pass all data country
                intent.putExtra("country",
                        (worldpopulationlist.get(position).getCountry()));
                // Pass all data population
                intent.putExtra("population",
                        (worldpopulationlist.get(position).getPopulation()));
                // Pass all data flag
                intent.putExtra("flag",
                        (worldpopulationlist.get(position).getFlag()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });
        return view;
    }*/


/*//*
/Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        bookingList.clear();
        if (charText.length() == 0) {
            bookingList.addAll(arraylist);
        } else {
            for (Patient wp : arraylist) {
                if (wp.getPatientname().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    bookingList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/

        return view;


    }
}


