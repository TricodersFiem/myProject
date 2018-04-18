package com.example.dhritidhruve.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    EditText name, email, pass, collegeid;
    EditText qualification, designation;
    Spinner usertype, year, department;
    String type, currentYear, currentDepartment;
    Student current;
    Staff currentStaff;
    ImageView imageView;
    public static final int RESULT_LOAD_IMAGE = 1;
    private StorageReference mStorageRef;

    public void signup(View view) {
        if (name.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("") || collegeid.getText().toString().equals("") || currentDepartment.equals("")){
            Toast.makeText(register.this, "empty input fields", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(register.this, "Please wait...", "Processing...", true);
            db = FirebaseFirestore.getInstance();

            if (type.equals("STUDENT")) {
                current = new Student(name.getText().toString(), collegeid.getText().toString(), email.getText().toString(), pass.getText().toString(), currentYear, currentDepartment);
                db.collection("Department").document(current.getDepartment()).collection("StudentCollection").document(current.getYear()).set(current)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(register.this, "User Successfully added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "Can't add user", Toast.LENGTH_SHORT).show();
                            }
                        });

            } else {
                currentStaff=new Staff(name.getText().toString(),collegeid.getText().toString(),email.getText().toString(), pass.getText().toString(),currentDepartment,qualification.getText().toString(),designation.getText().toString());
                db.collection("Department").document(current.getDepartment()).collection("StaffCollection").document("goOn").set(currentStaff);
            }
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null ){
            Uri selectedImage=data.getData();
            StorageReference imgeref =mStorageRef.child(name.getText().toString()+".jpg");
            imgeref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    });
            // upload to storage

            imageView.setImageURI(selectedImage);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        //Initialization of the instance variable
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        collegeid = (EditText) findViewById(R.id.collegeid);
        qualification = (EditText) findViewById(R.id.qualification);
        designation = (EditText) findViewById(R.id.designation);
        imageView=(ImageView)findViewById(R.id.image);
        mStorageRef = FirebaseStorage.getInstance().getReference();//Firebase storage variable initialization
        Button pic=(Button)findViewById(R.id.uploadpic);//Upload the picture
        department = (Spinner) findViewById(R.id.department);
        year = (Spinner) findViewById(R.id.year);
        usertype = (Spinner) findViewById(R.id.usertype);

        //button clk for uploading picture in the database
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

        //Setting the adapter for department (spinner)
        ArrayAdapter depadapt = ArrayAdapter.createFromResource(this, R.array.DEPARTMENT, android.R.layout.simple_spinner_item);
        department.setAdapter(depadapt);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentDepartment=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for year
        ArrayAdapter yearadapt = ArrayAdapter.createFromResource(this, R.array.YEAR, android.R.layout.simple_spinner_item);
        year.setAdapter(yearadapt);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    currentYear=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for usertype
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.USERTYPE, android.R.layout.simple_spinner_item);
        usertype.setAdapter(adapter);
        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
                if(i==1)
                {

                    year.setVisibility(View.VISIBLE);
                    qualification.setVisibility(View.GONE);
                    designation.setVisibility(View.GONE);
                    type = adapterView.getItemAtPosition(i).toString();
                }
                else if(i==2)
                {
                    year.setVisibility(View.GONE);
                    qualification.setVisibility(View.VISIBLE);
                    designation.setVisibility(View.VISIBLE);
                    type = adapterView.getItemAtPosition(i).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mAuth = FirebaseAuth.getInstance();

    }
}

