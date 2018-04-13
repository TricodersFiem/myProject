package com.example.dhritidhruve.app;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.util.ArrayList;
public class attendanceadapter extends ArrayAdapter<attendancedesign> {
    private Context context;
    private ArrayList<attendancedesign> item;

    attendanceadapter(Context context, ArrayList<attendancedesign> item) {

        super(context, 0, item);
        this.context = context;
        this.item = item;
    }
    public static class ViewHolder {
        CheckBox check;
        TextView roll, name,percent;
        attendancedesign attendance;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
         ViewHolder holder = new ViewHolder();
        LayoutInflater inflator = ((Activity) context).getLayoutInflater();
        listItemView = inflator.inflate(R.layout.linear_layout_attendance, parent, false);

        holder.attendance = item.get(position);
        holder.name = (TextView) listItemView.findViewById(R.id.name);
        holder.roll = (TextView) listItemView.findViewById(R.id.roll);
        holder.check = (CheckBox) listItemView.findViewById(R.id.preabs);
        holder.percent = (TextView) listItemView.findViewById(R.id.percent);
        addListenerOncheck(holder,holder.check);

        listItemView.setTag(holder);

        setupItem(holder);

        return listItemView;

    }

    private void setupItem(ViewHolder holder) {

       // if(holder.attendance.getAttendance())
         //   holder.check.setText("Present");
        holder.percent.setText(String.valueOf(holder.attendance.getPercent()));
        holder.name.setText(holder.attendance.getName());
        holder.roll.setText(holder.attendance.getRoll());

    }
    private void addListenerOncheck(final ViewHolder holder, CheckBox check){
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()) {
                    holder.attendance.setAttendance(true);
                    holder.check.setText("Present");
                } else {
                    holder.attendance.setAttendance(false);
                    holder.check.setText("Absent");
                }

            }
        });

        }
}
