package com.example.sai.aibill;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class chstatus extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    AutoCompleteTextView sellname;
    Button dat;
    ListView puur;
    List<String> naam = new ArrayList<>();
    DatabaseHelper myDb;
    int y,m,d;
    String ye,mo,da,god;
    ArrayList<String> theList12 = new ArrayList<>();
    MyCustomAdapter dataAdapter = null;
    CheckBox cb;
    ArrayList<items> itemlist = new ArrayList<items>(),list,itemlist5,itemlist69,itemlist90 = new ArrayList<items>();
    ListView listView;
    List<String> lee;
    String ite,ite1,mai,fs;
    String go;
    String[] qt;
    String daa,moo,yee,hoo,mii,yee1,itt;
    String itt1,qtt1,unn1,dii1,amm1,daa1,moo1,yee2,hoo1,mii1;
    String war = "";
    float vn = 0;
    Integer flag = 0;
    String Hour,Minute,Date,Month,Year,goh;
    Integer lu,yo;
    float pro,pro2 = 0;
    String pro1;
    String qp,qp1;
    String io,qo,uo,dio,dao,aao,mao,yao;
    ArrayList<String> arr1 = new ArrayList<String>();
    ArrayList<String> arr3 = new ArrayList<String>();
    float qz = 0,dz = 0,az = 0,az1 = 0;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    String q;
    items Item;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chstatus);
        myDb = new DatabaseHelper(chstatus.this, "cal.db", null, 1);
        sellname = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView5);
        dat = (Button) findViewById(R.id.button15);
        puur = (ListView) findViewById(R.id.statuslist);
        if (sellname.getText().toString().equals("")){
            theList12.add("NO DATA");
            ListAdapter listAdapter = new ArrayAdapter<>(chstatus.this, android.R.layout.simple_list_item_1, theList12);
        }
        else {
            itemlist.addAll(lili());
        }
        itemlist5 = new ArrayList<items>();
        list = new ArrayList<items>();
           dataAdapter = new chstatus.MyCustomAdapter(this, R.layout.os2, itemlist);
            puur.setAdapter(dataAdapter);
        Cursor shiv = myDb.search4();
        while (shiv.moveToNext()) {
            naam.add(shiv.getString(0));
        }
        Set<String> set = new HashSet<>(naam);
        naam.clear();
        naam.addAll(set);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, naam);
        sellname.setAdapter(adapter1);
        sellname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = 1;
                dataAdapter.addnew();
            }
        });
        calen();
        checkButtonClick();
    }

    public ArrayList<items> lili() {
            itemlist69 = new ArrayList<items>();
            Cursor gb = myDb.vi(sellname.getText().toString());
            if (gb.getCount() == 0) {
                Toast.makeText(chstatus.this, "No Data", Toast.LENGTH_LONG).show();
                theList12.add("NO DATA");
                ListAdapter listAdapter = new ArrayAdapter<>(chstatus.this, android.R.layout.simple_list_item_1, theList12);
                puur.setAdapter(listAdapter);
            } else {
                while (gb.moveToNext()) {
                    Float atm2 = Float.valueOf(0);
                    Integer qtm2 = 0;
                    String poi = gb.getString(1);
                    String p1 = "\nVER:";
                    String p2 = ";";
                    Pattern pa = Pattern.compile("(.*?)" + p1, Pattern.DOTALL);
                    Matcher m = pa.matcher(poi);
                    while (m.find()) {
                        q = m.group(1);
                    }
                    Cursor gb3 = myDb.vi2(sellname.getText().toString(), q + "%");
                    if (arr3.contains(q)) {
                        continue;
                    } else {
                        arr3.add(q);
                        while (gb3.moveToNext()) {
                            String qtm = gb3.getString(2);
                            String atm = gb3.getString(4);
                            qtm2 = qtm2 + Integer.parseInt(qtm);
                            atm2 = atm2 + Float.parseFloat(atm);

                        }
                        Item = new items(q + "\n" + "QTY :" + String.valueOf(qtm2) + "\n\n" + "UNIT :" + gb.getString(3) + "\n\n" + "AMOUNT :" + String.valueOf(atm2) + "\n\n" + "DATE :" + gb.getString(5) + "/" + gb.getString(6) + "/" + gb.getString(7) + "\n\n" + "TIME :" + gb.getString(8) + ";" + gb.getString(9) + ";" + "\n\n\n", false);
                        itemlist69.add(Item);

                    }
                }
            }
            arr3.clear();
            return itemlist69;
    }



    public class MyCustomAdapter extends ArrayAdapter<items> {
        ArrayList<items> itemList1;

        public MyCustomAdapter(chstatus context, int textviewResourceid, ArrayList<items> itemList) {

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
            Log.v("list",String.valueOf(itemList1.size()));
            return itemList1 != null ? itemList1.size() : 0;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            chstatus.MyCustomAdapter.ViewHolder holder = null;
            Log.v("ConvertView",String.valueOf(position));
            if (convertView == null)
            {
                LayoutInflater vi =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.os2,null);
                holder = new chstatus.MyCustomAdapter.ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                final chstatus.MyCustomAdapter.ViewHolder finalHolder = holder;
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb = (CheckBox) v;
                        items Item = (items) cb.getTag();
                        Item.setSelected(cb.isChecked());
                        openDialog();
                    }
                });
            }
            else
            {
                holder = (chstatus.MyCustomAdapter.ViewHolder) convertView.getTag();
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
        Button myButton = (Button) findViewById(R.id.button16);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> theList1 = new ArrayList<>();
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
                Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
                lu = Integer.parseInt(String.valueOf(Month));
                yo = lu + 1;
                goh = String.valueOf(yo);

                Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
                Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
                Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));

                /*StringBuffer sb = new StringBuffer();
                ArrayList<items> itemList1 = dataAdapter.itemList1;
                for (int i = 0; i < itemList1.size(); i++) {
                    items Item = itemList1.get(i);
                    if (Item.isSelected()) {
                        theList1.add(Item.getName());
                    }
                }
                for (int j = 0; j < theList1.size(); j++) {
                    String p1 = "\nQTY :";
                    String p5 = "\n\nDATE :";
                    String p6 = "/";
                    String p7 = "\n\nTIME :";
                    String p8 = ";";
                    Pattern pa1 = Pattern.compile("(.*?)" + p1, Pattern.DOTALL);
                    Pattern pa5 = Pattern.compile(p5 + "(.*?)" + p6, Pattern.DOTALL);
                    Pattern pa6 = Pattern.compile(p6 + "(.*?)" + p6, Pattern.DOTALL);
                    Pattern pa7 = Pattern.compile(p6 + "(.*?)" + p7, Pattern.DOTALL);
                    Pattern pa8 = Pattern.compile(p7 + "(.*?)" + p8);
                    Pattern pa9 = Pattern.compile(p8 + "(.*?)" + p8);
                    Matcher m1 = pa1.matcher(theList1.get(j));
                    Matcher m5 = pa5.matcher(theList1.get(j));
                    Matcher m6 = pa6.matcher(theList1.get(j));
                    Matcher m7 = pa7.matcher(theList1.get(j));
                    Matcher m8 = pa8.matcher(theList1.get(j));
                    Matcher m9 = pa9.matcher(theList1.get(j));
                    while (m1.find()) {
                        itt = m1.group(1);
                    }
                    while (m5.find()) {
                        daa = m5.group(1);
                    }
                    while (m6.find()) {
                        moo = m6.group(1);
                    }
                    while (m7.find()) {
                        yee = m7.group(1);
                    }
                    while (m8.find()) {
                        hoo = m8.group(1);
                    }

                    while (m9.find()) {
                        mii = m9.group(1);
                    }
                    String[] parts = yee.split("/");
                    yee1 = parts[1];
                    Cursor gf = myDb.search5(itt, daa, moo, yee1, hoo, mii); //buffer2
                    if (gf.getCount() != 0) {
                        while (gf.moveToNext()) {
                            itt1 = gf.getString(0);
                            qtt1 = gf.getString(1);
                            unn1 = gf.getString(2);
                            dii1 = gf.getString(3);
                            amm1 = gf.getString(4);
                            daa1 = gf.getString(5);
                            moo1 = gf.getString(6);
                            yee2 = gf.getString(7);
                            hoo1 = gf.getString(8);
                            mii1 = gf.getString(9);
                        }
                       Cursor sad = myDb.view14(sellname.getText().toString(),itt,daa,moo,yee1,hoo,mii); //unpaid table
                        if (sad.getCount() != 0) {
                            while (sad.moveToNext()) {
                                String nam = sad.getString(0);
                                String itt = sad.getString(1);
                                String qtt = sad.getString(2);
                                String utt = sad.getString(3);
                                String da = sad.getString(5);
                                String mo = sad.getString(6);
                                String ye = sad.getString(7);
                                String ho = sad.getString(8);
                                String mi = sad.getString(9);
                                String buy = sad.getString(12);
                                Float gf1 = Float.parseFloat(qtt1) * Float.parseFloat(buy);
                                Float gf2 = Float.parseFloat(qtt1) * Float.parseFloat(unn1);
                                Float gf3 = gf2 - Float.parseFloat(dii1);
                                pro = gf3 - gf1;
                                pro1 = String.valueOf(pro);
                                Float gf5 = gf1 - gf3;
                                String gf4 = String.valueOf(gf5);
                                myDb.insertdata17(itt1+"\nPaid",String.valueOf(0),buy,dii1,gf4,pro1,Date,goh,Year); //os table
                            }
                        }
                        //myDb.dele2(itt, qtt1, unn1, amm1);
                        myDb.up4(sellname.getText().toString(), itt1, daa1, moo1, yee2, hoo1, mii1, qtt1, String.valueOf(0)); //unpaid table


                    }
                }


                for (int j = 0; j < theList1.size(); j++) {
                    String p1 = "\nQTY :";
                    String p5 = "\n\nDATE :";
                    String p6 = "/";
                    String p7 = "\n\nTIME :";
                    String p8 = ";";
                    Pattern pa1 = Pattern.compile("(.*?)" + p1, Pattern.DOTALL);
                    Pattern pa5 = Pattern.compile(p5 + "(.*?)" + p6, Pattern.DOTALL);
                    Pattern pa6 = Pattern.compile(p6 + "(.*?)" + p6, Pattern.DOTALL);
                    Pattern pa7 = Pattern.compile(p6 + "(.*?)" + p7, Pattern.DOTALL);
                    Pattern pa8 = Pattern.compile(p7 + "(.*?)" + p8);
                    Pattern pa9 = Pattern.compile(p8 + "(.*?)" + p8);
                    Matcher m1 = pa1.matcher(theList1.get(j));
                    Matcher m5 = pa5.matcher(theList1.get(j));
                    Matcher m6 = pa6.matcher(theList1.get(j));
                    Matcher m7 = pa7.matcher(theList1.get(j));
                    Matcher m8 = pa8.matcher(theList1.get(j));
                    Matcher m9 = pa9.matcher(theList1.get(j));
                    while (m1.find()) {
                        itt = m1.group(1);
                    }
                    while (m5.find()) {
                        daa = m5.group(1);
                    }
                    while (m6.find()) {
                        moo = m6.group(1);
                    }
                    while (m7.find()) {
                        yee = m7.group(1);
                    }
                    while (m8.find()) {
                        hoo = m8.group(1);
                    }

                    while (m9.find()) {
                        mii = m9.group(1);
                    }
                    String[] parts = yee.split("/");
                    yee1 = parts[1];
                    String pa = "\nVER:";
                    Pattern paa = Pattern.compile(pa + "(.*?)" + p8, Pattern.DOTALL);
                    Pattern pab = Pattern.compile("(.*?)" + pa, Pattern.DOTALL);
                    Matcher m2 = paa.matcher(itt);
                    Matcher m3 = pab.matcher(itt);
                    while (m2.find()) {
                        qp = m2.group(1);
                    }
                    while (m3.find()) {
                        qp1 = m3.group(1);
                    }
                    Cursor nmo = myDb.view19(qp1+"%",daa,moo,yee1,hoo,mii);
                    if (arr1.contains(qp1)) {
                        continue;
                    } else {
                        arr1.add(qp1);
                        if (nmo.getCount() != 0) {
                            qz = 0;
                            dz = 0;
                            az = 0;
                            while (nmo.moveToNext()) {
                                io = nmo.getString(0);
                                qo = nmo.getString(1);
                                uo = nmo.getString(2);
                                dio = nmo.getString(3);
                                aao = nmo.getString(4);
                                qz = qz + Float.parseFloat(qo);
                                dz = dz + Float.parseFloat(dio);
                                az = az + Float.parseFloat(aao);
                                az1 = az1 + Float.parseFloat(aao);
                            }
                                war = war + "\n" + "ITEM :" + qp1 + "\n\n" + "QTY :" + String.valueOf(qz) + "\n\n" + "UNIT :" + uo + "\n\n" + "DISCOUNT :" + String.valueOf(dz) + "\n\n";


                        }
                    }
                }
                    myDb.newsell2(sellname.getText().toString());
                    boolean ins = myDb.insertdata5(sellname.getText().toString(), war, String.valueOf(az1), Date, goh, Year, Hour, Minute, String.valueOf(1));
                    Cursor sad2 = myDb.view4(String.valueOf(0));
                    if (sad2.getCount() != 0) {
                        while (sad2.moveToNext()) {
                            String nam = sad2.getString(0);
                            String itt = sad2.getString(1);
                            String qtt8 = sad2.getString(2);
                            String utt = sad2.getString(3);
                            String chqt2 = sad2.getString(10);
                            String da = sad2.getString(5);
                            String mo = sad2.getString(6);
                            String ye = sad2.getString(7);
                            String ho = sad2.getString(8);
                            String mi = sad2.getString(9);
                            String buy = sad2.getString(12);
                            Float gfl = Float.parseFloat(qtt8) - Float.parseFloat(chqt2);
                            if (gfl == 0) {
                                myDb.dele4(nam, itt, da, mo, ye, ho, mi, chqt2);
                            } else {
                                String lk = String.valueOf(gfl);
                                Float kv = Float.parseFloat(lk) * Float.parseFloat(utt);
                                String dj = String.valueOf(kv);
                                myDb.up5(nam, itt,lk, dj, String.valueOf(0), String.valueOf(1));
                            }
                        }

                    }*/
                testing();
                findBluetoothDevice();
                try {
                    openBluetoothPrinter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    printData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    myDb.dele3();
            }

        });
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
        if (sellname.getText().toString().equals("")) {
            Toast.makeText(chstatus.this, "ENTER THE NAME FIRST", Toast.LENGTH_LONG).show();
            theList40.add("NO NAME ENTERED");
            ListAdapter listAdapter30 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList40);
            puur.setAdapter(listAdapter30);
        } else {
            Cursor c = myDb.getdata(sellname.getText().toString(), da, mo, ye);
            if (c.getCount() == 0) {
                Toast.makeText(chstatus.this, "No Data", Toast.LENGTH_LONG).show();
                theList11.add("NO DATA FOUND");
                ListAdapter listAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList11);
                puur.setAdapter(listAdapter3);
            } else {
                while (c.moveToNext()) {
                    theList1.add("ITEM  " + c.getString(0) + "\n\n" + "QTY :" + c.getString(1) + "\n\n" + "UNIT :" + c.getString(2) + "\n\n" + "AMOUNT :" + c.getString(3) + "\n\n" + c.getString(4) + "/" + c.getString(5) + "/" + c.getString(6) + "\n\n" + c.getString(7) + ":" + c.getString(8) + "\n\n\n");
                    ListAdapter listAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList1);
                    puur.setAdapter(listAdapter1);
                }
            }

        }
    }

    public void openDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("oh",cb.getText().toString());
        Dialogex3 dio =new Dialogex3();
        dio.setArguments(bundle);
        dio.show(getSupportFragmentManager(),"example dialog");

    }

    @SuppressLint("SetTextI18n")
    void findBluetoothDevice(){

        try{
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(bluetoothAdapter.isEnabled()){
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT,0);
            }
            Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
            if(pairedDevice.size()>0){
                for(BluetoothDevice pairedDev:pairedDevice){
                    if(pairedDev.getName().equals("BlueTooth Printer")){
                        bluetoothDevice=pairedDev;
                        break;
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }



    }

    void openBluetoothPrinter() throws IOException {
        try{
            UUID uuidstring = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            bluetoothSocket=bluetoothDevice.createRfcommSocketToServiceRecord(uuidstring);
            bluetoothSocket.connect();
            outputStream=bluetoothSocket.getOutputStream();
            inputStream=bluetoothSocket.getInputStream();
            beginListenData();
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    void beginListenData(){
        try{
            final Handler handler = new Handler();
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!Thread.currentThread().isInterrupted() && !stopWorker){
                        try{
                            int byteAvailable = inputStream.available();
                            if(byteAvailable>0){
                                byte[] packetByte = new byte[byteAvailable];
                                inputStream.read(packetByte);
                                for (int i=0;i<byteAvailable;i++){
                                    byte b =packetByte[i];
                                    if(b == delimiter){
                                        byte[] encodedByte = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer,0,
                                                encodedByte,0,
                                                encodedByte.length
                                        );
                                        final String data = new String(encodedByte,"US-ASCII");
                                        readBufferPosition=0;
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {

                                            }
                                        });
                                    }else{
                                        readBuffer[readBufferPosition++]=b;
                                    }
                                }
                            }
                        }catch(Exception ex){
                            stopWorker=true;
                        }
                    }
                }
            });
            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    void printData() throws IOException {
        try {
            String linsp = System.getProperty("line.separator");
            String po = "YO BRO";
            byte[] bb2 = new byte[]{0x1B,0x21,0x20};
            outputStream.write(bb2);
            outputStream.write(po.getBytes());
            outputStream.write(linsp.getBytes());
            outputStream.write(linsp.getBytes());
            byte[] cc = new byte[]{0x1B,0x21,0x00};
            outputStream.write(cc);
            outputStream.write(war.getBytes());
            outputStream.write(linsp.getBytes());
            outputStream.write(linsp.getBytes());

        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    void testing(){
        Float gf7 = Float.valueOf(0);
        Cursor fd = myDb.view22();
        while (fd.moveToNext()) {
            Integer flago = 1;
            Float gf6 = Float.valueOf(0);
            String iv = fd.getString(0);
            String qv = fd.getString(1);
            String qv3 = fd.getString(1);
            String uv = fd.getString(2);
            String dv = fd.getString(3);
            String av = fd.getString(4);
            String dav = fd.getString(5);
            String mov = fd.getString(6);
            String yev = fd.getString(7);
            String hov = fd.getString(8);
            String miv = fd.getString(9);
            String del = fd.getString(10);
            Cursor fn = myDb.view21(sellname.getText().toString(), iv + "%", dav, mov, yev, hov, miv);
            while (flago == 1) {
                if (fn.moveToNext()) {
                    String qvv = fn.getString(2);
                    Float mn = Float.parseFloat(qv) - Float.parseFloat(qvv);
                    Float mn1 = Float.parseFloat(qvv) - Float.parseFloat(qv);
                    String npp = fn.getString(0);
                    String ipp = fn.getString(1);
                    String upp = fn.getString(3);
                    String app = fn.getString(4);
                    String dpp = fn.getString(5);
                    String mpp = fn.getString(6);
                    String ypp = fn.getString(7);
                    String hpp = fn.getString(8);
                    String mipp = fn.getString(9);
                    String ba = fn.getString(12);
                    if (mn > 0) {
                        Float gf1 = Float.parseFloat(qvv) * Float.parseFloat(ba);
                        Float gf2 = Float.parseFloat(qvv) * Float.parseFloat(upp);
                        Float dv1 = Float.parseFloat(qvv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        pro = gf3 - gf1;
                        pro1 = String.valueOf(pro);
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                        myDb.dele6(npp, ipp, dpp, mpp, ypp, hpp, mipp);
                        myDb.insertdata17(ipp + "\nPaid", String.valueOf(0), ba, String.valueOf(dv1), gf4, pro1, Date, goh, Year);
                        //Update unpaid and os table here and continue the loop
                        qv = String.valueOf(mn);
                        flago = 1;
                    } else if(mn < 0){
                        Float mn2 = mn1 * Float.parseFloat(upp);
                        Float gf1 = Float.parseFloat(qv) * Float.parseFloat(ba);
                        Float gf2 = Float.parseFloat(qv) * Float.parseFloat(upp);
                        Float dv1 = Float.parseFloat(qv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        pro = gf3 - gf1;
                        pro1 = String.valueOf(pro);
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                       myDb.up13(npp, ipp, String.valueOf(mn1), String.valueOf(mn2));
                        myDb.insertdata17(ipp + "\nPaid", String.valueOf(0), ba, String.valueOf(dv1), gf4, pro1, Date, goh, Year);
                        //Update unpaid and os table get out of the loop
                        flago = 0;
                    }
                    else if(mn.equals((float) 0)) {
                        Float gf1 = Float.parseFloat(qvv) * Float.parseFloat(ba);
                        Float gf2 = Float.parseFloat(qvv) * Float.parseFloat(upp);
                        Float dv1 = Float.parseFloat(qvv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        pro = gf3 - gf1;
                        pro1 = String.valueOf(pro);
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                        myDb.dele6(npp, ipp, dpp, mpp, ypp, hpp, mipp);
                        dataAdapter.addnew();
                        myDb.insertdata17(ipp + "\nPaid", String.valueOf(0), ba, String.valueOf(dv1), gf4, pro1, Date, goh, Year);
                        //Update unpaid and os table here and continue the loop
                        flago = 0;
                    }
                }
            }
            Float dv4 = Float.parseFloat(qv3) * Float.parseFloat(dv);
            war = war + "\n" + "ITEM :" + iv + "\n\n" + "QTY :" + qv3 + "\n\n" + "UNIT :" + uv + "\n\n" + "DISCOUNT :" + String.valueOf(dv4) + "\n\n";

        }
        myDb.newsell2(sellname.getText().toString());
        boolean ins = myDb.insertdata5(sellname.getText().toString(), war, String.valueOf(gf7), Date, goh, Year, Hour, Minute, String.valueOf(1));


    }



    }


