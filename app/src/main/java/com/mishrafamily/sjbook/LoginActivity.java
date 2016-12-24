package com.mishrafamily.sjbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;

    
    FirebaseAuth.AuthStateListener mAuthstateListener;

    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mLogin;
    private Button mSignUp;

    private ProgressDialog mProg;


    private  FirebaseAuth mAuth;

    private  FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = (EditText) findViewById(R.id.editTextTeachEmail);
        mPasswordField=(EditText) findViewById(R.id.editTextPassword);

        mProg = new ProgressDialog(this);

        mLogin=(Button)findViewById(R.id.buttonLogin);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null){


                    Intent i= new Intent(LoginActivity.this,NewEvent.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mProg.dismiss();
                    startActivity(i);


                }

            }
        };



        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProg.setMessage("Signing In");
                mProg.show();
                startSignIn();

            }
        });





    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

    }

    private void startSignIn() {



        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();

        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        mProg.dismiss();
                        Toast.makeText(LoginActivity.this, "SignIn problem", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
}
