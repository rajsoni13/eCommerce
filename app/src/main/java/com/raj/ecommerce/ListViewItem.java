package com.raj.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewItem extends AppCompatActivity {

    ImageView imageView;
    TextView name,price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item);

        name= findViewById(R.id.name_list);
        imageView = findViewById(R.id.img_list);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("namelist"));
        imageView.setImageResource(intent.getIntExtra("imagelist",0));
    }
}