package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Dhriti Dhruve on 05-04-2018.
 */

public class giveinternalmarks extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.giveinternalmarks, container, false);

        ArrayList<InternalMarksDesign> internalMarks = new ArrayList<InternalMarksDesign>();
        internalMarks.add(new InternalMarksDesign("Abhisekh", "2"));
        internalMarks.add(new InternalMarksDesign("Riya", "4"));
        InternalMarksAdapter marksAdapter = new InternalMarksAdapter(getActivity(), internalMarks);


       ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(marksAdapter);
        return view;
        }

}

