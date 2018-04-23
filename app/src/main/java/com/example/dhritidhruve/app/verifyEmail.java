package com.example.dhritidhruve.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class verifyEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
            Button verifyEmail = (Button) findViewById(R.id.verifyemail);
            verifyEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.sendEmailVerification()
                            .addOnCompleteListener(verifyEmail.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    findViewById(R.id.verifyemail).setEnabled(false);
                                    if (task.isSuccessful())
                                        Toast.makeText(verifyEmail.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_LONG).show();
                                    else {
                                        Log.e("TAG", "sendEmailVerification", task.getException());
                                        Toast.makeText(verifyEmail.this, "Failed to send verification email.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            });
            Button main = (Button) findViewById(R.id.mainact);
            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }
    }

