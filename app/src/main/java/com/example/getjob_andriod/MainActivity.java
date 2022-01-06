package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 5000;

    Animation topanim,bottomanim;
    ImageView image;
    TextView logo;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

      //animations
      topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
      bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

      //hook
      image = findViewById(R.id.imageView);
      logo = findViewById(R.id.textView);

      image.setAnimation(topanim);
      logo.setAnimation(bottomanim);


      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent = new Intent(MainActivity.this,loginActivity.class);
              startActivity(intent);
              finish();
          }
      },SPLASH_TIME);

    }}

