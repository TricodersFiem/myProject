package com.example.dhritidhruve.app;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InternalMarksAdapter extends ArrayAdapter<InternalMarksDesign> {
    private Context context;
    private ArrayList<InternalMarksDesign> item;
    FirebaseFirestore db;

    InternalMarksAdapter(Context context, ArrayList<InternalMarksDesign> item){

        super(context, 0, item);
        this.context = context;
        this.item = item;

    }

    public static class ViewHolder{
        EditText test1View,test2View;
        TextView rollTextView, nameTextView, totalTextView;
        Button enter;
        InternalMarksDesign internalMarks;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        final ViewHolder holder = new ViewHolder();
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        listItemView = inflator.inflate(R.layout.linear_layout_internal_marks, parent,false);

        holder.internalMarks = item.get(position);
        holder.nameTextView = (TextView) listItemView.findViewById(R.id.name);
        holder.rollTextView = (TextView) listItemView.findViewById(R.id.roll);
        holder.totalTextView = (TextView) listItemView.findViewById(R.id.total);
        holder.test1View = (EditText) listItemView.findViewById(R.id.test1);
        holder.test2View = (EditText) listItemView.findViewById(R.id.test2);
        holder.enter = (Button)listItemView.findViewById(R.id.submit);

        holder.enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val1,val2;
                val1 = Integer.parseInt(holder.test1View.getText().toString());
                val2 = Integer.parseInt(holder.test1View.getText().toString());
                holder.totalTextView.setText(String.valueOf(val1+val2));
            }
        });



        listItemView.setTag(holder);

        setupItem(holder);

        return listItemView;

    }

    private void setupItem(ViewHolder holder){
        holder.test1View.setText(String.valueOf(holder.internalMarks.getTest1()));
        holder.test2View.setText(String.valueOf(holder.internalMarks.getTest2()));
        holder.totalTextView.setText(String.valueOf(holder.internalMarks.getTotal()));
        holder.nameTextView.setText(holder.internalMarks.getName());
        holder.rollTextView.setText(holder.internalMarks.getRoll());
    }



}