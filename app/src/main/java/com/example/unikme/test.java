package com.example.unikme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class test extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextView textView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    public static int time=0;
    public static String modify="mins";
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView=findViewById(R.id.textView);
        fAuth =FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        //get the current user
        userId=fAuth.getCurrentUser().getUid();

        //if user isnt created it will craeate a new one
        //    DocumentReference documentReference=fStore.collection("users").document(userId);

        DocumentReference documentReference=fStore.collection("users").document(userId);
                }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }
}