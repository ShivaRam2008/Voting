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

public class Passwrod extends AppCompatActivity {
EditText passText;
    String piv;
    Button passButton;
    String thisisPassword;
    SharedPreferences shared;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mReference = mDatabase.getReference();
    String passwordvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwrod);
        shared = getSharedPreferences("fileName", Context.MODE_PRIVATE);

        SharedPreferences.Editor edio = shared.edit();
        passText =(EditText)findViewById(R.id.password);
        passButton = (Button)findViewById(R.id.idk);
        piv = shared.getString("piv",null);
        Toast.makeText(this, ""+piv, Toast.LENGTH_SHORT).show();

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                passwordvalue=dataSnapshot.child("piv").child(piv).child("password").getValue().toString();
                Toast.makeText(Passwrod.this, "pass= "+passwordvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passText.getText().toString().equals(""))    {
                    Toast.makeText(Passwrod.this, "Enter Password ", Toast.LENGTH_SHORT).show();
                }
                else    {
                    thisisPassword = passText.getText().toString();



                  /*  mReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            passwordvalue = dataSnapshot.child("piv").child(piv).child("password").getValue().toString();
                            Toast.makeText(Passwrod.this, "passwrodvalue= "+passwordvalue, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                    if(thisisPassword.equals(passwordvalue))    {
                        Toast.makeText(Passwrod.this, "okay", Toast.LENGTH_SHORT).show();
                        Intent toLogged = new Intent(Passwrod.this,Logged.class);
                        startActivity(toLogged);
                    }
                    else{
                        Toast.makeText(Passwrod.this, "Incorrect PAssword", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
