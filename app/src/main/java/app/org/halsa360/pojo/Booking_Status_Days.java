package app.org.halsa360.pojo;

import android.util.Log;

/**
 * Created by viji on 8/2/16.
 */
public class Booking_Status_Days {
    private String booking_status_date;
    private String booking_status_totalbookcount;
    private String booking_status_freeslot;




    public Booking_Status_Days(String booking_status_dates,String total_bokng_count,String free_slot_count) {
        this.booking_status_date=booking_status_dates;
        Log.e("booking date",booking_status_date);
        this.booking_status_totalbookcount = total_bokng_count;
        this.booking_status_freeslot = free_slot_count;

    }
    public String getBooking_status_date() {
        return booking_status_date;
    }

    public void setBooking_status_date(String booking_status_date) {
        this.booking_status_date = booking_status_date;
    }
    public String getBooking_status_freeslot() {
        return booking_status_freeslot;
    }

    public void setBooking_status_freeslot(String booking_status_freeslot) {
        this.booking_status_freeslot = booking_status_freeslot;
    }

    public String getBooking_status_totalbookcount() {
        return booking_status_totalbookcount;
    }

    public void setBooking_status_totalbookcount(String booking_status_totalbookcount) {
        this.booking_status_totalbookcount =booking_status_totalbookcount;
    }



}
