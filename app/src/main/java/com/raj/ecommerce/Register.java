package com.raj.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    TextView textView, closebtn;
    private EditText fname, email, password, confirmopassword;
    private FrameLayout frameLayout;
    private ImageView imgview;
    private Button btnReg;
    private ProgressBar progressBar;
    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.login_text);
        email = findViewById(R.id.edt_email);
        fname = findViewById(R.id.edt_fname);
        password = findViewById(R.id.edt_password);
        confirmopassword = findViewById(R.id.edt_copass);
        btnReg = findViewById(R.id.btn_reg);
        progressBar = findViewById(R.id.progressBarReg);
        closebtn = findViewById(R.id.register_close);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmopassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, home.class);
                startActivity(i);
                finish();
            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandpassword();
            }
        });
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fname.getText())) {
                if (!TextUtils.isEmpty(email.getText())) {

                    if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                        if (!TextUtils.isEmpty(confirmopassword.getText())) {

                            btnReg.setEnabled(true);
                            btnReg.setTextColor(Color.argb(200, 255, 255, 255));

                        } else {
                            btnReg.setEnabled(false);
                            btnReg.setTextColor(Color.argb(40, 255, 255, 255));
                        }
                    } else {
                        btnReg.setEnabled(false);
                        btnReg.setTextColor(Color.argb(40, 255, 255, 255));
                    }
                } else {
                    btnReg.setEnabled(false);
                    btnReg.setTextColor(Color.argb(40, 255, 255, 255));

                }
            } else {
                btnReg.setEnabled(false);
                btnReg.setTextColor(Color.argb(40, 255, 255, 255));
            }
        } else {
            btnReg.setEnabled(false);
            btnReg.setTextColor(Color.argb(40, 255, 255, 255));
        }
    }

    private void checkEmailandpassword() {

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error_icon);
        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());

        if (email.getText().toString().matches(emailpattern)) {
            if (password.getText().toString().equals(confirmopassword.getText().toString())) {
                progressBar.setVisibility(View.VISIBLE);
                btnReg.setEnabled(false);
                btnReg.setTextColor(Color.argb(40, 255, 255, 255));


            } else {
                confirmopassword.setError("password doesn't match!", customErrorIcon);
            }
        }
    }
}



