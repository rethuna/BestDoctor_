//package app.org.halsa360;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class AddRecordActivity extends AppCompatActivity {
//
//
//    private ProgressDialog prgDialog;
//    TextView datetxt,datemnthtxt,datemonthyr,daytxt,txtviewcount,txttime,txttoken,doctornametxt,clinicname,locationtxtview;
//    TextView patientnametxt,agetxt,Locationtxt,enternotestxt;
//    private String userid;
//    Button savebtn,clearbtn,maximizebtn,searchh,addnewtxt;
//
//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_record);
////        prgDialog = new ProgressDialog(this);
////        // Set Progress Dialog Text
////        prgDialog.setMessage("Please wait...");
////        // Set Cancelable as False
////        prgDialog.setCancelable(false);
////
////        SharedPreferences sharedpreferences = getSharedPreferences("SmartApp", Context.MODE_PRIVATE);
////        userid= "10"; //sharedpreferences.getString(Home_Login.USER_ID, ""); //Need to update with actual user id
////
////        datetxt = (TextView)findViewById(R.id.datetxt);
////        datetxt.setText("15");
////
////        datemnthtxt = (TextView)findViewById(R.id.datemnthtxt);
////        datemnthtxt.setText("Feb");
////
////        datemonthyr  =  (TextView)findViewById(R.id.datemnthyr);
////        datemonthyr.setText("2016");
////
////        daytxt  =  (TextView)findViewById(R.id.daytxt);
////        daytxt.setText("Monday");
////
////        txtviewcount  =  (TextView)findViewById(R.id.textViewcount);
////        txtviewcount.setText("20 days to go");
////
////        txttime  =  (TextView)findViewById(R.id.txtTime);
////        txttime.setText("5:30 AM");
////
////        txttoken  =  (TextView)findViewById(R.id.txttoken);
////        txttoken.setText("8");
////
////        doctornametxt  =  (TextView)findViewById(R.id.doctornametxt);
////        doctornametxt.setText("Doctor Name");
////
////        clinicname  =  (TextView)findViewById(R.id.clinicname);
////        clinicname.setText("Clinic Name");
////
////        locationtxtview  =  (TextView)findViewById(R.id.locationtxtview);
////        locationtxtview.setText("Location");
////
////        searchh  =  (Button)findViewById(R.id.searchh);
////        addnewtxt  =  (Button)findViewById(R.id.addnewtxt);
////
////        patientnametxt  =  (TextView)findViewById(R.id.patientnametxt);
////        patientnametxt.setText("Name");
////
////        agetxt  =  (TextView)findViewById(R.id.agetxt);
////        agetxt.setText("Age");
////
////        Locationtxt  =  (TextView)findViewById(R.id.Locationtxt);
////        Locationtxt.setText("Location");
////
////        enternotestxt  =  (TextView)findViewById(R.id.enternotestxt);
////        enternotestxt.setText("Enter notes");
////
////        clearbtn  =  (Button)findViewById(R.id.clearbtn);
////
////        maximizebtn  =  (Button)findViewById(R.id.maximizebtn);
////
////        savebtn  =  (Button)findViewById(R.id.savebtn);
////
////
////        savebtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////                RequestParams params = new RequestParams();
////                params.put("record_id", "0");
////                params.put("doc_regid", "165");
////                params.put("pat_regid", "86");
////                params.put("rec_date", "2016-02-14");
////                params.put("loc_user_id","1");
////                params.put("doctr_patient_med_id", "1");
////                params.put("next_start_date", "2016-02-18");
////                params.put("note", "testnotes");
////                invokeRecordvicitAdd(params);
////
////                }
////                });
//
//        }
//
//    private void invokeRecordvicitAdd(RequestParams params)
//    {
//
//        try {
//
//
//            AsyncHttpClient client = new AsyncHttpClient();
//           // client.get(Http_url.INSERT_RECORDVISIT, params, new AsyncHttpResponseHandler()
//          //  client.get(Http_url.INSERT_RECORDVISIT, params, new AsyncHttpResponseHandler()
//            client.get(Http_url.INSERT_RECORDVISIT+"record_id=0&doctr_patient_med_id=1&pat_regid=86&note=tetwhy&rec_date=2016-02-14&doc_regid=165&loc_user_id=1&next_start_date=2016-02-20" ,new AsyncHttpResponseHandler()
//
//
//                            //  client.get(Http_url.ADD_PATIENT, params, new AsyncHttpResponseHandler()
//            {
//
//                // When the response returned by REST has Http response code '200'
//                @Override
//                public void onSuccess(String response) {
//                    // Hide Progress Dialog
//
//                    try {
//                        // JSON Object
//                        JSONObject obj = new JSONObject(response);
//                        // When the JSON response has status boolean value assigned with true
//                      //  if (obj.getBoolean("status")) {
//                          //  String patientid = String.valueOf(obj.getInt("patientId"));
//                          //  Log.e("dff", patientid);
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecordActivity.this);
//                            builder.setMessage("Success")
//                                    .setCancelable(false)
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            //navigate_to_appoinment();
//                                        }
//                                    });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                     //   }
//                        // Else display error message
//                       // else {
//                            String error = obj.getString("error_msg");
//                       /* AlertDialog.Builder builder = new AlertDialog.Builder(doctor_addpatient.this);
//                        builder.setMessage("Patient already registered")
//                                .setCancelable(false)
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        //do things
//                                    }
//                                });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                        Toast.makeText(getApplicationContext(), "Patient already registered", Toast.LENGTH_LONG).show();*/
//                      //  }
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                  /*  AlertDialog.Builder builder = new AlertDialog.Builder(doctor_addpatient.this);
//                    builder.setMessage("Something Went Wrong At ServerSide")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //do things
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();*/
//                    }
//                }
//
//                // When the response returned by REST has Http response code other than '200'
//                @Override
//                public void onFailure(int statusCode, Throwable error,
//                                      String content) {
//                    // Hide Progress Dialog
//
//                    // When Http response code is '404'
//                    if (statusCode == 404) {
//                   /* AlertDialog.Builder builder = new AlertDialog.Builder(doctor_addpatient.this);
//                    builder.setMessage("Something Went Wrong At ServerSide")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //do things
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();*/
//                    } else {
//                  /*  Intent netconntn = new Intent(getApplicationContext(), Internet_Connection.class);
//                    startActivity(netconntn);
//*/
//                    }
//                }
//            });
//        } catch (Exception e) {
//            Log.d("error", String.valueOf(e));
//
//        }
//
//    }
//
//}
//
//
