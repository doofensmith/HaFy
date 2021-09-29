package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

public class Akun extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbarCollapsed;
    MaterialToolbar toolbarExpanded;

    //firebase+
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        //Auth
        auth = FirebaseAuth.getInstance();

        //init view
        toolbarCollapsed = findViewById(R.id.aa_toolbar_collapsed);
        toolbarExpanded = findViewById(R.id.aa_toolbar_expanded);

        //nav toolbar collapsed
        toolbarCollapsed.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //toolbar menu
        toolbarCollapsed.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_toolbar_akun_pengaturan:
                        Toast.makeText(Akun.this, "Pengaturan", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_toolbar_akun_keluar:
                        logOut(Akun.this);
                        return true;
                }

                return true;
            }
        });

        //navigation toolbar expanded
        toolbarExpanded.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //toolbar expanded option menu
        toolbarExpanded.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_toolbar_akun_pengaturan:
                        Toast.makeText(Akun.this, "Pengaturan", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_toolbar_akun_keluar:
                        logOut(Akun.this);
                        return true;
                }

                return true;
            }
        });
    }

    void logOut(Context context) {

        //auth sign out
        AuthUI.getInstance().signOut(context);

        //intent
        Intent intent = new Intent(context, Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Akun.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}