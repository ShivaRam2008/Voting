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

public class SignUp extends AppCompatActivity {
    EditText phoneNumber;
    Button signUp;
    int registered = 0;
     String phonetoDatabase;
    FirebaseDatabase nDatabase;
    SharedPreferences shared ;
    String ph;

   //  SharedPreferences.Editor edio = getSharedPreferences("fileName",MODE_PRIVATE).edit();
   String nametoDatabase;
    //EditText aadharNumber;
    DatabaseReference nReference;
    String aadharcard;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent prev = new Intent(SignUp.this,Login.class);
        startActivity(prev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        shared= getSharedPreferences("fileName", Context.MODE_PRIVATE);


       // nameEditInSignUp = (EditText)findViewById(R.id.nameinsignup);
        phoneNumber = (EditText)findViewById(R.id.phonennumberinsignup);
        signUp = (Button)findViewById(R.id.signupubutton);
       // aadharNumber = (EditText)findViewById(R.id.aadhar);

        nDatabase = FirebaseDatabase.getInstance();
        nReference = nDatabase.getReference();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(phoneNumber.getText().toString().equals(""))   {
                    Toast.makeText(SignUp.this, "Information not sufficient", Toast.LENGTH_SHORT).show();

                }
                else    {
                    Intent toS = new Intent(SignUp.this,Login.class);
                    phonetoDatabase = phoneNumber.getText().toString();


                    nReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("registered_mobiles").child(phonetoDatabase).exists()) {
                                String message = dataSnapshot.child("registered_mobiles").child(phonetoDatabase).getValue().toString();
                                Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                                 Intent j = new Intent(SignUp.this,SignUp.class);
                                registered = 1;
                                Toast.makeText(SignUp.this, "registered" + registered, Toast.LENGTH_SHORT).show();
                                phoneNumber.setText("");
                                startActivity(j);
                            }
                            else {
                                Model model = new Model(ph,nametoDatabase,phonetoDatabase,aadharcard);
                                PhioneModel phModel = new PhioneModel(phonetoDatabase,ph);

                                Intent toOtp = new Intent(SignUp.this,Otp_verification.class);
                                nReference.child("sign_up").child(phonetoDatabase).setValue("None");
                                Toast.makeText(SignUp.this, "User added", Toast.LENGTH_SHORT).show();
                                startActivity(toOtp);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                /*   nReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int  i= (int) dataSnapshot.child("registered_mobiles").getChildrenCount();
                            Toast.makeText(SignUp.this, "childcount"+i, Toast.LENGTH_SHORT).show();
                           // for(int n=0;n<i;n++) {
                                if(dataSnapshot.child("registered_mobiles").child(phonetoDatabase).exists())   {
                                    String message = dataSnapshot.child("registered_mobiles").child(phonetoDatabase).getValue().toString();
                                    Toast.makeText(SignUp.this,message, Toast.LENGTH_SHORT).show();
                                   // Intent j = new Intent(SignUp.this,SignUp.class);
                                    registered = 1;
                                    Toast.makeText(SignUp.this, "registered"+registered, Toast.LENGTH_SHORT).show();
                                    phoneNumber.setText("");

                            }
                            else {
                                Model model = new Model(ph,nametoDatabase,phonetoDatabase,aadharcard);
                                PhioneModel phModel = new PhioneModel(phonetoDatabase,ph);

                                Intent toOtp = new Intent(SignUp.this,Otp_verification.class);
                                nReference.child("sign_up").child(phonetoDatabase).setValue("None");
                                Toast.makeText(SignUp.this, "User added", Toast.LENGTH_SHORT).show();
                                startActivity(toOtp);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                    SharedPreferences.Editor edio = shared.edit();
                    // nametoDatabase = nameEditInSignUp.getText().toString();
                    edio.putString("nameToDatabase",nametoDatabase);
                    edio.putString("phoneToDatabase",phonetoDatabase);
                    edio.putString("aadharCard",aadharcard);
                    edio.commit();

                    phoneNumber.setText("");
                   // aadharcard = aadharNumber.getText().toString();
                    //String signUpID = nReference.push().getKey();
                    /*if(registered==1) {


                        startActivity(toS);
                    }
                    else {
                        Toast.makeText(SignUp.this, "reg"+registered, Toast.LENGTH_SHORT).show();
                        Intent toOtp = new Intent(SignUp.this,Otp_verification.class);
                        ph = nReference.push().getKey();
                        PhioneModel phonemodel = new PhioneModel(phonetoDatabase, phonetoDatabase);
                        nReference.child("sign_up").child(phonetoDatabase).setValue(phonetoDatabase);
                        startActivity(toOtp);
                    }*/
                  /*  if(registered != 1) {
                        nReference.child("sign_up").child(phonetoDatabase).setValue("None");
                    }*/
                    //Model model = new Model(signUpID,nametoDatabase,phonetoDatabase);
                    //nReference.child(phonetoDatabase).setValue(model);
                   // startActivity(toOtp);


                  //  nameEditInSignUp.setText("");

                   // aadharNumber.setText("");




                }
                /*if(registered == 1) {
                    Toast.makeText(SignUp.this, "number already registerd", Toast.LENGTH_SHORT).show();
                   // Intent j = new Intent(SignUp.this,SignUp.class);
                   // startActivity(j);
                }*/



            }
        });

    }
}
