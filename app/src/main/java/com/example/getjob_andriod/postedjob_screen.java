package com.example.getjob_andriod;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class postedjob_screen extends AppCompatActivity {
    private RecyclerView postedjobRV;
    private ArrayList<postjobhelper> postjobArrayList = null;
    private postedjobRVadepter postedjobRVadepter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);

        postedjobRV = (RecyclerView)findViewById(R.id.idRVCourses);
        loadingPB = findViewById(R.id.idProgressBar);

        db = FirebaseFirestore.getInstance();

        postjobArrayList = new ArrayList<>();
        postedjobRV.setHasFixedSize(true);
        postedjobRV.setLayoutManager(new LinearLayoutManager(this));

        // adding our array list to our recycler view adapter class.
        postedjobRVadepter = new postedjobRVadepter(postjobArrayList, this);

        // setting adapter to our recycler view.
        postedjobRV.setAdapter(postedjobRVadepter);

       db.collection("PostJob").get().addOnSuccessListener(queryDocumentSnapshots -> {

           if (!queryDocumentSnapshots.isEmpty()) {
               loadingPB.setVisibility(View.GONE);
               List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
               for (DocumentSnapshot d : list) {
                   // after getting this list we are passing
                   // that list to our object class.
                   postjobhelper p = d.toObject(postjobhelper.class);

                   // and we will pass this object class
                   // inside our arraylist which we have
                   // created for recycler view.

                   postjobArrayList.add(p);
               }
               postedjobRVadepter.notifyDataSetChanged();
           } else {
               // if the snapshot is empty we are displaying a toast message.
               Toast.makeText(postedjob_screen.this, "No jobs found", Toast.LENGTH_SHORT).show();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(postedjob_screen.this, "Fail to get the data of job.", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
