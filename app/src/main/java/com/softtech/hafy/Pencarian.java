package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class Pencarian extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    SearchView cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        //init view
        toolbar = findViewById(R.id.ap_toolbar);
        cari = findViewById(R.id.ap_searchview);

        //nav toolbar
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //auto focus cari
        cari.requestFocus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Pencarian.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}