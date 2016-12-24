package com.mishrafamily.sjbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class NewEvent extends AppCompatActivity {
    Firebase mref;
    EditText editTextEventDescription,mEmpId;
    String dept;
    Button buttonSubmit;
    Button buttonReset;
    Button buttonSignOut;
    String event,empid;
    ProgressDialog mProg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Spinner spinner_dept = (Spinner) findViewById(R.id.spinnerTeacherDept);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Semester,android.R.layout.simple_list_item_1);
        spinner_dept.setAdapter(adapter);

        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long
                    id) {
                Object department = parent.getItemAtPosition(position);
                dept=department.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextEventDescription=(EditText)findViewById(R.id.editTextEventDescription);
        mEmpId = (EditText )findViewById(R.id.editTextEmpId);
        mProg = new ProgressDialog(NewEvent.this);

        buttonSubmit=(Button)findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProg.setMessage("Creating Event!");
                mProg.show();


                event = editTextEventDescription.getText().toString();
                empid = mEmpId.getText().toString();

                if (TextUtils.isEmpty(event) || TextUtils.isEmpty(empid)) {

                    mProg.dismiss();
                    Toast.makeText(NewEvent.this,"Fields are empty!",Toast.LENGTH_SHORT).show();

                } else {
                    //mref = new Firebase(url);
                    mref = new Firebase("https://sjbook-b02b6.firebaseio.com");

                    mref.child("teachers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.hasChild(empid)) {

                                String eventdes1 = editTextEventDescription.getText().toString();
                                String dept1 = dept;
                                // String key = mref.child(dept).push().getKey();
                                mref.child(dept1).push().setValue(eventdes1);
                                mProg.dismiss();
                                Toast.makeText(getBaseContext(), "Event Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NewEvent.this,ShowEvent.class));

                            } else {
                                mProg.dismiss();
                                Toast.makeText(getBaseContext(), "No such Emp Id", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                            mProg.dismiss();
                            Toast.makeText(NewEvent.this,"Failed to create Event",Toast.LENGTH_SHORT).show();

                        }
                    });



                }
            }
        });


        buttonSignOut = (Button)findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(NewEvent.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    private void createMenu(Menu menu) {
        MenuItem mnu=menu.add(0,1,1,"View Events");
        {
            mnu.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
        MenuItem mnu1=menu.add(0,2,2,"About us");
        {
            mnu.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        selectItem(item);
        return true;
    }

    private void selectItem(MenuItem item) {
        int itemId=item.getItemId();
        switch (itemId){
            case 1: startActivity(new Intent(NewEvent.this,ShowEvent.class)); break;
            case 2:Intent intent = new Intent("com.mishrafamily.AboutUs");
                startActivity(intent);
                break;
        }
    }
}

