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

public class pview extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    AutoCompleteTextView purname;
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
        setContentView(R.layout.activity_pview);
        myDb = new DatabaseHelper(pview.this, "cal.db", null, 1);
        purname = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        dat = (Button) findViewById(R.id.button6);
        puur = (ListView) findViewById(R.id.purlist);
        Cursor shiv = myDb.tab();
        while (shiv.moveToNext()) {
            if (!shiv.getString(0).equals("OPEN_STALK") && !shiv.getString(0).equals("DETAILS"))
                naam.add(shiv.getString(0));
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, naam);
        purname.setAdapter(adapter1);
        purname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Cursor gb = myDb.view1(purname.getText().toString());
               if(gb.getCount() == 0){
                   Toast.makeText(pview.this, "No Data", Toast.LENGTH_LONG).show();
                   theList12.add("NO DATA");
                   ListAdapter listAdapter = new ArrayAdapter<>(pview.this,android.R.layout.simple_list_item_1,theList12);
                   puur.setAdapter(listAdapter);
               }
               else {
                   while (gb.moveToNext()){
                       theList12.add("ITEM  " + gb.getString(0) + "\n");
                       theList12.add("QTY :" + gb.getString(1) + "\n");
                       theList12.add("UNIT :" + gb.getString(2) + "\n");
                       theList12.add("AMOUNT :" + gb.getString(3) + "\n");
                       theList12.add(gb.getString(4) + "/" + gb.getString(5) + "/" + gb.getString(6) + "\n");
                       theList12.add(gb.getString(7) + ":" + gb.getString(8) + "\n\n");
                       ListAdapter listAdapter1 = new ArrayAdapter<>(pview.this,android.R.layout.simple_list_item_1,theList12);
                       puur.setAdapter(listAdapter1);
                   }
               }
            }
        });
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
        if (purname.getText().toString().equals("")) {
            Toast.makeText(pview.this, "ENTER THE NAME FIRST", Toast.LENGTH_LONG).show();
            theList40.add("NO NAME ENTERED");
            ListAdapter listAdapter30 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList40);
            puur.setAdapter(listAdapter30);
        } else {
            Cursor c = myDb.getdata(purname.getText().toString(), da, mo, ye);
            if (c.getCount() == 0) {
                Toast.makeText(pview.this, "No Data", Toast.LENGTH_LONG).show();
                theList11.add("NO DATA FOUND");
                ListAdapter listAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList11);
                puur.setAdapter(listAdapter3);
            } else {
                while (c.moveToNext()) {
                    theList1.add("ITEM :" + c.getString(0) + "\n");
                    theList1.add("QTY :" + c.getString(1) + "\n");
                    theList1.add("UNIT :" + c.getString(2) + "\n");
                    theList1.add("AMOUNT :" + c.getString(3) + "\n");
                    theList1.add(c.getString(4) + "/" + c.getString(5) + "/" + c.getString(6) + "\n");
                    theList1.add(c.getString(7) + ":" + c.getString(8) + "\n\n");
                    ListAdapter listAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList1);
                    puur.setAdapter(listAdapter1);
                }
            }

        }
    }
}
