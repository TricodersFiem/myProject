package com.example.dhritidhruve.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class internalyear extends Fragment{

    FirebaseFirestore iterate;
    FirebaseUser user;
    ArrayList<SubjectDesign> subjects;
    SubjectAdapter subjectAdapter;

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getView().findViewById(R.id.list1);
        user = FirebaseAuth.getInstance().getCurrentUser();
        iterate = FirebaseFirestore.getInstance();

        subjects = new ArrayList<SubjectDesign>();
        subjectAdapter = new SubjectAdapter(getActivity(), subjects);
        iterate.collection("Person").document("STAFF "+user.getEmail())
                .collection("Subjects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    Log.i("document",document.getId()+" -> "+document.getData());
                                    subjects.add(new SubjectDesign(document.getData().get("department").toString(),document.getData().get("year").toString(),document.getData().get("subjectName").toString(),document.getData().get("subjectCode").toString()));
                                    subjectAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });

        listView.setAdapter(subjectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    giveinternalmarks secondFrag = new giveinternalmarks();
                    String d, y,s, sc;
                    TextView dep =(TextView) view.findViewById(R.id.department);
                    TextView yy =(TextView) view.findViewById(R.id.year);
                    TextView ss =(TextView) view.findViewById(R.id.subjName);
                    TextView cc = (TextView) view.findViewById(R.id.subjCode);
                    d = dep.getText().toString();
                    y = yy.getText().toString();
                    s = ss.getText().toString();
                    sc = cc.getText().toString();
                    Bundle args = new Bundle();
                    args.putString("department",d);
                    args.putString("year",y);
                    args.putString("subjName",s);
                    args.putString("subjCode",sc);
                    secondFrag.setArguments(args);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, secondFrag)
                            .commit();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attendanceyear, container, false);
        getActivity().setTitle("Internal Marks");
        return view;


    }


}



