package com.example.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth auth;
    ProgressBar loading;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser!=null){
            Intent intent=new Intent(login.this, content.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);

        super.onCreate(savedInstanceState);
        TextInputEditText username=(TextInputEditText) (findViewById(R.id.text));
        TextInputEditText password=(TextInputEditText) (findViewById(R.id.text1));
        AppCompatButton login_button=(AppCompatButton) (findViewById(R.id.button));
        TextView register=(TextView)(findViewById(R.id.textView1));
        TextView forgot=(TextView)(findViewById(R.id.textView2));
        TextView head=(TextView)(findViewById(R.id.textView4));
        ImageView google=(ImageView)(findViewById(R.id.imageView));
        ImageView facebook=(ImageView)(findViewById(R.id.imageView2));
        ImageView login=(ImageView)(findViewById(R.id.textView));
        Intent n=new Intent(login.this,new_acc.class);
        Intent c=new Intent(login.this, content.class);
        c.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        auth=FirebaseAuth.getInstance();
        loading=(ProgressBar)(findViewById(R.id.load));

//        login.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up));
//        Handler  handler=new Handler();
//        Runnable runnable=new Runnable() {
//
//        @Override
//        public void run() {
//            username.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            password.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            login_button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            register.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            forgot.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            lgnwith.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            google.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            facebook.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//            head.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade));
//        }
//    };
//    handler.postDelayed(runnable,800);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                if((username.getText().toString().equals("admin") && password.getText().toString().equals("panda"))){
                    Toast.makeText(com.example.hostel.login.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(c);
                }else if(TextUtils.isEmpty(username.getText().toString())){
                    loading.setVisibility(View.GONE);
                    username.setError("Username is Mandatory");
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    loading.setVisibility(View.GONE);
                    password.setError("Password is Mandatory");
                }else{
                    loginUser(username.getText().toString(),password.getText().toString());
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(n);
            }
        });
    }
    private void loginUser(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loading.setVisibility(View.GONE);
                Toast.makeText(login.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, content.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loading.setVisibility(View.GONE);
                Toast.makeText(login.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            }
        });
    }
}