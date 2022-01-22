package com.softlaboratory.hafy;

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
import com.softlaboratory.hafy.adapter.AProfessionalAccount;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.viewholder.VHProfessionalAccount;

public class Konsultasi extends AppCompatActivity {

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
        setContentView(R.layout.activity_konsultasi);

        //auth db
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.ak_toolbar);

        //nav toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //INFLTE DATA
        //query
        query = firestore.collection("account").whereEqualTo("accountType","Konsultan");
        //option
        options = new FirestoreRecyclerOptions.Builder<MAccount>()
                .setLifecycleOwner(Konsultasi.this)
                .setQuery(query,MAccount.class).build();
        //adapter
        adapter = new AProfessionalAccount(options,Konsultasi.this);
        //recyler view
        recyclerView = findViewById(R.id.ak_recyclerview);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Konsultasi.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}