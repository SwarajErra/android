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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class postJobActivity extends AppCompatActivity {
    Button postjob,postnewjob;
    EditText Regjobtitle,Regjobdis,Reglocation,Regpayrate,Regjobtype,Regshift,Regreqqua;
    String companyName,jobDescription,CompanyLocation,payRate,jobType,shift,reqqua;
    boolean pressAttention,reportJob;



    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postjob);
        db=FirebaseFirestore.getInstance();
        Regjobtitle=findViewById(R.id.jobtitle);
        Regjobdis=findViewById(R.id.jobdis);
        Reglocation=findViewById(R.id.location);
        Regpayrate=findViewById(R.id.payrate);
        Regjobtype=findViewById(R.id.jobtype);
        Regshift=findViewById(R.id.shifts);
        Regreqqua=findViewById(R.id.reqqua);
        postjob=findViewById(R.id.postjob);
        postnewjob=findViewById(R.id.postnewjob);

        postnewjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(postJobActivity.this, postedjob_screen.class);
               startActivity(intent);
            }
        }




        );

        postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                companyName = Regjobtitle.getText().toString();
                jobDescription = Regjobdis.getText().toString();
                CompanyLocation = Reglocation.getText().toString();
                payRate = Regpayrate.getText().toString();
                jobType = Regjobtype.getText().toString();
                shift = Regshift.getText().toString();
                reqqua = Regreqqua.getText().toString();

                if (TextUtils.isEmpty(companyName)) {
                    Regjobtitle.setError("Please enter Job Title");
                } else if (TextUtils.isEmpty(jobDescription)) {
                    Regjobdis.setError("Please enter Job Description");
                } else if (TextUtils.isEmpty(CompanyLocation)) {
                    Reglocation.setError("Please enter location");
                } else if (TextUtils.isEmpty(payRate)) {
                    Regpayrate.setError("Please enter payrate");
                }else if (TextUtils.isEmpty(jobType)) {
                    Regjobtype.setError("Please enter Job Type");
                }else if (TextUtils.isEmpty(shift)) {
                    Regshift.setError("Please enter Job pay rate");
                }else if (TextUtils.isEmpty(reqqua)) {
                    Regreqqua.setError("Please required qualifications");
                }
                else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(companyName,jobDescription,CompanyLocation,payRate,jobType,shift,reqqua);
                }


            }
        });
    }

    private void addDataToFirestore(String companyName, String jobDescription, String CompanyLocation, String payRate, String jobType, String shift, String reqqua)
    {

        CollectionReference dbpostjobdb = db.collection("PostJob");

        postjobhelper jobhelper = new postjobhelper(companyName,jobDescription,CompanyLocation,payRate,jobType,shift,reqqua);

        dbpostjobdb.add(jobhelper).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(postJobActivity.this, "your job is posted sucessfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(postJobActivity.this, "job posting is failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
