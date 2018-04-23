package com.example.dhritidhruve.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class checkattendance extends Fragment {

    View view;
    ArrayList<studentAttendanceDesign> studentAttendanceDesign;
    studentAtendanceAdapter studentAtendanceAdapter;
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        FirebaseFirestore db;
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email= user.getEmail();
        ListView listView = (ListView) getView().findViewById(R.id.listview);
        studentAttendanceDesign = new ArrayList<studentAttendanceDesign>();
        studentAtendanceAdapter= new studentAtendanceAdapter(getActivity(), studentAttendanceDesign);
        db = FirebaseFirestore.getInstance();
        db.collection("Person")
                .document("STUDENT " + email).collection("Subjects").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    Log.i("document",document.getId()+" -> "+document.getData());
                                    studentAttendanceDesign.add(new studentAttendanceDesign(document.getData().get("subjectCode").toString(),document.getData().get("test1").toString(),document.getData().get("test2").toString(),document.getData().get("total").toString()));
                                    studentAtendanceAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
        listView.setAdapter(studentAtendanceAdapter);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_checkattendance, container, false);
        getActivity().setTitle("CHECK ATTENDANCE ");
        return view;
    }
}

