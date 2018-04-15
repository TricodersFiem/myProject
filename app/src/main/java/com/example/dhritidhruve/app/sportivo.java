package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class sportivo extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getView().findViewById(R.id.list1);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("dhriti");
        arrayList.add("charvi");
        arrayList.add("anu");
        arrayList.add("golu");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("person tapped", arrayList.get(i));
            }
        });
    }

        View view;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            view= inflater.inflate(R.layout.sportivo,container,false);
            return view;

        }
}



