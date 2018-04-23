package com.example.dhritidhruve.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Academic extends Fragment {

   // StorageReference imageref;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView event =(ImageView)view.findViewById(R.id.event);
     /*   imageref = FirebaseStorage.getInstance().getReference();
        imageref.child("academic.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity())
                        .load(uri)
                        .into(event);

            }
        });*/
     event.setImageResource(R.drawable.academic);
    }
        View view;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            view= inflater.inflate(R.layout.noticetypes,container,false);
            return view;

        }
}


