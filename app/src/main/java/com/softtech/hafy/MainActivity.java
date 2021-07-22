package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.softtech.hafy.Fragments.Beranda;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate 1st fragment
        changeFragments(new Beranda());

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
}