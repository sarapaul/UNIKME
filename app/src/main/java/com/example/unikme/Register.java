package com.example.unikme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mEmail, mPassword ;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth; //its a class with which we can register the user
    ProgressBar progressBar;
    FirebaseFirestore fStore;//its a class to store data

    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.loginBtn);
        mLoginBtn = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();//instance of cloud
        progressBar = findViewById(R.id.progressBar);
        //if current user object is already present the user is send to the main activity
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), test.class));
            finish();
        }
        //register 4 a register btn
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the values entered by the user
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                //checking if the fields are empty
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                //checking if the length of the user is 6 or more
                if(password.length() <6){
                    mPassword.setError("Password must have 6 characters atleast");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //register the user in firebase
                //object instance to create and have a listerner for that action
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //process of reg the user is task
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //sending a msg user created
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            //get the current user id from fire auth
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference =fStore.collection("users").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                //logmessage
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User Profile is created for"+userID);

                                }
                            });
                            startActivity(new Intent(getApplicationContext(), test.class));
                        }
                        else{
                            //getting the error and displaying it
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        //creating a listner 4 goto login activity
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.unikme.login.class));
            }
        });
    }
}