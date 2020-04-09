package com.example.login_form;

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

public class Login_form extends AppCompatActivity {
private EditText emaillogin, passwordlogin;
private Button loginbtn;
FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login");


        emaillogin=findViewById(R.id.emailloginid);
        passwordlogin=findViewById(R.id.passloginid);
        loginbtn=findViewById(R.id.btnlogin);

        firebaseAuth=FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emaillogin .getText().toString().trim();
                String password= passwordlogin.getText().toString().trim();

                if (TextUtils.isEmpty(email) &&  TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_form.this, "please enter fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                            Toast.makeText(Login_form.this,"Incorrect password or Email",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });
    }


    public void btnsignup(View view) {
        startActivity(new Intent(Login_form.this,Signup.class));
    }
}
