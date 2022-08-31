
package app.org.halsa360.pojo;


/**
 * Created by viji on 7/2/16.
 */

public class Free_Booking_Slots {
    private String slot_token;
    private String slot_time;


//booking_date":"2016-02-02","total_bokng_count":1,"free_slot_count":-1}

    public Free_Booking_Slots(String slot_token_day, String slot_time_day) {
        this.slot_token = slot_token_day;
        this.slot_time = slot_time_day;

    }

    public String getSlot_token() {
        return slot_token;
    }

    public void setSlot_token(String slot_token) {
        this.slot_token = slot_token;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }
}
