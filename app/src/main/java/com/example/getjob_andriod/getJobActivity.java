package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class getJobActivity extends AppCompatActivity {

    Button applyJobButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        applyJob();
    }

    public void applyJob() {
        final Context context = this;
        applyJobButton = (Button) findViewById(R.id.ApplyButton);
        applyJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, applyActivity.class);
                startActivity(intent);
            }
        });
    }

}