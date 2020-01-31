package com.example.sai.aibill;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class osview extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button dat;
    ListView puur;
    List<String> naam = new ArrayList<>();
    DatabaseHelper myDb;
    int y,m,d;
    String ye,mo,da;
    ArrayList<String> theList12 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osview);
        myDb = new DatabaseHelper(osview.this, "cal.db", null, 1);
        dat = (Button) findViewById(R.id.button20);
        puur = (ListView) findViewById(R.id.oslist);
        calen();
    }

    public void calen() {
        dat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "datePicker");


                    }
                }
        );

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int date) {
        Calendar ca = Calendar.getInstance();
        y = year;
        ye = String.valueOf(y);
        m = month + 1;
        mo = String.valueOf(m);
        d = date;
        da = String.valueOf(d);
        ArrayList<String> theList1 = new ArrayList<>();
        ArrayList<String> theList11 = new ArrayList<>();
        ArrayList<String> theList40 = new ArrayList<>();

            Cursor c = myDb.view25(da, mo, ye);
            if (c.getCount() == 0) {
                Toast.makeText(osview.this, "No Data", Toast.LENGTH_LONG).show();
                theList11.add("NO DATA FOUND");
                ListAdapter listAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList11);
                puur.setAdapter(listAdapter3);
            } else {
                while (c.moveToNext()) {
                    theList1.add("ITEM :" + c.getString(0) + "\n");
                    theList1.add("QTY :" + c.getString(1) + "\n");
                    theList1.add("UNIT :" + c.getString(2) + "\n");
                    theList1.add("DISCOUNT :" + c.getString(3) + "\n");
                    theList1.add("AMOUNT :" + c.getString(4) + "\n");
                    theList1.add(c.getString(7) + "/" + c.getString(8) + "/" + c.getString(9) + "\n");
                    ListAdapter listAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList1);
                    puur.setAdapter(listAdapter1);
                }
            }

        }
    }

