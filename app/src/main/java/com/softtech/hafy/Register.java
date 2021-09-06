package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softtech.hafy.Model.MAkun;

public class Register extends AppCompatActivity {

    //declare view
    MaterialButton btnbuat;
    TextView txmasuk;
    TextInputEditText ed_nama;
    TextInputEditText ed_email;
    TextInputEditText ed_password;
    TextInputEditText ed_confpassword;

    //firebase
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //AUTH
        auth = FirebaseAuth.getInstance();

        //init view
        btnbuat = findViewById(R.id.ar_btnbuat);
        txmasuk = findViewById(R.id.ar_txmasuk);
        ed_nama = findViewById(R.id.ar_ed_nama);
        ed_email = findViewById(R.id.ar_ed_email);
        ed_password = findViewById(R.id.ar_ed_password);
        ed_confpassword = findViewById(R.id.ar_ed_confpassword);

        //btn buat
        btnbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = ed_nama.getText().toString();
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                String confpassword = ed_confpassword.getText().toString();
                fun_daftar(nama, email, password, confpassword);
            }
        });

        //text masuk
        txmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void fun_daftar(String nama, String email, String password, String conf_password) {
        //kondisi string
        if (nama.isEmpty()||email.isEmpty()||password.isEmpty()||conf_password.isEmpty()) {
            //gagal daftar
            //tampilkan dialog gagal daftar
            AlertDialog dialog = new AlertDialog.Builder(Register.this).create();
            dialog.setTitle("Buat Akun Gagal");
            dialog.setMessage("Ada data yang belum diisi.");
            dialog.setCancelable(true);
            dialog.show();
        }else if (!password.equals(conf_password)) {
            //gagal daftar
            //tampilkan dialog gagal daftar
            AlertDialog dialog = new AlertDialog.Builder(Register.this).create();
            dialog.setTitle("Buat Akun Gagal");
            dialog.setMessage("Password dan Konfimasi Password tidak sama.");
            dialog.setCancelable(true);
            dialog.show();
        } else {
            //syarat registrasi terpenuhi
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //cek berhasil daftar
                            if (task.isSuccessful()) {
                                //buat profil
                                //model akun
                                MAkun mAkun = new MAkun(nama, email, "Pengguna", false);

                                //database
                                reference = FirebaseDatabase.getInstance().getReference().child(auth.getCurrentUser().getUid()).child("Akun");
                                reference.setValue(mAkun);

                                //pindah activity
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                                finish();

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