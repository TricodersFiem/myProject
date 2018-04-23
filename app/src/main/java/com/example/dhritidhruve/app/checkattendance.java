package com.example.dhritidhruve.app;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class checkattendance extends Fragment {

    View view;
    FirebaseFirestore db;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String email= user.getEmail();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_checkattendance, container, false);
        getActivity().setTitle("CHECK INTERNAL MARKS");
        db = FirebaseFirestore.getInstance();
        int i;
          /*  db.collection("Person")
                    .document("STUDENT " + email).collection("Subjects").whereEqualTo("fgf",efe);
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                }
                            }
                        }
                    });
        */
        return view;
    }
}
