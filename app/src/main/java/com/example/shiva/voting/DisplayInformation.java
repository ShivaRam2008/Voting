package com.example.shiva.voting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DisplayInformation extends AppCompatActivity {

Button bAccept,bReject;
FirebaseDatabase mDatabase;
    DatabaseReference mReference;
TextView fName,fAadhar,fGender,fAddress,fDOB;
String piv;
    String uID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

        SharedPreferences shared = getSharedPreferences("fileName",Context.MODE_PRIVATE);
        SharedPreferences.Editor edio = shared.edit();

        bAccept =(Button)findViewById(R.id.accept);
        bReject = (Button)findViewById(R.id.reject);
        fName = (TextView)findViewById(R.id.namedisplay);
        fAadhar = (TextView)findViewById(R.id.aadhardisplaying);
        fGender = (TextView)findViewById(R.id.gen);
        fAddress = (TextView)findViewById(R.id.addre);
        fDOB = (TextView)findViewById(R.id.dob);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        uID = shared.getString("userId",null);
       piv = shared.getString("piv",null);

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(DisplayInformation.this, dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("aadhar_no").getValue().toString(), Toast.LENGTH_SHORT).show();
                fAadhar.setText(dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("aadhar_no").getValue().toString());
                fDOB.setText(dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("dob").getValue().toString());
                fName.setText(dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("name").getValue().toString());
                fGender.setText(dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("gender").getValue().toString());
                fAddress.setText(dataSnapshot.child("piv").child(piv).child("data_verification").child(uID).child("address").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxt = new Intent(DisplayInformation.this,NextToAccept.class);
                mReference.child("piv").child(piv).child("request").child(uID).setValue("accepted");
                startActivity(nxt);
            }
        });
        bReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference.child("piv").child(piv).child("request").child(uID).setValue("rejected");
                Intent toLogin = new Intent(DisplayInformation.this,Login.class);
                startActivity(toLogin);
            }
        });

    }
}
