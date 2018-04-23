package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Created by Dhriti Dhruve on 05-04-2018.
 */

public class giveattendance extends Fragment{
    FirebaseFirestore db,db2;
    ArrayList<attendancedesign> attendance;
    attendanceadapter Attendanceadapter;

    String department, year, subjectName, subjectCode;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.giveattendance,container,false);
        getActivity().setTitle("Submit Attendance");
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


        attendance = new ArrayList<attendancedesign>();
        Attendanceadapter = new attendanceadapter(getActivity(), attendance);
        ListView listView = (ListView) view.findViewById(R.id.list_view1);
        listView.setAdapter(Attendanceadapter);
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
                                        Student student = document.toObject(Student.class);
                                        int roll = Integer.parseInt(student.getCollegeId().substring(student.getCollegeId().length()-3));
                                        attendance.add(new attendancedesign(student.getName(), Integer.toString(roll)));
                                        Attendanceadapter.notifyDataSetChanged();

                                    }
                                }
                            }
                        }
                    }
                });
        //attendance.add(new attendancedesign("Abhisekh", "2"));
        //attendance.add(new attendancedesign("Riya", "4"));



        return view;
    }
}