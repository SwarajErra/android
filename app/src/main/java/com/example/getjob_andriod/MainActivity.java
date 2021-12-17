package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends AppCompatActivity {
    Animation topanim,bottomanim;
    ImageView image;
    TextView logo;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }
    public void addListenerOnButton() {

        final Context context = this;

//        button = (Button) findViewById(R.id.button1);


        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, loginActivity.class);
                startActivity(intent);

            }

        });

    }

}