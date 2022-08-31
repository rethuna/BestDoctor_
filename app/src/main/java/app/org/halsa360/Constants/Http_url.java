package app.org.halsa360.Constants;

/**
 * Created by ACER on 12/14/2015.
 */
public class Http_url {
    public static final String BASEURL = "http://128.199.200.68:8080/mediconnect/";
    public static final String USER_REG = BASEURL + "webresources/new_user?";
    public static final String LOGIN = BASEURL + "webresources/login?";//user=test&pass=test&date=17-12-2015";
    public static final String Booking_Post_Detail=BASEURL+"webresources/booking?";
    //for viewing all booking data of a particular day
    public static final String BOOKING_LIST = BASEURL + "webresources/view_dr_appointments?";
    public static  final String CITY_lOCALITY=BASEURL+"webresources/city_locality_pin";
    //for calender view of bookings
    public static final String BOOKING_CALENDER_VIEW = BASEURL + "webresources/view_doc_book_count?";
    //userreg_id=182&&start_date=2016-02-01&&end_date=2016-02-05&&loc_user_id=78
    public static final String ADD_PATIENT = BASEURL + "webresources/patient?";
    //webresources/view_doc_book_count_on_days?userreg_id=182&&start_date=2016-02-08&&num_of_days=2&&loc_user_id=78
    public static final String BOOKING_STATUS = BASEURL + "webresources/view_doc_book_count_on_days?";

    public static final String CURRENT_CLINIC = BASEURL + "webresources/view_Location_on_time?";
    //?userreg_id=182&&date=2016-02-08&&current_time=08:00:00
    public static final String CLINIC_DETAIL = BASEURL + "webresources/view_all_dr_location?";
    //view_all_dr_location?userreg_id=182
    public static final String SLOT_BOOKING = BASEURL + "webresources/view_doc_free_slot?";
}