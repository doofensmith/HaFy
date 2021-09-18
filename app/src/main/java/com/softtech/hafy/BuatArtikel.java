package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class BuatArtikel extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_artikel);

        //init view
        toolbar = findViewById(R.id.aba_toolbar);

        //toolbar navigation
        toolbar.setNavigationOnClickListener(toolbarNavigationClick());
    }

    //toolbar navigation function
    View.OnClickListener toolbarNavigationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sama seperti tombol back
                onBackPressed();
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(BuatArtikel.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}