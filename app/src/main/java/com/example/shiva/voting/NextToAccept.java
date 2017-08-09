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

public class NextToAccept extends AppCompatActivity {
EditText otp2;
    String phoneToDatabase,uID;
    String otpotp;
    String checkOtpValue;
    Button sub;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_to_accept);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        SharedPreferences shared = getSharedPreferences("fileName", Context.MODE_PRIVATE);
        SharedPreferences.Editor edio = shared.edit();

        otp2 = (EditText)findViewById(R.id.otp2);
        sub = (Button)findViewById(R.id.subotp2);
        uID = shared.getString("userId",null);
        phoneToDatabase = shared.getString("phoneToDatabase",null);



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp2.getText().toString().equals("")){
                    Toast.makeText(NextToAccept.this, "Enter the OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                  otpotp = otp2.getText().toString();
                    mReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("piv").child("otp_value").child(uID).exists()){
                                checkOtpValue=dataSnapshot.child("piv").child("otp_value").child(uID).getValue().toString();
                                Toast.makeText(NextToAccept.this, ""+checkOtpValue, Toast.LENGTH_SHORT).show();
                                if(otpotp.equals(checkOtpValue)) {
                                    mReference.child("piv").child("otp_entry").child(uID).setValue(otpotp);
                                    Toast.makeText(NextToAccept.this, "added", Toast.LENGTH_SHORT).show();
                                    otp2.setText("");
                                    Intent toLogged = new Intent(NextToAccept.this,Logged.class);
                                    startActivity(toLogged);
                                }
                                else{
                                    Toast.makeText(NextToAccept.this, "Retry", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(checkOtpValue.equals("")){
                                Toast.makeText(NextToAccept.this, "not yet generated da", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(NextToAccept.this, "Wrong", Toast.LENGTH_SHORT).show();
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
