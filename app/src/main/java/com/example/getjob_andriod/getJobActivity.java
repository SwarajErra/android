package com.example.getjob_andriod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class JobData{
    String jobType;
    String companyLocation;
    String companyName;
    String jobDescription;
    String payRate;
    boolean pressAttention;
    boolean reportJob;

    public JobData(String jobType,String companyLocation,
            String companyName,
            String jobDescription,
            String payRate
                   ) {
        this.jobType = jobType;
        this.companyLocation =  companyLocation;
        this.companyName =  companyName;
        this.jobDescription =  jobDescription;
        this.payRate =  payRate;
//        this.pressAttention =  pressAttention;
//        this.reportJob =  reportJob;

    }
}

public class getJobActivity extends AppCompatActivity {

    Button applyJobButton;
    ArrayList<JobData> arrayJobs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        applyJob();
        getPostedJobsdata();
        searchButton();
    }

    public List<JobData> findUsingLoop(String search, List<JobData> list) {
        List<JobData> matches = new ArrayList<JobData>();
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).companyName.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)) ||
                    list.get(i).companyLocation.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)) ||
                    list.get(i).jobDescription.contains(search.toLowerCase(Locale.ROOT)) ||
                    list.get(i).jobType.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)) ||
                    list.get(i).payRate.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)) ) {

                matches.add(new JobData(
                        list.get(i).jobType,
                        list.get(i).companyLocation,
                        list.get(i).companyName,
                        list.get(i).jobDescription,
                        list.get(i).payRate
//                        list.get(i).pressAttention,
//                        list.get(i).reportJob
                ));
            }
        }
        return matches;
    }

    public void applyJob() {


    }

    void buildCard(ArrayList<JobData> newArray){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.homeCardLayout);

        LayoutInflater li =  (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < newArray.size();  i++){
            View tempView = li.inflate(R.layout.job_card, null);

            TextView companyName = (TextView) tempView.findViewById(R.id.companyName);
            TextView companyLocation = (TextView) tempView.findViewById(R.id.companyLocation);
            TextView jobDescription = (TextView) tempView.findViewById(R.id.jobDescription);
            TextView jobType = (TextView) tempView.findViewById(R.id.jobType);
            TextView payRate = (TextView) tempView.findViewById(R.id.payRate);


            companyName.setText(Html.fromHtml("<b>" + "Company Name : " +  "</b> " + newArray.get(i).companyName));
            companyLocation.setText(Html.fromHtml("<b>" + "Company Location : " +  "</b> " + newArray.get(i).companyLocation));
            jobDescription.setText(Html.fromHtml("<b>" + "Job Description : " +  "</b> " + newArray.get(i).jobDescription));
            jobType.setText(Html.fromHtml("<b>" + "Job Type : " +  "</b> " + newArray.get(i).jobType));
            payRate.setText(Html.fromHtml("<b>" + "Pay Rate : " +  "</b> " + newArray.get(i).payRate));

            mainLayout.addView(tempView);
        }

    }


    private void getPostedJobsdata() {

        FirebaseFirestore.getInstance().collection("PostJob").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot jobs = task.getResult();

                    for(int i=0; i<jobs.getDocuments().size(); i++) {
                        DocumentSnapshot doc = jobs.getDocuments().get(i);
                        System.out.println(doc.getString("companyLocation"));
                        arrayJobs.add(new JobData(doc.getString("jobType"),
                                doc.getString("companyLocation"),
                                doc.getString("companyName"),
                                doc.getString("jobDescription"),
                                doc.getString("payRate")

                        ));
                    }

                   buildCard(arrayJobs);
                    applyJobButton = (Button) findViewById(R.id.ApplyButton);
                    applyJobButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if(applyJobButton.getText() == "APPLIED"){
                                applyJobButton.setBackgroundColor(getResources().getColor(R.color.blue));
                                applyJobButton.setText("Apply Now");
                            }
                            else{
                                applyJobButton.setBackgroundColor(getResources().getColor(R.color.green));
                                applyJobButton.setText("APPLIED");
                            }

                        }
                    });

                } else {
                    Toast.makeText(getJobActivity.this, "Error getting documents:", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void searchButton() {
        final Context context = this;
        applyJobButton = (Button) findViewById(R.id.searchButton);
        EditText view = (EditText) findViewById(R.id.filledTextField);
        applyJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.homeCardLayout);
                mainLayout.removeAllViews();

                if (!view.getText().toString().matches("")) {
                    ArrayList<JobData> newArray = (ArrayList<JobData>) findUsingLoop(String.valueOf(view.getText()), arrayJobs);

                    buildCard(newArray);
                }else{
                    buildCard(arrayJobs);
                }

            }
        });
    }



}