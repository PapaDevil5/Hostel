package com.example.hostel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class laundry extends AppCompatActivity {

    RadioButton r;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioButton r5;
    private FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        r=(RadioButton) findViewById(R.id.checkBox);
        r1=(RadioButton) findViewById(R.id.checkBox1);
        r2=(RadioButton) findViewById(R.id.checkBox2);
        r3=(RadioButton) findViewById(R.id.checkBox3);
        r4=(RadioButton) findViewById(R.id.checkBox4);
        r5=(RadioButton) findViewById(R.id.checkBox5);
        Button button=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r.setChecked(true);
            }
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r1.setChecked(true);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r2.setChecked(true);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r3.setChecked(true);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r4.setChecked(true);
            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioChecked();
                r5.setChecked(true);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users").child(auth.getCurrentUser().getUid());
                reference.child(auth.getCurrentUser().getUid()).setValue("Laundry: "+whatChecked()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                    }
                });
            }
        });
    }

    public String whatChecked() {
        if (r.isChecked()) {
            return r.getText().toString();
        } else if (r1.isChecked()) {
            return r1.getText().toString();
        } else if (r2.isChecked()) {
            return r2.getText().toString();
        } else if (r3.isChecked()) {
            return r3.getText().toString();
        } else if (r3.isChecked()) {
            return r3.getText().toString();
        } else if (r4.isChecked()) {
            return r4.getText().toString();
        } else{
            return r5.getText().toString();
        }
    }
    public void clearRadioChecked() {
        r.setChecked(false);
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r4.setChecked(false);
        r5.setChecked(false);
    }
}



