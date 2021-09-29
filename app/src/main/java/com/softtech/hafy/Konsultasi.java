package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class Konsultasi extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    MaterialButton btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        //init view
        toolbar = findViewById(R.id.ak_toolbar);
        //btn_test = findViewById(R.id.item_beranda_3_btnchat);

        //nav toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        btn_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Konsultasi.this, Pembayaran.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Konsultasi.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}