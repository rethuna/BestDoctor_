package app.org.halsa360.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ACER on 12/14/2015.
 */
public class Const_values {
    public static String LASTDATEOFMONTH ;
    public static String CURRENT_DOCTOR;
    public static String CURRENT_DOCTOR_ID;
    public static String PRESENTDATE;
    public static String CURRENT_CLINIC;
    public static String CURRENT_CLINIC_ID;
    public static String DATE_SELECTED;

    public static Map<String, String> location= new HashMap<>();
    public static Map<String,String>  spindata= new HashMap<>();
    public static Map<String, String> booking_status= new HashMap<>();
    public static int Dateflag=1;
    public static Map<Integer, String> session1tokens= new HashMap<>();
    public static Map<Integer, String> session2tokens= new HashMap<>();
    public static Map<Integer, String> session3tokens= new HashMap<>();
    public static Map<Integer, String> session4tokens= new HashMap<>();
    public static Map<Integer, String> session1= new HashMap<>();
    public static Map<Integer, String> session2= new HashMap<>();
    public static Map<Integer, String> session3= new HashMap<>();
    public static Map<Integer, String> session4= new HashMap<>();
   // public static ArrayList<Map<Integer, String>> arraylist = new ArrayList<>();
    public static  Map<Integer,String>map1 = new TreeMap<Integer, String>();
    public static  Map<Integer,String>map2 = new TreeMap<Integer, String>();
    public static  Map<Integer,String>map3 = new TreeMap<Integer, String>();
    public static  Map<Integer,String>map4 = new TreeMap<Integer, String>();

    public static int slot_arraysize;
    public static int session1_arraysize;
    public static int session2_arraysize;
    public static int session3_arraysize;
    public static int session4_arraysize;
}


