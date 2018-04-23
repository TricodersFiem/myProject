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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class user extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseFirestore db;
    TextView qualification, designation, name, collegeid, department, year;
    ImageView userpic;
    Student student;
    String email;
    Staff staff;
    StorageReference imageref;
    FirebaseUser user;
    String type="student";
    NavigationView  navigationView;
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
                                    String docs = document.getId();
                                    String temp = docs.split(" ")[0];
                                    if (temp.equals("STUDENT")) {
                                        Log.d("MyTag", "passed");
                                        student = document.toObject(Student.class);
                                        type = "student";
                                        //Log.d("msg",student.getPhotoId());
                                        changeTextStudent();
                                    } else {
                                        staff = document.toObject(Staff.class);
                                        type = "staff";
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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
   public void hideItem(){
        if(type.equals("student")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_user_drawer1);
        }
        else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_user_drawer);
        }
       navigationView.setNavigationItemSelectedListener(this);

    }

    public void changeTextStudent() {
        designation.setVisibility(View.GONE);
        qualification.setVisibility(View.GONE);
        year.setVisibility(View.VISIBLE);
        name.setText("Name: " + student.getName());
        department.setText("Department: " + student.getDepartment());
        year.setText("Year: " + student.getYear());
        collegeid.setText("College Id: " + student.getCollegeId());
        hideItem();
        changeImageByUrl();

    }

    public void changeTextStaff() {
        year.setVisibility(View.GONE);
        designation.setVisibility(View.VISIBLE);
        qualification.setVisibility(View.VISIBLE);
        name.setText("Name: " + staff.getName());
        department.setText("Department: " + staff.getDepartment());
        designation.setText("Designation: " + staff.getDesignation());
        collegeid.setText("College Id: " + staff.getCollegeId());
        qualification.setText("Qualification: " + staff.getQualification());
        hideItem();
        changeImageByUrl();
    }

    public void changeImageByUrl() {
        imageref.child(email + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        ((ConstraintLayout)findViewById(R.id.ll)).removeAllViews();

        if (id == R.id.nav_timetable) {
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new timetable())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_attendance) {
            if(type.equals("staff")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new attendanceyear())
                        .commit();
            }
            else{
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new checkattendance())
                        .commit();
            }

        } else if (id == R.id.nav_internalmarks) {
            if(type.equals("staff")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new internalyear())
                        .commit();
            }
            else{
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new checkinternalmarks())
                        .commit();
            }

        } else if (id == R.id.nav_notice) {
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new notice())
                    .commit();

        }
         if(id==R.id.nav_myprofile){
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
        else if(id==R.id.nav_subject){
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor,new SubjectAdd())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
