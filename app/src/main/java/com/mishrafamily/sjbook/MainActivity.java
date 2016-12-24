package com.mishrafamily.sjbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonTeacherSignup;
    Button buttonStudentSignUp;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTeacherSignup = (Button)findViewById(R.id.buttonTeacherSignup);
        buttonStudentSignUp=(Button)findViewById(R.id.buttonStudentSignUp);
        buttonLogin= (Button) findViewById(R.id.buttonLogin);

        buttonTeacherSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(MainActivity.this,TeacherSignUp.class));


            }
        });

        buttonStudentSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               startActivity(new Intent(MainActivity.this,StudentSignUp.class));



            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(MainActivity.this,LoginAs.class));
                Intent intent = new Intent(MainActivity.this,LoginAs.class);
                startActivity(intent);
            }
        });
    }


}
