package com.example.sai.aibill;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dialogex3 extends AppCompatDialogFragment {
    EditText qt,upp,qt1,dis;
    DatabaseHelper myDb;
    boolean inserted;
    String ite,q,u,d,a,da,mo,y1,y,h,mi,q2,a2;
    Integer q1;
    Float a1;
    TextView shdis,shco;
    String uzi,uzi2;
    private TextWatcher watcher;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String sal1 = getArguments().getString("oh");
        myDb = new DatabaseHelper(getContext(), "cal.db", null, 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogex3, null);
        qt1 = view.findViewById(R.id.editText6);
        dis = view.findViewById(R.id.editText9);
        shdis = view.findViewById(R.id.textView4);
        shco = view.findViewById(R.id.textView5);
        String pz = "\n\nUNIT :";
        String pz1 = "\n\nAMOUNT :";
        String p1 = "\nQTY :";
        Pattern paz = Pattern.compile(p1 + "(.*?)" + pz, Pattern.DOTALL);
        Pattern paz2 = Pattern.compile(pz + "(.*?)" + pz1, Pattern.DOTALL);
        Matcher mz = paz.matcher(sal1);
        Matcher mz2 = paz2.matcher(sal1);
        while (mz2.find()) {
            uzi = mz2.group(1);
        }
        while (mz.find()) {
            uzi2 = mz.group(1);
        }
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    int input = Integer.parseInt(dest.toString() + source.toString());
                    if (isInRange(0, Integer.parseInt(uzi2), input)) {
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
        qt1.setFilters(new InputFilter[]{filter});
         dis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() == 0){
                    shco.setText( "DISCOUNT: 0.0");
                }else {
                    if (!qt1.getText().toString().equals("")) {
                        Float mx = Float.parseFloat(qt1.getText().toString()) * Float.parseFloat(dis.getText().toString());
                        shco.setText("DISCOUNT: " + String.valueOf(mx));
                    }
                    else {
                        shco.setText("DISCOUNT: 0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        qt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() == 0){
                    shdis.setText("COST: 0.0");
                    shco.setText("DISCOUNT: 0.0");
                }else {
                    Float mx1 = Float.parseFloat(uzi) * Float.parseFloat(qt1.getText().toString());
                    shdis.setText("COST: " + String.valueOf(mx1));
                    if (!dis.getText().toString().equals("")){
                        Float mx = Float.parseFloat(qt1.getText().toString()) * Float.parseFloat(dis.getText().toString());
                        shco.setText("DISCOUNT: " + String.valueOf(mx));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        builder.setView(view).setTitle("").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String p1 = "\nQTY :";
                String p2 = "\n\nUNIT :";
                String p3 = "\n\nAMOUNT :";
                String p5 = "\n\nDATE :";
                String p6 = "/";
                String p7 = "\n\nTIME :";
                String p8 = ";";
                Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                Pattern pa1 = Pattern.compile("(.*?)" + p1, Pattern.DOTALL);
                Pattern pa2 = Pattern.compile(p2 + "(.*?)" + p3, Pattern.DOTALL);
                Pattern pa4 = Pattern.compile(p3 + "(.*?)" + p5, Pattern.DOTALL);
                Pattern pa5 = Pattern.compile(p5 + "(.*?)" + p6, Pattern.DOTALL);
                Pattern pa6 = Pattern.compile(p6 + "(.*?)" + p6, Pattern.DOTALL);
                Pattern pa7 = Pattern.compile(p6 + "(.*?)" + p7, Pattern.DOTALL);
                Pattern pa8 = Pattern.compile(p7 + "(.*?)" + p8);
                Pattern pa9 = Pattern.compile(p8 + "(.*?)" + p8);
                Pattern pa10 = Pattern.compile(p7 + "(.*?)" + p8);
                Matcher m = pa.matcher(sal1);
                Matcher m1 = pa1.matcher(sal1);
                Matcher m2 = pa2.matcher(sal1);
                Matcher m4 = pa4.matcher(sal1);
                Matcher m5 = pa5.matcher(sal1);
                Matcher m6 = pa6.matcher(sal1);
                Matcher m7 = pa7.matcher(sal1);
                Matcher m8 = pa8.matcher(sal1);
                Matcher m9 = pa9.matcher(sal1);
                while (m.find()) {
                    q = m.group(1);
                }
                while (m1.find()) {
                    ite = m1.group(1);
                }
                while (m2.find()) {
                    u = m2.group(1);
                }
                while (m4.find()) {
                    a = m4.group(1);
                }
                while (m5.find()) {
                    da = m5.group(1);
                }
                while (m6.find()) {
                    mo = m6.group(1);
                }
                while (m7.find()) {
                    y = m7.group(1);
                }
                while (m8.find()) {
                    h = m8.group(1);
                }

                while (m9.find()) {
                    mi = m9.group(1);
                }
                String[] parts = y.split("/");
                y1 = parts[1];
                q1 = Integer.parseInt(q) - Integer.parseInt(qt1.getText().toString());
                q2 = String.valueOf(q1);
                if (q1 < 0) {
                    Toast.makeText(getActivity(),"Inserted Quantity is more than the available quantity", Toast.LENGTH_LONG).show();
                }
                else {
                    a1 = Float.parseFloat(qt1.getText().toString()) * Float.parseFloat(u);
                    float a4 = a1 - Float.parseFloat(dis.getText().toString());
                    a2 = String.valueOf(a4);
                    myDb.insertdata7(ite,qt1.getText().toString(),u,dis.getText().toString(),a2,da,mo,y1,h,mi);
                    if (q.equals(qt1.getText().toString())){
                        myDb.up14(ite,qt1.getText().toString(),u,dis.getText().toString(),a2,da,mo,y1);
                    }
                    else {
                        myDb.up15(ite,qt1.getText().toString(),u,dis.getText().toString(),a2,da,mo,y1);
                    }

                }
                           }
        });
        qt = view.findViewById(R.id.editText20);
        upp = view.findViewById(R.id.editText2);
        return builder.create();
    }
    private boolean isInRange(int a,int b,int c){
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }


}
