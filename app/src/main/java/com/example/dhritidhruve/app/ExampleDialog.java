package com.example.dhritidhruve.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText subjectName, subjectCode;
    private Spinner department, year;
    String currentDepartment,currentYear;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle("Subject Details")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        subjectCode = (EditText)view.findViewById(R.id.subjName);
        subjectName= (EditText)view.findViewById(R.id.subjCode);
        department = (Spinner) view.findViewById(R.id.department);
        year = (Spinner) view.findViewById(R.id.year);
        //Set the spinner for department
        ArrayAdapter depadapt = ArrayAdapter.createFromResource(getActivity(), R.array.DEPARTMENT, android.R.layout.simple_spinner_item);
        department.setAdapter(depadapt);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentDepartment=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for year
        ArrayAdapter yearadapt = ArrayAdapter.createFromResource(getActivity(), R.array.YEAR, android.R.layout.simple_spinner_item);
        year.setAdapter(yearadapt);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentYear=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return builder.create();
    }
}
