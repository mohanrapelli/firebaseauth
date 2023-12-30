package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    EditText email;
    Button reset;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        email=findViewById(R.id.email);
        reset=findViewById(R.id.link);
        firebaseAuth=FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=email.getText().toString();
                if (TextUtils.isEmpty(a)) {
                    email.setError("enter ur email");
                    return;
                }
                firebaseAuth.sendPasswordResetEmail(a).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(forgotpassword.this, "Mail sent", Toast.LENGTH_SHORT).show();
startActivity(new Intent(forgotpassword.this,MainActivity.class));
                    }
                });


            }
        });
    }
}