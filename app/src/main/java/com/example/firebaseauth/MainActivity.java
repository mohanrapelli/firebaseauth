package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView registertext,forgotpassword;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextUsername);
        password=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);
        registertext=findViewById(R.id.regtext);
        forgotpassword=findViewById(R.id.forgot);
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=email.getText().toString();
                String b=password.getText().toString();
                if (TextUtils.isEmpty(a)) {
                    email.setError("enter ur email");
                    return;
                }
                else{

                }
                if (TextUtils.isEmpty(b)) {
                    password.setError("enter ur password");
                    return;
                }
                else{

                }
                if (password.length()<6 ) {
                    password.setError("pasword should be greater than 5 letters");
                    return;
                }
                else{

                }
                if (!isPasswordValid(b)) {
                    password.setError("Password should contain at least one uppercase letter, one lowercase letter, and one digit");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(a,b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login sucessfull", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this, Home.class);
                            startActivity(i);

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Login failed"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            private boolean isPasswordValid(String password) {

                return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
            }
        });
        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, Register.class);
                startActivity(i);

            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, forgotpassword.class);
                startActivity(i);

            }
        });
    }
}