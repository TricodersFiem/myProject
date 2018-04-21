package com.example.dhritidhruve.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;


public class user extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseFirestore db;
    TextView qualification, designation, name, collegeid, department, year, useremail,username;
    ImageView userpic;
    Student student;
    String email;
    Staff staff;
    //Create an instance of storage reference
    StorageReference imageref;
    FirebaseUser user;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageref = FirebaseStorage.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        name = (TextView) findViewById(R.id.name);
        department = (TextView) findViewById(R.id.department);
        designation = (TextView) findViewById(R.id.designation);
        qualification = (TextView) findViewById(R.id.qualification);
        year = (TextView) findViewById(R.id.year);
        collegeid = (TextView) findViewById(R.id.collegeId);
        userpic = (ImageView) findViewById(R.id.userpic);
        useremail=(TextView)findViewById(R.id.usernamenav);
        username=(TextView)findViewById(R.id.usernamenav);

        db = FirebaseFirestore.getInstance();
        db.collection("Person")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    if (document.getId().equals("STUDENT")) {
                                        Log.d("MyTag", "passed");
                                        student = document.toObject(Student.class);
                                        //Log.d("msg",student.getPhotoId());
                                        changeTextStudent();
                                    } else {
                                        staff = document.toObject(Staff.class);
                                        changeTextStaff();
                                    }
                                }
                            }


                        } else {
                            Log.d("Tag", "failed");
                        }
                    }
                });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void changeTextStudent() {
        designation.setVisibility(View.GONE);
        qualification.setVisibility(View.GONE);
        name.setText("Name: " + student.getName());
        useremail.setText(student.getEmail());
        username.setText(student.getName());
        department.setText("Department: " + student.getDepartment());
        year.setText("Year: " + student.getYear());
        collegeid.setText("College Id: " + student.getCollegeId());

        changeImageByUrl();

    }
    public void changeTextStaff() {
        name.setText("Name: " + staff.getName());
        department.setText("Department: " + staff.getDepartment());
        designation.setText("Designation: "+ staff.getDesignation());
        collegeid.setText("College Id: " + staff.getCollegeId());
        qualification.setText("Qualification: "+ staff.getQualification());
        year.setVisibility(View.GONE);
        changeImageByUrl();

    }
    public void changeImageByUrl(){
        imageref.child(email+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(userpic);

            }
        });
        }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        ((ConstraintLayout)findViewById(R.id.remove)).removeAllViews();
        if (id == R.id.nav_timetable) {
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new timetable())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_attendance) {
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new attendanceyear())
                    .commit();

        } else if (id == R.id.nav_internalmarks) {

            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new internalyear())
                    .commit();

        } else if (id == R.id.nav_notice) {
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new notice())
                    .commit();

        }
        else if(id==R.id.nav_myprofile){
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new myprofile())
                    .commit();
        }
        else if (id == R.id.nav_signout) {

            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
