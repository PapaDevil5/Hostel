package com.example.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class clean extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);
        AppCompatButton sun=(AppCompatButton) (findViewById(R.id.imageView15));
        AppCompatButton tue=(AppCompatButton) (findViewById(R.id.imageView10));
        AppCompatButton wed=(AppCompatButton) (findViewById(R.id.imageView11));
        AppCompatButton thur=(AppCompatButton) (findViewById(R.id.imageView12));
        AppCompatButton fri=(AppCompatButton) (findViewById(R.id.imageView13));
        AppCompatButton sat=(AppCompatButton) (findViewById(R.id.imageView14));
        AppCompatButton mon=(AppCompatButton) (findViewById(R.id.imageView9));
        Intent f=new Intent(clean.this,cleaning.class);
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        thur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(f);
            }
        });
    }
}