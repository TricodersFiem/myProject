package com.example.dhritidhruve.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class register extends AppCompatActivity {
    public void register1 (View view) {

        EditText name =(EditText) findViewById(R.id.name);
        EditText username=(EditText) findViewById(R.id.email );
        EditText pass=(EditText) findViewById(R.id.password);
        EditText reenter=(EditText) findViewById(R.id.reenter );
        EditText department=(EditText) findViewById(R.id.department);
        EditText roll=(EditText) findViewById(R.id.roll );
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

}
