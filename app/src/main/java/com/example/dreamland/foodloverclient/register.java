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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class register extends AppCompatActivity  {

    Button userRegister;
    FirebaseAuth userAuth;;
    EditText userName, userAge, userUsername, userEmail, userPassword, userconfirmpass;
    DatabaseReference userref;
    StorageReference storageReference=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        userName = (EditText) findViewById(R.id.etName);
        userAge = (EditText) findViewById(R.id.etAge);
        userUsername = (EditText) findViewById(R.id.etUsername);
        userEmail = (EditText) findViewById(R.id.etEmail);
        userPassword = (EditText) findViewById(R.id.etPassword);
        userRegister = (Button) findViewById(R.id.bRegister);

        userconfirmpass = (EditText) findViewById(R.id.confirmpass);
        userAuth=FirebaseAuth.getInstance();
        userref= FirebaseDatabase.getInstance().getReference("UserInfo");
        storageReference= FirebaseStorage.getInstance().getReference("UserInfo");
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=userEmail.getText().toString().trim();
                String password=userPassword.getText().toString().trim();
                String name=userName.getText().toString().trim();
                String Age=userAge.getText().toString().trim();
                String Username=userUsername.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(Age)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(Username)){
                    final DatabaseReference UserPost=userref.push();
                    final DatabaseReference y=UserPost.push();
                    y.child("name").setValue(name);
                    y.child("Age").setValue(Age);
                    y.child("email").setValue(email);
                    y.child("Password").setValue(password);
                    y.child("Username").setValue(Username);
                }
                userAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(register.this,login.class));
                            Toast.makeText(getApplicationContext(),"Created",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
