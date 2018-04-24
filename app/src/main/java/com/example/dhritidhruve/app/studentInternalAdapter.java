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
    private ArrayList<studentInternalDesign> item = new ArrayList<studentInternalDesign>();

    studentInternalAdapter(Context context, ArrayList<studentInternalDesign> item){
        super(context, 0, item);
        this.context = context;
        this.item = item;
    }
    TextView test1View,test2View,totalTextView,subjectCode;
    studentInternalDesign internalMarks;

    @Override
    public void add(studentInternalDesign object) {
        item.add(object);
        super.add(object);
    }

    public int getCount() {
        return this.item.size();
    }

    static class ViewHolder {
        private TextView totalTextView,test1View,test2View,subjectCode;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        //Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        convertView = inflator.inflate(R.layout.linear_layout_studentinternalmarks, parent,false);
        holder = new ViewHolder();

        holder.totalTextView = (TextView) convertView.findViewById(R.id.total);
        holder.test1View = (TextView) convertView.findViewById(R.id.test1);
        holder.test2View = (TextView) convertView.findViewById(R.id.test2);
        holder.subjectCode = (TextView)convertView.findViewById(R.id.subjectCode);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        studentInternalDesign person = getItem(position);
            internalMarks = item.get(position);

        /*
        holder.totalTextView.setText("25");
        holder.subjectCode.setText("M101");
        holder.test1View.setText("1");
        holder.test2View.setText("2");
        */

        holder.totalTextView.setText(internalMarks.getTotal());
        holder.subjectCode.setText(internalMarks.getSubjectCode());
        holder.test1View.setText(internalMarks.getTest1());
        holder.test2View.setText(internalMarks.getTest2());

        return convertView;

    }
}