package com.example.getjob_andriod;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginActivity extends AppCompatActivity {
    Button button,newUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);
        signIn();
        newUser();
    }

    public void signIn() {
        final Context context = this;
        button = (Button) findViewById(R.id.postJobButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Selection.class);
                startActivity(intent);
            }
        });
    }

    public void newUser() {
        final Context context = this;
        newUserButton = (Button) findViewById(R.id.newUser);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, signUpActivity.class);
                startActivity(intent);
            }
        });
    }
}