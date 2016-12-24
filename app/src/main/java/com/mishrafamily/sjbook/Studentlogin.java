package com.mishrafamily.sjbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Studentlogin extends AppCompatActivity {

    EditText mUsername,mPassword;
    Button mLogin;
    String Username,Password;

    Firebase mRef;


    ProgressDialog mProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);


        mUsername = (EditText) findViewById(R.id.editTextUsername);
        mPassword = (EditText) findViewById(R.id.editTextPassword);

        mLogin = (Button) findViewById(R.id.buttonLogin);

        mProg = new ProgressDialog(Studentlogin.this);


        mRef = new Firebase("https://sjbook-b02b6.firebaseio.com/students");







        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Username = mUsername.getText().toString();
                Password = mPassword.getText().toString();





                if (TextUtils.isEmpty(mUsername.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())) {
                    Toast.makeText(Studentlogin.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProg.setMessage("Signing In");
                    mProg.show();

                   mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {

                           if (dataSnapshot.hasChild(Username)) {

                               String firepass = dataSnapshot.child(Username).getValue().toString();




                               if (firepass.equals(Password)) {

                                    mProg.dismiss();
                                   startActivity(new Intent(Studentlogin.this, ShowEvent.class));

                               } else {
                                   mProg.dismiss();
                                   Toast.makeText(Studentlogin.this,"Wrong Password!",Toast.LENGTH_SHORT).show();
                               }


                           } else {
                               mProg.dismiss();
                               Toast.makeText(Studentlogin.this,"Wrong Username!",Toast.LENGTH_SHORT).show();
                           }

                       }

                       @Override
                       public void onCancelled(FirebaseError firebaseError) {

                           mProg.dismiss();
                           Toast.makeText(Studentlogin.this,"Error while Signing in!",Toast.LENGTH_SHORT).show();
                       }
                   });


                }

            }
        });

    }
}
