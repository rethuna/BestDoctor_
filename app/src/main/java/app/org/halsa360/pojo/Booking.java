package app.org.halsa360.pojo;

import java.util.Date;

/**
 * Created by jitha on 4/2/16.
 */
public class Booking {
    private String bookimage;
    private String bookname;
    private String bookage;
    private String bookslot;
    private String bookmobile;
    private String booktime;
    private String bookid;

//new Booking(j.optString("booking_detail_id"),j.optString("patient_name"),j.optString("mobile_number"),j.optString("age"),
   // j.optString("section_slot_no"),j.optString("section"),j.optString("image"));

    public Booking(String bookingid,String book_name,String book_mobileno, String book_age, String book_slot_no, String book_time,String book_image) {
        this.bookid=bookingid;
        this.bookname = book_name;
        this.bookage = book_age;
        this.bookslot= book_slot_no;
        this.bookmobile = book_mobileno;
        this.bookimage = book_image;
        this.booktime = book_time;
        

    }
    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookimage() {
        return bookimage;
    }

    public void setBookimage(String bookimage) {
        this.bookimage =bookimage;
    }

    public String getBookage() {
        return bookage;
    }

    public void setBookage(String bookage) {
        this.bookage = bookage;
    }

    public String getBookslot() {
        return bookslot;
    }

    public void setBookslot(String bookslot) {
        this.bookslot = bookslot;
    }

    public String getBookmobile() {
        return bookmobile;
    }

    public void setBookmobile(String bookmobile) {
        this.bookmobile = bookmobile;
    }

    public String getBooktime() {
        return  booktime;
    }

    public void setBooktime(String booktime) {
        this.booktime=booktime;
    }

}


