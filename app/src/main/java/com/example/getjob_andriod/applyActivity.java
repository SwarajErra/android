package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class applyActivity extends AppCompatActivity {
    Button applyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_screen);
        applyJob();


    }

    public void applyJob() {
        final Context context = this;
        applyButton = (Button) findViewById(R.id.ApplyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"Job Applied Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, getJobActivity.class);
                startActivity(intent);
            }
        });
    }
}