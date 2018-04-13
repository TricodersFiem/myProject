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

    public static class attendancedesign{

        private String name, roll;
        private int test1, test2;

        public attendancedesign(String Name, String Roll, int Test1, int Test2)
        {
            name = Name;
            roll = Roll;
            test1 = Test1;
            test2 = Test2;
        }

        public attendancedesign(String Name, String Roll)
        {
            name = Name;
            roll = Roll;
            test1 = 0;
            test2 = 0;
        }

        public String getName(){ return name;}

        public String getRoll(){ return roll;}

        public void setTest1(int data){
            test1 = data;
        }

        public void setTest2(int data){
            test2 = data;
        }
        public void setTotal(int data){
            test2 = data;
        }

        public int getTest1(){ return test1;}

        public int getTotal(){ return test1+test2;}

        public int getTest2(){ return test2;}
    }
}



