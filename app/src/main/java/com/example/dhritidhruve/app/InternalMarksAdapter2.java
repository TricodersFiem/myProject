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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InternalMarksAdapter2 extends ArrayAdapter<InternalMarksDesign> {
    Context context;
    private List<InternalMarksDesign> item = new ArrayList<InternalMarksDesign>();

    InternalMarksAdapter2(Context context, ArrayList<InternalMarksDesign> item){

        super(context, 0, item);
        this.context = context;
        this.item = item;

    }
    @Override
    public void add(InternalMarksDesign object) {
        item.add(object);
        super.add(object);
    }
    public int getCount() {
        return this.item.size();
    }


    static class ViewHolder {
        private TextView name, roll, test1, test2, total;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflator = ((Activity)context).getLayoutInflater();
            convertView = inflator.inflate(R.layout.linear_layout_internal_marks2, parent, false);

            holder = new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.roll = (TextView)convertView.findViewById(R.id.roll);
            holder.test1 = (TextView) convertView.findViewById(R.id.test1);
            holder.test2 = (TextView) convertView.findViewById(R.id.test2);
            holder.total = (TextView) convertView.findViewById(R.id.total);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        InternalMarksDesign person = getItem(position);
        holder.name.setText(person.getName());
        holder.roll.setText(person.getRoll());
        holder.test1.setText(person.getTest1());
        holder.test2.setText(person.getTest2());
        holder.total.setText(person.getTotal());


        //holder.personImageView.setImageBitmap(person.getImage());

        return convertView;
    }
    public InternalMarksDesign getItem(int index) {
        return this.item.get(index);
    }

}

