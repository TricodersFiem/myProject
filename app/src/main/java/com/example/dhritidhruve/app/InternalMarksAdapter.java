package com.example.dhritidhruve.app;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InternalMarksAdapter extends ArrayAdapter<InternalMarksDesign> {
    Context context;
    ArrayList<InternalMarksDesign> item;


    public InternalMarksAdapter(thirdfragment context, ArrayList<InternalMarksDesign> item){

        super(context, 0, item);
        this.context = context;
        this.item = item;

    }

    public static class ViewHolder{
        EditText test1View,test2View;
        TextView rollTextView, nameTextView, totalTextView;
        InternalMarksDesign internalMarks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        ViewHolder holder = new ViewHolder();
        LayoutInflater inflator = ((Activity)context).getLayoutInflater();
        listItemView = inflator.inflate(R.layout.linear_layout_internal_marks, parent,false);

        holder.internalMarks = item.get(position);
        holder.nameTextView = (TextView) listItemView.findViewById(R.id.name);
        holder.rollTextView = (TextView) listItemView.findViewById(R.id.roll);
        holder.totalTextView = (TextView) listItemView.findViewById(R.id.total);
        holder.test1View = (EditText) listItemView.findViewById(R.id.test1);
        holder.test2View = (EditText) listItemView.findViewById(R.id.test2);

        setTest1ViewTextChangeListener(holder);
        setTest2ViewTextChangeListener(holder);

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

    private void setTest1ViewTextChangeListener(final ViewHolder holder){
        holder.test1View.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>0)
                    holder.internalMarks.setTest1(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*if (s.toString().length() > 0) {
                    holder.internalMarks.setTest1(Integer.parseInt(s.toString()));
                    int val1 = Integer.parseInt(holder.test1View.getText().toString());
                    int val2 = Integer.parseInt(holder.test2View.getText().toString());
                    holder.internalMarks.setTotal(val1 + val2);
                    holder.totalTextView.setText(String.valueOf(val1 + val2));
                }*/
            }
        });
    }

    private void setTest2ViewTextChangeListener(final ViewHolder holder){
        holder.test2View.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>0)
                    holder.internalMarks.setTest2(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    holder.internalMarks.setTest2(Integer.parseInt(s.toString()));
                    int val1 = Integer.parseInt(holder.test1View.getText().toString());
                    int val2 = Integer.parseInt(holder.test2View.getText().toString());
                    holder.internalMarks.setTotal(val1 + val2);
                    holder.totalTextView.setText(String.valueOf(val1 + val2));
                }
            }
        });
    }

}