package com.softlaboratory.hafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.model.MArticle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BuatArtikel extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    MaterialButton buttonPost;
    TextInputEditText editTextTitle;
    TextInputEditText editTextContent;
    ImageButton addImage;

    //VAR
    int SELECT_IMAGE = 200;
    Uri imageUri;
    Uri downloadUri;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    StorageReference imageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_artikel);

        //AUTH
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        imageRef = FirebaseStorage.getInstance().getReference();

        //init view
        toolbar = findViewById(R.id.aba_toolbar);
        buttonPost = findViewById(R.id.aba_btn_post);
        editTextTitle = findViewById(R.id.aba_ed_title);
        editTextContent = findViewById(R.id.aba_ed_content);
        addImage = findViewById(R.id.act_buat_artikel_addimage);

        //toolbar navigation
        toolbar.setNavigationOnClickListener(toolbarNavigationClick());

        //button add image
        //base image reference
        addImage.setOnClickListener(buttonAddImage(BuatArtikel.this));

        //get account info
        firestore.collection("account").document(auth.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        MAccount mAccount = documentSnapshot.toObject(MAccount.class);

                        //button post
                        buttonPost.setOnClickListener(buttonPost(BuatArtikel.this ,mAccount,editTextTitle, editTextContent, downloadUri));
                    }
                });

    }

    View.OnClickListener buttonAddImage(Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //intent.setType("image/*");
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Pilih gambar"),SELECT_IMAGE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE) {
            imageUri = data.getData();
            Glide.with(BuatArtikel.this).load(imageUri)
                    .centerCrop()
                    .into(addImage);
        }
    }

    //button post function
    View.OnClickListener buttonPost(Context context,MAccount mAccount, TextInputEditText editTextTitle, TextInputEditText editTextContent, Uri downloadUri) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prepare data
                String articleTitle = editTextTitle.getText().toString();
                String articleContent = editTextContent.getText().toString();
                String keyArticle = firestore.collection("articles").document().getId();
                DateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy, HH:mm");
                String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
                String articleWriter = mAccount.getUserName();
                String articleWriterImage = mAccount.getProfilePic();


                //check edit text
                if (imageUri.equals(null)) {
                    //alert information
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setCancelable(true);
                    alertDialog.setTitle("Information");
                    alertDialog.setMessage("Please insert image.");
                    alertDialog.show();
                }else if (articleTitle.isEmpty()||articleContent.isEmpty()) {
                    //alert information
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setCancelable(true);
                    alertDialog.setTitle("Information");
                    alertDialog.setMessage("Cannot create empty article.");
                    alertDialog.show();
                }else {
                    //tampilkan progress dialog
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Posting");
                    progressDialog.setMessage("Posting your article in progress...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();

//                    //save image to ref
//                    imageRef.child("image_"+keyArticle+".jpg").putFile(imageUri)
//                            .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                                @Override
//                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//
//                                    if (!task.isSuccessful()) {
//                                        throw task.getException();
//                                    }
//
//                                    return imageRef.getDownloadUrl();
//                                }
//                            })
//                            .addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//                                    if (task.isSuccessful()) {
//                                        downloadUri = task.getResult();
//                                    }
//                                }
//                            });


                    //model
                    MArticle mArticle = new MArticle();
                    mArticle.setKeyArticle(keyArticle);
                    mArticle.setArticleTitle(articleTitle);
                    mArticle.setArticleContent(articleContent);
                    mArticle.setDatePublished(timeStamp);
                    mArticle.setArticleWriter(articleWriter);
                    mArticle.setFeatured(false);
                    mArticle.setArticleTag("Terbaru");
                    mArticle.setArticleWriterImage(articleWriterImage);



                    //save data
                    firestore.collection("articles").document(keyArticle).set(mArticle)
                            .continueWithTask(new Continuation<Void, Task<UploadTask.TaskSnapshot>>() {
                                @Override
                                public Task<UploadTask.TaskSnapshot> then(@NonNull Task<Void> task) throws Exception {
                                    return imageRef.child("article_images/image_"+keyArticle+".jpg").putFile(imageUri);
                                }
                            })
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    firestore.collection("articles").document(keyArticle).update("articleImageUrl",taskSnapshot.getDownloadUrl());
//                                }
//                            })
                            .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    return imageRef.child("article_images/image_"+keyArticle+".jpg").getDownloadUrl();
                                }
                            })
                            .continueWithTask(new Continuation<Uri, Task<Void>>() {
                                @Override
                                public Task<Void> then(@NonNull Task<Uri> task) throws Exception {
                                    System.out.println("RESULT : "+task.getResult());
                                    return firestore.collection("articles").document(keyArticle).update("articleImageUrl",task.getResult().toString());
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //pindah activity
                                        Intent intent = new Intent(context, MainActivity.class);
                                        //intent.putExtra("fragmentId",1);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                        finish();
                                    }else {
                                        //alert information
                                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                        alertDialog.setCancelable(true);
                                        alertDialog.setTitle("Information");
                                        alertDialog.setMessage(task.getException().getMessage().toString());
                                        alertDialog.show();
                                    }
                                }
                            });
                }
            }
        };
    }

    //toolbar navigation function
    View.OnClickListener toolbarNavigationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sama seperti tombol back
                onBackPressed();
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(BuatArtikel.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}