package com.example.dhritidhruve.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class SubjectAdd extends Fragment implements ExampleDialog.ExampleDialogListener {

    ArrayList<SubjectDesign> subjects;
    SubjectAdapter subjectAdapter;

    @Override
    public void applyTexts(String transferDepartment, String transferYear, String subjectName, String subjectCode) {
        subjects.add(new SubjectDesign(transferDepartment,transferYear,subjectName,subjectCode));
        subjectAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_add, container, false);
        Button addSubject = (Button) view.findViewById(R.id.addSubject);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        subjects = new ArrayList<SubjectDesign>();
        subjectAdapter = new SubjectAdapter(getActivity(), subjects);
        ListView listView = (ListView) view.findViewById(R.id.subjectAddList);
        listView.setAdapter(subjectAdapter);
        return view;
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getFragmentManager(),"example dialog");
    }

}