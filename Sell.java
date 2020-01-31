package com.example.sai.aibill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sell extends AppCompatActivity {
    Button pay,npay,ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        pay = (Button) findViewById(R.id.button9);
        npay = (Button) findViewById(R.id.button11);
        ch = (Button) findViewById(R.id.button14);
        pay();
        npay();
        stat();
    }

    public void pay() {
        pay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sell.this,paid.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void npay() {
        npay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sell.this,npaid.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void stat() {
        ch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sell.this,chstatus.class);
                        startActivity(intent);
                    }
                }
        );

    }



}
