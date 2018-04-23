package com.example.dhritidhruve.app;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class studentAtendanceAdapter extends ArrayAdapter<studentAttendanceDesign> {
    private Context context;
    private ArrayList<studentAttendanceDesign> item;

    studentAtendanceAdapter(Context context, ArrayList<studentAttendanceDesign> item){
        super(context, 0, item);
        this.context = context;
        this.item = item;
    }
    TextView attendedclasses,totalclasses,percent,subjectCode;
    studentAttendanceDesign attendance;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        listItemView = inflator.inflate(R.layout.linear_layout_studentattendance, parent,false);

        attendance = item.get(position);
        percent = (TextView) listItemView.findViewById(R.id.percent);
        attendedclasses = (TextView) listItemView.findViewById(R.id.classesattended);
        totalclasses = (TextView) listItemView.findViewById(R.id.totalclasses);
        subjectCode = (TextView)listItemView.findViewById(R.id.subjCode);

        percent.setText(attendance.getPercent());
        subjectCode.setText(attendance.getSubjectCode());
        attendedclasses.setText(attendance.getClassesattended());
        totalclasses.setText(attendance.getTotalclasses());

        return listItemView;

    }
}