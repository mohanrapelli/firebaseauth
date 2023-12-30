package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email,password,name,phone;
    Button register;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.Fullname);
       password=findViewById(R.id.editTextPassword);
        email=findViewById(R.id.editTextUsername);
       phone=findViewById(R.id.Phonenumber);
       register=findViewById(R.id.register1);
       firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=email.getText().toString().trim();
                String b=password.getText().toString().trim();
                String c=phone.getText().toString();
                String d=name.getText().toString();
                if (TextUtils.isEmpty(c)) {
                    phone.setError("enter ur email");
                    return;
                }
                if (TextUtils.isEmpty(d)) {
                    name.setError("enter ur email");
                    return;
                }
                if (TextUtils.isEmpty(a)) {
                    email.setError("enter ur email");
                    return;
                }
                if (TextUtils.isEmpty(b)) {
                    password.setError("enter ur password");
                    return;
                }
                if (password.length()<6 ) {
                    password.setError("pasword should be greater than 5 letters");
                    return;
                }
                if (phone.length()!= 10 ) {
                    phone.setError("phone number should be 10 numbers");
                    return;
                }
                if (!isPasswordValid(b)) {
                    password.setError("Password should contain at least one uppercase letter, one lowercase letter, and one digit");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(a,b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Registered sucessfully", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Register.this, MainActivity.class);
                            startActivity(i);
                        }

                    }
                });

            }

            private boolean isPasswordValid(String password) {
                return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
            }
        });

    }
}