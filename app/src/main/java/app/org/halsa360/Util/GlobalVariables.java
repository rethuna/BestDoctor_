package app.org.halsa360.Util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ACER on 11/29/2015.
 */
public class GlobalVariables {
    public static  String patientListCurrentDate = null;  // Used for Patient List page
    private static final String REQUIRED_MSG = "required";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9_.]*@[a-zA-Z]*.[a-zA-Z]*";
    public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    public static String ZIPCODE_PATTERN = "^[0-9]{6}(?:-[0-9]{4})?$";
    private static final String EMAIL_MSG = "invalid email";
    //matches 10-digit numbers only
    public static String MOBILE_NUMBER_PATTERN = "[0-9][0-9]{9,11}";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");

    private static final String PHONE_MSG = "###-#######";
    //font
    public static Typeface getTypeface(Context context){
        return  Typeface.createFromAsset(context.getAssets(), "Aller_Rg.ttf");
    }
    //for getting lastdate of a month
    public static String getDate(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
        return sdf.format(date);
    }
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, MOBILE_NUMBER_PATTERN, PHONE_MSG, required);
    }


  public static boolean validate(final String hex, String PATTERN) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_PATTERN, EMAIL_MSG, required);
    }
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }


    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

}
