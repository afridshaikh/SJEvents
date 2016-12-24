package com.mishrafamily.sjbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginAs extends AppCompatActivity {

    Button mStudent,mTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);

        mStudent = (Button) findViewById(R.id.buttonLoginStudent);
        mTeacher = (Button) findViewById(R.id.buttonLoginTeacher);

        mStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginAs.this,Studentlogin.class));

            }
        });

        mTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginAs.this,LoginActivity.class));

            }
        });

    }
}
