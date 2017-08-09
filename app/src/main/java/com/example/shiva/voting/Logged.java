package com.example.shiva.voting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Logged extends AppCompatActivity {
EditText userid,aadharcard;
    Button userAdd;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    String pivValue;

    String piv,uID,aadhC;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toPr = new Intent(Logged.this,Login.class);
        startActivity(toPr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        SharedPreferences shared= getSharedPreferences("fileName", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edio = shared.edit();
        piv = shared.getString("piv",null);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        userAdd = (Button)findViewById(R.id.adduser);
        userid = (EditText)findViewById(R.id.userID);
        aadharcard = (EditText)findViewById(R.id.aad);

        userAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid.getText().toString().equals("") ||aadharcard.getText().toString().equals("")){
                    Toast.makeText(Logged.this, "Information insufficient", Toast.LENGTH_SHORT).show();
                }

                else{
                    uID=userid.getText().toString();
                    aadhC=aadharcard.getText().toString();
                    final Intent toInfo = new Intent(Logged.this,DisplayInformation.class);
                    mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("piv").child(piv).child("valid_id").child(uID).exists()){

                                if(!dataSnapshot.child("piv").child("registered_aadhar").child(aadhC).exists()) {
                                    edio.putString("userId",uID);
                                    edio.commit();
                                    //String toast = dataSnapshot.child("piv").child("registered_aadhar").child(aadhC).getValue().toString();
                                    //Toast.makeText(Logged.this, ""+toast, Toast.LENGTH_SHORT).show();
                                    startActivity(toInfo);
                                }
                                else
                                {

                                    Toast.makeText(Logged.this, dataSnapshot.child("registered_aadhar").child(aadhC).getValue().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Logged.this, "user ID not valid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
