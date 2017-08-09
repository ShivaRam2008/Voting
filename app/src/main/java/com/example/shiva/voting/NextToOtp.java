package com.example.shiva.voting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NextToOtp extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference otpReference = mDatabase.getReference();
    String checkOtp;
    EditText fullName,aadh;
    String phoneToDatabase;
    Button goButton;
    String gender;
    RadioGroup group;
    String nameToDatabase,addressToDatabase,aadharToDatabase,dobToDatabase;
    RadioButton male,female;
    EditText dob,address;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent pre = new Intent(NextToOtp.this,SignUp.class);
        startActivity(pre);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_to_otp);

        final TextView t = (TextView)findViewById(R.id.te);
        SharedPreferences shared = getSharedPreferences("fileName", Context.MODE_PRIVATE);
        fullName = (EditText)findViewById(R.id.fullName);
        aadh = (EditText)findViewById(R.id.aadharN);
        goButton = (Button)findViewById(R.id.go);
        SharedPreferences.Editor edio = shared.edit();
        address = (EditText)findViewById(R.id.address);
        dob = (EditText)findViewById(R.id.dob);
        male = (RadioButton)findViewById(R.id.r1);
        female = (RadioButton) findViewById(R.id.r2);
        group = (RadioGroup)findViewById(R.id.gg);
        phoneToDatabase = shared.getString("phoneToDatabase",null);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gender = "male";
                    }
                });
                female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gender = "female";
                    }
                });
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullName.getText().toString().equals("")||aadh.getText().toString().equals("")||dob.getText().toString().equals("")||address.getText().toString().equals("")||gender.equals("")){
                    Toast.makeText(NextToOtp.this, "Information Insufficient", Toast.LENGTH_SHORT).show();
                }
                else    {
                    nameToDatabase = fullName.getText().toString();
                    aadharToDatabase = aadh.getText().toString();
                    dobToDatabase = dob.getText().toString();
                    addressToDatabase = address.getText().toString();
                    DataEntry dataEntry = new DataEntry(aadharToDatabase,addressToDatabase,dobToDatabase,gender,nameToDatabase);
                    otpReference.child("data_entry").child(phoneToDatabase).setValue(dataEntry);
                    fullName.setText("");
                    aadh.setText("");
                    dob.setText("");
                    address.setText("");
                    male.setChecked(false);
                    female.setChecked(false);
                    Intent toMain = new Intent(NextToOtp.this,Login.class);
                    startActivity(toMain);

                }
            }
        });




    }
}
