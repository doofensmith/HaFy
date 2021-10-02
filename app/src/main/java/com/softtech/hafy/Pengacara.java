package com.softtech.hafy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softtech.hafy.adapter.AProfessionalAccount;
import com.softtech.hafy.model.MAccount;
import com.softtech.hafy.viewholder.VHProfessionalAccount;

public class Pengacara extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirestoreRecyclerOptions<MAccount> options;
    FirestoreRecyclerAdapter<MAccount, VHProfessionalAccount> adapter;
    Query query;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengacara);

        //auth db
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.act_pengacara_toolbar);

        //nav toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //INFLTE DATA
        //query
        query = firestore.collection("account").whereEqualTo("accountType","Pengacara");
        //option
        options = new FirestoreRecyclerOptions.Builder<MAccount>()
                .setLifecycleOwner(Pengacara.this)
                .setQuery(query,MAccount.class).build();
        //adapter
        adapter = new AProfessionalAccount(options,Pengacara.this);
        //recyler view
        recyclerView = findViewById(R.id.act_pengacara_recyclerview);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Pengacara.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}