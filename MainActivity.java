package com.example.sai.aibill;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button openstk,pur,sell;
    DatabaseHelper myDb;
    String Hour,Minute,Date,Month,Year,goh;
    int lu,yo;
    String daa1,maa1,yaa1;
    String itt2,qtt2,utt2,dtt2,att2,daa2,maa2,yaa2;
    String q,q1,q2,q3;
    ArrayList<String> arr = new ArrayList<String>(2000);
    String qz,uz,dz,az;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(MainActivity.this, "cal.db", null, 1);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        lu = Integer.parseInt(String.valueOf(Month));
        yo = lu + 1;
        goh = String.valueOf(yo);

        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));

        openstk = (Button) findViewById(R.id.button);
        pur = (Button) findViewById(R.id.button2);
        sell = (Button) findViewById(R.id.button3);
        myDb.dele1();
        myDb.dele3();
        myDb.up6(String.valueOf(0),String.valueOf(1));
        Cursor lk = myDb.view6(Date, goh, Year);
        if (lk.getCount() == 0) {

            Cursor nc = myDb.view7();
            if (nc.getCount() != 0) {
                if (nc.moveToLast()) {
                    daa1 = nc.getString(7);
                    maa1 = nc.getString(8);
                    yaa1 = nc.getString(9);

                }
                Cursor bv = myDb.view6(daa1, maa1, yaa1);
                if (bv.getCount() != 0) {
                    while (bv.moveToNext()) {
                        itt2 = bv.getString(0);
                        qtt2 = bv.getString(1);
                        utt2 = bv.getString(2);
                        dtt2 = bv.getString(3);
                        att2 = bv.getString(4);
                        daa2 = bv.getString(7);
                        maa2 = bv.getString(8);
                        yaa2 = bv.getString(9);
                        String p1 = "\nVER:";
                        String p2 = ";";
                        Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                        Pattern pa1 = Pattern.compile("(.*?)" + p1,Pattern.DOTALL);
                        Matcher m = pa.matcher(itt2);
                        Matcher m1 = pa1.matcher(itt2);
                        while (m.find()) {
                            q = m.group(1);
                        }
                        while (m1.find()) {
                            q1 = m1.group(1);
                        }
                        if(arr.contains(q1)){


                        }
                        else {
                            arr.add(q1);
                            String qf = q1 + "%";
                            Cursor bz = myDb.view15(qf);
                            if (bz.getCount() != 0 ){
                                Integer count = 1;
                                while (bz.moveToNext()){
                                    String ian = bz.getString(0);
                                    qz = bz.getString(1);
                                    uz = bz.getString(2);
                                    dz = bz.getString(3);
                                    az = bz.getString(4);
                                    String p3 = "\nVER:";
                                    String p4 = ";";
                                    Pattern pa2 = Pattern.compile(p3 + "(.*?)" + p4, Pattern.DOTALL);
                                    Pattern pa3 = Pattern.compile("(.*?)" + p3,Pattern.DOTALL);
                                    Matcher m2 = pa2.matcher(ian);
                                    Matcher m3 = pa3.matcher(ian);
                                    while (m2.find()) {
                                        q2 = m2.group(1);
                                    }
                                    while (m3.find()) {
                                        q3 = m3.group(1);
                                    }
                                    String te = q3 + "\nVER:"+String.valueOf(count);
                                    myDb.insertdata16(te,qz,uz,dz,az, String.valueOf(1),String.valueOf(0), Date, goh, Year);
                                    count = count + 1;
                                }
                            }
                        }

                    }
                }
            }
            else{

            }
        }
        hoj();
        hojo();
        hojo1();
    }

    void hoj(){
        openstk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,os.class);
                        startActivity(intent);

                    }
                }
        );

    }

    void hojo(){
        pur.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Pur.class);
                        startActivity(intent);

                    }
                }
        );

    }

    void hojo1(){
        sell.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Sell.class);
                        startActivity(intent);

                    }
                }
        );

    }
}
