package com.example.sai.aibill;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

public class Dialogex2 extends AppCompatDialogFragment {
    EditText qt,upp,dis;
    TextView shco,shdi;
    DatabaseHelper myDb;
    String Hour,Minute,Date,Month,Year,goh;
    int lu,yo;
    String cb,naam,fla;
    SQLiteDatabase db;
    boolean inserted;
    String qtt,utt,dtt;
    String itt,qtt1,dtt1,att,poo,daa,maa,yaa,utt1;
    Integer qg = 0;
    public MyDialogFragmentListener DialogFragmentListner;

    public interface MyDialogFragmentListener{
        void onReturnValue(String foo);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cb = getArguments().getString("poo");
        }
        if (getArguments() != null) {
            naam = getArguments().getString("poo1");
        }
        if (getArguments() != null) {
            fla = getArguments().getString("poo2");
        }
        myDb = new DatabaseHelper(getContext(), "cal.db", null, 1);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        lu = Integer.parseInt(String.valueOf(Month));
        yo = lu + 1;
        goh = String.valueOf(yo);

        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogex2, null);
        qt = view.findViewById(R.id.editText33);
        upp = view.findViewById(R.id.editText21);
        dis = view.findViewById(R.id.editText8);
        shco = view.findViewById(R.id.textView7);
        shdi = view.findViewById(R.id.textView8);
        Cursor n = myDb.view2(cb);
        if (n.getCount() != 0){
            while (n.moveToNext()){
                qtt = n.getString(1);
                utt = n.getString(2);
                dtt = n.getString(3);
                qt.setText(qtt,TextView.BufferType.EDITABLE);
                upp.setText(utt,TextView.BufferType.EDITABLE);
                dis.setText(dtt,TextView.BufferType.EDITABLE);

            }
        }

        Cursor fas = myDb.view23(cb+"%",Date,goh,Year);
        if(fas.getCount() != 0){
            while (fas.moveToNext()){
                qg = qg + Integer.parseInt(fas.getString(1));
            }
        }

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
        qt.setFilters(new InputFilter[]{filter});

        qt.addTextChangedListener(new TextWatcher() {
                                      @Override
                                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                      }

                                      @Override
                                      public void onTextChanged(CharSequence s, int start, int before, int count) {
                                          if(s.toString().trim().length() == 0){
                                              shco.setText("COST: 0.0");
                                              shdi.setText("DISCOUNT: 0.0");
                                          }else {
                                              if (!upp.getText().toString().equals("")) {
                                                  Float mx1 = Float.parseFloat(upp.getText().toString()) * Float.parseFloat(qt.getText().toString());
                                                  shco.setText("COST: " + String.valueOf(mx1));
                                              }
                                              if (!dis.getText().toString().equals("")){
                                                  Float mx = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(dis.getText().toString());
                                                  shdi.setText("DISCOUNT: " + String.valueOf(mx));
                                              }
                                          }
                                      }

                                      @Override
                                      public void afterTextChanged(Editable s) {
                                      }

                                  });

        upp.addTextChangedListener(new TextWatcher() {
                                      @Override
                                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                      }

                                      @Override
                                      public void onTextChanged(CharSequence s, int start, int before, int count) {
                                          if(s.toString().trim().length() == 0){
                                              shco.setText("COST: 0.0");
                                          }else {
                                              if (!qt.getText().toString().equals("")){
                                                  Float mx = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                                                  shco.setText("COST: " + String.valueOf(mx));
                                              }
                                          }
                                      }

                                      @Override
                                      public void afterTextChanged(Editable s) {
                                      }

                                  });
        dis.addTextChangedListener(new TextWatcher() {
                                      @Override
                                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                      }

                                      @Override
                                      public void onTextChanged(CharSequence s, int start, int before, int count) {
                                          if(s.toString().trim().length() == 0){
                                              shdi.setText("DISCOUNT: 0.0");
                                          }else {
                                              if (!qt.getText().toString().equals("")){
                                                  Float mx = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(dis.getText().toString());
                                                  shdi.setText("DISCOUNT: " + String.valueOf(mx));
                                              }
                                          }
                                      }

                                      @Override
                                      public void afterTextChanged(Editable s) {
                                      }

                                  });

        builder.setView(view).setTitle(naam).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cursor bv = myDb.view2(cb);
                if(bv.getCount() == 0) {
                    Cursor gh = myDb.view11(cb, Date, goh, Year);
                    while (gh.moveToNext()) {
                        itt = gh.getString(0);
                        qtt1 = gh.getString(1);
                        utt1 = gh.getString(2);
                        dtt1 = gh.getString(3);
                        att = gh.getString(4);
                        poo = gh.getString(6);
                        daa = gh.getString(7);
                        maa = gh.getString(8);
                        yaa = gh.getString(9);


                    }
                        float jok = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                        float bob = jok - Float.parseFloat(dis.getText().toString());
                        String am = String.valueOf(bob);
                        myDb.insertdata4(cb, qt.getText().toString(), upp.getText().toString(), dis.getText().toString(), am);

                }
                else {
                    Cursor gh = myDb.view11(cb, Date, goh, Year);
                    while (gh.moveToNext()) {
                        itt = gh.getString(0);
                        qtt1 = gh.getString(1);
                        utt1 = gh.getString(2);
                        dtt1 = gh.getString(3);
                        att = gh.getString(4);
                        poo = gh.getString(6);
                        daa = gh.getString(7);
                        maa = gh.getString(8);
                        yaa = gh.getString(9);


                    }
                    Float qdd = Float.parseFloat(qtt1) - Float.parseFloat(qt.getText().toString());
                    String qdd1 = String.valueOf(qdd);
                    if (qdd < 0) {
                       // Toast.makeText(getActivity(), "Quantity exceeded Please check Opening Stalk for Quantity", Toast.LENGTH_LONG).show();
                        DialogFragmentListner.onReturnValue("0");
                    } else {
                        float jok = Float.parseFloat(qt.getText().toString()) * Float.parseFloat(upp.getText().toString());
                        ;
                        float bob = jok - Float.parseFloat(dis.getText().toString());
                        String am = String.valueOf(bob);
                        myDb.up1(cb, qt.getText().toString(), upp.getText().toString(), dis.getText().toString(), am);

                    }
                }

            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            DialogFragmentListner =(MyDialogFragmentListener) getActivity();

        }catch (ClassCastException e){

        }
    }

    private boolean isInRange(int a,int b,int c){
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }


}

