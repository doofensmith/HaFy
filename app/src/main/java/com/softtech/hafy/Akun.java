package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class Akun extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        //init view
        toolbar = findViewById(R.id.aa_toolbar);

        //nav toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //toolbar menu
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.toolbarakun_pengaturan:
                        Toast.makeText(Akun.this, "Pengaturan", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.toolbarakun_keluar:
                        Toast.makeText(Akun.this, "Keluar", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Akun.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}