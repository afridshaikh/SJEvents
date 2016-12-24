package com.mishrafamily.sjbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class prac extends AppCompatActivity {

    Firebase mref,mref1;
   EditText editTexpract;
    Button buttonfirebase;
    String dept;
   // TextView textViewprac;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prac);
        Firebase.setAndroidContext(this);

        //code for spinner view for the Faculty input.---------------

        Spinner spinner_dept = (Spinner) findViewById(R.id.spinnerprac);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Semester, android.R.layout.simple_list_item_1);
        spinner_dept.setAdapter(adapter);

        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long
                    id) {
                Object department = parent.getItemAtPosition(position);
                dept = department.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//-----------------------------------------------------------------------------
        buttonfirebase = (Button) findViewById(R.id.buttonfirebase);
        editTexpract = (EditText) findViewById(R.id.editTextprac);
        //textViewprac =(TextView)findViewById(R.id.textViewPrac);
        listView =(ListView)findViewById(R.id.listViewprac);
        buttonfirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventdes1 = editTexpract.getText().toString();
                mref = new Firebase("https://sjbook-b02b6.firebaseio.com");
               // String key = mref.child(dept).push().getKey();
                mref.child(dept).push().setValue(eventdes1);
                Toast.makeText(getBaseContext(), "Values Persisted", Toast.LENGTH_SHORT).show();

            }
        });
    }


  @Override
    protected void onStart() {
        super.onStart();


     mref = new Firebase("https://sjbook-b02b6.firebaseio.com/Mechanical");
      //mref1= mref.child(dept);

       // mref.addValueEventListener(new ValueEventListener() {
         //   @Override
           // public void onDataChange(DataSnapshot dataSnapshot) {
             //  Map<String,String> map = dataSnapshot.getValue(Map.class);
                /*String Eventsdes = map.get("eventdes");
                textViewprac.setText(Eventsdes);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
*/

      FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
               this,
               String.class,
               android.R.layout.simple_list_item_1,
                mref
       ) {
           @Override
           protected void populateView(View view, String s, int i) {
               TextView textView =(TextView)view.findViewById(android.R.id.text1);
               textView.setText(s);

           }
       };
       listView.setAdapter(firebaseListAdapter);

    }
}