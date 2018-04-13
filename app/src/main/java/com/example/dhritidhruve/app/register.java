package com.example.dhritidhruve.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
   /* public static class holder{
        boolean success;
    }*/
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private EditText txtEmail, txtPassword, department, roll, name;

    public void register1 (View view) {

        //holder h = new holder();
       // h.success = false;
        final ProgressDialog progressDialog = ProgressDialog.show(register.this, "Please wait...", "Processing...", true);
        db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name.getText().toString());
        user.put("email", txtEmail.getText().toString().trim());
        user.put("password", txtPassword.getText().toString().trim());
        user.put("roll", roll.getText().toString());
        user.put("department", department.getText().toString());
        db.collection("users").document(txtEmail.getText().toString()).set(user);

        mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    //holder h = new holder();
                    Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent loginClass = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginClass);
                    finish();
                    //h.success = true;
                } else {
                    Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*if (h.success) {
            final ProgressDialog progressDialog2 = ProgressDialog.show(register.this, "Please wait...", "Logging you in...", true);
            mAuth.signInWithEmailAndPassword(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressDialog2.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(register.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), user.class);
                        startActivity(i);
                        finish();
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }*/
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();

        txtEmail=(EditText) findViewById(R.id.email );
        txtPassword=(EditText) findViewById(R.id.password);
        department=(EditText) findViewById(R.id.department);
        roll=(EditText) findViewById(R.id.roll );
        name =(EditText) findViewById(R.id.name);

    }
}
