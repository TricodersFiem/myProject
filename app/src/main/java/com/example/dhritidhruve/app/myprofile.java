package com.example.dhritidhruve.app;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class myprofile extends Fragment{
    FirebaseFirestore db;
    TextView qualification, designation, name, collegeid, department, year;
    ImageView userpic;
    Student student;
    String email;
    Staff staff;
    StorageReference imageref;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user, container, false);
        getActivity().setTitle("My Profile");
        assert container != null;
        imageref = FirebaseStorage.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        name = (TextView) view.findViewById(R.id.name);
        department = (TextView) view.findViewById(R.id.department);
        designation = (TextView) view.findViewById(R.id.designation);
        qualification = (TextView) view.findViewById(R.id.qualification);
        year = (TextView) view.findViewById(R.id.year);
        collegeid = (TextView) view.findViewById(R.id.collegeId);
        userpic = (ImageView) view.findViewById(R.id.userpic);

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
        return view;
    }
    public void changeTextStudent() {
        designation.setVisibility(View.GONE);
        qualification.setVisibility(View.GONE);
        year.setVisibility(View.VISIBLE);
        name.setText("Name: " + student.getName());
        department.setText("Department: " + student.getDepartment());
        year.setText("Year: " + student.getYear());
        collegeid.setText("College Id: " + student.getCollegeId());
        changeImageByUrl();

    }
    public void changeTextStaff() {
        year.setVisibility(View.GONE);
        designation.setVisibility(View.VISIBLE);
        qualification.setVisibility(View.VISIBLE);
        name.setText("Name: " + staff.getName());
        department.setText("Department: " + staff.getDepartment());
        designation.setText("Designation: "+ staff.getDesignation());
        collegeid.setText("College Id: " + staff.getCollegeId());
        qualification.setText("Qualification: "+ staff.getQualification());
        changeImageByUrl();

    }
    public void changeImageByUrl(){
        imageref.child(email+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity())
                        .load(uri)
                        .into(userpic);

            }
        });
    }



}



