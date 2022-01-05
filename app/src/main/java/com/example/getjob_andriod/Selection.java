package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Selection extends AppCompatActivity {

    Button getJobButton,postJobButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_screen);
        getJob();
        postJob();
    }

    public void getJob() {
        final Context context = this;
        getJobButton = (Button) findViewById(R.id.getJob);
        getJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, getJobActivity.class);
                startActivity(intent);
            }
        });
    }

    public void postJob() {
        final Context context = this;
        postJobButton = (Button) findViewById(R.id.postJob);
        postJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, postJobActivity.class);
                startActivity(intent);
            }
        });
    }
}