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

public class signUpActivity extends AppCompatActivity {
    EditText inputEmail,inputpassword,inputconpassword;
    Button signUp,AlreadyAc;
    String emailPattern= "[a-zA-Z0-9._%+-]+@[A-Za-z.-]+\\.+[a-z]+";
     ProgressDialog progressDialog;

     private FirebaseAuth mAuth;
     FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUp = (Button) findViewById(R.id.signUp);
        inputEmail=findViewById(R.id.inputEmail);
        inputpassword=findViewById(R.id.inputpassword);
        inputconpassword=findViewById(R.id.inputconpassword);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlreadyAc();
                PerForAuth();

            }
        });
    }

    private void AlreadyAc() {
            final Context context = this;
            AlreadyAc = (Button) findViewById(R.id.AlreadyAc);
            AlreadyAc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, loginActivity.class);
                    startActivity(intent);
                }
            });
        }

    private void PerForAuth() {
        String email=inputEmail.getText().toString();
        String password=inputpassword.getText().toString();
        String conpassword=inputconpassword.getText().toString();

        if (!email.matches(emailPattern)){
            inputEmail.setError("enter valid email address");
            inputEmail.requestFocus();

        }else if (password.isEmpty()||password.length()<8)
        {
            inputpassword.setError("enter proper password");
        }else if (!password.equals(conpassword)){
            inputconpassword.setError("password not matched");
        }else{
            progressDialog.setMessage("Please Wait While Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(signUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(signUpActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(signUpActivity.this,Selection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}