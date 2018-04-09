package com.example.dhritidhruve.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void register (View view) {

        EditText username=(EditText) findViewById(R.id.email );
        EditText pass=(EditText) findViewById(R.id.password);
        Intent intent= new Intent(getApplicationContext(),register.class);
        startActivity(intent);
    }
    public void login(View view) {
        Intent i = new Intent(getApplicationContext(), user.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
