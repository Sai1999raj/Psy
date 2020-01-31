package com.example.sai.aibill;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dialogex4 extends AppCompatDialogFragment {
    EditText qn,upn;
    TextView shw;
    DatabaseHelper myDb;
    String Hour,Minute,Date,Month,Year,goh;
    int lu,yo;
    String sal,sali,sali1;
    SQLiteDatabase db;
    boolean inserted;
    Float am;
    String amu;
    String vas;
    Integer qg = 0;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        if (getArguments() != null) {
            sal = getArguments().getString("poo");
        }
        if (getArguments() != null) {
            sali = getArguments().getString("poo1");
        }
        if (getArguments() != null) {
            sali1 = getArguments().getString("poo2");
        }
        myDb = new DatabaseHelper(getContext(), "cal.db", null, 1);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        Month =String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        lu = Integer.parseInt(String.valueOf(Month));
        yo = lu + 1;
        goh = String.valueOf(yo);

        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
        Cursor fas = myDb.view23(sal+"%",Date,goh,Year);
        if(fas.getCount() != 0){
            while (fas.moveToNext()){
                qg = qg + Integer.parseInt(fas.getString(1));
            }
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogex4, null);
        qn = view.findViewById(R.id.editText13);
        upn = view.findViewById(R.id.editText14);
        shw = view.findViewById(R.id.textView6);
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    int input = Integer.parseInt(dest.toString() + source.toString());
                    if (isInRange(0, qg, input)) {
                        return null;
                    }
                    else {
                        Toast.makeText(getActivity(),"QUANTITY EXCEEDED", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException nfe) {
                }
                return "";
            }
        };
        qn.setFilters(new InputFilter[]{filter});
        qn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() == 0){
                    shw.setText( "COST: 0.0");
                }else {
                    if (!upn.getText().toString().equals("")) {
                        Float mx = Float.parseFloat(qn.getText().toString()) * Float.parseFloat(upn.getText().toString());
                        shw.setText("COST: " + String.valueOf(mx));
                    }
                    else {
                        shw.setText("COST: 0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        upn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() == 0){
                    shw.setText( "COST: 0.0");
                }else {
                    if (!qn.getText().toString().equals("")) {
                        Float mx = Float.parseFloat(qn.getText().toString()) * Float.parseFloat(upn.getText().toString());
                        shw.setText("COST: " + String.valueOf(mx));
                    }
                    else {
                        shw.setText("COST: 0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Cursor n = myDb.view2(sal);
        if (n.getCount() != 0){
            while (n.moveToNext()){
                String qtt = n.getString(1);
                String utt = n.getString(2);
                qn.setText(qtt,TextView.BufferType.EDITABLE);
                upn.setText(utt,TextView.BufferType.EDITABLE);

            }
        }
        builder.setView(view).setTitle(sal).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cursor bv = myDb.view2(sal);
                if(bv.getCount() == 0){
                    am = Float.parseFloat(qn.getText().toString()) * Float.parseFloat(upn.getText().toString());
                    amu = String.valueOf(am);
                    inserted = myDb.insertData1(sal,qn.getText().toString(),upn.getText().toString(),amu);
                }
                else{
                    float am = Float.parseFloat(qn.getText().toString()) * Float.parseFloat(upn.getText().toString());
                    String amu = Float.toString(am);
                    myDb.up12(sal,qn.getText().toString(),upn.getText().toString(),amu);

                }



            }
        });
        return builder.create();
    }
    private boolean isInRange(int a,int b,int c){
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }



}
