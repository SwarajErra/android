package com.example.getjob_andriod;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdatedPostjob extends AppCompatActivity {

    // creating variables for our edit text
    private EditText CompanyLocationEdt,companyNameEdt,jobDescriptionEdt,payRateEdt,jobTypeEdt,shiftEdt,reqquaEdt;

    // creating a strings for storing our values from Edittext fields.
    String CompanyLocation,companyName,jobDescription,payRate,jobType,shift,reqqua;
    // creating a variable for firebasefirestore.
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_postjob);
        postjobhelper courses = (postjobhelper) getIntent().getSerializableExtra("PostJob");

        // getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();
        CompanyLocationEdt=findViewById(R.id.idEdtcompanyLocation);
        companyNameEdt=findViewById(R.id.idEdtcompanyName);
        jobDescriptionEdt=findViewById(R.id.idEdtjobDescription);
        jobTypeEdt=findViewById(R.id.idEdtjobType);
        payRateEdt=findViewById(R.id.idEdtpayRate);
        reqquaEdt=findViewById(R.id.idEdtreqQua);
        shiftEdt=findViewById(R.id.idEdtshift);


        // creating variable for button
        Button updateCOurseBtn = findViewById(R.id.idBtnUpdateCourse);
        Button deleteBtn = findViewById(R.id.idBtnDeleteCourse);
        CompanyLocationEdt.setText(courses.getCompanyLocation());
        companyNameEdt.setText(courses.getCompanyName());
        jobDescriptionEdt.setText(courses.getjobDescription());
        jobTypeEdt.setText(courses.getjobType());
       payRateEdt.setText(courses.getpayRate());
        reqquaEdt.setText(courses.getReqqua());
        shiftEdt.setText(courses.getShift());

        // adding on click listener for delete button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to delete the course.
                deleteCourse(courses);
            }
        });



        updateCOurseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyLocation = CompanyLocationEdt.getText().toString();
                companyName = companyNameEdt.getText().toString();
                jobDescription = jobDescriptionEdt.getText().toString();
                jobType = jobTypeEdt.getText().toString();
                payRate = payRateEdt.getText().toString();
                reqqua = reqquaEdt.getText().toString();
                shift = shiftEdt.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(companyName)) {
                    companyNameEdt.setError("Please enter Job Title");
                } else if (TextUtils.isEmpty(jobDescription)) {
                    jobDescriptionEdt.setError("Please enter Job Description");
                } else if (TextUtils.isEmpty(CompanyLocation)) {
                    CompanyLocationEdt.setError("Please enter location");
                } else if (TextUtils.isEmpty(payRate)) {
                    payRateEdt.setError("Please enter payrate");
                }else if (TextUtils.isEmpty(jobType)) {
                    jobTypeEdt.setError("Please enter Job Type");
                }else if (TextUtils.isEmpty(shift)) {
                    shiftEdt.setError("Please enter Job pay rate");
                }else if (TextUtils.isEmpty(reqqua)) {
                    reqquaEdt.setError("Please required qualifications");
                }
                else {
                    // calling a method to update our course.
                     updatePostJob(courses,CompanyLocation,companyName,jobDescription,payRate,jobType,shift,reqqua);
                }


            }
        });
    }

    private void updatePostJob(postjobhelper courses, String CompanyLocation, String companyName, String jobDescription, String payRate, String jobType, String shift, String reqqua) {
        // inside this method we are passing our updated values
        // inside our object class and later on we
        // will pass our whole object to firebase Firestore.
        postjobhelper updatedCourse = new postjobhelper(companyName,jobDescription,CompanyLocation,payRate,jobType,shift,reqqua);

        // after passing data to object class we are
        // sending it to firebase with specific document id.
        // below line is use to get the collection of our Firebase Firestore.
        db.collection("PostJob").
                // below line is use toset the id of
                // document where we have to perform
                // update operation.
                        document(courses.getId()).

                // after setting our document id we are
                // passing our whole object class to it.
                        set(updatedCourse).

                // after passing our object class we are
                // calling a method for on success listener.
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // on successful completion of this process
                        // we are displaying the toast message.
                        Toast.makeText(UpdatedPostjob.this, "Course has been updated..", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdatedPostjob.this, postedjob_screen.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            // inside on failure method we are
            // displaying a failure message.
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdatedPostjob.this, "Fail to update the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCourse(postjobhelper courses) {
        // below line is for getting the collection
        // where we are storing our courses.
        db.collection("PostJob").
                // after that we are getting the document
                // which we have to delete.
                        document(courses.getId()).

                // after passing the document id we are calling
                // delete method to delete this document.
                        delete().
                // after deleting call on complete listener
                // method to delete this data.
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // inside on complete method we are checking
                        // if the task is success or not.
                        if (task.isSuccessful()) {
                            // this method is called when the task is success
                            // after deleting we are starting our MainActivity.
                            Toast.makeText(UpdatedPostjob.this, "Course has been deleted from Database.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UpdatedPostjob.this, postedjob_screen.class);
                            startActivity(i);
                        } else {
                            // if the delete operation is failed
                            // we are displaying a toast message.
                            Toast.makeText(UpdatedPostjob.this, "Fail to delete the course. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}