package com.example.sai.aibill;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dialogex extends AppCompatDialogFragment {
    EditText qt,upp;
    DatabaseHelper myDb;
    boolean inserted;
    String Date,Year,Hour,Minute,Month,goh;
    Integer lu,yo;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final String sal = getArguments().getString("edt");
        myDb = new DatabaseHelper(getContext(), "cal.db", null,1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view).setTitle(sal).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float am = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                String amu = Float.toString(am);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
                Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
                lu = Integer.parseInt(String.valueOf(Month));
                yo = lu + 1;
                goh = String.valueOf(yo);

                Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
                Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
                Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));

                Cursor c = myDb.we(sal,upp.getText().toString(),Date,goh,Year);
                if (c.getCount() == 0) {
                    inserted = myDb.insertData(sal, qt.getText().toString(), upp.getText().toString(),String.valueOf(0), amu,String.valueOf(1),String.valueOf(0),Date,goh,Year);
                }
                else {
                    inserted = myDb.up2(sal,qt.getText().toString(),upp.getText().toString(),String.valueOf(0),amu,Date,goh,Year);
                }
                if(inserted){
                    Toast.makeText(getContext(),"DATA INSERTED",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"DATA NOT INSERTED" ,Toast.LENGTH_LONG).show();
                }

            }
        });
        qt = view.findViewById(R.id.editText20);
        upp = view.findViewById(R.id.editText2);
        return builder.create();
    }


}
