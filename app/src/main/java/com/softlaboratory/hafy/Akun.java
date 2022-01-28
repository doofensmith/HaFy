package com.softlaboratory.hafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softlaboratory.hafy.model.MAccount;

public class Akun extends AppCompatActivity {

    //declare view
    TextView buttonEditProfile;
    TextView textName;
    TextView textEmail;
    TextView textPhoneNumber;
    TextView textBirthDate;
    TextView textAddress;
    TextView textJob;
    TextView textEducation;
    MaterialToolbar toolbar;
    ImageView profilePic;

    //firebase+
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    DocumentReference documentReference;
    StorageReference storageReference;
    Query query;

    //VAR
    int IMAGE_CHOOSER = 200;
    Uri imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        //Auth n DB
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        //init view
        toolbar = findViewById(R.id.aa_toolbar);
        textName = findViewById(R.id.aa_text_name);
        textEmail = findViewById(R.id.aa_text_email);
        textPhoneNumber = findViewById(R.id.aa_text_phonenumber);
        textBirthDate = findViewById(R.id.aa_text_birthdate);
        textAddress = findViewById(R.id.aa_text_address);
        textJob = findViewById(R.id.aa_text_job);
        textEducation = findViewById(R.id.aa_text_education);
        profilePic = findViewById(R.id.act_akun_profilepic);

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

                Glide.with(Akun.this).load(mAccount.getProfilePic()).into(profilePic);


                //akun pro
//                textJob.setText(mAccount.getJob());
//                textEducation.setText(mAccount.getEducation());
            }
        });

        //PROFILE PIC ON CLICK, CHANGE PHOTO
        profilePic.setOnClickListener(profilePicOnClick());

    }

    //PROFILE PIC ON CLICK INTENT ACTION
    View.OnClickListener profilePicOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Pilih gambar"),IMAGE_CHOOSER);

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMAGE_CHOOSER) {
            imagePath = data.getData();
            Glide.with(Akun.this).load(imagePath).centerCrop().into(profilePic);

            storageReference.child("profile_pic/profilepic_"+auth.getUid()+".jpg").putFile(imagePath)
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return storageReference.child("profile_pic/profilepic_"+auth.getUid()+".jpg")
                                    .getDownloadUrl();
                        }
                    })
                    .continueWithTask(new Continuation<Uri, Task<Void>>() {
                        @Override
                        public Task<Void> then(@NonNull Task<Uri> task) throws Exception {
                            return firestore.collection("account")
                                    .document(auth.getUid())
                                    .update("profilePic",task.getResult().toString());
                        }
                    });

        }

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