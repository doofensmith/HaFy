package com.softlaboratory.hafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.softlaboratory.hafy.fragments.Beranda;

public class Login extends AppCompatActivity {

    //declare view
    MaterialButton buttonLogin;
    TextView textViewBuat;
    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;

    //firebase
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //auth
        auth = FirebaseAuth.getInstance();

        //init view
        buttonLogin = findViewById(R.id.al_btnmasuk);
        textViewBuat = findViewById(R.id.al_txbuat);
        editTextEmail = findViewById(R.id.al_ed_email);
        editTextPassword = findViewById(R.id.al_ed_password);

        //btn login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                loginAccount(Login.this, buttonLogin, email, password);
            }
        });

        //text buat
        textViewBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        //warna status bar merah
        if (Build.VERSION.SDK_INT>=21) {
            Window window = Login.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Login.this.getResources().getColor(R.color.primary));
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getWindowSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    //fungsi login
    void loginAccount(Context context,View viewForSnackbar, String email, String password) {
        //kondisi string kosong
        if (email.isEmpty()||password.isEmpty()) {

            Snackbar snackbar = Snackbar.make(viewForSnackbar,"! Email atau Password salah.", Snackbar.LENGTH_LONG);
            snackbar.setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                }
            });
            snackbar.show();

//            AlertDialog dialog = new AlertDialog.Builder(Login.this).create();
//            dialog.setMessage("Email atau Password Salah!");
//            dialog.setTitle("Gagal Login");
//            dialog.setCancelable(true);
//            dialog.show();
        }else {
            //Progress dialog
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Loging In");
            progressDialog.setMessage("Account login in progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            //proses login
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //cek berhasil login
                            if (task.isSuccessful()) {
                                //pindah activity
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                //off progress dialog
                                progressDialog.dismiss();
                                finish();
                            }else {
                                //off progress dialog
                                progressDialog.dismiss();
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
}