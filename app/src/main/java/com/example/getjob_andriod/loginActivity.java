package com.example.getjob_andriod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    EditText inputEmail,inputpassword;
    Button signIn,newUser;
    String emailPattern= "[a-zA-Z0-9._%+-]+@[A-Za-z.-]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inputEmail=findViewById(R.id.inputLogEmail);
        inputpassword=findViewById(R.id.inputLogpassword);
        signIn=findViewById(R.id.signIn);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
                PerLogAuth();

            }
        });
    }

    private void newUser() {
            final Context context = this;
            newUser = (Button) findViewById(R.id.newUser);
            newUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, signUpActivity.class);
                    startActivity(intent);
                }
            });
        }



    private void PerLogAuth() {
        String email=inputEmail.getText().toString();
        String password=inputpassword.getText().toString();

        if (!email.matches(emailPattern)){
            inputEmail.setError("enter valid email address");
            inputEmail.requestFocus();

        }else if (password.isEmpty()||password.length()<8)
        {
            inputpassword.setError("enter proper password");
        }
        else{
        progressDialog.setMessage("Please Wait While Login...");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  progressDialog.dismiss();
                  sendUserToNextActivity();
                  Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
              }
              else {
                  progressDialog.dismiss();
                  Toast.makeText(loginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
              }
            }
        });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent= new Intent(loginActivity.this,Selection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
