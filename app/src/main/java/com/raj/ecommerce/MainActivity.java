package com.raj.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int splashTime = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GifImageView gifImageView = (GifImageView) findViewById(R.id.img_data);
        gifImageView.setGifImageResource(R.drawable.ecommerce);


        SharedPreferences sharedPreferences = getSharedPreferences("MyApp",MODE_PRIVATE);
        final String strEmail = sharedPreferences.getString("EMAIL_KEY","");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                 if (strEmail.equals("")){

                    Intent i = new Intent(MainActivity.this, login.class);
                    startActivity(i);
                    finish();

                }else {

                Intent i = new Intent(MainActivity.this, home.class);
                startActivity(i);
                finish();

                // }

            }
            }
        },splashTime);


    }

}