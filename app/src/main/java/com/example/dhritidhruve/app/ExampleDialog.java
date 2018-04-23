package com.example.dhritidhruve.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
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

    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        subjectCode = (EditText)view.findViewById(R.id.subjCode);
        subjectName= (EditText)view.findViewById(R.id.subjName);
        department = (Spinner) view.findViewById(R.id.department);
        year = (Spinner) view.findViewById(R.id.year);

        try {
            listener = (ExampleDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString()+"must implement ExampleDialogListener");
        }

        builder.setView(view)
                .setTitle("Subject Details")
                .setPositiveButton("Add Subject", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String subjName,subjCode;
                        subjName=subjectName.getText().toString();
                        subjCode = subjectCode.getText().toString();
                        Log.i("name ",subjName);
                        Log.i("code: ",subjCode);
                        ((ExampleDialogListener)getTargetFragment()).applyTexts(currentDepartment, currentYear, subjName,subjCode);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        //Set the spinner for department
        ArrayAdapter depadapt = ArrayAdapter.createFromResource(getActivity(), R.array.DEPARTMENTSTUDENT, android.R.layout.simple_spinner_item);
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


    public interface ExampleDialogListener{
        void applyTexts(String transferDepartment,String transferYear, String subjectName, String subjectCode);
    }


}
