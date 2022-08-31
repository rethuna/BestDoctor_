package app.org.halsa360.Adapters;

import android.content.Context;
import android.graphics.Typeface;
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

import java.util.ArrayList;
import java.util.List;

import app.org.halsa360.Constants.ImageLoader;
import app.org.halsa360.R;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.pojo.Patient;

/**
 * Created by viji on 7/2/16.
 */
public class list_view_adapter_patient  extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Patient> arraylist;
    ImageLoader imageLoader;
    private List<Patient> patientList = null;
    Typeface typeface ;
    Boolean flag;
    public list_view_adapter_patient(Context context,
                                     ArrayList<Patient> patientList,Boolean DeleteFlag) {
        mContext = context;
        this.patientList = patientList;
        this.flag=DeleteFlag;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Patient>();
        this.arraylist.addAll(patientList);
        imageLoader = new ImageLoader(mContext);
        typeface = GlobalVariables.getTypeface(mContext);

    }

    public class ViewHolder {
        TextView mobile;
        TextView patientname;
        TextView patientlastname;
        TextView age;
        TextView sex;
        ImageView patientimage;
        RelativeLayout booking_delete;
        CheckBox checkdelete;
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Patient getItem(int position) {
        return patientList.get(position);
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
            holder.patientname.setTypeface(typeface);
            holder.age.setTypeface(typeface);
            holder.mobile.setTypeface(typeface);
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
        if(patientList.get(position).getPatientimage()==null||patientList.get(position).getPatientimage()=="")
        {
            try {
                String name = patientList.get(position).getPatientname();

                //get first letter of each String item
                String firstLetter = String.valueOf(name.charAt(0));

                ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
                // generate random color
                int color = generator.getColor(getItem(position));

                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(firstLetter, color);

                holder.patientimage.setImageDrawable(drawable);

            }
            catch(Exception e)
            {

            }

        }
        else{
            imageLoader.DisplayImage(patientList.get(position).getPatientimage(), holder.patientimage);
        }
        holder.patientname.setText(patientList.get(position).getPatientname());
        holder.age.setText(patientList.get(position).getAge()+" years");
        holder.mobile.setText(patientList.get(position).getMobilepatient());



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

