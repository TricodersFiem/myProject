package com.example.dhritidhruve.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText txtEmail, txtPassword;


    public void signIn(View view) {
        if (txtEmail.getText().toString().equals("") ) {
            Toast.makeText(MainActivity.this, "EMPTY EMAIL ID FIELD", Toast.LENGTH_SHORT).show();
        }
        else if(txtPassword.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "EMPTY PASSWORD FIELD", Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Processing...", true);
            mAuth.signInWithEmailAndPassword(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Log.d("TAG", "signInWithEmail:success");
                                mAuth= FirebaseAuth.getInstance();
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(!user.isEmailVerified()){
                                    Toast.makeText(MainActivity.this, "PROCEED TO VERIFY YOUR ACCOUNT ", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), verifyEmail.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "SUCCESSFUL SIGN IN AND EMAIL VERIFICATION..CONGRATULATIONS ", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), user.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        FirebaseUser user = mAuth.getCurrentUser();

        if (user!=null) {
            // User is signed in.
            Intent i = new Intent(getApplicationContext(), user.class);
            startActivity(i);
            finish();

        }

        TextView mSignUp;
        (mSignUp = (TextView) findViewById(R.id.signUp))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), register.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }
}