package app.org.halsa360;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Hashtable;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.ConnectionDetector;
import app.org.halsa360.Util.Encrypt;
import app.org.halsa360.Util.GlobalVariables;
import app.org.halsa360.Webservice.login_splash;

/**
 * Created by jitha on 3/2/16.
 */
public class User_Login_Activity extends AppCompatActivity {


    private EditText username, password;
    private TextView forgot_password, new_user_signup;
    private Button signin;
    private Typeface typeface;
    private Boolean connection;
    Encrypt encrpt=new Encrypt();//password encriptor
    private ProgressDialog prgDialog;
    public static final String SIGNIN_PREF = "SignInPref";
    public static final String USER_ID = "UserId";
    public static final String CONFIRM_STATUS = "confirm";
    public static final String APPROVAL_STATUS = "approval";
    public static final String US_ID = "Usid";
    public static final String US_REGNO = "Usregno";
    public static final String US_NAME = "Usname";
    String userregidd,getstart,usertypeid,Utid,Utrno,Utname,otp;
    String Cconfirm;
    String Aaproval;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        cd = new ConnectionDetector(getApplicationContext());
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        typeface = GlobalVariables.getTypeface(User_Login_Activity.this);
        /*
        *
        * */
        username = (EditText) findViewById(R.id.edittext_username);
        password = (EditText) findViewById(R.id.edittext_password);
        forgot_password = (TextView) findViewById(R.id.textview_forgot_password);
        new_user_signup = (TextView) findViewById(R.id.textview_newuser_signup);
        signin = (Button) findViewById(R.id.button_signin);
        username.setTypeface(typeface);
        password.setTypeface(typeface);
        forgot_password.setTypeface(typeface);
        new_user_signup.setTypeface(typeface);
        signin.setTypeface(typeface);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            username.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
                String struserName = username.getText().toString().trim();
                String strpassword = password.getText().toString();
                String currentDate = GlobalVariables.sdf.format(new Date());
                String currentTime = GlobalVariables.sdftime.format(new Date());
                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", struserName);
                    loginPrefsEditor.putString("password", strpassword);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                if (struserName == null || struserName.length() == 0) {
                    username.setError("Please enter the username");
                } else if (strpassword == null || strpassword.length() == 0) {
                    password.setError("Please enter the password");

                } else {
                    try {
                        RequestParams params = new RequestParams();
                        params.put("user",struserName);
                        params.put("pass",encrpt.encrypt(strpassword));
                        params.put("login_date", currentDate);
                        params.put("login_time",currentTime );
                        //  params.put("date",currentDateandTime);
                        // Invoke RESTful Web Service with Http parameters
                       invokeWS(params);
                    }
                    catch(Exception ex){


                        ex.printStackTrace();
                        Log.d("TAG", "authentication challenge " + ex.getMessage().contains("authentication challenge"));


                    }

                }

            }

        });
    }




    public void invokeWS(RequestParams params){
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Http_url.LOGIN, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                Log.e("fff",response);
                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    Log.e("response____>",response);
                    // When the JSON response has status boolean value assigned with true
                    //boolean status=obj.getBoolean("status");
                    boolean login=obj.getBoolean("login");
                    //System.out.println(status);
                    System.out.println(login);



                    if (obj.getBoolean("login")) {
//getting response to a string
                        userregidd = obj.getString("userreg_id");
                        usertypeid = obj.getString("user_type");
                        getstart = obj.getString("error_msg");

                        if (getstart.equals("1")) {
                            Cconfirm = obj.getString("confirm_status");
                            Aaproval = obj.getString("approval_status");
                            if (usertypeid.equals("1")) {
                                Utid = obj.getString("doctor_id");
                                Utname = obj.getString("doctor_name");
                                Utrno = obj.getString("reg_no");
                                Const_values.CURRENT_DOCTOR=Utname;
                                Const_values.CURRENT_DOCTOR_ID=Utid;
                                navigatetodoctor();
                            } else if (usertypeid.equals("2")) {
                                Utid = obj.getString("clinic_id");
                                Utname = obj.getString("clinic_name");
                                Utrno = obj.getString("reg_no");
                               // navigatetoclinic();

                                Toast.makeText(getApplicationContext(), "Work Processing", Toast.LENGTH_LONG).show();
                            } else if (usertypeid.equals("3")) {
                                Toast.makeText(getApplicationContext(), "Work Processing", Toast.LENGTH_LONG).show();
                            } else if (usertypeid.equals("4")) {
                                Toast.makeText(getApplicationContext(), "Work Processing", Toast.LENGTH_LONG).show();
                            }
                        } else if (getstart.equals("0"))
                        {
                            Cconfirm="false";
                            Aaproval="false";
                            otp = obj.getString("otp_verify");
                            if (otp.equals("0")) {
                                // navigatetootp();
                            } else if (otp.equals("1")) {
                                if (usertypeid.equals("1")) {
                                    navigatetodoctor();
                                } else if (usertypeid.equals("2")) {
                                    //navigatetoclinic();
                                } else if (usertypeid.equals("3")) {

                                } else if (usertypeid.equals("4")) {

                                }
                            }
                        }
//
                    }
                    // Else display error message
                    else {
                        //showdata.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                        String error =obj.getString("error_msg");
                        Log.e("errr",error);
                        AlertDialog.Builder builder = new AlertDialog.Builder(User_Login_Activity.this);
                        builder.setMessage("Invalid User")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        //  Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(User_Login_Activity.this);
                    builder.setMessage("Invalid User")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                   /* Intent netconntn = new Intent(getApplicationContext(),Activity_Server_Down.class);
                    startActivity(netconntn);*/
                }
                // When Http response code is '500'

                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    /*Intent netconntn = new Intent(getApplicationContext(),Internet_Connection.class);
                    startActivity(netconntn);*/

                }
            }
        });
    }


    private void navigatetodoctor() {
        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(User_Login_Activity.USER_ID, userregidd);
        editor.putString(User_Login_Activity.CONFIRM_STATUS, Cconfirm);
        editor.putString(User_Login_Activity.APPROVAL_STATUS, Aaproval);
        editor.putString(User_Login_Activity.US_ID, Utid);
        editor.putString(User_Login_Activity.US_NAME, Utname);
        editor.putString(User_Login_Activity.US_REGNO, Utrno);
        editor.commit();
        Intent i = new Intent(getApplicationContext(),login_splash.class);
        startActivity(i);

        username.setText("");
        password.setText("");

    }



}


