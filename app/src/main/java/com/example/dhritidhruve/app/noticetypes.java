package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class noticetypes extends Fragment {


        View view;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            view= inflater.inflate(R.layout.noticetypes,container,false);
            return view;

        }
}

