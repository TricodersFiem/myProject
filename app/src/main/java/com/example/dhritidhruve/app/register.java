package com.example.dhritidhruve.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public void register1 (View view) {

        EditText name =(EditText) findViewById(R.id.name);
        EditText email=(EditText) findViewById(R.id.email );
        EditText pass=(EditText) findViewById(R.id.password);
        EditText department=(EditText) findViewById(R.id.department);
        EditText roll=(EditText) findViewById(R.id.roll );
        db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name.getText().toString());
        user.put("email", email.getText().toString());
        user.put("password",pass.getText().toString());
        user.put("roll",roll.getText().toString());
        user.put("department",department.getText().toString());
        db.collection("users")
                .document(roll.getText().toString())
                .set(user);

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString());
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();

    }
}
