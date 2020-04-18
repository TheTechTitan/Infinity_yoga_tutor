package com.infinity.yogacorrectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText mUsername,mEmail,mPassword,mCPassword;
    Button mSignUpBtn;
    TextView mSignInBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mUsername =findViewById(R.id.Username);
        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.Password);
        mCPassword=findViewById(R.id.CPassword);
        mSignUpBtn=findViewById(R.id.SignUpBtn);
        mSignInBtn=findViewById(R.id.AlreadyUser);
        progressBar=findViewById(R.id.progressBar);

        fAuth=FirebaseAuth.getInstance();

        //Check whether user already logged in
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String cPassword=mCPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Please enter Email");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Please enter Password");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password should be 6 or more characters");
                    return;
                }

                if(!TextUtils.equals(cPassword,password)){
                    mCPassword.setError("Passwords do not match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Registering the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Successfully Created User ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(SignUp.this, "Sorry Invalid User", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });

    }
}
