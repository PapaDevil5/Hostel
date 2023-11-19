package com.example.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class content extends AppCompatActivity {
    NavigationView navigation;
    DrawerLayout drawer_layout;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        drawer_layout=findViewById(R.id.drawer);
        navigation=findViewById(R.id.navigationView);
//        LinearLayout toolbars=(LinearLayout)findViewById(R.id.toolbar1);
        LinearLayout toolb=(LinearLayout) findViewById(R.id.text9);
        ImageView bell=(ImageView)findViewById(R.id.imageView3);
        CardView clo= (CardView) findViewById(R.id.card);
        CardView cle= (CardView) findViewById(R.id.card2);
        CardView f= (CardView) findViewById(R.id.card3);
        LinearLayout logO=(LinearLayout)findViewById(R.id.logout);
        Intent food= new Intent(content.this,food.class);
        Intent clothes=new Intent(content.this,clothes.class);
        Intent clean=new Intent(content.this,clean.class);
        Intent logI=new Intent(content.this, login.class);
        Intent hm=new Intent(content.this,content.class);
        Intent fq=new Intent(content.this,faq.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Users");
        navigation.bringToFront();

        logO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(logI);
                finish();
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(content.this, "No New Announcement.", Toast.LENGTH_SHORT).show();
            }
        });
        toolb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
        clo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(clothes);
            }
        });
        cle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(clean);
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(food);
            }
        });



        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.home){
                    startActivity(hm);
                    finish();
                }else if(id==R.id.faq){
                    startActivity(fq);
                }
                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        View header=navigation.getHeaderView(0);
        TextView name=(TextView) header.findViewById(R.id.textView8);
        TextView name1=(TextView) findViewById(R.id.textView0);
        TextView email=(TextView) header.findViewById(R.id.textView9);
        reference.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data found, update UI
                    String Name = dataSnapshot.child("name").getValue(String.class);
                    String Email=dataSnapshot.child("email").getValue(String.class);
                    name.setText(Name);
                    name1.setText("Hi, "+Name+"!");
                    email.setText(Email);
                } else {
                    name.setText("Not Found.");
                    email.setText("Not Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(content.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}