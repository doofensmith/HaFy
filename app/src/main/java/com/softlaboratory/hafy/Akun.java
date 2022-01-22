package com.softlaboratory.hafy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.model.MAccount;

public class Akun extends AppCompatActivity {

    //declare view
    ImageView profilePhoto;
    TextView buttonEditProfile;
    TextView textName;
    TextView textEmail;
    TextView textPhoneNumber;
    TextView textBirthDate;
    TextView textAddress;
    TextView textJob;
    TextView textEducation;
    MaterialToolbar toolbar;

    //firebase+
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    DocumentReference documentReference;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        //Auth n DB
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.aa_toolbar);
        textName = findViewById(R.id.aa_text_name);
        textEmail = findViewById(R.id.aa_text_email);
        textPhoneNumber = findViewById(R.id.aa_text_phonenumber);
        textBirthDate = findViewById(R.id.aa_text_birthdate);
        textAddress = findViewById(R.id.aa_text_address);
        textJob = findViewById(R.id.aa_text_job);
        textEducation = findViewById(R.id.aa_text_education);

        //toolbar nav btn
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //toolbar action button
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener());

        //firebase catch data
        firestore.collection("account").document(auth.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MAccount mAccount = documentSnapshot.toObject(MAccount.class);
                textName.setText(mAccount.getUserName());
                textEmail.setText(mAccount.getEmail());
                textPhoneNumber.setText(mAccount.getPhoneNumber());
                textBirthDate.setText(mAccount.getBirthDate());
                textAddress.setText(mAccount.getAddress());
                textJob.setText("");
                textEducation.setText("");

                //akun pro
//                textJob.setText(mAccount.getJob());
//                textEducation.setText(mAccount.getEducation());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    //toolbar on menu click
    Toolbar.OnMenuItemClickListener onMenuItemClickListener() {
       return new Toolbar.OnMenuItemClickListener() {
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
               return false;
           }
       };
    }

    //fungsi logout
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