package com.softlaboratory.hafy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    //firebase
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //DISABLE NIGHT THEME
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        //auth
        auth = FirebaseAuth.getInstance();

        //intent
        Intent intent;

        //kondisi intent
        if (auth.getCurrentUser()==null) {
            intent = new Intent(this, Login.class);
        }else {
            intent = new Intent(this, MainActivity.class);
        }

        //start activity
        startActivity(intent);
        finish();
    }

}
