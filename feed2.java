package com.example.sai.aibill;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class feed2 extends AppCompatActivity {
    AutoCompleteTextView nam,siz,bran;
    Button fee;
    os o;
    ArrayList<items> arr;
    DatabaseHelper myDb;
    String q1,q2,q3,itt;
    List<String> arr1 = new ArrayList<>();
    List<String> arr2 = new ArrayList<>();
    List<String> arr3 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2);
        myDb = new DatabaseHelper(feed2.this, "cal.db", null, 1);
        nam = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView9);
        siz = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView10);
        bran = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView11);
        fee = (Button) findViewById(R.id.button10);
        Cursor shiv = myDb.view20();
        while (shiv.moveToNext()) {
            itt = shiv.getString(0);
            String p1 = "NAME:";
            String p2 = "\nSIZE:";
            String p3 = "\nBRAND:";
            String p4 = "\nVER:";
            Pattern pa1 = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
            Pattern pa2 = Pattern.compile(p2 + "(.*?)" + p3, Pattern.DOTALL);
            Pattern pa3 = Pattern.compile(p3 + "(.*?)" + p4, Pattern.DOTALL);
            Matcher m1 = pa1.matcher(itt);
            Matcher m2 = pa2.matcher(itt);
            Matcher m3 = pa3.matcher(itt);
            while (m1.find()) {
                q1 = m1.group(1);
            }
            while (m2.find()) {
                q2 = m2.group(1);
            }
            while (m3.find()) {
                q3 = m3.group(1);
            }
            arr1.add(q1);
            arr2.add(q2);
            arr3.add(q3);
        }
        Set<String> set1 = new HashSet<>(arr1);
        arr1.clear();
        arr1.addAll(set1);
        Set<String> set2 = new HashSet<>(arr2);
        arr2.clear();
        arr2.addAll(set2);
        Set<String> set3 = new HashSet<>(arr3);
        arr3.clear();
        arr3.addAll(set3);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr3);
        nam.setAdapter(adapter1);
        siz.setAdapter(adapter2);
        bran.setAdapter(adapter3);
        vi();
    }

    public void vi() {
        fee.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean inserted = myDb.insertitem("NAME:"+nam.getText().toString()+ "\nSIZE:"+siz.getText().toString()+"\nBRAND:"+bran.getText().toString()+"\n");
                        if (inserted){
                            Toast.makeText(feed2.this,"FEED SUCCESSFUL",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(feed2.this,Pur.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(feed2.this,"FEED UNSUCCESSFUL",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
}
