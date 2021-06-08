package com.example.dreamland.foodloverclient;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class login extends AppCompatActivity {

    EditText useremail, userpassword;
    Button loginbtnuser, registerbtnuser;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        useremail=(EditText)findViewById(R.id.emailfield);
        userpassword=(EditText)findViewById(R.id.passfield);
        loginbtnuser=(Button)findViewById(R.id.login);
        registerbtnuser=(Button)findViewById(R.id.register);
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Toast.makeText(login.this,"Sign In ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(login.this,"Create A new Account",Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginbtnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });
        registerbtnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(login.this,register.class);
                startActivity(a);
            }
        });

    }
    private void startSignin(){
        String user_email=useremail.getText().toString().trim();
        String user_password=userpassword.getText().toString().trim();
        if(TextUtils.isEmpty(user_email)||TextUtils.isEmpty(user_password)){
            Toast.makeText(login.this,"Field is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(login.this,"Sign In Problem",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent i=new Intent(login.this,mainmenu.class);
                        startActivity(i);
                        Toast.makeText(login.this,"Sign In",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

