package com.example.sai.aibill;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class npaid extends AppCompatActivity {

    AutoCompleteTextView sellname;
    Button print;
    MyCustomAdapter dataAdapter = null;
    CheckBox cb;
    ArrayList<items> itemlist,itemlist69;
    ListView listView;
    DatabaseHelper myDb;
    List<String> naam = new ArrayList<>();
    AutoCompleteTextView name;
    Integer flag = 0;
    String Hour,Minute,momo,Date,Month,Year,goh,ite,qt,un,di,am;
    int lu,yo;
    String x,y,z,b;
    String god = "";
    float nv = 0;
    String itt,qtt,utt,dtt,att,daa,maa,yaa;
    String itt1,qtt1,utt1,dtt1,att1,daa1,maa1,yaa1;
    String itt2,qtt2,utt2,dtt2,att2,daa2,maa2,yaa2;
    Float didi;
    String poo,poo1,poo2;
    Cursor op,gh,gh1;
    Integer flag1 = 1,flag2 = 1,flag3 = 1,flag4 = 1;
    String qp,qp1,uc;
    ArrayList<String> arr1 = new ArrayList<String>();
    ArrayList<String> arr3 = new ArrayList<String>();
    float qz = 0,dz = 0,az = 0,az1 = 0;
    String q;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaid);
        myDb = new DatabaseHelper(npaid.this, "cal.db", null, 1);
        name = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView4);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        lu = Integer.parseInt(String.valueOf(Month));
        yo = lu + 1;
        goh = String.valueOf(yo);

        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
        itemlist = new ArrayList<items>();
        itemlist.addAll(lili());
        dataAdapter = new npaid.MyCustomAdapter(this, R.layout.os2, itemlist);
        listView = (ListView) findViewById(R.id.npaidlist);
        listView.setAdapter(dataAdapter);
        Cursor shiv = myDb.tab();
        while (shiv.moveToNext()) {
            if(!shiv.getString(0).equals("OPEN_STALK") && !shiv.getString(0).equals("DETAILS"))
                naam.add(shiv.getString(0));
        }
        Cursor shi = myDb.search4();
        while (shi.moveToNext()) {
            naam.add(shi.getString(0));
        }
        Set<String> set = new HashSet<>(naam);
        naam.clear();
        naam.addAll(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, naam);
        name.setAdapter(adapter);
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataAdapter.addnew();
                flag = 1;
            }
        });

        checkButtonClick();
    }

    public ArrayList<items> lili() {
        itemlist69 = new ArrayList<items>();
        Cursor hop = myDb.view17(Date,goh,Year);
        if (hop.getCount() == 0) {
            Toast.makeText(npaid.this, "No Data", Toast.LENGTH_LONG).show();
        } else {
            while (hop.moveToNext()) {
                String igi = hop.getString(0);
                String p1 = "\nVER:";
                String p2 = ";";
                Pattern pa = Pattern.compile("(.*?)" + p1, Pattern.DOTALL);
                Matcher m = pa.matcher(igi);
                while (m.find()) {
                    q = m.group(1);
                }
                if(arr3.contains(q)){
                    continue;
                }
                else {
                    arr3.add(q);
                    items Items = new items(q,false);
                    itemlist69.add(Items);
                }
            }
        }
        arr3.clear();
        return itemlist69;
    }


    public class MyCustomAdapter extends ArrayAdapter<items> {
        ArrayList<items> itemList1;

        public MyCustomAdapter(Context context, int textviewResourceid, ArrayList<items> itemList) {

            super(context, textviewResourceid, itemList);
            this.itemList1 = new ArrayList<items>();
            this.itemList1.addAll(itemList);


        }

        public void addnew(){
            itemList1.clear();
            itemList1.addAll(lili());
            dataAdapter.notifyDataSetChanged();
        }

        private class ViewHolder {

            CheckBox name;
        }

        @Override
        public int getCount(){
            return itemList1 != null ? itemList1.size() : 0;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            npaid.MyCustomAdapter.ViewHolder holder = null;
            Log.v("ConvertView",String.valueOf(position));
            if (convertView == null)
            {
                LayoutInflater vi =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.os2,null);
                holder = new npaid.MyCustomAdapter.ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                final npaid.MyCustomAdapter.ViewHolder finalHolder = holder;
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
                holder = (npaid.MyCustomAdapter.ViewHolder) convertView.getTag();
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
        final Button myButton = (Button) findViewById(R.id.button13);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ArrayList<String> theList1 = new ArrayList<>();
                ArrayList<items> itemList1 = dataAdapter.itemList1;
                for (int i=0;i<itemList1.size();i++){
                    items Item = itemList1.get(i);
                    if(Item.isSelected()){
                        theList1.add(Item.getName());
                    }
                }

                for (int j = 0; j < theList1.size(); j++) {
                    String pa = "\nVER:";
                    String pb = ";";
                    Pattern paa = Pattern.compile(pa + "(.*?)" + pb, Pattern.DOTALL);
                    Pattern pab = Pattern.compile("(.*?)" + pa, Pattern.DOTALL);
                    Matcher m2 = paa.matcher(theList1.get(j));
                    Matcher m3 = pab.matcher(theList1.get(j));
                    while (m2.find()) {
                        qp = m2.group(1);
                    }
                    while (m3.find()) {
                        qp1 = m3.group(1);
                    }
                    Cursor mc = myDb.view18(qp1 + "%");
                    if (arr1.contains(qp1)) {
                        continue;
                    } else {
                        arr1.add(qp1);
                        if (mc.getCount() != 0) {
                            qz = 0;
                            dz = 0;
                            az = 0;
                            if(mc.moveToNext()){
                                uc = mc.getString(2);
                            }
                            Cursor mc1 = myDb.view18(qp1 + "%");
                            while (mc1.moveToNext() && flag2 == 1) {
                                ite = mc1.getString(0);
                                qt = mc1.getString(1);
                                un = mc1.getString(2);
                                di = mc1.getString(3);
                                am = mc1.getString(4);
                                if (!uc.equals(un)){
                                    flag2 = 0;
                                    Toast.makeText(npaid.this,"Particular Items Selling Price Not Matching Please re enter values",Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }

                        }
                    }
                    if (flag2 == 0){
                        break;
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
                            gh = myDb.view11(ite,Date,goh,Year);
                            if(gh.getCount() == 0){
                                Toast.makeText(npaid.this,"Item Not Available",Toast.LENGTH_LONG).show();
                                flag3 = 0;
                            }
                            else{
                                while (gh.moveToNext()) {
                                    itt = gh.getString(0);
                                    qtt = gh.getString(1);
                                    utt = gh.getString(2);
                                    dtt = gh.getString(3);
                                    att = gh.getString(4);
                                    poo = gh.getString(6);
                                    daa = gh.getString(7);
                                    maa = gh.getString(8);
                                    yaa = gh.getString(9);


                                }
                                Float qdd = Float.parseFloat(qtt) - Float.parseFloat(qt);
                                String qdd1 = String.valueOf(qdd);
                                if (qdd <= 0){
                                    Toast.makeText(npaid.this,"Quantity exceeded Please check Opening Stalk for Quantity",Toast.LENGTH_LONG).show();
                                    flag4 = 0;
                                }
                                else {
                                    myDb.up9(ite, qdd1, Date, goh, Year);
                                }

                            }
                        }
                            if (flag == 1){
                            if (gh.getCount() != 0 && flag3 == 1 && flag4 == 1 && flag2 == 1){
                                myDb.insertdata11(name.getText().toString(),ite,qt,un,am,Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),utt);

                            }
                            myDb.dele5(ite,qt,un,am);
                        }
                        else {
                                if (gh.getCount() != 0 && flag3 == 1 && flag4 == 1 && flag2 == 1){
                                    myDb.insertdata12(name.getText().toString(),ite,qt,un,am,Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),utt);

                                }
                                myDb.dele5(ite,qt,un,am);}



                }*/
                testing();
               myDb.dele1();


            }
        });
    }


    public void openDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("poo",cb.getText().toString());
        bundle.putString("poo1",name.getText().toString());
        bundle.putString("poo2",String.valueOf(flag));
        Dialogex4 dio =new Dialogex4();
        dio.setArguments(bundle);
        dio.show(getSupportFragmentManager(),"example dialog");

    }

    void testing(){
        Float gf7 = Float.valueOf(0);
        ArrayList<String> theList1 = new ArrayList<>();
        ArrayList<items> itemList1 = dataAdapter.itemList1;
        for (int i=0;i<itemList1.size();i++){
            items Item = itemList1.get(i);
            if(Item.isSelected()){
                theList1.add(Item.getName());
            }
        }
        Cursor fd = myDb.view24();
        while (fd.moveToNext()){
            Integer flago = 1;
            Float gf6 = Float.valueOf(0);
            String iv = fd.getString(0);
            String qv = fd.getString(1);
            String qv3 = fd.getString(1);
            String uv = fd.getString(2);
            String dv = fd.getString(3);
            String av = fd.getString(4);
            Cursor fn = myDb.view23(iv+"%",Date,goh,Year);
            while(flago == 1) {
                if (fn.moveToNext()) {
                    String qvv = fn.getString(1);
                    Float amu = Float.parseFloat(qvv) * Float.parseFloat(uv);
                    Float amu1 = Float.parseFloat(qv) * Float.parseFloat(uv);
                    Float mn = Float.parseFloat(qv) - Float.parseFloat(qvv);
                    Float mn1 = Float.parseFloat(qvv) - Float.parseFloat(qv);
                    String ipp = fn.getString(0);
                    String upp = fn.getString(2);
                    String app = fn.getString(4);
                    String dpp = fn.getString(3);
                    if (mn > 0){
                        myDb.up9(ipp,String.valueOf(0),Date,goh,Year);
                        if (flag == 1){
                            myDb.insertdata11(name.getText().toString(),ipp,qvv,uv,String.valueOf(amu),Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),upp);
                        }
                        else {
                            myDb.insertdata12(name.getText().toString(),ipp,qvv,uv,String.valueOf(amu),Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),upp);
                        }
                        //Update unpaid and os table here and continue the loop
                        qv = String.valueOf(mn);
                        flago = 1;
                    }
                    else if(mn < 0){
                        myDb.up9(ipp,String.valueOf(mn1),Date,goh,Year);
                        if (flag == 1){
                            myDb.insertdata11(name.getText().toString(),ipp,qv,uv,String.valueOf(amu1),Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),upp);
                        }
                        else {
                            myDb.insertdata12(name.getText().toString(),ipp,qv,uv,String.valueOf(amu1),Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),upp);
                        }
                        //Update unpaid and os table get out of the loop
                        flago = 0;
                    }
                    else if(mn.equals((float) 0)){
                        myDb.up9(ipp,String.valueOf(0),Date,goh,Year);
                        if (flag == 1){
                            myDb.insertdata11(name.getText().toString(),ipp,qvv,uv,String.valueOf(amu),Date,goh,Year,Hour,Minute,String.valueOf(0),String.valueOf(1),upp);
                        }
                        else {
                            myDb.insertdata12(name.getText().toString(), ipp, qvv, uv, String.valueOf(amu), Date, goh, Year, Hour, Minute, String.valueOf(0), String.valueOf(1), upp);
                        }
                        dataAdapter.addnew();
                        flago = 0;
                    }
                }
            }
        }

    }

}
