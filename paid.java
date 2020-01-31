package com.example.sai.aibill;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class paid extends AppCompatActivity implements Dialogex2.MyDialogFragmentListener {
    AutoCompleteTextView sellname;
    Button print;
    MyCustomAdapter dataAdapter = null;
    CheckBox cb;
    ArrayList<items> itemlist;
    ListView listView;
    DatabaseHelper myDb;
    List<String> naam = new ArrayList<>();
    AutoCompleteTextView name;
    Integer flag = 0,flag1 = 1,flag3 = 1;
    String Hour,Minute,momo,Date,Month,Year,goh,ite,qt,un,di,am;
    int lu,yo;
    String x,y,z,b;
    String god = "";
    ArrayList<String> arr3 = new ArrayList<String>();
    float nv = 0;
    String itt,qtt,utt,dtt,att,daa,maa,yaa;
    String itt1,qtt1,utt1,dtt1,att1,daa1,maa1,yaa1;
    String itt2,qtt2,utt2,dtt2,att2,daa2,maa2,yaa2,rate,vas;
    Float didi,didi1;
    int flag5,flag2 = 1;
    String poo,poo1,poo2;
    Cursor op,gh,gh1;
    String zor = "";
    String qp,qp1,uc;
    ArrayList<String> arr1 = new ArrayList<String>();
    float qz,dz,az,az1;
    ArrayList<items> itemlist69;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    String war = "",q;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);
        listView = (ListView) findViewById(R.id.paidlist);
        myDb = new DatabaseHelper(paid.this, "cal.db", null, 1);
        name = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        lu = Integer.parseInt(String.valueOf(Month));
        yo = lu + 1;
        goh = String.valueOf(yo);

        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));

        Cursor shiv = myDb.tab();
        while (shiv.moveToNext()) {
            if(!shiv.getString(0).equals("OPEN_STALK") && !shiv.getString(0).equals("DETAILS"))
                naam.add(shiv.getString(0));
        }
        itemlist = new ArrayList<items>();
        itemlist.addAll(lili());
        dataAdapter = new paid.MyCustomAdapter(this, R.layout.os2, itemlist);
        listView.setAdapter(dataAdapter);

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

    @Override
    public void onReturnValue(String foo) {
        zor = foo;
    }
    public ArrayList<items> lili() {
        itemlist69 = new ArrayList<items>();
        Cursor hop = myDb.view17(Date,goh,Year);
        if (hop.getCount() == 0) {
            Toast.makeText(paid.this, "No Data", Toast.LENGTH_LONG).show();
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

        @SuppressLint("ClickableViewAccessibility")
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            paid.MyCustomAdapter.ViewHolder holder = null;
            Log.v("ConvertView",String.valueOf(position));
            if (convertView == null)
            {
                LayoutInflater vi =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.os2,null);
                holder = new paid.MyCustomAdapter.ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                final paid.MyCustomAdapter.ViewHolder finalHolder = holder;
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb = (CheckBox) v;
                        items Item = (items) cb.getTag();
                        Item.setSelected(cb.isChecked());
                        if(finalHolder.name.isChecked()) {
                                Bundle bundle = new Bundle();
                                bundle.putString("poo", cb.getText().toString());
                                bundle.putString("poo1", name.getText().toString());
                                bundle.putString("poo2", String.valueOf(flag));
                                Dialogex2 dio = new Dialogex2();
                                dio.setArguments(bundle);
                                dio.show(getSupportFragmentManager(), "example dialog");

                        }

                    }

                });
               /* holder.name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        cb = (CheckBox) v;
                        items Item = (items) cb.getTag();
                        Item.setSelected(cb.isChecked());
                        if (event.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                            Bundle bundle = new Bundle();
                            bundle.putString("poo", cb.getText().toString());
                            bundle.putString("poo1", name.getText().toString());
                            bundle.putString("poo2", String.valueOf(flag));
                            Dialogex2 dio = new Dialogex2();
                            dio.setArguments(bundle);
                            dio.show(getSupportFragmentManager(), "example dialog");
                        }
                        return true;
                    }
                });*/

                /*final ViewHolder finalHolder1 = holder;
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb = (CheckBox) v;
                        items Item = (items) cb.getTag();
                        Item.setSelected(cb.isChecked());
                        if (finalHolder.name.isChecked()) {
                            finalHolder1.name.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    cb = (CheckBox) v;
                                    items Item = (items) cb.getTag();
                                    Item.setSelected(cb.isChecked());
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("poo", cb.getText().toString());
                                        bundle.putString("poo1", name.getText().toString());
                                        bundle.putString("poo2", String.valueOf(flag));
                                        Dialogex2 dio = new Dialogex2();
                                        dio.setArguments(bundle);
                                        dio.show(getSupportFragmentManager(), "example dialog");
                                    }
                                    return true;
                                }
                            });
                            if (zor.equals("0")) {
                                finalHolder.name.setChecked(false);
                            } else {
                                finalHolder.name.setChecked(true);
                            }

                        }
                    }


                });*/
            }
            else
            {
                holder = (paid.MyCustomAdapter.ViewHolder) convertView.getTag();
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
        final Button myButton = (Button) findViewById(R.id.button12);
        myButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ArrayList<String> theList1 = new ArrayList<>();
                                            ArrayList<items> itemList1 = dataAdapter.itemList1;
                                            for (int i = 0; i < itemList1.size(); i++) {
                                                items Item = itemList1.get(i);
                                                if (Item.isSelected()) {
                                                    theList1.add(Item.getName());
                                                }
                                            }

                                           /* for (int j = 0; j < theList1.size(); j++) {
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
                                                            qz = qz + Float.parseFloat(qt);
                                                            dz = dz + Float.parseFloat(di);
                                                            az = az + Float.parseFloat(am);
                                                            az1 = az1 + Float.parseFloat(am);
                                                            if (!uc.equals(un)){
                                                                flag2 = 0;
                                                                Toast.makeText(paid.this,"Particular Items Selling Price Not Matching Please re enter values",Toast.LENGTH_LONG).show();
                                                        break;
                                                        }

                                                        }
                                                        if (flag2 == 1) {
                                                            god = god + "\n" + "ITEM :" + qp1 + "\n\n" + "QTY :" + String.valueOf(qz) + "\n\n" + "UNIT :" + un + "\n\n" + "DISCOUNT :" + String.valueOf(dz) + "\n\n";
                                                        }

                                                    }
                                                }
                                                if (flag2 == 0){
                                                    god = "";
                                                    break;
                                                }
                                            }

                                            for (int j = 0; j < theList1.size(); j++) {
                                                Cursor c = myDb.view2(theList1.get(j));
                                                if (c.getCount() != 0) {
                                                    while (c.moveToNext()) {
                                                        ite = c.getString(0);
                                                        qt = c.getString(1);
                                                        un = c.getString(2);
                                                        di = c.getString(3);
                                                        am = c.getString(4);
                                                    }

                                                    gh = myDb.view11(ite, Date, goh, Year);
                                                    if (gh.getCount() == 0) {
                                                        Toast.makeText(paid.this, "Item Not Available", Toast.LENGTH_LONG).show();
                                                        flag3 = 0;
                                                    } else {
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
                                                        if (qdd < 0) {
                                                            Toast.makeText(paid.this, "Quantity exceeded Please check Opening Stalk for Quantity", Toast.LENGTH_LONG).show();
                                                            flag1 = 0;
                                                        } else {
                                                            if (flag2 == 1) {
                                                                Float amm2 = Float.parseFloat(qt) * Float.parseFloat(un);
                                                                Float tb = Float.parseFloat(qt) * Float.parseFloat(utt);
                                                                didi = Float.parseFloat(dtt) + Float.parseFloat(di);
                                                                Float bc = amm2 - Float.parseFloat(di);
                                                                Float amm3 = Float.parseFloat(att) - bc;
                                                                Float pro = bc - tb;
                                                                Float pro2 = Float.parseFloat(poo) + pro;
                                                                myDb.up11(ite, qdd1, String.valueOf(didi), String.valueOf(amm3), String.valueOf(pro2), daa, maa, yaa);
                                                            }
                                                        }

                                                    }
                                                }


                                            }
                                            myDb.dele(ite, qt, un, di, am);
                                            myDb.dele1();
                    if (flag == 1 && flag1 == 1 && flag3 == 1 && flag2 == 1) {
                        myDb.newsell2(name.getText().toString());
                        boolean ins = myDb.insertdata5(name.getText().toString(), god, String.valueOf(az1), Date, goh, Year, Hour, Minute, String.valueOf(1));
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
                    } else {
                        if (flag == 0 && flag1 == 1 && flag3 == 1 && flag2 == 1) {
                            myDb.newsell(name.getText().toString());
                            boolean ins1 = myDb.insertdata6(name.getText().toString(), god, String.valueOf(az1), Date, goh, Year, Hour, Minute, String.valueOf(1));
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
                        }
                    }*/
                                           testing();
                }

        });
    }


    public void openDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("poo",cb.getText().toString());
        bundle.putString("poo1",name.getText().toString());
        bundle.putString("poo2",String.valueOf(flag));
        Dialogex2 dio =new Dialogex2();
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
            outputStream.write(god.getBytes());
            outputStream.write(linsp.getBytes());
            outputStream.write(linsp.getBytes());

        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    void testing(){
        Float gf7 = Float.valueOf(0);
        Cursor fd = myDb.view24();
        while (fd.moveToNext()) {
            Integer flago = 1;
            Float gf6 = Float.valueOf(0);
            String iv = fd.getString(0);
            String qv = fd.getString(1);
            String qv3 = fd.getString(1);
            String uv = fd.getString(2);
            String dv = fd.getString(3);
            String av = fd.getString(4);
            Cursor fn = myDb.view23(iv + "%",Date,goh,Year);
            while (flago == 1) {
                if (fn.moveToNext()) {
                    String qvv = fn.getString(1);
                    Float mn = Float.parseFloat(qv) - Float.parseFloat(qvv);
                    Float mn1 = Float.parseFloat(qvv) - Float.parseFloat(qv);
                    String ipp = fn.getString(0);
                    String upp = fn.getString(2);
                    String dipp = fn.getString(3);
                    String app = fn.getString(4);
                    String por = fn.getString(6);
                    if (mn > 0) {
                        Float gf1 = Float.parseFloat(qvv) * Float.parseFloat(upp);
                        Float gf2 = Float.parseFloat(qvv) * Float.parseFloat(uv);
                        Float dv1 = Float.parseFloat(qvv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        Float pro = gf3 - gf1;
                        Float dv3 = Float.parseFloat(dipp) + dv1;
                        String pro1 = String.valueOf(pro);
                        Float por1 = Float.parseFloat(por) + Float.parseFloat(pro1);
                        Float pt = Float.parseFloat(app) - gf3;
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                        myDb.up11(ipp,String.valueOf(0),String.valueOf(dv3),String.valueOf(pt),String.valueOf(por1),Date,goh,Year);
                        //Update unpaid and os table here and continue the loop
                        qv = String.valueOf(mn);
                        flago = 1;
                    } else if(mn < 0){
                        Float mn2 = mn1 * Float.parseFloat(upp);
                        Float gf1 = Float.parseFloat(qv) * Float.parseFloat(upp);
                        Float gf2 = Float.parseFloat(qv) * Float.parseFloat(uv);
                        Float dv1 = Float.parseFloat(qv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        Float pro = gf3 - gf1;
                        Float dv3 = Float.parseFloat(dipp) + dv1;
                        String pro1 = String.valueOf(pro);
                        Float por1 = Float.parseFloat(por) + Float.parseFloat(pro1);
                        Float pt = Float.parseFloat(app) - gf3;
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                        myDb.up11(ipp,String.valueOf(mn1),String.valueOf(dv3),String.valueOf(pt),String.valueOf(por1),Date,goh,Year);
                        flago = 0;
                    }
                    else if(mn.equals((float) 0)) {
                        Float mn2 = mn1 * Float.parseFloat(upp);
                        Float gf1 = Float.parseFloat(qvv) * Float.parseFloat(upp);
                        Float gf2 = Float.parseFloat(qvv) * Float.parseFloat(uv);
                        Float dv1 = Float.parseFloat(qvv) * Float.parseFloat(dv);
                        Float gf3 = gf2 - dv1;
                        gf6 = gf6 + gf3;
                        gf7 = gf7 + gf3;
                        Float pro = gf3 - gf1;
                        Float dv3 = Float.parseFloat(dipp) + dv1;
                        String pro1 = String.valueOf(pro);
                        Float por1 = Float.parseFloat(por) + Float.parseFloat(pro1);
                        Float pt = Float.parseFloat(app) - gf3;
                        Float gf5 = gf1 - gf3;
                        String gf4 = String.valueOf(gf5);
                        myDb.up11(ipp,String.valueOf(0),String.valueOf(dv3),String.valueOf(pt),String.valueOf(por1),Date,goh,Year);
                        dataAdapter.addnew();
                        //Update unpaid and os table here and continue the loop
                        flago = 0;
                    }
                }
            }
            Float dv4 = Float.parseFloat(qv3) * Float.parseFloat(dv);
            war = war + "\n" + "ITEM :" + iv + "\n\n" + "QTY :" + qv3 + "\n\n" + "UNIT :" + uv + "\n\n" + "DISCOUNT :" + String.valueOf(dv4) + "\n\n";

        }
        myDb.newsell2(name.getText().toString());
        boolean ins = myDb.insertdata5(name.getText().toString(), war, String.valueOf(gf7), Date, goh, Year, Hour, Minute, String.valueOf(1));
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

    }


}
