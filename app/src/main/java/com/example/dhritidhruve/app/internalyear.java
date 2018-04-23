package com.example.dhritidhruve.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class internalyear extends Fragment{


    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getView().findViewById(R.id.list1);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("CSE1 1ST M401 Mathematics");
        arrayList.add("CSE2 4TH M401 Mathematics");
        arrayList.add("CSE1 3RD M401 Mathematics");
        arrayList.add("CSE2 2ND M401 Mathematics");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contenedor,new giveinternalmarks())
                            .commit();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attendanceyear, container, false);
        getActivity().setTitle("GIVE INTERNAL MARKS TO");
        return view;


    }


}



