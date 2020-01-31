package com.example.sai.aibill;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pur extends AppCompatActivity {
    List<String> naam = new ArrayList<>();
    AutoCompleteTextView name;
    DatabaseHelper myDb;
    MyCustomAdapter dataAdapter = null;
    CheckBox cb;
    Integer flag = 0;
    ArrayList<items> itemlist,list;
    ListView listView;
    SQLiteDatabase db;
    Button purview,feedpu;
    String ite,qt,di,un,am,god,Date,Month,Year,Hour,Minute,goh;
    Float nv;
    Integer lu,yo;
    String itt,qtt,utt,dtt,att,daa,maa,yaa;
    String itt1,qtt1,utt1,dtt1,att1,daa1,maa1,yaa1;
    String itt2,qtt2,utt2,dtt2,att2,daa2,maa2,yaa2;
    ArrayList<String> arr = new ArrayList<String>(2000);
    Integer i;
    String q,q1,q2,q3,q5;
    String iee,iee2;
    Integer flag8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pur);
        myDb = new DatabaseHelper(Pur.this, "cal.db", null, 1);
        name = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        purview = (Button) findViewById(R.id.button8);
        feedpu = (Button) findViewById(R.id.button7);
        Cursor shiv = myDb.tab();
            while (shiv.moveToNext()) {
                if(!shiv.getString(0).equals("OPEN_STALK") && !shiv.getString(0).equals("DETAILS"))
                naam.add(shiv.getString(0));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, naam);
            name.setAdapter(adapter);
            name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    flag = 1;
                }
            });
            displaylistView();
            checkButtonClick();
            view();
            feed();

        }
    public void displaylistView() {
        itemlist = new ArrayList<items>();
        Cursor hop = myDb.search();
        if (hop.getCount() == 0) {
            Toast.makeText(Pur.this, "No Data", Toast.LENGTH_LONG).show();
        } else {
            while (hop.moveToNext()) {
                String iit = hop.getString(0);
                String p1 = "\nVER:";
                String p2 = ";";
                Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                Pattern pa1 = Pattern.compile("(.*?)" + p1,Pattern.DOTALL);
                Matcher m = pa.matcher(iit);
                Matcher m1 = pa1.matcher(iit);
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
                        }



            }
            for (i = 0;i < arr.size();i++) {
                items Items = new items(arr.get(i), false);
                itemlist.add(Items);
            }
        }

        dataAdapter = new Pur.MyCustomAdapter(this, R.layout.os2, itemlist);
        listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items Item = (items) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "clicked on row :" + Item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public class MyCustomAdapter extends ArrayAdapter<items> {
        ArrayList<items> itemList1;

        public MyCustomAdapter(Context context, int textviewResourceid, ArrayList<items> itemList) {

            super(context, textviewResourceid, itemList);
            this.itemList1 = new ArrayList<items>();
            this.itemList1.addAll(itemList);


        }

        private class ViewHolder {
            CheckBox name;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            Pur.MyCustomAdapter.ViewHolder holder = null;
            Log.v("ConvertView",String.valueOf(position));
            if (convertView == null)
            {
                LayoutInflater vi =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.os2,null);
                holder = new Pur.MyCustomAdapter.ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                final Pur.MyCustomAdapter.ViewHolder finalHolder = holder;
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb = (CheckBox) v;
                        items Item = (items) cb.getTag();
                        Item.setSelected(cb.isChecked());
                        if(finalHolder.name.isChecked()){
                            openDialog();
                        }

                    }
                });
            }
            else
            {
                holder = (Pur.MyCustomAdapter.ViewHolder) convertView.getTag();
            }
            items Item = itemList1.get(position);
            holder.name.setText(Item.getName());
            holder.name.setChecked(Item.isSelected());
            holder.name.setTag(Item);

            return convertView;

        }


    }


    public void checkButtonClick()
    {
        Button myButton = (Button) findViewById(R.id.button19);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> theList1 = new ArrayList<>();
                ArrayList<items> itemList1 = dataAdapter.itemList1;
                for (int i=0;i<itemList1.size();i++){
                    items Item = itemList1.get(i);
                    if(Item.isSelected()){
                        theList1.add(Item.getName());
                    }
                }
                for (int j=0;j<theList1.size();j++) {
                    Cursor c = myDb.view2(theList1.get(j));
                    if (c.getCount() != 0) {
                        while (c.moveToNext()) {
                            ite = c.getString(0);
                            qt = c.getString(1);
                            un = c.getString(2);
                            am = c.getString(4);
                        }
                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
                        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
                        lu = Integer.parseInt(String.valueOf(Month));
                        yo = lu + 1;
                        goh = String.valueOf(yo);

                        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
                        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
                        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
                        if(flag == 1){
                            Cursor lo = myDb.view16(ite+"%",un);
                            if (lo.getCount() == 0) {
                                Cursor lo2 = myDb.view15(ite+"%");
                                if (lo2.getCount() != 0) {
                                    while (lo2.moveToNext()) {
                                        iee = lo2.getString(0);
                                    }

                                    String p1 = "\nVER:";
                                    String p2 = ";";
                                    Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                                    Matcher m = pa.matcher(iee);
                                    while (m.find()) {
                                        q5 = m.group(1);
                                    }
                                    Integer tar = Integer.parseInt(q5) + 1;
                                    String tar1 = String.valueOf(tar);
                                    iee2 = ite + "\nVER:" + tar1 +";";
                                    flag8 = 1;
                                }
                                else {
                                    iee2 = ite + "\nVER:" + String.valueOf(1)+";";
                                    flag8 = 2;

                                }
                            }
                            else {
                                while (lo.moveToNext()){
                                    iee = lo.getString(0);
                                    String p1 = "\nVER:";
                                    String p2 = ";";
                                    Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                                    Matcher m = pa.matcher(iee);
                                    while (m.find()) {
                                        q5 = m.group(1);
                                    }
                                    iee2 = ite + "\nVER:" +q5+";";
                                    flag8 = 3;
                                }
                            }

                            boolean ins = myDb.insertdata14(name.getText().toString(),iee2,qt,un,am,Date,goh,Year,Hour,Minute);
                            Cursor op = myDb.view5(iee2,un,Date,goh,Year);
                            if (op.getCount() == 0) {
                                //Cursor re = myDb.view13(iee2, Date, goh, Year);
                                //if (re.getCount() == 0) {
                                    myDb.insertdata16(iee2, qt, un, String.valueOf(0), am, String.valueOf(0), String.valueOf(0), Date, goh, Year);
                                //} else {
                                  //  Toast.makeText(Pur.this, "Buying Price Not Matching Please see Opening Stalk", Toast.LENGTH_LONG).show();
                                //}
                            }
                            else {
                                while(op.moveToNext()){
                                    itt = op.getString(0);
                                    qtt = op.getString(1);
                                    utt = op.getString(2);
                                    dtt = op.getString(3);
                                    att = op.getString(4);
                                    daa = op.getString(7);
                                    maa = op.getString(8);
                                    yaa = op.getString(9);

                                }
                                Float qdd = Float.parseFloat(qtt) + Float.parseFloat(qt);
                                String qdd1 = String.valueOf(qdd);
                                Float amm = Float.parseFloat(att) + Float.parseFloat(am);
                                String amm1 = String.valueOf(amm);
                                myDb.up7(iee2,qdd1,un,amm1,String.valueOf(0),Date,goh,Year);

                            }
                        }
                        else {
                            myDb.newpur(name.getText().toString());
                            Cursor lo = myDb.view16(ite+"%",un);
                            if (lo.getCount() == 0) {
                                Cursor lo2 = myDb.view15(ite+"%");
                                if (lo2.getCount() != 0) {
                                    while (lo2.moveToNext()) {
                                        iee = lo2.getString(0);
                                    }

                                    String p1 = "\nVER:";
                                    String p2 = ";";
                                    Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                                    Matcher m = pa.matcher(iee);
                                    while (m.find()) {
                                        q5 = m.group(1);
                                    }
                                    Integer tar = Integer.parseInt(q5) + 1;
                                    String tar1 = String.valueOf(tar);
                                    iee2 = ite + "\nVER:" + tar1+";";
                                    flag8 = 1;
                                }
                                else {
                                    iee2 = ite + "\nVER:" + String.valueOf(1) + ";";
                                    flag8 = 2;

                                }
                            }
                            else {
                                while (lo.moveToNext()){
                                    iee = lo.getString(0);
                                    String p1 = "\nVER:";
                                    String p2 = ";";
                                    Pattern pa = Pattern.compile(p1 + "(.*?)" + p2, Pattern.DOTALL);
                                    Matcher m = pa.matcher(iee);
                                    while (m.find()) {
                                        q5 = m.group(1);
                                    }
                                    iee2 = ite + "\nVER:" +q5+";";
                                    flag8 = 3;
                                }
                            }

                            boolean ins1 = myDb.insertdata15(name.getText().toString(),iee2,qt,un,am,Date,goh,Year,Hour,Minute);
                            Cursor op = myDb.view5(iee2,un,Date,goh,Year);
                            if (op.getCount() == 0){
                               // Cursor re = myDb.view13(iee2, Date, goh, Year);
                                //if (re.getCount() == 0) {
                                    myDb.insertdata16(iee2, qt, un, String.valueOf(0), am, String.valueOf(0), String.valueOf(0), Date, goh, Year);
                                //} else {
                                  //  Toast.makeText(Pur.this, "Buying Price Not Matching Please see Opening Stalk", Toast.LENGTH_LONG).show();
                                //}
                            }
                            else {
                                while(op.moveToNext()){
                                    itt = op.getString(0);
                                    qtt = op.getString(1);
                                    utt = op.getString(2);
                                    dtt = op.getString(3);
                                    att = op.getString(4);
                                    daa = op.getString(7);
                                    maa = op.getString(8);
                                    yaa = op.getString(9);

                                }
                                Float qdd = Float.parseFloat(qtt) + Float.parseFloat(qt);
                                String qdd1 = String.valueOf(qdd);
                                Float amm = Float.parseFloat(att) + Float.parseFloat(am);
                                String amm1 = String.valueOf(amm);
                                myDb.up7(iee2,qdd1,un,amm1,String.valueOf(0),Date,goh,Year);

                            }
                        }
                        myDb.dele5(ite,qt,un,am);
                    }
                }
                myDb.dele1();

            }
        });
    }



    public void openDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("ted",name.getText().toString());
        bundle.putString("ted1",cb.getText().toString());
        bundle.putString("ted2", String.valueOf(flag));
        Dialogex1 dio =new Dialogex1();
        dio.setArguments(bundle);
        dio.show(getSupportFragmentManager(),"example dialog");

    }

    void view(){
        purview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Pur.this,pview.class);
                        startActivity(intent);

                    }
                }
        );

    }

    void feed(){
        feedpu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Pur.this,feed2.class);
                        startActivity(intent);

                    }
                }
        );

    }
    }

