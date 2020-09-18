package com.raj.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView txt_notuser,txt_reg,closebutton;
    Button btnlogin;
    EditText edtemail;
    EditText edtPassword;
    ImageView passViewImage;
    private int passwordNotVisible=1;
    private FirebaseAuth firebaseAuth;
    private String Emailpattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private ProgressBar progressBar;
    public static boolean onBackLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_reg=findViewById(R.id.txt_reg);
        btnlogin=findViewById(R.id.btn_login);
        edtemail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password);
        passViewImage = findViewById(R.id.passView);
        closebutton = findViewById(R.id.login_close);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        final String str = edtemail.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyApp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL_KEY",str);
        editor.commit();

        passViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordNotVisible == 1){
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                }else {

                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });
        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Register.class);
                startActivity(intent);
            }
        });

        edtemail.addTextChangedListener(new TextWatcher() {
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
        edtPassword.addTextChangedListener(new TextWatcher() {
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

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandPassword();
            }
        });
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,home.class);
                startActivity(i);
                finish();
            }
        });

    }
    private void checkInputs(){
        if(!TextUtils.isEmpty(edtemail.getText())){
            if(!TextUtils.isEmpty(edtPassword.getText())){
                btnlogin.setEnabled(true);
                btnlogin.setTextColor(Color.argb(200,255,255,255));
            }else{
                btnlogin.setEnabled(false);
                btnlogin.setTextColor(Color.argb(40,255,255,255));
            }
        }else{
            btnlogin.setEnabled(false);
            btnlogin.setTextColor(Color.argb(40,255,255,255));

        }
    }

    private void checkEmailandPassword(){
        if(edtemail.getText().toString().matches(Emailpattern)){
            if(edtPassword.length() >= 8){

                progressBar.setVisibility(View.VISIBLE);
                btnlogin.setEnabled(false);
                btnlogin.setTextColor(Color.argb(40,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(edtemail.getText().toString(),edtPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mainIntent();
                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    btnlogin.setEnabled(true);
                                    btnlogin.setTextColor(Color.argb(200,255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(login.this, ""+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(this,"Incorrect Email & Password",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Incorrect Email & Password",Toast.LENGTH_SHORT).show();
        }
    }

    private void mainIntent(){
        Intent i = new Intent(login.this,home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                        finish();
                    }

                }).create().show();
    }
}

