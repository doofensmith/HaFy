package com.softlaboratory.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.softlaboratory.hafy.fragments.Artikel;
import com.softlaboratory.hafy.fragments.Beranda;
import com.softlaboratory.hafy.fragments.Kegiatan;

public class MainActivity extends AppCompatActivity {

    //declare component
    BottomNavigationView botnavview;
    //MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate 1st fragment
        changeFragments(new Beranda());

        //init view
        botnavview = findViewById(R.id.botnavview);
        //toolbar = findViewById(R.id.am_toolbar);
        //aturToolbar(toolbar,"Beranda",true,true,true, false);

        //bot nav view on item select
        botnavview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnavmenu_beranda:
                        //aturToolbar(toolbar,"Beranda",true,true,true, false);
                        changeFragments(new Beranda());
                        break;
                    case R.id.botnavmenu_artikel:
                        //aturToolbar(toolbar,"Artikel",false,false,false, true);
                        changeFragments(new Artikel());
                        break;
                    case R.id.botnavmenu_kegiatan:
                        //aturToolbar(toolbar,"Kegiatan",false,false,false,false);
                        changeFragments(new Kegiatan());
                        break;
                    case R.id.botnavmenu_akun:
                        Intent intent = new Intent(MainActivity.this, Akun.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });

//        //toolbar menu
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.toolbarmenu_search:
//                        Intent intent_cari = new Intent(MainActivity.this, Pencarian.class);
//                        startActivity(intent_cari);
//                        finish();
//                        return true;
//                    case R.id.toolbarmenu_pesan:
//                        Intent intent_chat = new Intent(MainActivity.this, Chat.class);
//                        startActivity(intent_chat);
//                        finish();
//                        return true;
//                    case R.id.toolbarmenu_buat:
//                        Intent intent_buat = new Intent(MainActivity.this, BuatArtikel.class);
//                        startActivity(intent_buat);
//                        finish();
//                        return true;
//                }
//
//                return true;
//            }
//        });

    }


    public boolean changeFragments (Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.am_framelayout, fragment)
                    .commit();
            return true;
        }
        return false;

    }

    @Override
    protected void onResume() {
        super.onResume();

        changeFragments(new Beranda());

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        changeFragments(new Beranda());
    }

    //    void aturToolbar (Toolbar toolbar, String title, boolean menu_cari, boolean menu_pesan, boolean menu_notif, boolean menu_buat) {
//        toolbar.setTitle(title);
//        toolbar.getMenu().getItem(0).setVisible(menu_cari);
//        toolbar.getMenu().getItem(1).setVisible(menu_pesan);
//        toolbar.getMenu().getItem(2).setVisible(menu_notif);
//        toolbar.getMenu().getItem(3).setVisible(menu_buat);
//    }

}