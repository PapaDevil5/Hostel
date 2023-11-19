package com.example.hostel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_acc extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        loading=(ProgressBar) (findViewById(R.id.load));
        EditText name=(EditText) (findViewById(R.id.text));
        EditText hostel=(EditText) (findViewById(R.id.text2));
        EditText email=(EditText) (findViewById(R.id.text3));
        EditText password=(EditText) (findViewById(R.id.text4));
        auth=FirebaseAuth.getInstance();
        AppCompatButton submit=(AppCompatButton) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1,email1,hostel1,password1;
                loading.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(name.getText().toString())){
                    loading.setVisibility(View.GONE);
                    name.setError("Enter Name");
                }
                if(TextUtils.isEmpty(email.getText().toString())){
                    loading.setVisibility(View.GONE);
                    email.setError("Enter E-mail");
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    loading.setVisibility(View.GONE);
                    password.setError("Enter Password");
                }
                if(TextUtils.isEmpty(hostel.getText().toString())){
                    loading.setVisibility(View.GONE);
                    hostel.setError("Enter Hostel");
                }
                if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(hostel.getText().toString())||TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                    loading.setVisibility(View.GONE);
                    Toast.makeText(new_acc.this,"Fill all the Fields",Toast.LENGTH_SHORT).show();
                }else{
                    name1=name.getText().toString();
                    email1=email.getText().toString();
                    hostel1=hostel.getText().toString();
                    password1=password.getText().toString();
                    auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new_acc.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                users user=new users(name1,hostel1,email1);
                                db=FirebaseDatabase.getInstance();
                                reference=db.getReference("Users");
                                reference.child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        name.setText("");
                                        email.setText("");
                                        hostel.setText("");
                                        password.setText("");
                                    }
                                });
                                Toast.makeText(new_acc.this,"Account Created Successfully!",Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                finish();
                            }else{
                                loading.setVisibility(View.GONE);
                                Toast.makeText(new_acc.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
