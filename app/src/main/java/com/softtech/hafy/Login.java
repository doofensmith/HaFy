package com.softtech.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //declare view
    MaterialButton btnlogin;
    TextView txbuat;
    TextInputEditText ed_email;
    TextInputEditText ed_password;

    //firebase
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //auth
        auth = FirebaseAuth.getInstance();

        //init view
        btnlogin = findViewById(R.id.al_btnmasuk);
        txbuat = findViewById(R.id.al_txbuat);
        ed_email = findViewById(R.id.al_ed_email);
        ed_password = findViewById(R.id.al_ed_password);

        //btn login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                fun_login(email, password);
            }
        });

        //text buat
        txbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //fungsi login
    void fun_login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //cek berhasil login
                        if (task.isSuccessful()) {
                            //pindah activity
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            //gagal login
                            //tampilkan dialog gagal login
                            AlertDialog dialog = new AlertDialog.Builder(Login.this).create();
                            dialog.setTitle("Gagal Login");
                            dialog.setMessage(task.getException().toString());
                            dialog.setCancelable(true);
                            dialog.show();
                        }
                    }
                });

    }
}