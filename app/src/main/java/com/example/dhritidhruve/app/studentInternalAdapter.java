package com.example.dhritidhruve.app;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class studentInternalAdapter extends ArrayAdapter<studentInternalDesign> {
    private Context context;
    private ArrayList<studentInternalDesign> item;

    studentInternalAdapter(Context context, ArrayList<studentInternalDesign> item){
        super(context, 0, item);
        this.context = context;
        this.item = item;
    }
    TextView test1View,test2View,totalTextView,subjectCode;
    studentInternalDesign internalMarks;

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        listItemView = inflator.inflate(R.layout.linear_layout_studentinternalmarks, parent,false);

        internalMarks = item.get(position);
        totalTextView = (TextView) listItemView.findViewById(R.id.total);
        test1View = (TextView) listItemView.findViewById(R.id.test1);
        test2View = (TextView) listItemView.findViewById(R.id.test2);
        subjectCode = (TextView)listItemView.findViewById(R.id.subjCode);

        totalTextView.setText(internalMarks.getTotal());
        subjectCode.setText(internalMarks.getSubjectCode());
        test1View.setText(internalMarks.getTest1());
        test2View.setText(internalMarks.getTest2());

        return listItemView;

    }
}