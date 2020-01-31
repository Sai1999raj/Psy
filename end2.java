package com.example.sai.aibill;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class end2 extends AppCompatActivity {

    EditText dis;
    Button pro;
    TextView tamt;
    String name,det,fla,am,ta1,Date,Month,Year,Minute,Hour,goh;
    DatabaseHelper myDb;
    Float ta;
    Integer lu,yo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end2);
        myDb = new DatabaseHelper(end2.this, "cal.db", null, 1);
        tamt = (TextView) findViewById(R.id.textView3);
        dis = (EditText) findViewById(R.id.editText7);
        pro = (Button) findViewById(R.id.button18);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        name = bundle.getString("gas");
        det = bundle.getString("gas2");
        fla = bundle.getString("gas3");
        am = bundle.getString("gas4");
        tamt.setText("Total Amount " + am);
        fad();
    }

    public void fad(){
        pro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        Date = String.valueOf(gregorianCalendar.get(Calendar.DATE));
                        Month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
                        lu = Integer.parseInt(String.valueOf(Month));
                        yo = lu + 1;
                        goh = String.valueOf(yo);

                        Year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
                        Hour = String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
                        Minute = String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
                        ta = Float.parseFloat(am) - Float.parseFloat(dis.getText().toString());
                        ta1 = String.valueOf(ta);
                        myDb.newsell2(name);
                            Cursor sad = myDb.view4(String.valueOf(0));
                            if (sad.getCount() != 0){
                                while (sad.moveToNext()) {
                                    String nam = sad.getString(0);
                                    String itt = sad.getString(1);
                                    String qtt = sad.getString(2);
                                    String utt = sad.getString(3);
                                    String chqt = sad.getString(10);
                                    String da = sad.getString(5);
                                    String mo = sad.getString(6);
                                    String ye = sad.getString(7);
                                    String ho = sad.getString(8);
                                    String mi = sad.getString(9);
                                    Float gf = Float.parseFloat(qtt) - Float.parseFloat(chqt);
                                    if (gf == 0) {
                                        myDb.dele4(nam, itt, da, mo, ye, ho, mi, chqt);
                                    } else {
                                        String lk = String.valueOf(gf);
                                        Float kv = Float.parseFloat(lk) * Float.parseFloat(utt);
                                        String dj = String.valueOf(kv);
                                    }
                                }
                            }

                    }
                }
        );

    }
}
