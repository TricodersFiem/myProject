package com.example.dhritidhruve.app;

import android.annotation.SuppressLint;
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

class SubjectAdapter extends ArrayAdapter<SubjectDesign> {
        private Context context;
        private ArrayList<SubjectDesign> item;

        SubjectAdapter(Context context, ArrayList<SubjectDesign> item) {

            super(context, 0, item);
            this.context = context;
            this.item = item;
        }

        TextView department, year, subName, subCode;
        SubjectDesign subjects;


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItemView = convertView;
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            listItemView = inflator.inflate(R.layout.linear_layout_subject, parent, false);

            subjects = item.get(position);
            department = (TextView)listItemView.findViewById(R.id.department);
            year = (TextView)listItemView.findViewById(R.id.year);
            subName = (TextView)listItemView.findViewById(R.id.subjName);
            subCode = (TextView)listItemView.findViewById(R.id.subjCode);

            department.setText(subjects.getDepartment());
            year.setText(subjects.getYear());
            subName.setText(subjects.getSubjectName());
            subCode.setText(subjects.getSubjectCode());



            return listItemView;

        }


}
