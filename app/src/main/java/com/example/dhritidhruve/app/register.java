package com.example.dhritidhruve.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    EditText name,email,pass,department,roll;
    EditText year,semester,qualification,designation;
    TextView signin;
    String usertype;

    public void signup (View view) {

          name =(EditText) findViewById(R.id.name);
         email=(EditText) findViewById(R.id.email );
         pass=(EditText) findViewById(R.id.password);
        department=(EditText) findViewById(R.id.department);
        roll=(EditText) findViewById(R.id.roll );
        year=(EditText) findViewById(R.id.studentyear);
        semester=(EditText)findViewById(R.id.studentsemester);
        qualification=(EditText) findViewById(R.id.staffqualification);
        designation=(EditText) findViewById(R.id.staffdesignation);
        signin=(TextView)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
      /*  Button pic=(Button)findViewById(R.id.uploadpic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView
            }
        });
        */
        db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name.getText().toString());
        user.put("email", email.getText().toString());
        user.put("password",pass.getText().toString());
        user.put("roll",roll.getText().toString());
        user.put("department",department.getText().toString());
        if(usertype.equals("STUDENT")) {
            user.put("Year", year.getText().toString());
            user.put("Semester",semester.getText().toString());
        }
        else {
            user.put("Designation",designation.getText().toString());
            user.put("Qualification",qualification.getText().toString());
        }
            db.collection(usertype)
                .document(department.getText().toString())
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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.TYPE, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        year=(EditText) findViewById(R.id.studentyear);
        semester=(EditText)findViewById(R.id.studentsemester);
        qualification=(EditText) findViewById(R.id.staffqualification);
        designation=(EditText) findViewById(R.id.staffdesignation);
        if(i==1)
        {
            year.setVisibility(View.VISIBLE);
            semester.setVisibility(View.VISIBLE);
            qualification.setVisibility(View.GONE);
            designation.setVisibility(View.GONE);
            usertype="STUDENT";
        }
        else if(i==2)
        {
            year.setVisibility(View.GONE);
            semester.setVisibility(View.GONE);
           qualification.setVisibility(View.VISIBLE);
           designation.setVisibility(View.VISIBLE);
           usertype="STAFF";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

