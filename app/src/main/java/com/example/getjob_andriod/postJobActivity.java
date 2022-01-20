package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class postJobActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createjob_screen);
        postJob();

    }

    public void postJob() {
        final Context context = this;
        button = (Button) findViewById(R.id.signIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, postNewJobActivity.class);
                startActivity(intent);
            }
        });
    }
}