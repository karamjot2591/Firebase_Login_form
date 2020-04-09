package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private EditText  Email, Password, ConfirmPass;
    private ProgressBar progressBar;
    private Button registerbtn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup");


        Email=findViewById(R.id.emailid);
        Password=findViewById(R.id.passid);
        ConfirmPass= findViewById(R.id.compasswordid);
        progressBar=findViewById(R.id.progressid);
        registerbtn= findViewById(R.id.btnsign);
// firebase auth initial here....
        firebaseAuth = FirebaseAuth.getInstance();

        // on click operation on register btn here.....
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creating a variables to get values by  user in fields like user name ,
                // passsword etc.....

                 String email = Email .getText().toString().trim();
                 String password= Password.getText().toString().trim();
                 String confirmpass= ConfirmPass.getText().toString().trim();

                 if (TextUtils.isEmpty(email) &&  TextUtils.isEmpty(password)
                 && TextUtils.isEmpty(confirmpass)){
                     Toast.makeText(Signup.this,"please enter fields",Toast.LENGTH_SHORT).show();
                     return;
                                      }
                 if (password.length()<6 ){
                     Toast.makeText(Signup.this,"password must have 6 Characters",Toast.LENGTH_SHORT).show();
                     return;

                 }
                 progressBar.setVisibility(View.VISIBLE);
                 if (password.equals(confirmpass)){

                     firebaseAuth.createUserWithEmailAndPassword(email, password)
                             .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {

                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     progressBar.setVisibility(View.GONE);

                                     if (task.isSuccessful()) {
                                         // Sign in success, update UI with the signed-in user's information
                                         startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                         Toast.makeText(Signup.this,"Successfull authication ",Toast.LENGTH_SHORT).show();
                                     } else {
                                         // If sign in fails, display a message to the user.
                                         Toast.makeText(Signup.this,"failed authication ",Toast.LENGTH_SHORT).show();
                                     }

                                     // ...
                                 }
                             });




                 }

            }
        });

    }
}
