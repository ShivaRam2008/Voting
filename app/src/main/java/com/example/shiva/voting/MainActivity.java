package com.example.shiva.voting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText nameEdittext;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    Button loginButton;
    AppCompatTextView signupTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("https://e-voting-74058.firebaseio.com/");
        nameEdittext = (EditText)findViewById(R.id.name);
        loginButton = (Button)findViewById(R.id.button);
        signupTextView = (AppCompatTextView)findViewById(R.id.signuphere);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEdittext.getText().toString().equals(""))    {
                    Toast.makeText(MainActivity.this, "Enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else    {
                    String n = nameEdittext.getText().toString();
                    String logInID = mReference.push().getKey();
                    Model2 model2 = new Model2(logInID,n);
                    mReference.child(logInID).setValue(model2);
                    nameEdittext.setText("");
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignup = new Intent(MainActivity.this,SignUp.class);
                startActivity(toSignup);
            }
        });
    }
}
