package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.softtech.hafy.model.MAccount;
import com.softtech.hafy.model.MArticle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BuatArtikel extends AppCompatActivity {

    //declare view
    MaterialToolbar toolbar;
    MaterialButton buttonPost;
    TextInputEditText editTextTitle;
    TextInputEditText editTextContent;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_artikel);

        //AUTH
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //init view
        toolbar = findViewById(R.id.aba_toolbar);
        buttonPost = findViewById(R.id.aba_btn_post);
        editTextTitle = findViewById(R.id.aba_ed_title);
        editTextContent = findViewById(R.id.aba_ed_content);

        //toolbar navigation
        toolbar.setNavigationOnClickListener(toolbarNavigationClick());

        //get account info
        firestore.collection("account").document(auth.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        MAccount mAccount = documentSnapshot.toObject(MAccount.class);

                        //button post
                        buttonPost.setOnClickListener(buttonPost(BuatArtikel.this ,mAccount,editTextTitle, editTextContent));
                    }
                });

    }

    //button post function
    View.OnClickListener buttonPost(Context context,MAccount mAccount, TextInputEditText editTextTitle, TextInputEditText editTextContent) {
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


                //check edit text
                if (articleTitle.isEmpty()||articleContent.isEmpty()) {
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

                    //model
                    MArticle mArticle = new MArticle();
                    mArticle.setKeyArticle(keyArticle);
                    mArticle.setArticleTitle(articleTitle);
                    mArticle.setArticleContent(articleContent);
                    mArticle.setDatePublished(timeStamp);
                    mArticle.setArticleWriter(articleWriter);
                    mArticle.setFeatured(false);
                    mArticle.setArticleTag("Terbaru");

                    //save data
                    firestore.collection("articles").document(keyArticle).set(mArticle)
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