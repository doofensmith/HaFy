package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.softtech.hafy.adapter.AChatRoom;
import com.softtech.hafy.model.MChatPerson;
import com.softtech.hafy.model.MChatRoom;
import com.softtech.hafy.viewholder.VHChatRoom;

public class ChatRoom extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirestoreRecyclerOptions<MChatRoom> options;
    FirestoreRecyclerAdapter<MChatRoom, VHChatRoom> adapter;
    RecyclerView recyclerView;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //auth n db
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.ac_toolbar);

        //nav toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        //INFLATE CHAT ROOM
        //query
        query = firestore.collection("chat_room")
                .whereEqualTo("keyChatRoom",auth.getUid())
                .orderBy("lastChatTime", Query.Direction.DESCENDING);
        //option
        options = new FirestoreRecyclerOptions.Builder<MChatRoom>()
                .setLifecycleOwner(ChatRoom.this)
                .setQuery(query, MChatRoom.class)
                .build();
        //adapter
        adapter = new AChatRoom(options, ChatRoom.this);
        //recyclerview
        recyclerView = findViewById(R.id.act_chat_room_recyclerview);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ChatRoom.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}