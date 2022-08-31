package app.org.halsa360;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import app.org.halsa360.Constants.Const_values;
import app.org.halsa360.Constants.Http_url;
import app.org.halsa360.Util.GlobalVariables;

/**
 * Created by jitha on 2/2/16.
 */
public class Add_Patient_Activity extends AppCompatActivity {

    /*
    *
    * intializing the widgets
    * */
    private EditText patientname,patientphoneno,patientage,patientemail;
    private String str_patientname,str_patientphoneno,str_patientage,str_patientemail,patientid;
    private Typeface typeface;
    private TextView patientphonecountry;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        typeface = GlobalVariables.getTypeface(Add_Patient_Activity.this);
        /*
        *
        *
        *
        * */
        patientname=(EditText)findViewById(R.id.edittext_patientname);
        patientage=(EditText)findViewById(R.id.edittext_patienage);
        patientemail=(EditText)findViewById(R.id.edittext_patientemail);
        patientphonecountry=(TextView)findViewById(R.id.edittext_patientphnocountry);
        patientphoneno=(EditText)findViewById(R.id.edittext_patientphno);
        /*
        *
        *
        * 
        * */

         patientphoneno.setTypeface(typeface);
         patientphonecountry.setTypeface(typeface);
         patientname.setTypeface(typeface);
         patientage.setTypeface(typeface);
         patientemail.setTypeface(typeface);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_add, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_edit) {


        }if (id == R.id.action_done) {
            RequestParams params = new RequestParams();
            str_patientname = patientname.getText().toString();
            str_patientphoneno= patientphoneno.getText().toString();
            str_patientemail = patientemail.getText().toString();
            str_patientage=patientage.getText().toString();
            if (setValidation(str_patientname, str_patientphoneno,str_patientage)) {
                params.put("usertype_id", "5");
                params.put("firstname", str_patientname);
                params.put("email", str_patientemail);
                params.put("mobile", str_patientphoneno);
                params.put("user_id","182");
                params.put("age", str_patientage);
                params.put("reg_date", Const_values.PRESENTDATE);
                Log.e("value...", String.valueOf(params));
                invokeWS(params);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Http_url.ADD_PATIENT, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog

                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        patientid = String.valueOf(obj.getInt("patientId"));
                        Log.e("dff", patientid);
                           }
                    // Else display error message
                    else {
                        String error = obj.getString("error_msg");
                                 }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block

                }
            }
  // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog

                // When Http response code is '404'
                if (statusCode == 404) {

                } else {

                }
            }
        });
    }


    private boolean setValidation(String str_patientname, String str_patientphoneno, String str_patientage) {
        if (str_patientname.trim().length() == 0 || str_patientname.isEmpty()) {
            patientname.setError("Patient firstname cannot be empty.");
            return false;

        }else if (str_patientage.trim().length() == 0 || str_patientage.trim().isEmpty()) {
            patientage.setError("Enter Age.");
            return false;
        }else if (str_patientage.trim().equals("0")) {
            patientage.setError("Enter Valid Age.");
            return false;
        } else if(patientphoneno.getText().toString().length() == 0 || patientphoneno.getText().toString().isEmpty()){
            patientphoneno.setError("Primary mobile number cannot be empty.");
            return false;
        }else if(!GlobalVariables.validate(patientphoneno.getText().toString(), GlobalVariables.MOBILE_NUMBER_PATTERN)) {
            patientphoneno.setError("Please enter valid mobile number.");
            return false;
        }else if(patientemail.getText().toString().length()!=0){
            if(!GlobalVariables.validate(patientemail.getText().toString(), GlobalVariables.EMAIL_PATTERN)) {
                patientemail.setError("Please enter valid Email Id.");
                return false;
            }
        }

        return true;}


}

