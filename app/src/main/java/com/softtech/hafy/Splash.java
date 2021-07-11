package com.softtech.hafy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent
        Intent intent;

        //kondisi intent
        intent = new Intent(this, MainActivity.class);

        //start
        startActivity(intent);
        finish();
    }

}
