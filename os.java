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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class os extends AppCompatActivity implements java.io.Serializable {

    MyCustomAdapter dataAdapter = null;
    CheckBox cb;
    ArrayList<items> itemlist,list;
    ListView listView;
    DatabaseHelper myDb;
    Button vw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);
        myDb = new DatabaseHelper(os.this, "cal.db", null, 1);
        vw = (Button) findViewById(R.id.button21);
        displaylistView();
        checkButtonClick();
        hoj();
    }

    public void hoj(){
        vw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(os.this,osview.class);
                        startActivity(intent);

                    }
                }
        );

    }

    public void displaylistView() {
        itemlist = new ArrayList<items>();
        Cursor hop = myDb.search();
        if (hop.getCount() == 0) {
            Toast.makeText(os.this, "No Data", Toast.LENGTH_LONG).show();
        } else {
            while (hop.moveToNext()) {
                items Items = new items(hop.getString(0), false);
                itemlist.add(Items);
            }
        }

        dataAdapter = new MyCustomAdapter(this, R.layout.os2, itemlist);
        listView = (ListView) findViewById(R.id.listview);
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


            ViewHolder holder = null;
            Log.v("ConvertView",String.valueOf(position));
            if (convertView == null)
            {
                LayoutInflater vi =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.os2,null);
                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                final ViewHolder finalHolder = holder;
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
                holder = (ViewHolder) convertView.getTag();
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
        Button myButton = (Button) findViewById(R.id.button4);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(os.this,feed.class);
                startActivity(intent);
            }
        });
    }


    public void openDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("edt",cb.getText().toString());
        Dialogex dio =new Dialogex();
        dio.setArguments(bundle);
        dio.show(getSupportFragmentManager(),"example dialog");

    }
}

