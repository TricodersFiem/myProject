package com.example.dhritidhruve.app;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

/**
 * Created by Dhriti Dhruve on 05-04-2018.
 */

public class timetable extends Fragment{
    FirebaseFirestore db;
    String email,year;
    FirebaseUser user;
    StorageReference imageref;
    ImageView timetablepic;
    Student student;
    Staff staff;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageref = FirebaseStorage.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
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
                                        student = document.toObject(Student.class);
                                        year = student.getYear();
                                        uploadTimetableStudent();

                                    } else {
                                        staff = document.toObject(Staff.class);
                                        uploadTimetableStaff();
                                    }
                                }
                            }


                        } else {
                            Log.d("Tag", "failed");
                        }
                    }
                });
    }
    public void uploadTimetableStudent(){
        timetablepic = (ImageView) getView().findViewById(R.id.timetablepic);

        imageref.child(year+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity())
                        .load(uri)
                        .into(timetablepic);

            }
        });
        timetablepic.setVisibility(View.VISIBLE);
        }
        public void uploadTimetableStaff(){
        timetablepic = (ImageView) getView().findViewById(R.id.timetablepic);

        imageref.child(year +".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity())
                        .load(uri)
                        .into(timetablepic);

            }
        });
        timetablepic.setVisibility(View.VISIBLE);
    }
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.timetable,container,false);
        return view;
    }
}
