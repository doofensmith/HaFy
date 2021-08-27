package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatPerson extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    FloatingActionButton fabkirim;
    EditText pesan;
    LinearLayout test_chat_kanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_person);

        //init view
        toolbar = findViewById(R.id.acp_toolbar);
        fabkirim = findViewById(R.id.acp_fabsend);
        pesan = findViewById(R.id.acp_ed_pesan);
        test_chat_kanan = findViewById(R.id.acp_test_chat_kanan);

        //toolbar nav back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fabkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test_chat_kanan.setVisibility(View.VISIBLE);
                pesan.setText("");
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ChatPerson.this, Chat.class);
        startActivity(intent);
        finish();

    }
}