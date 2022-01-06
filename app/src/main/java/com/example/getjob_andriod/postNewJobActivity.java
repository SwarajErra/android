package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class postNewJobActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postjob_screen);
        postNewJob();

    }

    public void postNewJob() {
        final Context context = this;
        button = (Button) findViewById(R.id.postNewJobButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, postJobActivity.class);
                startActivity(intent);
            }
        });
    }
}