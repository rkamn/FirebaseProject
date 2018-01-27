package com.example.rakesh.firebaseproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etemail,etpassword;
    private Button bregister;
    private TextView tvalreadyregister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText) findViewById(R.id.etpassword);
        bregister = (Button) findViewById(R.id.bregister);
        tvalreadyregister = (TextView) findViewById(R.id.tvalreadyregister);
        bregister.setOnClickListener(this);
        tvalreadyregister.setOnClickListener(this);

    }
 private  void registerUser(){
        String email = etemail.getText().toString().trim();
        String pass = etpassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            // stop the function exacution
            return;
        }
     if (TextUtils.isEmpty(pass)) {
         // password is empty
         Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
         // stop the function exacution
         return;
     }
     // if validation is ok
     // show the progressor first
     progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // user register sucessfull
                            Toast.makeText(MainActivity.this, "user register sucessfully", Toast.LENGTH_SHORT).show();
                            }
                        else {
                            Toast.makeText(MainActivity.this,"Could not Register. Please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
     }
    @Override
    public void onClick(View view) {
     if(view ==bregister){
         registerUser();
     }
     if (view == tvalreadyregister){
         //open login activty
         Intent intent = new Intent(this,Welcome.class);
         startActivity(intent);

     }
    }
}
