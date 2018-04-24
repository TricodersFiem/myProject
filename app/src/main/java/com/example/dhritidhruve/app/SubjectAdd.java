package com.example.dhritidhruve.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SubjectAdd extends Fragment implements ExampleDialog.ExampleDialogListener {

    ArrayList<SubjectDesign> subjects;
    SubjectAdapter subjectAdapter;
    FirebaseFirestore db,db2,dbStaff,iterate;
    FirebaseUser user;
    @Override
    public void applyTexts(String transferDepartment, String transferYear, String subjectName, final String subjectCode) {



        Map<String,String> subjectsStaff = new HashMap<>();
        subjectsStaff.put("department",transferDepartment);
        subjectsStaff.put("subjectCode",subjectCode);
        subjectsStaff.put("subjectName",subjectName);
        subjectsStaff.put("year",transferYear);
        dbStaff = FirebaseFirestore.getInstance();
        dbStaff.collection("Person").document("STAFF "+user.getEmail()).collection("Subjects").document(subjectCode).set(subjectsStaff);

        final Map<String,Object> myData = new HashMap<>();

        final Map<String,Object> subjectsStudent = new HashMap<>();

        subjectsStudent.put("classAttended",10);
        subjectsStudent.put("email",user.getEmail());
        subjectsStudent.put("internalMarks1",0);
        subjectsStudent.put("internalMarks2",0);
        subjectsStudent.put("subjectName",subjectName);
        subjectsStudent.put("subjectCode",subjectCode);
        subjectsStudent.put("totalClass",5);

        myData.put(subjectCode,subjectsStudent);
        //Add the collection Subjects with the subject Code document id to the students
        db.collection("Person")
                .whereEqualTo("department",transferDepartment)
                .whereEqualTo("year",transferYear)
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
                                        //Log.d("msg",student.getPhotoId());
                                        db2 = FirebaseFirestore.getInstance();
                                        db2.collection("Person").document(document.getId())
                                                .set(myData, SetOptions.merge());
                                                /*
                                                .collection("Subjects").document(subjectCode)
                                                .set(subjectsStudent);*/
                                    }
                                }
                            }
                        }
                    }
                });

         subjects.add(new SubjectDesign(transferDepartment,transferYear,subjectName,subjectCode));
        subjectAdapter.notifyDataSetChanged();

    }
    

    private Button addSubject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_add, container, false);
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        iterate = FirebaseFirestore.getInstance();
        getActivity().setTitle("Add Subject");

        addSubject = (Button)view.findViewById(R.id.addSubject);
        subjects = new ArrayList<SubjectDesign>();
        subjectAdapter = new SubjectAdapter(getActivity(), subjects);
        ListView listView = (ListView) view.findViewById(R.id.subjectAddList);

        iterate.collection("Person").document("STAFF "+user.getEmail())
                .collection("Subjects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    Log.i("document",document.getId()+" -> "+document.getData());
                                    subjects.add(new SubjectDesign(document.getData().get("department").toString(),document.getData().get("year").toString(),document.getData().get("subjectName").toString(),document.getData().get("subjectCode").toString()));
                                    subjectAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                }
        });
        listView.setAdapter(subjectAdapter);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        return view;
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getFragmentManager(),"example dialog");
        exampleDialog.setTargetFragment(this, 0);
    }

}