package com.mishrafamily.sjbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherSignUp extends AppCompatActivity {

    private EditText mEmail,mPassword,mEmpId;
    private Button mSignUp;
    private String Email,Password,EmpId;
    private Firebase mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ProgressDialog mProg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);

        mEmail = (EditText) findViewById(R.id.editTextTeachEmail);
        mPassword = (EditText) findViewById(R.id.editTextTeachPassword);
        mEmpId = (EditText) findViewById(R.id.editTextEmpId);
        Email = mEmail.getText().toString();
        Password = mPassword.getText().toString();
        EmpId = mEmpId.getText().toString();

        mProg = new ProgressDialog(TeacherSignUp.this);

        mSignUp = (Button) findViewById(R.id.buttonSignUp);

        mRef = new Firebase("https://sjbook-b02b6.firebaseio.com/teachers");
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    startActivity(new Intent(TeacherSignUp.this,NewEvent.class));


                }

            }
        };

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProg.setMessage("Signing Up");
                mProg.show();

                Email = mEmail.getText().toString();
                Password = mPassword.getText().toString();
                EmpId = mEmpId.getText().toString();

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(EmpId)) {
                    mProg.dismiss();
                    Toast.makeText(TeacherSignUp.this, "Fields are Empty!", Toast.LENGTH_SHORT).show();

                } else {



                    mAuth.createUserWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener(TeacherSignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {
                                        mProg.dismiss();
                                        mRef.child(EmpId).setValue("1");
                                        Toast.makeText(TeacherSignUp.this, "Signed Up!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(TeacherSignUp.this,NewEvent.class));
                                    } else {
                                        mProg.dismiss();
                                        Toast.makeText(TeacherSignUp.this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);

    }
}
