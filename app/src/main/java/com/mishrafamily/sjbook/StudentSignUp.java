package com.mishrafamily.sjbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentSignUp extends AppCompatActivity {

    EditText mUsername, mPassword;

    Button mSignUp;
    ProgressDialog mProg;

    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        mUsername = (EditText) findViewById(R.id.editTextUsername);
        mPassword = (EditText) findViewById(R.id.editTextPassword);

        mSignUp = (Button) findViewById(R.id.buttonSignUp);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        mProg = new ProgressDialog(StudentSignUp.this);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProg.setMessage("Signing Up");
                mProg.show();
                if (TextUtils.isEmpty(mUsername.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())) {

                    Toast.makeText(StudentSignUp.this, "Fields Empty!", Toast.LENGTH_SHORT).show();

                } else {

                    mDatabaseRef.child("students").child(mUsername.getText().toString()).setValue(mPassword.getText().toString());
                    mProg.dismiss();
                    Toast.makeText(StudentSignUp.this,"Log in now",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentSignUp.this,Studentlogin.class));
                }


            }
        });

    }
}
