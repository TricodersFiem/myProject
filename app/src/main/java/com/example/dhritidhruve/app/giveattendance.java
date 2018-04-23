package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Dhriti Dhruve on 05-04-2018.
 */

public class giveattendance extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.giveattendance,container,false);
        getActivity().setTitle("SUBMIT ATTENDANCE");
        ArrayList<attendancedesign> attendance = new ArrayList<attendancedesign>();
        attendance.add(new attendancedesign("Abhisekh", "2"));
        attendance.add(new attendancedesign("Riya", "4"));
        attendanceadapter Attendanceadapter = new attendanceadapter(getActivity(), attendance);


        ListView listView = (ListView) view.findViewById(R.id.list_view1);
        listView.setAdapter(Attendanceadapter);
        return view;
    }
}