package com.example.dhritidhruve.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class yearinternal extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getView().findViewById(R.id.list1);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("CSE1");
        arrayList.add("CSE2");
        arrayList.add("ECE1");
        arrayList.add("ECE2");
        arrayList.add("ME");
        arrayList.add("AEIE");
        arrayList.add("EE");
        arrayList.add("IT");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),giveinternalmarks.class);
                startActivity(intent);
            }
        });
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.yearattendance,container,false);
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}



