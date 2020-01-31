package com.example.sai.aibill;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dialogex1 extends AppCompatDialogFragment {
    EditText qt,upp;
    DatabaseHelper myDb;
    String Hour,Minute,Date,Month,Year,goh;
    int lu,yo;
    String sal,sali,sali1;
    SQLiteDatabase db;
    boolean inserted;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        if (getArguments() != null) {
            sal = getArguments().getString("ted");
        }
        if (getArguments() != null) {
            sali = getArguments().getString("ted1");
        }
        if (getArguments() != null) {
            sali1 = getArguments().getString("ted2");
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog1, null);
        qt = view.findViewById(R.id.editText20);
        upp = view.findViewById(R.id.editText10);
        Cursor n = myDb.view2(sali);
        if (n.getCount() != 0){
            while (n.moveToNext()){
                String qtt = n.getString(1);
                String utt = n.getString(2);
                qt.setText(qtt,TextView.BufferType.EDITABLE);
                upp.setText(utt,TextView.BufferType.EDITABLE);

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
                Cursor bv = myDb.view2(sali);
                if(bv.getCount() == 0){
                    float am = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                    String amu = Float.toString(am);
                    inserted = myDb.insertData1(sali,qt.getText().toString(),upp.getText().toString(),amu);
                }
                else{
                    float am = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                    String amu = Float.toString(am);
                    myDb.up12(sali,qt.getText().toString(),upp.getText().toString(),amu);

                }



            }
        });
        return builder.create();
    }


}
