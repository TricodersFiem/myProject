package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dhriti Dhruve on 05-04-2018.
 */

public class giveinternalmarks extends Fragment {
    FirebaseFirestore db,db2;
    ArrayList<InternalMarksDesign> internalMarks;
    InternalMarksAdapter2 marksAdapter;

    String department, year, subjectName, subjectCode,name;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.giveinternalmarks, container, false);
        getActivity().setTitle("Submit Internal Marks");
        db = FirebaseFirestore.getInstance();


        Bundle args = getArguments();
        if(args!=null) {
            department = args.getString("department");
            year = args.getString("year");
            subjectName = args.getString("subjName");
            subjectCode = args.getString("subjCode");
            Log.i("department",department);
            Log.i("year",year);
            Log.i("subjName",subjectName);
            Log.i("subjCode",subjectCode);
        }
        internalMarks = new ArrayList<InternalMarksDesign>();
        marksAdapter = new InternalMarksAdapter2(getActivity(), internalMarks);
        ListView listView = (ListView) view.findViewById(R.id.list_view1);
        listView.setAdapter(marksAdapter);
        //Add the collection Subjects with the subject Code document id to the students
        db.collection("Person")
                .whereEqualTo("department",department)
                .whereEqualTo("year",year)
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
                                        //Student student = document.toObject(Student.class);
                                        //name = student.getName();
                                        String r = document.getData().get("collegeId").toString();
                                        int roll = Integer.parseInt(r.substring(r.length()-3));
                                        //Log.i("myDoc","->"+document.getData());
                                        Map<String,Object> values =  (Map<String,Object>)document.getData().get(subjectCode);
                                        Log.i("val1",values.get("email").toString());
                                        internalMarks.add(new InternalMarksDesign(document.getData().get("name").toString(),Integer.toString(roll),subjectCode,values.get("internalMarks1").toString(),values.get("internalMarks2").toString()));
                                        marksAdapter.notifyDataSetChanged();

                                    }
                                }
                            }
                        }
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String beforeName = String.valueOf(marksAdapter.getItem(i).getTest1());

                String changedName = "15";
                marksAdapter.getItem(i).setTest1(changedName);
                marksAdapter.notifyDataSetChanged();

            }
        });

        return view;
    }

}