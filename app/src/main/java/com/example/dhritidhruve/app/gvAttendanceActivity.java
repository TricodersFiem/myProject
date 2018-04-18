package com.example.dhritidhruve.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class gvAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giveattendance);
        ArrayList<attendancedesign> attendance = new ArrayList<attendancedesign>();
        attendance.add(new attendancedesign("Abhisekh", "2"));
        attendance.add(new attendancedesign("Riya", "4"));
        attendanceadapter Attendanceadapter = new attendanceadapter(getApplicationContext(), attendance);


        ListView listView = (ListView) findViewById(R.id.list_view1);
        listView.setAdapter(Attendanceadapter);
    }
}
