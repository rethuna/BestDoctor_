package app.org.halsa360;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.org.halsa360.Util.GlobalVariables;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaStr = "1970-01-01 08:52:00.0";
        Date fechaNueva = null;
        try {
            fechaNueva = format.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat("'Date : 'dd-MM-yyyy\n'Time : 'KK:mm a");
        System.out.println(format.format(fechaNueva));
        String h=outputFormat .format(fechaNueva);
        Log.e("hfghjdsgufdgud", h);
    }
}
