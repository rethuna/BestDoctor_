package app.org.halsa360.pojo;

/**
 * Created by viji on 7/2/16.
 */
public class Free_Booking_Days {
    private String cal_book_date;
    private String cal_total_book_count;
    private String cal_free_count;


//booking_date":"2016-02-02","total_bokng_count":1,"free_slot_count":-1}

    public Free_Booking_Days(String calendar_bookingdate,String calendar_total_book_count,String calendar_free_slot_count) {
        this.cal_book_date=calendar_bookingdate;
        this.cal_free_count = calendar_free_slot_count;
        this.cal_total_book_count = calendar_total_book_count;

    }
    public String getCal_book_date() {
        return cal_book_date;
    }

    public void setCal_book_date(String cal_book_date) {
        this.cal_book_date = cal_book_date;
    }

    public String getCal_total_book_count() {
        return cal_total_book_count;
    }

    public void setCal_total_book_count(String cal_total_book_count) {
        this.cal_total_book_count = cal_total_book_count;
    }
    public String getCal_free_count() {
        return cal_free_count;
    }

    public void setCal_free_count(String cal_free_count) {
        this.cal_free_count= cal_free_count;
    }
}
