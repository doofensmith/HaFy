package com.softlaboratory.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.adapter.AChatPerson;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.model.MChatPerson;
import com.softlaboratory.hafy.model.MChatRoom;
import com.softlaboratory.hafy.viewholder.VHChatPerson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class ChatPerson extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    FloatingActionButton fabSend;
    TextInputEditText editTextMessage;
    ImageView profilePic;
    TextView userName;
    TextView lastOnline;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirestoreRecyclerOptions<MChatPerson> options;
    FirestoreRecyclerAdapter<MChatPerson, VHChatPerson> adapter;
    Query query;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_person);

        //auth
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.act_chat_person_toolbar);
        fabSend = findViewById(R.id.act_chat_person_fab);
        editTextMessage = findViewById(R.id.act_chat_person_inpmessage);
        profilePic = findViewById(R.id.act_chat_person_pic);
        userName = findViewById(R.id.act_chat_person_name);
        lastOnline = findViewById(R.id.act_chat_person_lastonline);

        //toolbar nav back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        //data bundle
        String targetKeyAccount = getIntent().getStringExtra("keyAccount");

        firestore.collection("account").document(targetKeyAccount).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        MAccount mAccount = documentSnapshot.toObject(MAccount.class);
                        userName.setText(mAccount.getUserName());
                        lastOnline.setText(mAccount.getLastOnline());
                        Glide.with(ChatPerson.this).load(mAccount.getProfilePic()).into(profilePic);
                    }
                });



        //INFLATE CHAT
        //query
        query = firestore.collection("chat_messages")
                .whereIn("keyChatRoom",Arrays.asList(auth.getUid()+targetKeyAccount,targetKeyAccount+auth.getUid()))
                .orderBy("timestamp", Query.Direction.ASCENDING);

        //option
        options = new FirestoreRecyclerOptions.Builder<MChatPerson>()
                .setLifecycleOwner(ChatPerson.this)
                .setQuery(query, MChatPerson.class).build();
        //adapter
        adapter = new AChatPerson(options,auth,ChatPerson.this);
        //recyclerview
        recyclerView = findViewById(R.id.act_chat_person_recyclerview);
        recyclerView.setAdapter(adapter);

        //tombol kirim
        fabSend.setOnClickListener(fabSend(editTextMessage, targetKeyAccount, firestore, auth,recyclerView,adapter));




    }

    //fungsi kirim
    View.OnClickListener fabSend(EditText editTextMessage,
                                 String targetKeyAccount,
                                 FirebaseFirestore firestore,
                                 FirebaseAuth auth,
                                 RecyclerView recyclerView,
                                 FirestoreRecyclerAdapter adapter) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chat room

                //keychat
                String keyChat = firestore.collection("chat_messages").document().getId();
                //key chatroom
                String keyChatRoom = auth.getUid()+targetKeyAccount;
                //message
                String message = editTextMessage.getText().toString();
                //timestamp
                //FieldValue.serverTimestamp();//
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                String date = dateFormat.format(Calendar.getInstance().getTime());

                //model object to save
                MChatPerson mChatPerson = new MChatPerson();
                mChatPerson.setMessage(message);
                mChatPerson.setSender(auth.getUid());
                mChatPerson.setReceiver(targetKeyAccount);
                mChatPerson.setKeyChat(keyChat);
                mChatPerson.setKeyChatRoom(keyChatRoom);

                if (!message.isEmpty()&&!message.equals("")) {
                    //add to database
                    firestore.collection("chat_messages").document(keyChat).set(mChatPerson)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //clear edit text
                                        editTextMessage.setText("");
                                        //scroll recyclerview
                                        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);

                                        //update chat room
                                        firestore.collection("account").document(targetKeyAccount).get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        MAccount mAccount = documentSnapshot.toObject(MAccount.class);
                                                        MChatRoom mChatRoom = new MChatRoom();
                                                        mChatRoom.setTargetPic(mAccount.getProfilePic());
                                                        mChatRoom.setTargetName(mAccount.getUserName());
                                                        mChatRoom.setTargetKey(targetKeyAccount);
                                                        mChatRoom.setKeyChatRoom(auth.getUid());
                                                        mChatRoom.setLastChat(message);
                                                        firestore.collection("chat_room").document(auth.getUid())
                                                                .set(mChatRoom);
                                                    }
                                                });

                                        firestore.collection("account").document(auth.getUid()).get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        MAccount mAccount = documentSnapshot.toObject(MAccount.class);
                                                        MChatRoom mChatRoom = new MChatRoom();
                                                        mChatRoom.setTargetPic(mAccount.getProfilePic());
                                                        mChatRoom.setTargetName(mAccount.getUserName());
                                                        mChatRoom.setTargetKey(auth.getUid());
                                                        mChatRoom.setKeyChatRoom(targetKeyAccount);
                                                        mChatRoom.setLastChat(message);
                                                        firestore.collection("chat_room").document(targetKeyAccount)
                                                                .set(mChatRoom);
                                                    }
                                                });

//                                        //update chat room
//                                        HashMap<String,Object> data = new HashMap<>();
//                                        data.put("lastChat",message);
//                                        data.put("lastChatTime", FieldValue.serverTimestamp());
//                                        //add data to chat room
//                                        firestore.collection("chat_room")
//                                                .document(auth.getUid())
//                                                .update(data);
//                                        firestore.collection("chat_room")
//                                                .document(targetKeyAccount)
//                                                .update(data);


                                    }
                                }
                            });
                }

            }
        };
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ChatPerson.this, ChatRoom.class);
        startActivity(intent);
        finish();



    }
}