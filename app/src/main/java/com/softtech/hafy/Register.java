package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.softtech.hafy.model.MAccount;

public class Register extends AppCompatActivity {

    //declare view
    MaterialButton buttonCreate;
    TextView textViewMasuk;
    TextInputEditText editTextName;
    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    TextInputEditText editTextConfirmPassword;

    //firebase
    FirebaseAuth auth;
    //DatabaseReference reference;
    FirebaseFirestore db;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //AUTH
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //init view
        buttonCreate = findViewById(R.id.ar_btnbuat);
        textViewMasuk = findViewById(R.id.ar_txmasuk);
        editTextName = findViewById(R.id.ar_ed_nama);
        editTextEmail = findViewById(R.id.ar_ed_email);
        editTextPassword = findViewById(R.id.ar_ed_password);
        editTextConfirmPassword = findViewById(R.id.ar_ed_confpassword);

        //btn buat
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                createAccount(Register.this, buttonCreate, name, email, password, confirmPassword);
            }
        });

        //text masuk
        textViewMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void createAccount(Context context, View snackbarForView, String name, String email, String password, String confirmPassword) {
        //kondisi string
        if (name.isEmpty()||email.isEmpty()||password.isEmpty()|| confirmPassword.isEmpty()) {
            //gagal daftar
            //tampilkan snackbar data kosong
            Snackbar snackbar = Snackbar.make(context, snackbarForView,"! Isi data yang masih kosong.", Snackbar.LENGTH_LONG);
            snackbar.setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();

//            AlertDialog dialog = new AlertDialog.Builder(Register.this).create();
//            dialog.setTitle("Buat Akun Gagal");
//            dialog.setMessage("Ada data yang belum diisi.");
//            dialog.setCancelable(true);
//            dialog.show();
        }else if (!password.equals(confirmPassword)) {
            //gagal daftar
            //tampilkan dialog gagal daftar
            Snackbar.make(snackbarForView,"! Konfirmasi password tidak sama.",Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //dismiss
                        }
                    })
                    .show();

//            AlertDialog dialog = new AlertDialog.Builder(Register.this).create();
//            dialog.setTitle("Buat Akun Gagal");
//            dialog.setMessage("Password dan Konfimasi Password tidak sama.");
//            dialog.setCancelable(true);
//            dialog.show();
        } else {
            //tampilkan progress dialog
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Signing In");
            progressDialog.setMessage("Creating account in progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            //syarat registrasi terpenuhi
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //cek berhasil daftar
                            if (task.isSuccessful()) {
                                //buat profil
                                //model akun
                                MAccount mAccount = new MAccount();
                                mAccount.setKeyAccount(auth.getUid());
                                mAccount.setUserName(name);
                                mAccount.setEmail(email);
                                mAccount.setVerified(false);
                                mAccount.setProfessional(false);
                                mAccount.setAccountType("User");

                                //database
                                db.collection("account").document(auth.getUid()).set(mAccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //off progress dialog
                                            progressDialog.dismiss();
                                            //pindah activity
                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            //off progress dialog
                                            progressDialog.dismiss();
                                            //tampil message rror
                                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else {
                                //gagal daftar
                                //tampilkan dialog gagal daftar
                                AlertDialog dialog = new AlertDialog.Builder(Register.this).create();
                                dialog.setTitle("Buat Akun Gagal");
                                dialog.setMessage(task.getException().toString());
                                dialog.setCancelable(true);
                                dialog.show();
                            }

                        }
                    });
        }

    }
}