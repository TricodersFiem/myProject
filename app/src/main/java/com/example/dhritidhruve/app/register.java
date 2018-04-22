package com.example.dhritidhruve.app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    EditText name, email, pass, collegeId,designation;
    Spinner usertype, year, department,qualification;
    String type, currentYear, currentDepartment,currentQuali;
    Student current;
    Staff currentStaff;
    ImageView imageView;
    String photoId;
    TextView checkimage;
    public static final int RESULT_LOAD_IMAGE = 1;
    private StorageReference mStorageRef;
    int k=0;
    Uri selectedImage;

    public void signup(View view) {

        if(k<8) {
             if (name.getText().toString().equals(""))
                Toast.makeText(register.this, "ENTER VALID NAME", Toast.LENGTH_LONG).show();
             else if (email.getText().toString().equals(""))
                 Toast.makeText(register.this, "ENTER VALID EMAIL ID", Toast.LENGTH_LONG).show();
             else if (pass.getText().toString().equals(""))
                 Toast.makeText(register.this, "ENTER VALID PASSWORD", Toast.LENGTH_LONG).show();
             else if (collegeId.getText().toString().equals(""))
                 Toast.makeText(register.this, "ENTER VALID COLLEGE ID", Toast.LENGTH_LONG).show();
             else if (type.equals(""))
                 Toast.makeText(register.this, "ENTER STUDENT OR STAFF TYPE USER", Toast.LENGTH_LONG).show();
             else if (type.equals("STUDENT") && currentYear.equals(""))
                 Toast.makeText(register.this, "ENTER VALID YEAR OF STUDENT", Toast.LENGTH_LONG).show();
             else if (type.equals("STAFF") && designation.getText().toString().equals(""))
                 Toast.makeText(register.this, "ENTER VALID USER TYPE", Toast.LENGTH_LONG).show();
             else if (type.equals("STAFF") && currentQuali.equals(""))
                 Toast.makeText(register.this, "ENTER VALID QUALIFICATION OF STAFF", Toast.LENGTH_LONG).show();
             else if (currentDepartment.equals(""))
                 Toast.makeText(register.this, "ENTER VALID DEPARTMENT OF STAFF", Toast.LENGTH_LONG).show();
             else if (selectedImage==null)
                Toast.makeText(register.this, "ENTER VALID IMAGE", Toast.LENGTH_LONG).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(register.this, "Please wait...", "Processing...", true);

            db = FirebaseFirestore.getInstance();

            if (type.equals("STUDENT")) {
                current = new Student(name.getText().toString(), collegeId.getText().toString(), email.getText().toString(), currentYear, currentDepartment, photoId);
                db.collection("Person").document("STUDENT "+email.getText().toString()).set(current)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(register.this, "USER SUCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "Can't add user", Toast.LENGTH_SHORT).show();
                            }
                        });

            } else {
                currentStaff = new Staff(name.getText().toString(), collegeId.getText().toString(), email.getText().toString(), currentDepartment, currentQuali, designation.getText().toString(), photoId);
                db.collection("Person").document("STAFF "+email.getText().toString()).set(currentStaff)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(register.this, "USER SUCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "Can't add user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null ){
            selectedImage=data.getData();
            StorageReference imgeref = mStorageRef.child(email.getText().toString()+".jpg");
            if (selectedImage != null) {
                imgeref.putFile(selectedImage)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                k++;
                                if (downloadUrl != null) {
                                    photoId = downloadUrl.toString();
                                }
                            }
                        });
                imageView.setImageURI(selectedImage);
                checkimage.setText("IMAGE UPLOADED");
            }
            else {
                Toast.makeText(register.this, "PLEASE SELECT AN IMAGE", Toast.LENGTH_LONG).show();
            }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if(name.getText().toString().length()>0) {
                    String nameip = name.getText().toString();
                    if (nameip.charAt(0) < 65 || nameip.charAt(0) > 90)
                        name.setError("First character of first name should be in uppercase");
                    else {
                        int space = nameip.indexOf(" ");
                        String lastname = nameip.substring(space + 1,name.getText().toString().length());
                        if(lastname.equals(""))
                            name.setError("Enter last name with first character in uppercase");
                        else if (lastname.charAt(0) < 65 || lastname.charAt(0) > 90)
                            name.setError("First character of last name should be in uppercase");
                        k++;

                    }
                }
            }
        });
        email = (EditText) findViewById(R.id.email);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (email.getText().toString().length() == 0)
                    email.setError("EMPTY EMAIL FIELD");
                else k++;
            }
        });

        pass = (EditText) findViewById(R.id.password);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {

                if (pass.getText().toString().length()<6)
                    pass.setError("INPUT PASSWORD ATLEAST 6 CHARACTERS LONG");
                if(pass.getText().toString().length()!=0)
                    k++;
            }
        });
        collegeId = (EditText) findViewById(R.id.collegeid);
        collegeId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if(collegeId.getText().toString().length()!=0)
                    k++;

            }
        });
        department = (Spinner) findViewById(R.id.department);
        usertype = (Spinner) findViewById(R.id.usertype);
        qualification = (Spinner) findViewById(R.id.qualification);
        year = (Spinner) findViewById(R.id.year);
        designation = (EditText) findViewById(R.id.designation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.USERTYPE, android.R.layout.simple_spinner_item);
        usertype.setAdapter(adapter);
        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type="";
                if(i==1)
                {
                    type = adapterView.getItemAtPosition(i).toString();
                    k++;
                    year.setVisibility(View.VISIBLE);
                    ArrayAdapter<CharSequence> yearadapt = ArrayAdapter.createFromResource(register.this, R.array.YEAR, android.R.layout.simple_spinner_item);
                    year.setAdapter(yearadapt);
                    year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            currentYear = adapterView.getItemAtPosition(i).toString();
                            if (i == 0) currentYear ="";
                            else k++;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    qualification.setVisibility(View.GONE);
                    designation.setVisibility(View.GONE);
                    ArrayAdapter<CharSequence> depadapt = ArrayAdapter.createFromResource(register.this, R.array.DEPARTMENTSTUDENT, android.R.layout.simple_spinner_item);
                    department.setAdapter(depadapt);
                    department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            currentDepartment = adapterView.getItemAtPosition(i).toString();
                            if (i == 0)
                                currentDepartment = "";
                                else k++;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if(i==2)
                {
                    type = adapterView.getItemAtPosition(i).toString();
                    k++;
                    year.setVisibility(View.GONE);
                    qualification.setVisibility(View.VISIBLE);
                    ArrayAdapter<CharSequence> qualiadapt = ArrayAdapter.createFromResource(register.this, R.array.QUALIFICATION, android.R.layout.simple_spinner_item);
                    qualification.setAdapter(qualiadapt);
                    qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            currentQuali = adapterView.getItemAtPosition(i).toString();
                            if (i == 0) currentQuali ="";
                            else k++;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    designation.setVisibility(View.VISIBLE);
                    designation.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                        @Override
                        public void afterTextChanged(Editable editable) {
                                if(!designation.getText().toString().equals(""))
                                    k++;

                        }
                    });
                    final ArrayAdapter<CharSequence> depadapt = ArrayAdapter.createFromResource(register.this, R.array.DEPARTMENTSTAFF, android.R.layout.simple_spinner_item);
                    department.setAdapter(depadapt);
                    department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            currentDepartment = adapterView.getItemAtPosition(i).toString();
                            if (i == 0) currentDepartment = "";
                            else k++;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageView=(ImageView)findViewById(R.id.image);
        checkimage=(TextView)findViewById(R.id.checkimage);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Button pic=(Button)findViewById(R.id.uploadpic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });
        }
    }


