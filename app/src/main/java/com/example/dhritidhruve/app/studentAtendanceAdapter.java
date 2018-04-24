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
    private ArrayList<studentAttendanceDesign> item = new ArrayList<studentAttendanceDesign>();

    studentAtendanceAdapter(Context context, ArrayList<studentAttendanceDesign> item){
        super(context, 0, item);
        this.context = context;
        this.item = item;
    }

    @Override
    public void add(studentAttendanceDesign object) {
        item.add(object);
        super.add(object);
    }

    public int getCount() {
        return this.item.size();
    }

    static class ViewHolder {
        TextView attendedclasses, totalclasses, percent, subjectCode;
    }
    studentAttendanceDesign attendance;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        convertView = inflator.inflate(R.layout.linear_layout_studentattendance, parent,false);
            holder = new ViewHolder();


        holder.percent = (TextView) convertView.findViewById(R.id.percent);
        holder.attendedclasses = (TextView) convertView.findViewById(R.id.classesattended);
        holder.totalclasses = (TextView) convertView.findViewById(R.id.totalclasses);
        holder.subjectCode = (TextView)convertView.findViewById(R.id.subjectCode);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        attendance = item.get(position);
        holder.percent.setText(attendance.getPercent());
        holder.subjectCode.setText(attendance.getSubjectCode());
        holder.attendedclasses.setText(attendance.getClassesattended());
        holder.totalclasses.setText(attendance.getTotalclasses());

        return convertView;

    }
}