package com.example.shiva.voting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Otp_verification extends AppCompatActivity {
    EditText otp;
    Button submit;
    SharedPreferences shared;
    String checkOtp;
    int count;
    TextView timet;
    CountDownTimer timeleftcount;

    String otpToDatabase;
    TextView tt;
    //SharedPreferences.Editor edio = getSharedPreferences("fileName",MODE_PRIVATE).edit();
    String phoneToDatabase, nameToDatabase, aadharcard;
    FirebaseDatabase otpDatabase;
    String check;
    DatabaseReference otpReference;

    @Override
    protected void onStart() {
        super.onStart();
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timet.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timet.setText("done!");
                final AlertDialog.Builder builder = new AlertDialog.Builder(Otp_verification.this,android.R.style.Theme_Material_Dialog);

                builder.setTitle("OTP VERIFICATION").setMessage("Extend time?").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent toOp = new Intent(Otp_verification.this,Otp_verification.class);
                        dialog.cancel();
                        startActivity(toOp);

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent tosign = new Intent(Otp_verification.this,SignUp.class);
                        startActivity(tosign);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        }.start();
    }

    //Model2 model2 = new Model2();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent pr = new Intent(Otp_verification.this,Login.class);
        startActivity(pr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);



        shared = getSharedPreferences("fileName",Context.MODE_PRIVATE);
        timet = (TextView) findViewById(R.id.timeLeft);
        // final Handler handler = new Handler();

        tt = (TextView) findViewById(R.id.aaa);
        phoneToDatabase = shared.getString("phoneToDatabase", null);
        nameToDatabase = shared.getString("nameToDatabase", null);
        aadharcard = shared.getString("aadharCard",null);


        otpDatabase = FirebaseDatabase.getInstance();
        otpReference = otpDatabase.getReference();
        submit = (Button) findViewById(R.id.buttonSubmit);
        otp = (EditText) findViewById(R.id.otp);
        //////////
        otpReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("otp_values").child(phoneToDatabase).exists()) {
                    checkOtp = dataSnapshot.child("otp_values").child(phoneToDatabase).getValue().toString();
                    Toast.makeText(Otp_verification.this, "" + checkOtp, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Otp_verification.this, "otp not yet generated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        otpReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("otp_values").child(phoneToDatabase).exists()) {
                    checkOtp = dataSnapshot.child("otp_values").child(phoneToDatabase).getValue().toString();
                    Toast.makeText(Otp_verification.this, "" + checkOtp, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Otp_verification.this, "otp not yet generated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      //////////

         /* otpReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Toast.makeText(Otp_verification.this, ""+sss, Toast.LENGTH_SHORT).show();
                checkOtp = dataSnapshot.child("otp_values").child(phoneToDatabase).getValue().toString();
                Toast.makeText(Otp_verification.this, "cehce" +checkOtp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.getText().toString().equals("")) {
                    Toast.makeText(Otp_verification.this, "Enter the OTP", Toast.LENGTH_SHORT).show();

                    //Remove this afterwards
                    //Intent toNext = new Intent(Otp_verification.this, NextToOtp.class);

                   // startActivity(toNext);

                } else {

                    otpToDatabase = otp.getText().toString();
                    Model2 model2 = new Model2(otpToDatabase, phoneToDatabase);

                    otpReference.child("otp_entry").child(phoneToDatabase).setValue(otpToDatabase);
                    otp.setText("");
                    if (checkOtp.equals(otpToDatabase)) {
                        Intent toNext = new Intent(Otp_verification.this, NextToOtp.class);

                        startActivity(toNext);
                    } else {
                        Toast.makeText(Otp_verification.this, "oops! Wrong OTP", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



}
