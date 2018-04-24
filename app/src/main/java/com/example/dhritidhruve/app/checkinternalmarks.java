package com.example.dhritidhruve.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class checkinternalmarks extends Fragment {

    View view;
    ArrayList<studentInternalDesign> StudentInternal;
    studentInternalAdapter studentAdapter;
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        FirebaseFirestore db;
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email= user.getEmail();
        ListView listView = (ListView) getView().findViewById(R.id.listview);
        StudentInternal = new ArrayList<studentInternalDesign>();
        studentAdapter = new studentInternalAdapter(getActivity(), StudentInternal);
        db = FirebaseFirestore.getInstance();
            db.collection("Person")
                    .document("STUDENT " + email).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Map<String,Object> subjects =(Map<String, Object>) document.getData().get("Subjects");
                                    for(Map.Entry<String,Object> subj: subjects.entrySet()){
                                        Map<String,Object> values = (Map<String,Object>)subj.getValue();
                                        StudentInternal.add(new studentInternalDesign(values.get("subjectCode").toString(),values.get("internalMarks1").toString(),values.get("internalMarks2").toString()));
                                        studentAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    });

        listView.setAdapter(studentAdapter);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_checkinternalmarks, container, false);
        getActivity().setTitle("Check Internal Marks");
        return view;
    }
}

